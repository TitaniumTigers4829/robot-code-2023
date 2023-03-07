// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.limb;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClawConstants;

public class ClawSubsystemImpl extends SubsystemBase implements ClawSubsystem {

  private final DoubleSolenoid clawSolenoid;

  private final WPI_TalonFX wristMotor;
  private final DigitalInput wristLimitSwitch;

  private final CANSparkMax leftWheel;
  private final CANSparkMax rightWheel;

  private boolean isClawClosed;

  /** Creates a new ClawSubsystemImpl. */
  public ClawSubsystemImpl() {
    clawSolenoid = new DoubleSolenoid(
      PneumaticsModuleType.CTREPCM,
      ClawConstants.SOLENOID_FORWARD,
      ClawConstants.SOLENOID_BACKWARD
    );

    wristMotor = new WPI_TalonFX(ClawConstants.WRIST_MOTOR_ID);

    leftWheel = new CANSparkMax(ClawConstants.LEFT_WHEEL_MOTOR_ID, MotorType.kBrushless);
    rightWheel = new CANSparkMax(ClawConstants.RIGHT_WHEEL_MOTOR_ID, MotorType.kBrushless);

    leftWheel.restoreFactoryDefaults();
    rightWheel.restoreFactoryDefaults();

    leftWheel.setInverted(ClawConstants.LEFT_WHEEL_MOTOR_INVERTED);
    rightWheel.setInverted(ClawConstants.RIGHT_WHEEL_MOTOR_INVERTED);

    wristLimitSwitch = new DigitalInput(ClawConstants.WRIST_LIMIT_SWITCH_PORT);

    wristMotor.config_kF(0, ClawConstants.WRIST_FEED_FORWARD_GAIN);
    wristMotor.config_kP(0, ClawConstants.WRIST_P);
    wristMotor.config_kI(0, ClawConstants.WRIST_I);
    // wristMotor.config_IntegralZone(0, 150.0 / (600.0) * Constants.FALCON_ENCODER_RESOLUTION); //This ratio was taken from climb subsystem, I don't know if it needs changing

    wristMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {}

  @Override
  public void close() {
    clawSolenoid.set(DoubleSolenoid.Value.kForward);
    isClawClosed = true;    
  }

  @Override
  public void open() {
    clawSolenoid.set(DoubleSolenoid.Value.kReverse); 
    isClawClosed = false;   
  }

  @Override
  public boolean getClawClosed() { 
    return isClawClosed;
  }

  @Override
  public void setMotorSpeed(double speed) {
    leftWheel.set(motorOutputClamp(speed));
    rightWheel.set(motorOutputClamp(speed));
  }

  @Override
  public double getWristAngle() {
    return wristMotor.getSelectedSensorPosition() * (360 / Constants.FALCON_ENCODER_RESOLUTION);
  }

  @Override
  public void goToWristAngle(double desiredAngle) {
    // Stop's the wrist from rotating past its minimum position
    if (!isWristLimitSwitchPressed() || desiredAngle > ClawConstants.MIN_WRIST_ROTATION_DEGREES) {
      double desiredPos = desiredAngle * (Constants.FALCON_ENCODER_RESOLUTION / 360);
      wristMotor.set(ControlMode.MotionMagic, desiredPos);
    }
  }

  @Override
  public boolean isWristLimitSwitchPressed() {
    return wristLimitSwitch.get();
  }

  @Override
  public void zeroWristEncoder() {
    wristMotor.setSelectedSensorPosition(ClawConstants.MIN_WRIST_ROTATION_ENCODER_UNITS);
  }

  /*
   * Returns the motor output with a min. of -1 and max. of 1
   */
  private double motorOutputClamp(double motorOutput) {
    return Math.max(-1, Math.min(1, motorOutput));
  }

}
