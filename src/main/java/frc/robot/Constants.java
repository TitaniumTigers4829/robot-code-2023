package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.PneumaticsModuleType;


public final class Constants {

  private Constants() {}

  // TODO: make an optimizeCan function
  public static final class HardwareConstants {
    public static final String CANIVORE_CAN_BUS_STRING = "Canivore 1";
    // public static final String CANIVORE_CAN_BUS_STRING = "rio";
    public static final String RIO_CAN_BUS_STRING = "rio";

    public static final double FALCON_ENCODER_RESOLUTION = 2048.0;
    public static final double CANCODER_RESOLUTION = 4096.0; 

    public static final double MIN_FALCON_DEADBAND = 0.001;

    public static final PneumaticsModuleType PNEUMATICS_MODULE_TYPE = PneumaticsModuleType.CTREPCM;

    public static final int TIMEOUT_MS = 30;
  }

  public static final class Conversions {
    public static final double DEGREES_TO_CANCODER_UNITS = HardwareConstants.CANCODER_RESOLUTION / 360.0;
    public static final double DEGREES_TO_FALCON_UNITS = HardwareConstants.FALCON_ENCODER_RESOLUTION / 360.0;
  }

  public static final class JoystickConstants {

    public static final int DRIVER_JOYSTICK_ID = 0;
    public static final int OPERATOR_JOYSTICK_ID = 1;
    public static final int BUTTON_BOARD_1_ID = 2;
    public static final int BUTTON_BOARD_2_ID = 3;

    public static final int DRIVER_LEFT_STICK_X = 0;
    public static final int DRIVER_LEFT_STICK_Y = 1;
    public static final int DRIVER_RIGHT_STICK_X = 4;
    public static final int DRIVER_RIGHT_STICK_Y = 5;
    public static final int DRIVER_X_BUTTON_ID = 3;
    public static final int DRIVER_A_BUTTON_ID = 1;
    public static final int DRIVER_B_BUTTON_ID = 2;
    public static final int DRIVER_Y_BUTTON_ID = 4;
    public static final int DRIVER_LEFT_BUMPER_ID = 5;
    public static final int DRIVER_RIGHT_BUMPER_ID = 6;
    public static final int DRIVER_LEFT_TRIGGER_ID = 2;
    public static final int DRIVER_RIGHT_TRIGGER_ID = 3;
    public static final int DRIVER_BACK_BUTTON_ID = 7;
    public static final int DRIVER_START_BUTTON_ID = 8;
    public static final int DRIVER_LEFT_STICK_PRESS_ID = 9;
    public static final int DRIVER_RIGHT_STICK_PRESS_ID = 10;

    public static final int OPERATOR_LEFT_STICK_X = 0;
    public static final int OPERATOR_LEFT_STICK_Y = 1;
    public static final int OPERATOR_RIGHT_STICK_X = 4;
    public static final int OPERATOR_RIGHT_STICK_Y = 5;

    public static final int OPERATOR_X_BUTTON_ID = 3;
    public static final int OPERATOR_A_BUTTON_ID = 1;
    public static final int OPERATOR_B_BUTTON_ID = 2;
    public static final int OPERATOR_Y_BUTTON_ID = 4;
    public static final int OPERATOR_LEFT_BUMPER_ID = 5;
    public static final int OPERATOR_RIGHT_BUMPER_ID = 6;
    public static final int OPERATOR_LEFT_TRIGGER_ID = 2;
    public static final int OPERATOR_RIGHT_TRIGGER_ID = 3;
    public static final int OPERATOR_BACK_BUTTON_ID = 7;
    public static final int OPERATOR_START_BUTTON_ID = 8;
    public static final int OPERATOR_LEFT_STICK_PRESS_ID = 9;
    public static final int OPERATOR_RIGHT_STICK_PRESS_ID = 10;

    public static final int LEFT_DPAD_ID = 270;
    public static final int UP_DPAD_ID = 0;
    public static final int RIGHT_DPAD_ID = 90;
    public static final int DOWN_DPAD_ID = 180;

    // Top Six Buttons

    /** Z axis */
    public static final int BIG_BUTTON_1 = -1;
    /** Z axis */
    public static final int BIG_BUTTON_2 = 1;
    /** X axis */
    public static final int BIG_BUTTON_3 = -1;
    /** X axis */
    public static final int BIG_BUTTON_4 = 1;
    /** Y axis */
    public static final int BIG_BUTTON_5 = -1;
    /** Y axis */
    public static final int BIG_BUTTON_6 = 1;

    // Joystick 1 Autoplace Buttons
    public static final int BUTTON_1 = 1;
    public static final int BUTTON_2 = 2;
    public static final int BUTTON_3 = 3;
    public static final int BUTTON_4 = 4;
    public static final int BUTTON_5 = 5;
    public static final int BUTTON_6 = 6;
    public static final int BUTTON_7 = 7;
    public static final int BUTTON_8 = 8;
    public static final int BUTTON_9 = 9;
    public static final int BUTTON_10 = 10;
    public static final int BUTTON_11 = 11;
    public static final int BUTTON_12 = 1; // Z axis going forwards
    public static final int BUTTON_13 = 0; // POV Joystick 1
    public static final int BUTTON_14 = 180; // POV Joystick 1
    public static final int BUTTON_15 = 270; // POV Joystick 1
    public static final int BUTTON_16 = 90; // POV Joystick 1

    // Joystick 2 Autoplace Buttons
    public static final int BUTTON_17 = 1;
    public static final int BUTTON_18 = 2;
    public static final int BUTTON_19 = 3;
    public static final int BUTTON_20 = 4;
    public static final int BUTTON_21 = 5;
    public static final int BUTTON_22 = 6;
    public static final int BUTTON_23 = 7;
    public static final int BUTTON_24 = 8;
    public static final int BUTTON_25 = 9;
    public static final int BUTTON_26 = 10;
    public static final int BUTTON_27 = 11;
  }

  public static final class DriveConstants {

    // Distance between centers of right and left wheels on robot
    public static final double TRACK_WIDTH = Units.inchesToMeters(22.25);
    // Distance between front and back wheels on robot
    public static final double WHEEL_BASE = Units.inchesToMeters(28.5);
    public static final SwerveDriveKinematics DRIVE_KINEMATICS = new SwerveDriveKinematics(
      new Translation2d(WHEEL_BASE / 2, TRACK_WIDTH / 2), // Front Left
      new Translation2d(WHEEL_BASE / 2, -TRACK_WIDTH / 2), // Front Right
      new Translation2d(-WHEEL_BASE / 2, TRACK_WIDTH / 2), // Rear Left
      new Translation2d(-WHEEL_BASE / 2, -TRACK_WIDTH / 2) // Rear Right
    );

    public static final int FRONT_LEFT_DRIVE_MOTOR_ID = 1;
    public static final int FRONT_RIGHT_DRIVE_MOTOR_ID = 2;
    public static final int REAR_LEFT_DRIVE_MOTOR_ID = 3;
    public static final int REAR_RIGHT_DRIVE_MOTOR_ID = 4;

    public static final int FRONT_LEFT_TURN_MOTOR_ID = 5;
    public static final int FRONT_RIGHT_TURN_MOTOR_ID = 6;
    public static final int REAR_LEFT_TURN_MOTOR_ID = 7;
    public static final int REAR_RIGHT_TURN_MOTOR_ID = 8;

    public static final int FRONT_LEFT_CANCODER_ID = 11;
    public static final int FRONT_RIGHT_CANCODER_ID = 12;
    public static final int REAR_LEFT_CANCODER_ID = 13;
    public static final int REAR_RIGHT_CANCODER_ID = 14;

    public static final double FRONT_LEFT_ZERO_ANGLE = 169.716796875;
    public static final double FRONT_RIGHT_ZERO_ANGLE = -76.46484375;
    public static final double REAR_LEFT_ZERO_ANGLE = 46.58203125;
    public static final double REAR_RIGHT_ZERO_ANGLE = -78.57421875 + 90;

    public static final boolean FRONT_LEFT_CANCODER_REVERSED = false;
    public static final boolean FRONT_RIGHT_CANCODER_REVERSED = false;
    public static final boolean REAR_LEFT_CANCODER_REVERSED = false;
    public static final boolean REAR_RIGHT_CANCODER_REVERSED = false;
    
    public static final boolean FRONT_LEFT_DRIVE_ENCODER_REVERSED = false;
    public static final boolean FRONT_RIGHT_DRIVE_ENCODER_REVERSED = true; 
    public static final boolean REAR_LEFT_DRIVE_ENCODER_REVERSED = false;
    public static final boolean REAR_RIGHT_DRIVE_ENCODER_REVERSED = true;
    
    public static final double TURN_S = 0.77;
    public static final double TURN_V = 0.75;
    public static final double TURN_A = 0;
    public static final double MAX_ANGULAR_SPEED_RADIANS_PER_SECOND = Math.PI * 4;

    public static final double MAX_SPEED_METERS_PER_SECOND = 4.5;

    public static final double FACEFORWARD_P = 0.015;

    // TODO: Remove this if we don't end up doing auto balance
    public static final class BalanceConstants {
      public static final double BALANCE_P = 0.07;
      public static final double BALANCE_I = 0;
      public static final double BALANCE_D = 0;
      
      public static final double ORIENTATION_ERROR_CONSIDERED_ORIENTED = 2.5; // +/- degrees
      public static final double BALANCE_ERROR_CONSIDERED_BALANCED = 2.4; // +/- degrees

      public static final double INITIAL_SPEED = 0.7;
      public static final double BALANCE_ERROR_INIT_DEGREES = 10;
      public static final double BALANCE_ERROR_NEAR_BALANCED = 3;
    }

    public static final double RED_CHUTE_X = 2.68 - 0.29;
    public static final double BLUE_CHUTE_X = TrajectoryConstants.FIELD_LENGTH_METERS - RED_CHUTE_X;
    public static final double LEDS_ACCEPTABLE_ERROR = 0.2;

    // < than
    public static final double RED_CHUTE_THRESHOLD_X = 3.75;
    // > than
    public static final double RED_CHUTE_THRESHOLD_Y = 6.28;
    // > than
    public static final double BLUE_CHUTE_THRESHOLD_X = 12.75;
    // > than
    public static final double BLUE_CHUTE_THRESHOLD_Y = 6.28;
  }
  
  public static final class ModuleConstants { 
    public static final double DRIVE_GEAR_RATIO = 7.36;
    public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(4);
    public static final double WHEEL_CIRCUMFERENCE_METERS = WHEEL_DIAMETER_METERS * Math.PI;
    public static final double DRIVE_TO_METERS = 
      WHEEL_CIRCUMFERENCE_METERS / (DRIVE_GEAR_RATIO * HardwareConstants.FALCON_ENCODER_RESOLUTION);
    public static final double DRIVE_TO_METERS_PER_SECOND =
      (10 * WHEEL_CIRCUMFERENCE_METERS) / (DRIVE_GEAR_RATIO * HardwareConstants.FALCON_ENCODER_RESOLUTION);

    public static final double TURN_P = 7; // 8.1
    public static final double TURN_I = 0;
    public static final double TURN_D = 0;
    public static final double MAX_ANGULAR_SPEED_RADIANS_PER_SECOND = 10 * Math.PI;
    public static final double MAX_ANGULAR_ACCELERATION_RADIANS_PER_SECOND_SQUARED = 8 * Math.PI;
    public static final TrapezoidProfile.Constraints TURN_CONSTRAINTS =
      new TrapezoidProfile.Constraints(
        MAX_ANGULAR_SPEED_RADIANS_PER_SECOND,
        MAX_ANGULAR_ACCELERATION_RADIANS_PER_SECOND_SQUARED
      );

    public static final double DRIVE_F = 0; // 0.059;
    public static final double DRIVE_P = 0.3; // 0.19;
    public static final double DRIVE_I = 0;
    public static final double DRIVE_D = 0;
    public static final double DRIVE_S = 0.29155 / 12.0;
    public static final double DRIVE_V = 2.3621 / 12.0;
    public static final double DRIVE_A = 0.72606 / 12.0;
  }

  public static final class ArmConstants {
    public static final int ROTATION_ENCODER_ID = 15;
    public static final int LEADER_ROTATION_MOTOR_ID = 9;
    public static final int FOLLOWER_ROTATION_MOTOR_ID = 10;
    public static final int EXTENSION_MOTOR_ID = 16;
    public static final int EXTENSION_LOCK_ENGAGED_ID = 2;
    public static final int EXTENSION_LOCK_DISENGAGED_ID = 3;

    public static final double ROTATION_ENCODER_OFFSET = -9.580078125 - 180;

    public static final double EXTENSION_MOTOR_GEAR_RATIO = 16.0;
    public static final double EXTENSION_SPOOL_DIAMETER = Units.inchesToMeters(2.5);
    public static final double MAX_EXTENSION_METERS = 1.2;
    public static final double EXTENSION_MOTOR_POS_TO_METERS = EXTENSION_SPOOL_DIAMETER / (HardwareConstants.FALCON_ENCODER_RESOLUTION * EXTENSION_MOTOR_GEAR_RATIO) * Math.PI;
    public static final double EXTENSION_METERS_TO_MOTOR_POS = -1.0 / EXTENSION_MOTOR_POS_TO_METERS;

    public static final boolean LEADER_ROTATION_MOTOR_INVERTED = false;
    public static final double ROTATION_P = 3.5;
    public static final double ROTATION_I = 0;
    public static final double ROTATION_D = 0;
    public static final double ROTATION_MAX_VELOCITY_ENCODER_UNITS = 240;
    public static final double ROTATION_MAX_ACCELERATION_ENCODER_UNITS = 400;
    public static final int ROTATION_SMOOTHING = 1;
    public static final double ROTATION_ACCEPTABLE_ERROR = 1;
    public static final double ROTATION_TOLERANCE_DEGREES = 0;
    public static final double ROTATION_TOLERANCE_ENCODER_UNITS = ROTATION_TOLERANCE_DEGREES * Conversions.DEGREES_TO_CANCODER_UNITS;
    public static final double MAX_ROTATION_ENCODER_UNITS = 305.5 * Conversions.DEGREES_TO_CANCODER_UNITS;
    public static final double MIN_ROTATION_ENCODER_UNITS = 60 * Conversions.DEGREES_TO_CANCODER_UNITS;
    
    public static final boolean EXTENSION_MOTOR_INVERTED = true;
    public static final double EXTENSION_ACCELERATION_GAIN = 0;
    public static final double EXTENSION_VELOCITY_GAIN = 0.2;
    public static final double EXTENSION_P = -6;
    public static final double EXTENSION_I = 0;
    public static final double EXTENSION_D = 0;
    public static final double EXTENSION_F = 0;
    public static final double EXTENSION_MAX_VELOCITY = 1;
    public static final double EXTENSION_MAX_ACCELERATION = 2.5;
    public static final double EXTENSION_ACCEPTABLE_ERROR = 0.025;
    public static final double EXTENSION_TOLERANCE = 0.025; 
    public static final TrapezoidProfile.Constraints EXTENSION_CONSTRAINTS = new TrapezoidProfile.Constraints(
      EXTENSION_MAX_VELOCITY, EXTENSION_MAX_ACCELERATION);

    public static final double IDLE_EXTENSION_OUTPUT = 0.025;

    public static final double STOWED_ROTATION = 180;
    public static final double STOWED_EXTENSION = 0.01;

    public static final double PICKUP_GROUND_ROTATION_AUTO = 288;
    public static final double PICKUP_GROUND_EXTENSION_AUTO = 0.875;
    public static final double PICKUP_GROUND_ROTATION = 305;
    public static final double PICKUP_GROUND_EXTENSION = 0.13;
    public static final double PICKUP_LOADING_STATION_ROTATION = 111;
    public static final double PICKUP_LOADING_STATION_EXTENSION = .768;
    public static final double PICKUP_CHUTE_ROTATION = 104.3;
    public static final double PICKUP_CHUTE_EXTENSION = 0.215;

    public static final double PLACE_HIGH_ROTATION = 234.5;
    public static final double PLACE_HIGH_EXTENSION = 1.08;
    // TODO: Get values
    public static final double PLACE_MIDDLE_ROTATION = 239.2;
    public static final double PLACE_MIDDLE_EXTENSION = 0.38;
    public static final double PLACE_LOW_ROTATION = 180;
    public static final double PLACE_LOW_EXTENSION = 0.5;
    public static final double PLACE_MIDDLE_AUTO_ROTATION = 109.5;
    public static final double PLACE_MIDDLE_AUTO_EXTENSION = 0.9;
    public static final double SHOOT_CUBE_MIDDLE_ROTATION = 135;

    public static final double ARM_WEIGHT_NEWTONS = 9.8 * 25;
    public static final double ARM_AXIS_OF_ROTATION_RADIUS = Units.inchesToMeters(2.1);

    public static final double[][] CENTER_OF_MASS_LOOKUP_TABLE = {
      //{extension (meters), distance from pivot point to COM}
      {0, Units.inchesToMeters(1.024)},
      {.1, Units.inchesToMeters(2.9)},
      {.2, Units.inchesToMeters(4.5)},
      {.4, Units.inchesToMeters(8.4)},
      {.6, Units.inchesToMeters(12)},
      {.75, Units.inchesToMeters(16)},
      {1.05, Units.inchesToMeters(20)},
      {1.25, Units.inchesToMeters(34)},
    };
  }

  public static final class ClawConstants {
    public static final int WRIST_MOTOR_ID = 17;
    public static final int INTAKE_MOTOR_ID = 18;
    public static final int CLAW_FORWARD = 0;
    public static final int CLAW_BACKWARD = 1;
    
    public static final double WRIST_GEAR_RATIO = 76.0 / 20.0;
    public static final double WRIST_POS_TO_DEG = (360.0 / HardwareConstants.FALCON_ENCODER_RESOLUTION) / WRIST_GEAR_RATIO;
    public static final double DEG_TO_WRIST_POS = (HardwareConstants.FALCON_ENCODER_RESOLUTION / 360.0) * WRIST_GEAR_RATIO;

    public static final boolean WRIST_MOTOR_INVERTED = true;
    public static final double WRIST_P = 2;
    public static final double WRIST_I = 0;
    public static final double WRIST_D = 0;
    public static final double WRIST_F = 0;
    public static final double WRIST_MAX_VELOCITY_ENCODER_UNITS = 90 * DEG_TO_WRIST_POS;
    public static final double WRIST_MAX_ACCELERATION_ENCODER_UNITS = 180 * DEG_TO_WRIST_POS;
    public static final double WRIST_TOLERANCE = 0 * DEG_TO_WRIST_POS;
    public static final int WRIST_SMOOTHING = 0;
    
    public static final boolean INTAKE_MOTOR_INVERTED = false;

    public static final double MIN_WRIST_ROTATION_ENCODER_UNITS = 0 * DEG_TO_WRIST_POS;
    public static final double MAX_WRIST_ROTATION_ENCODER_UNITS = 180 * DEG_TO_WRIST_POS;

    public static final double PICKUP_CONE_INTAKE_SPEED = 0.5;

    public static final double HOLD_CONE_INTAKE_SPEED = 0.02;
    public static final double HOLD_CUBE_INTAKE_SPEED = 0.05;

    public static final double PLACE_CONE_INTAKE_SPEED = -0.05;
    public static final double PLACE_CUBE_INTAKE_SPEED = -0.08;
    public static final double SHOOT_CUBE_INTAKE_SPEED = -1;
  }

  public static final class LimelightConstants {
    public static final int FRAMES_BEFORE_ADDING_VISION_MEASUREMENT = 5;
  
    public static final String FRONT_LIMELIGHT_NAME = "limelight-front";
    public static final String BACK_LIMELIGHT_NAME = "limelight-back";

    public static final double[][] APRIL_TAG_POSITIONS = {
      // { x, y, z}
      {Units.inchesToMeters(610.77), Units.inchesToMeters(42.19), Units.inchesToMeters(18.22)}, // 1
      {Units.inchesToMeters(610.77), Units.inchesToMeters(108.19), Units.inchesToMeters(18.22)}, // 2
      {Units.inchesToMeters(610.77), Units.inchesToMeters(174.19), Units.inchesToMeters(18.22)}, // 3
      {Units.inchesToMeters(636.96), Units.inchesToMeters(265.74), Units.inchesToMeters(27.38)}, // 4
      {Units.inchesToMeters(14.25), Units.inchesToMeters(265.74), Units.inchesToMeters(27.38)}, // 5
      {Units.inchesToMeters(40.45), Units.inchesToMeters(174.19), Units.inchesToMeters(18.22)}, // 6
      {Units.inchesToMeters(40.45), Units.inchesToMeters(108.19), Units.inchesToMeters(18.22)}, // 7
      {Units.inchesToMeters(40.45), Units.inchesToMeters(42.19), Units.inchesToMeters(18.22)} // 8
    };

    public static final double[][] CAMERA_CROP_LOOKUP_TABLE = {
      // TODO: All of these are placeholder values
      // {x position in meters, limelight lower y crop}
      {0, -1},
      {1, -.5},
      {2, -.25},
      {3, 0},
      {4, .25}
    };

    public static final double[][] ONE_APRIL_TAG_LOOKUP_TABLE = {
      // {distance in meters, x std deviation, y std deviation, r (in degrees) std deviation}
      {0, 0.01, 0.01, 10},
      {1.5, 0.01, 0.01, 10},
      {3, 0.145, 1.20, 30},
      {4.5, 0.75, 5.0, 90},
      {6, 1.0, 8.0, 180}
    };

    public static final double[][] TWO_APRIL_TAG_LOOKUP_TABLE = {
      // {distance in meters, x std deviation, y std deviation, r (in degrees) std deviation}
      {0, 0.01, 0.01, 5},
      {1.5, 0.02, 0.02, 5},
      {3, 0.04, 0.04, 15},
      {4.5, 0.1, 0.1, 30},
      {6, 0.3, 0.3, 60}
    };
  }

    
  public static final class LEDConstants {

    public static final int LEDPort = 0;
    public static final double ANIMATION_SPEED = 24; // "frames" per second
    public static final String EASTER_EGG_MESSAGE = "We'll miss you, Mr. Blanchard! -4829";

    public static final double ALLIANCE_ANIMATION_STRENGTH = 0.2; // 0-1

    public static final class SparkConstants {
      // This subclass contains the constant values for the LED patterns.
      public static final double RAINBOW = -0.99;
      public static final double SHOT_RED = -0.85;
      public static final double SHOT_BLUE = -0.83;
      public static final double SHOT_WHITE = -0.81;
      public static final double RED_ALLIANCE_BLINKIN = -0.39;
      public static final double RAINBOW_WAVE = -0.45;
      public static final double OCEAN = -0.41;
      public static final double BOUNCE_RED = -0.35;
      public static final double BOUNCE_GRAY = -0.33;
      public static final double HEARTBEAT_RED = -0.25;
      public static final double HEARTBEAT_GRAY = -0.19;
      public static final double STROBE_RED = -0.11;
      public static final double STROBE_BLUE = -0.09;
      public static final double STROBE_GOLD = -0.07;
      public static final double STROBE_WHITE = -0.05;

      public static final double HEARTBEAT_1 = 0.43;
      public static final double HEARTBEAT_2 = 0.27;

      public static final double MAGENTA = 0.57;
      public static final double DARK_RED = 0.59;
      public static final double RED = 0.61;
      public static final double VERMILION = 0.63;
      public static final double ORANGE = 0.65;
      public static final double GOLD = 0.67;
      public static final double YELLOW = 0.69;
      public static final double LAWN_GREEN = 0.71;
      public static final double LIME = 0.73;
      public static final double DARK_GREEN = 0.75;
      public static final double GREEN = 0.77;
      public static final double CYAN = 0.79;
      public static final double AQUA = 0.81;
      public static final double SKY_BLUE = 0.83;
      public static final double DARK_BLUE = 0.85;
      public static final double BLUE = 0.87;
      public static final double INDIGO = 0.89;
      public static final double PURPLE = 0.91;
      public static final double WHITE = 0.93;
      public static final double GRAY = 0.95;
      public static final double DARK_GRAY = 0.97;
      public static final double BLACK = 0.99;    
    }
    
    public enum LEDProcess {
      /** alliance color */
      ALLIANCE_COLOR (0, 0, 0, 0),
      /** default */
      DEFAULT (0, 0, 0, 0),
      RAINBOW (SparkConstants.RAINBOW, 0, 0, 0),
      RED_ALLIANCE (SparkConstants.RED_ALLIANCE_BLINKIN, 255, 0, 0),
      BLUE_ALLIANCE (SparkConstants.OCEAN, 0, 0, 255),
      SCORING_CUBE (SparkConstants.HEARTBEAT_1, 255, 255, 0),
      SCORING_CONE (SparkConstants.HEARTBEAT_2, 128, 0, 255),
      OFF (SparkConstants.BLACK, 0, 0, 0),
      CUBE (SparkConstants.PURPLE, 255, 0, 200),
      CONE (SparkConstants.YELLOW, 255, 255, 0),
      AUTONOMOUS (SparkConstants.RAINBOW_WAVE, 0, 0, 0),
      LINE_UP (SparkConstants.GREEN, 0, 255, 0),
      FINISH_LINE_UP (SparkConstants.SHOT_WHITE, 255, 255, 255),
      GREEN (SparkConstants.GREEN, 0, 255, 0),
      RED (SparkConstants.RED, 255, 0, 0),
      INTAKE (SparkConstants.MAGENTA, 255, 0, 255);

      private final double sparkValue;
      private final int red, green, blue;
      LEDProcess(double sparkValue, int red, int green, int blue) {
        this.sparkValue = sparkValue;
        this.red = red;
        this.green = green;
        this.blue = blue;
      }
      public double getSparkValue() { return sparkValue; }
      public int getRed() { return red; }
      public int getGreen() { return green; }
      public int getBlue() { return blue; }
    }
  }

  public static final class TrajectoryConstants {
    public static final double MAX_SPEED = 4;
    public static final double MAX_ACCELERATION = 3;
    public static final double DEPLOYED_X_CONTROLLER_P = .35;
    public static final double DEPLOYED_Y_CONTROLLER_P = .35;
    public static final double DEPLOYED_THETA_CONTROLLER_P = .8;
    public static final double REAL_TIME_X_CONTROLLER_P = 2;
    public static final double REAL_TIME_Y_CONTROLLER_P = 2;
    public static final double REAL_TIME_THETA_CONTROLLER_P = 3;
    public static final double THETA_PROFILED_CONTROLLER_P = 1;
    public static final double MAX_ANGULAR_SPEED_RADIANS_PER_SECOND = Math.PI ;
    public static final double MAX_ANGULAR_SPEED_RADIANS_PER_SECOND_SQUARED = 2 * Math.PI;
    // The length of the field in the x direction (left to right)
    public static final double FIELD_LENGTH_METERS = 16.54175;
    // The length of the field in the y direction (top to bottom)
    public static final double FIELD_WIDTH_METERS = 8.0137;
  
    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints THETA_CONTROLLER_CONSTRAINTS =
      new TrapezoidProfile.Constraints(MAX_ANGULAR_SPEED_RADIANS_PER_SECOND, MAX_ANGULAR_SPEED_RADIANS_PER_SECOND_SQUARED);
  
    public static final double BLUE_BALANCE_X_POSITION = 3.885;
    public static final double RED_BALANCE_X_POSITION = 12.635;
    public static final double BALANCE_X_CONTROLLER_P = .3;
    public static final double BALANCE_Y_CONTROLLER_P = .3;

    public static final double X_TOLERANCE = 0.02;
    public static final double Y_TOLERANCE = 0.02;
    public static final double THETA_TOLERANCE = 1.25;
  
    // These are ordered from top left to bottom right from driver perspective
    public static final double[] BLUE_NODE_Y_POSITIONS = 
      {4.9784, 4.4196, 3.8608, 3.302, 2.7432, 2.1844, 1.6256, 1.0668, 0.508};
    public static final double[] RED_NODE_Y_POSITIONS = 
      {0.508, 1.0668, 1.6256, 2.1844, 2.7432, 3.302, 3.8608, 4.4196, 4.9784};

    // Factors in bumper width and wheelbase
    public static final double BLUE_NODE_X_POSITION = 1.92;
    public static final double RED_NODE_X_POSITION = 14.61;
    public static final double BLUE_OUTER_WAYPOINT_X = 5.3;
    public static final double RED_OUTER_WAYPOINT_X = 11.26;
    public static final double BLUE_INNER_WAYPOINT_X = 2.5;
    public static final double RED_INNER_WAYPOINT_X = 14.26;
    public static final double UPPER_WAYPOINT_Y = 4.75;
    public static final double LOWER_WAYPOINT_Y = 0.75;
    public static final Rotation2d BLUE_END_ROTATION = Rotation2d.fromDegrees(0);
    public static final Rotation2d BLUE_HEADING = Rotation2d.fromDegrees(180);
    public static final Rotation2d RED_END_ROTATION = Rotation2d.fromDegrees(180);
    public static final Rotation2d RED_HEADING = Rotation2d.fromDegrees(0);

    public static final String TWO_CONE_BALANCE_AUTO_FIRST = "Two Cone Balance First Red";
    public static final String TWO_CONE_BALANCE_AUTO_SECOND = "Two Cone Balance Second Red";
    public static final String TWO_CONE_BALANCE_AUTO_THIRD = "Two Cone Balance Third Red";

    public static final String THREE_PIECE_BALANCE_AUTO_FIRST_RED = "Two Piece Balance First Red";
    public static final String THREE_PIECE_BALANCE_AUTO_SECOND_RED = "Two Piece Balance Second Red";
    public static final String THREE_PIECE_BALANCE_AUTO_THIRD_RED = "Two Piece Balance Third Red";

    public static final String THREE_PIECE_BALANCE_AUTO_FIRST_BLUE = "Two Piece Balance First Blue";
    public static final String THREE_PIECE_BALANCE_AUTO_SECOND_BLUE = "Two Piece Balance Second Blue";
    public static final String THREE_PIECE_BALANCE_AUTO_THIRD_BLUE = "Two Piece Balance Third Blue";
  }

  public static final class SmarterDashboardConstants {
    public static final String botPoseKey = "botPose";
    public static final String limelightPoseKey = "limelightPose";
    public static final String botOrientationKey = "botAngle";
    public static final String nodeIDKey = "selectedNode";
    public static final String cargoModeKey = "cargoMode";
  }
}