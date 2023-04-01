package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ClawConstants;
import frc.robot.extras.NodeRegistry;
import frc.robot.subsystems.arm.ArmSubsystem;
import frc.robot.subsystems.claw.ClawSubsystem;

public class PlaceGamePiece extends CommandBase {

  private final ArmSubsystem armSubsystem;
  private final ClawSubsystem clawSubsystem;

  private double rotation;
  private double extension;

  public PlaceGamePiece(ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem) {
    this.armSubsystem = armSubsystem;
    this.clawSubsystem = clawSubsystem;
    addRequirements(armSubsystem, clawSubsystem);
  }

  @Override
  public void initialize() {
    if (NodeRegistry.getSelectedNode() > 18) {
      rotation = ArmConstants.PLACE_HIGH_ROTATION;
      extension = ArmConstants.PLACE_HIGH_EXTENSION;
    } else if (NodeRegistry.getSelectedNode() > 9) {
      rotation = ArmConstants.PLACE_MIDDLE_ROTATION;
      extension = ArmConstants.PLACE_MIDDLE_EXTENSION;
    } else {
      rotation = ArmConstants.PLACE_LOW_ROTATION;
      extension = ArmConstants.PLACE_LOW_EXTENSION;
    }

    armSubsystem.resetExtensionController();
    if (clawSubsystem.isConeMode()) {
      if (rotation > 180) {
        clawSubsystem.setWristPosition(0);
      } else {
        clawSubsystem.setWristPosition(180);
      }
    }
  }

  @Override
  public void execute() {
    armSubsystem.setRotation(rotation);
    if (Math.abs(rotation - armSubsystem.getRotation()) < ArmConstants.ROTATION_ACCEPTABLE_ERROR) {
      armSubsystem.setExtension(extension);
    }
  }

  @Override
  public void end(boolean interrupted) {
    clawSubsystem.open();
    if (!clawSubsystem.isConeMode()) {
      clawSubsystem.setIntakeSpeed(ClawConstants.PLACE_CUBE_INTAKE_SPEED);
    } else {
      clawSubsystem.setIntakeSpeed(ClawConstants.PLACE_CONE_INTAKE_SPEED);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
