// JackLib 2023

package frc.robot.commands.autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.TrajectoryConstants;
import frc.robot.subsystems.drive.DriveSubsystemImpl;

import java.util.List;
import java.util.function.BooleanSupplier;

public class FollowRealTimeTrajectory extends CommandBase {

  // To get the x and y positions of where you want to go, you might have to use a subsystem
  private final DriveSubsystemImpl driveSubsystem;
  private final BooleanSupplier whileHeldButtonBooleanSupplier;

  public FollowRealTimeTrajectory(DriveSubsystemImpl driveSubsystem, BooleanSupplier whileHeldButtonBooleanSupplier) {
    this.driveSubsystem = driveSubsystem;
    this.whileHeldButtonBooleanSupplier = whileHeldButtonBooleanSupplier;
    addRequirements(this.driveSubsystem);
  }

  @Override
  public void initialize() {

    /* EDIT CODE BELOW HERE */

    // X and Y should be in meters
    // You might have to switch the x and y values and make them negative or positive
    // To get these 3 values, you should use the odometry or poseEstimator
    double startX = driveSubsystem.getPose().getX();
    double startY = driveSubsystem.getPose().getY();
    // The trajectory will not optimize the swerve modules without this being 0
    Rotation2d startRotation = new Rotation2d();
    Pose2d start = new Pose2d(startX, startY, startRotation);
    // These values should be field relative, if they are robot relative add them to the start values
    double endX = driveSubsystem.getPose().getX() + 2;
    double endY = driveSubsystem.getPose().getY();
    // Do not make this value based on the robot's current odometry, it might crash.
    Rotation2d endRotation = Rotation2d.fromDegrees(45);
    Pose2d end = new Pose2d(endX, endY, endRotation);

    // If you want any middle waypoints in the trajectory, add them here
    List<Translation2d> middleWaypoints = List.of(

      // Swerve is weird and messes up the spline sometimes if you don't have this
      new Translation2d(startX + ((endX - startX) * 0.95), startY + ((endY - startY) * 0.95))
    );

    // You should have constans or everything below here
    double driveMaxSpeedMetersPerSecond = TrajectoryConstants.autoMaxVelocity;
    double driveMaxAccelerationMetersPerSecond = TrajectoryConstants.autoMaxAcceleration;
    double turnMaxAngularSpeedRadiansPerSecond = TrajectoryConstants.maxAngularSpeedRadiansPerSecond;
    double turnMaxAngularSpeedRadiansPerSecondSquared = TrajectoryConstants.maxAngularSpeedRadiansPerSecondSquared;
    
    // Your probably only want to edit the P values
    PIDController xController = new PIDController(TrajectoryConstants.xControllerP, 0, 0);
    PIDController yController = new PIDController(TrajectoryConstants.yControllerP, 0, 0);
    // TODO: Tune P value
    ProfiledPIDController thetaController = new ProfiledPIDController(
      TrajectoryConstants.thetaProfiledControllerP, 0, 0,
      new TrapezoidProfile.Constraints(turnMaxAngularSpeedRadiansPerSecond, turnMaxAngularSpeedRadiansPerSecondSquared)
    );

    SwerveDriveKinematics kinematics = DriveConstants.driveKinematics;

    // IMPORTANT: Make sure your driveSubsystem has the methods getPose and setModuleStates

    /* EDIT CODE ABOVE HERE (ONLY TOUCH THE REST OF THE CODE IF YOU KNOW WHAT YOU'RE DOING) */

    TrajectoryConfig config = new TrajectoryConfig(
      driveMaxSpeedMetersPerSecond,
      driveMaxAccelerationMetersPerSecond)
      .setKinematics(DriveConstants.driveKinematics)
      .setStartVelocity(0)
      .setEndVelocity(0);

    // This is here just to ensure a bad trajectory doesn't crash the robot
    try {
      Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
          start,
          middleWaypoints,
          end,
          config
        );

      thetaController.enableContinuousInput(-Math.PI, Math.PI);

      new RealTimeSwerveControllerCommand(
        trajectory,
        driveSubsystem::getPose, // Functional interface to feed supplier
        kinematics,
        // Position controllers
        xController,
        yController,
        thetaController,
        driveSubsystem::setModuleStates,
        whileHeldButtonBooleanSupplier,
        end,
        driveSubsystem
      ).schedule();
    } catch(Exception e) {
      SmartDashboard.putString("Trajectory Error Message", e.getLocalizedMessage());
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }

}