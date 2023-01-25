// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.limb.claw.ToggleClaw;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.drive.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public ArmSubsystem armSubsystem;
  public ElevatorSubsystem elevatorSubsystem;
  private final DriveSubsystem driveSubsystem;
  private final Command driveCommand;

  private final Joystick driverJoystick;

  private final JoystickButton rightBumper;

  public final Joystick buttonBoard;

  private final JoystickButton clawButton;
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    armSubsystem = new ArmSubsystem();
    // elevatorSubsystem = new ElevatorSubsystem();

    // Configure the button bindings
  
    driverJoystick = new Joystick(JoystickConstants.driverJoystickID);
    rightBumper = new JoystickButton(driverJoystick, JoystickConstants.rightBumperID);

    buttonBoard = new Joystick(JoystickConstants.buttonBoardID);
    clawButton = new JoystickButton(buttonBoard, JoystickConstants.clawButtonID);

    driveSubsystem = new DriveSubsystem();

    DoubleSupplier leftStickX = () -> driverJoystick.getRawAxis(0);
    DoubleSupplier leftStickY = () -> driverJoystick.getRawAxis(1);
    DoubleSupplier rightStickX = () -> driverJoystick.getRawAxis(2);

    driveCommand = new DriveCommand(driveSubsystem, 
      () -> modifyAxisCubed(leftStickY) * -1, 
      () -> modifyAxisCubed(leftStickX) * -1, 
      () -> modifyAxisCubed(rightStickX), 
      () -> !rightBumper.getAsBoolean()
    );

    driveSubsystem.setDefaultCommand(driveCommand);
    configureButtonBindings();
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxisCubed(DoubleSupplier supplierValue) {
    double value = supplierValue.getAsDouble();

    // Deadband
    value = deadband(value, 0.1);

    // Cube the axis
    value = Math.copySign(value * value * value, value);

    return value;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    clawButton.whileTrue(new ToggleClaw(armSubsystem));
    POVButton rightDirectionPad = new POVButton(driverJoystick, JoystickConstants.rightDPadID);
    rightDirectionPad.onTrue(new InstantCommand(driveSubsystem::zeroHeading));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}