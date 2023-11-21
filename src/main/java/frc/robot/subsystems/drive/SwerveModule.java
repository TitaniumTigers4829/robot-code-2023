package frc.robot.subsystems.drive;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.DemandType;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
// import com.ctre.phoenix.motorcontrol.StatusFrame;
// import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
// import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
// import com.ctre.phoenix.sensors.AbsoluteSensorRange;
// import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.HardwareConstants;
import frc.robot.Constants.ModuleConstants;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

public class SwerveModule {

  private final CANcoder turnEncoder;
  private final TalonFX driveMotor;
  private final TalonFX turnMotor;

  private PositionVoltage angleSetter = new PositionVoltage(0);
  //private VelocityTorqueCurrentFOC driveSetter = new VelocityTorqueCurrentFOC(0);
  private VelocityVoltage driveSetter = new VelocityVoltage(0);

  // private final ProfiledPIDController turnPIDController =
  //   new ProfiledPIDController(
  //     ModuleConstants.TURN_P,
  //     ModuleConstants.TURN_I,
  //     ModuleConstants.TURN_D,
  //     ModuleConstants.TURN_CONSTRAINTS
  //   );

  // private final SimpleMotorFeedforward turnFeedForward = new SimpleMotorFeedforward(
  //   DriveConstants.TURN_S, 
  //   DriveConstants.TURN_V, 
  //   DriveConstants.TURN_A
  // );

  // private final SimpleMotorFeedforward driveFeedForward = new SimpleMotorFeedforward(
  //   ModuleConstants.DRIVE_S,
  //   ModuleConstants.DRIVE_V,
  //   ModuleConstants.DRIVE_A
  // );

  private String name;

  /**
   * Constructs a swerve module
   * @param driveMotorChannel ID of the drive motor
   * @param turnMotorChannel ID of the turn motor
   * @param turnEncoderChannel ID of the CANCoder
   * @param angleZero CANCoder offset
   * @param encoderReversed is the turn encoder reversed
   * @param driveReversed is the drive motor reversed
   */
  public SwerveModule(
    int driveMotorChannel,
    int turnMotorChannel,
    int turnEncoderChannel,
    double angleZero,
    SensorDirectionValue encoderReversed,
    InvertedValue driveReversed,
    String name
    ) {
    this.name = name;
    
    turnEncoder = new CANcoder(turnEncoderChannel, HardwareConstants.CANIVORE_CAN_BUS_STRING);
    driveMotor = new TalonFX(driveMotorChannel, HardwareConstants.CANIVORE_CAN_BUS_STRING);
    turnMotor = new TalonFX(turnMotorChannel, HardwareConstants.CANIVORE_CAN_BUS_STRING);

    turnEncoder.getConfigurator().apply(new CANcoderConfiguration());
    var angleEncoderConfigs = new CANcoderConfiguration();
    angleEncoderConfigs.MagnetSensor.MagnetOffset = angleZero;
    angleEncoderConfigs.MagnetSensor.SensorDirection = encoderReversed;
    angleEncoderConfigs.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Signed_PlusMinusHalf;
    turnEncoder.getConfigurator().apply(angleEncoderConfigs);

    driveMotor.getConfigurator().apply(new TalonFXConfiguration());
    var driveConfig = new TalonFXConfiguration();
    driveConfig.Slot0.kV = ModuleConstants.driveKV;
    driveConfig.Slot0.kS = ModuleConstants.driveKV;
    driveConfig.Slot0.kP = ModuleConstants.DRIVE_P;
    driveConfig.Slot0.kI = ModuleConstants.DRIVE_I;
    driveConfig.Slot0.kD = ModuleConstants.DRIVE_D;
    driveConfig.CurrentLimits.SupplyCurrentLimit = 60;
    driveConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    driveConfig.CurrentLimits.SupplyCurrentThreshold = 65;
    driveConfig.CurrentLimits.SupplyTimeThreshold = 0.1;
    driveConfig.MotorOutput.Inverted = driveReversed;
    driveConfig.MotorOutput.DutyCycleNeutralDeadband = 0.0001;
    driveConfig.MotionMagic.MotionMagicCruiseVelocity = ModuleConstants.MAX_ANGULAR_SPEED_RADIANS_PER_SECOND;
    driveConfig.MotionMagic.MotionMagicAcceleration = ModuleConstants.MAX_ANGULAR_ACCELERATION_RADIANS_PER_SECOND_SQUARED;
    driveConfig.MotionMagic.MotionMagicJerk = 0;
    driveConfig.ClosedLoopGeneral.ContinuousWrap = true;
    driveConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    driveConfig.Feedback.SensorToMechanismRatio = ModuleConstants.rotationsPerMeter;
    driveMotor.getConfigurator().apply(driveConfig);
    // driveMotor.setRotorPosition(0.0);
    // driveMotor.setInverted(driveMotorInverted);



    turnMotor.getConfigurator().apply(new TalonFXConfiguration());
    var turnConfig = new TalonFXConfiguration();
    turnConfig.Slot0.kV = 0.0; //Subject to change
    turnConfig.Slot0.kS = 0.0; //Subject to change
    turnConfig.Slot0.kP = ModuleConstants.TURN_P;
    turnConfig.Slot0.kI = ModuleConstants.TURN_I;
    turnConfig.Slot0.kD = ModuleConstants.TURN_D;
    turnConfig.ClosedLoopGeneral.ContinuousWrap = true;
    turnConfig.CurrentLimits.SupplyCurrentLimit = 60;
    turnConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    turnConfig.CurrentLimits.SupplyCurrentThreshold = 65;
    turnConfig.CurrentLimits.SupplyTimeThreshold = 0.1;
    // turnConfig.Voltage.PeakForwardVoltage = 10;
    // turnConfig.Voltage.PeakReverseVoltage = -10;
    //turnConfig.Feedback.SensorToMechanismRatio = ModuleConstants.DRIVE_GEAR_RATIO;
    turnConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RemoteCANcoder;
    turnConfig.Feedback.FeedbackRemoteSensorID = turnEncoderChannel;
    turnConfig.MotorOutput.DutyCycleNeutralDeadband = HardwareConstants.MIN_FALCON_DEADBAND;
    turnConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    //turnConfig.MotorOutput.Inverted = ModuleConstants.angleMotorInvert;
    turnMotor.getConfigurator().apply(turnConfig);


  }


  /**
   * @param currentAngle what the controller currently reads (radians)
   * @param targetAngleSetpoint the desired angle (radians)
   * @return the target angle in controller's scope (radians)
   */
  public static double calculateContinuousInputSetpoint(double currentAngle, double targetAngleSetpoint) {
    targetAngleSetpoint = Math.IEEEremainder(targetAngleSetpoint, Math.PI * 2);

    double remainder = currentAngle % (Math.PI * 2);
    double adjustedAngleSetpoint = targetAngleSetpoint + (currentAngle - remainder);

    // We don't want to rotate over 180 degrees, so just rotate the other way (add a
    // full rotation)
    if (adjustedAngleSetpoint - currentAngle > Math.PI) {
        adjustedAngleSetpoint -= Math.PI * 2;
    } else if (adjustedAngleSetpoint - currentAngle < -Math.PI) {
        adjustedAngleSetpoint += Math.PI * 2;
    }

    return adjustedAngleSetpoint;
  }

  /**
   * Gets the heading of the module
   * @return the absolute position of the CANCoder
   */
  public double getModuleHeading() {
    return this.turnEncoder.getAbsolutePosition().refresh().getValue();
  }

  /**
   * Returns the current state of the module.
   * @return The current state of the module.
   */
  public SwerveModuleState getState() {
    double speedMetersPerSecond = ModuleConstants.DRIVE_TO_METERS_PER_SECOND * driveMotor.getVelocity().refresh().getValue();
    //double turnRadians =  2.0 * Math.PI * turnEncoder.getAbsolutePosition().refresh().getValue();
    double turnRadians = Rotation2d.fromRotations(getModuleHeading()).getRadians();
    return new SwerveModuleState(speedMetersPerSecond, new Rotation2d(turnRadians));
  }

  public SwerveModulePosition getPosition() {
    double position = ModuleConstants.DRIVE_TO_METERS * driveMotor.getPosition().refresh().getValue();
    Rotation2d rotation = Rotation2d.fromRotations(getCANCoderABS());
    return new SwerveModulePosition(position, rotation);
  }

  /**
   * Sets the desired state for the module and sends calculated output from controller to the motor.
   * @param desiredState Desired state with speed and angle.
   */
  public void setDesiredState(SwerveModuleState desiredState) {
    double turnRadians = getTurnRadians();

    // Optimize the reference state to avoid spinning further than 90 degrees
    SwerveModuleState optimizedDesiredState = SwerveModuleState.optimize(desiredState, new Rotation2d(turnRadians));

    // // Converts meters per second to rpm
    // double desiredDriveRPM = optimizedDesiredState.speedMetersPerSecond * 60 
    //   * ModuleConstants.DRIVE_GEAR_RATIO / ModuleConstants.WHEEL_CIRCUMFERENCE_METERS;
      
    // // Converts rpm to encoder units per 100 milliseconds
    // double desiredDriveEncoderUnitsPer100MS = desiredDriveRPM / 600.0;

    // // Sets the drive motor's speed using the built in pid controller
    // driveMotor.set(ControlMode.Velocity, desiredDriveEncoderUnitsPer100MS, 
    //   DemandType.ArbitraryFeedForward, driveFeedForward.calculate(optimizedDesiredState.speedMetersPerSecond));

    // // Calculate the turning motor output from the turn PID controller.
    // double turnOutput =
    // turnPIDController.calculate(turnRadians, optimizedDesiredState.angle.getRadians())
    //   + turnFeedForward.calculate(turnPIDController.getSetpoint().velocity);
    //   turnMotor.set(turnOutput / 12);

    double velocityToSet = optimizedDesiredState.speedMetersPerSecond * 60 
    * ModuleConstants.DRIVE_GEAR_RATIO / ModuleConstants.WHEEL_CIRCUMFERENCE_METERS;

    VelocityVoltage driveOutput = new VelocityVoltage(velocityToSet / 600.0);
    driveMotor.setControl(driveOutput);

    // // Calculate the turning motor output from the turn PID controller.
    // double turnOutput =
    //   turnPIDController.calculate(turnRadians, optimizedDesiredState.angle.getRadians())
    //     + turnFeedForward.calculate(turnPIDController.getSetpoint().velocity);
    //     turnMotor.set(turnOutput / 12);

    MotionMagicVoltage turnOutput = new MotionMagicVoltage(optimizedDesiredState.angle.getRadians());
    turnMotor.setControl(turnOutput);

  }

  public double getTurnRadians() {
    return 2 * Math.PI * turnEncoder.getAbsolutePosition().refresh().getValue();
  }

  public double getAbsoluteRotationDegrees() {
    return turnEncoder.getAbsolutePosition().refresh().getValue() * 360.0;
  }

  /**
   * Gets the current position of the CANCoder in relation to the magnet
   * @return current CANCoder position
   */
  public double getCANCoderABS(){
    return turnEncoder.getAbsolutePosition().refresh().getValue();
  }

  @Deprecated
  /** Zeros all the SwerveModule encoders. */
  public void resetEncoders() {
    turnEncoder.setPosition(0);
    driveMotor.setRotorPosition(0.0);
  }

  public void periodicFunction() {}
}