// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.limb.claw;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystemImpl;

public class ToggleClaw extends CommandBase {

  private ArmSubsystemImpl armSubsystem;

  public ToggleClaw(ArmSubsystemImpl armSubsystem) {
    this.armSubsystem = armSubsystem;
    addRequirements(armSubsystem);
  }


  @Override
  public void initialize() {
    armSubsystem.invertClaw();
    if (armSubsystem.getClaw()) {
      armSubsystem.openClaw();
    } else {
      armSubsystem.closeClaw();
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}