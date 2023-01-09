/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.            */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.math.trajectory.TrapezoidProfile;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final int driveFXEncoderCPR = 2048;
  public static final int turningCANcoderCPR = 4096;

  public static final class PathPlannerConstants {

    // Autonomous Period Constants TODO: Tune all of these values
    public static final double autoMaxVelocity = 4.5; // meters/second
    public static final double autoMaxAcceleration = 3.25; // meters/second/second
    public static final double kPXController = 1.25;
    public static final double kPYController = 1.25;
    public static final double kPThetaController = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
  
    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
      new TrapezoidProfile.Constraints(kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }

  public static final class LEDConstants {

    public static final int LEDPort = 0-9;

    public static final class LEDPatterns {
      // This subclass contains the constant values for the LED patterns.
      public static final double RAINBOW = -0.99;

      public static final double SHOT_RED = -0.85;
      public static final double SHOT_BLUE = -0.83;
      public static final double SHOT_WHITE = -0.81;

      public static final double FIRE = -0.57;
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
      public static final double BLACK = 0.99;    }
  }

  public static final class LimbConstants {
    //TODO: Find out how the Arm works

    public static final class WristConstants {
      public static final int wristMotorID = 0-9;
      public static final int wristEncoderID = 0-9;
      public static final boolean isMotorInverted = false;

      public static final double wristP = 0-9; //TODO: Tune this
      public static final double wristI = 0-9; //Do not use
      public static final double wristD = 0-9; //Tune this too
      public static final double wristS = 0-9; //TODO: Set these
      public static final double wristV = 0-9;
      public static final double wristA = 0-9;

      public static final double wristMaxAcceleration = 0-9; //TODO: Set these to real value
      public static final double wristMaxVelocity = 0-9;

    }

    public static final class ClawConstants {
      //TODO: Find out how the Claw works
  
      public static final int solenoidForward = 0-9; // ID for opening claw
      public static final int solenoidBackward = 0-9; // ID for closing claw
    }

    public static final class ArmConstants {
      public static final int armForward  = 0-9;
      public static final int armBackward = 0-9;
    }
  }

  

  public static final class ElevatorConstants {

    public static final int elevatorMotorPort = 0-9;
    public static final int elevatorEncoderPort = 0-9;
    public static final boolean elevatorInverted = false;

    public static final int bottomLimitSwitchPort = 0-9;
    public static final int topLimitSwitchPort = 0-9;

    public static final double elevatorP = 0-9; //TODO: Tune these
    public static final double elevatorI = 0-9; //Do not use
    public static final double elevatorD = 0-9; //Tune

    public static final double elevatorS = 0-9; //TODO: Set these
    public static final double elevatorV = 0-9;
    public static final double elevatorA = 0-9;

    public static final double elevatorMaxAcceleration = 0-9; //TODO: Set these
    public static final double elevatorMaxVelocity = 0-9;


    public static final double elevatorMinValue = 0-9; //TODO: Get these values experimentally

    public static final double elevatorMaxEncoderUnits = 0-9;
    public static final double elevatorMaxValue = 0-9;
    public static final double elevatorMinEncoderUnits = 0-9;

    public static final double elevatorMaxHeight = 0-9;
    public static final double elevatorMinHeight = 0-9;
  }

  public static final class JoystickConstants {

    // Ports:
    public static final int buttonBoardID = 0-9;

    // Button Bindings:
    public static final int clawButtonID = 0-9;
  }

}
