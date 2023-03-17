// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.claw;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClawConstants;

public class ClawSubsystemImpl extends SubsystemBase implements ClawSubsystem {

  private final DoubleSolenoid clawSolenoid;

  private final WPI_TalonFX wristMotor;
  private final WPI_TalonFX intakeMotor;

  private boolean isClawClosed;
  private double setWristAngle = 0;

  /** Creates a new ClawSubsystemImpl. */
  public ClawSubsystemImpl() {
    clawSolenoid = new DoubleSolenoid(
      PneumaticsModuleType.CTREPCM,
      ClawConstants.SOLENOID_FORWARD,
      ClawConstants.SOLENOID_BACKWARD
    );

    wristMotor = new WPI_TalonFX(ClawConstants.WRIST_MOTOR_ID, Constants.RIO_CAN_BUS_STRING);
    intakeMotor = new WPI_TalonFX(ClawConstants.INTAKE_MOTOR_ID, Constants.RIO_CAN_BUS_STRING);

    wristMotor.config_kF(0, ClawConstants.WRIST_F);
    wristMotor.config_kP(0, ClawConstants.WRIST_P);
    wristMotor.config_kI(0, ClawConstants.WRIST_I);
    wristMotor.config_kD(0, ClawConstants.WRIST_D);
    intakeMotor.config_kF(0, ClawConstants.INTAKE_F);
    intakeMotor.config_kP(0, ClawConstants.INTAKE_P);
    intakeMotor.config_kI(0, ClawConstants.INTAKE_I);
    intakeMotor.config_kD(0, ClawConstants.INTAKE_D);

    wristMotor.setNeutralMode(NeutralMode.Brake);
    intakeMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("intake amps", intakeMotor.getSupplyCurrent());
    SmartDashboard.putNumber("wrist angle", getWristAngle());
    // wristPIDController.reset(getWristAngle(), getRotationSpeed());
    if (setWristAngle >= ClawConstants.MIN_WRIST_ROTATION_RADIANS && setWristAngle <= ClawConstants.MAX_WRIST_ROTATION_RADIANS) {
      wristMotor.set(ControlMode.Position, setWristAngle);
      SmartDashboard.putBoolean("working", true);
    } else {
      wristMotor.set(0);
      SmartDashboard.putBoolean("working", false);
    }
  }

  @Override
  public void close() {
    clawSolenoid.set(DoubleSolenoid.Value.kReverse);
    isClawClosed = true;
  }

  @Override
  public void open() {
    clawSolenoid.set(DoubleSolenoid.Value.kForward);    
    isClawClosed = false;
  }

  @Override
  public boolean isClawClosed() { 
    return isClawClosed;
  }

  @Override
  public void setIntakeSpeed(double speed) {
    intakeMotor.set(speed);
  }

  @Override 
  public void setWristMotorSpeed(double speed) {
    wristMotor.set(speed);
  }

  @Override
  public double getWristAngle() {
    return wristMotor.getSelectedSensorPosition() * (2.0 * Math.PI / Constants.FALCON_ENCODER_RESOLUTION);
  }

  @Override
  public void zeroWristEncoder() {
    wristMotor.setSelectedSensorPosition(0);
  }

  @Override
  public void setWristPosition(double angle) {
    setWristAngle = angle;
  }

  private double getRotationSpeed() {
    return wristMotor.getSelectedSensorVelocity() * (2.0 * Math.PI / Constants.FALCON_ENCODER_RESOLUTION) * 10.0;
  }

  /*
   * Returns the motor output with a min. of -1 and max. of 1.
   */
  private double motorOutputClamp(double motorOutput) {
    return Math.max(-1, Math.min(1, motorOutput));
  }


}