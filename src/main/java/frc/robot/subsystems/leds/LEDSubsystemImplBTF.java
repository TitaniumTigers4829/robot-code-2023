// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.leds;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants.LEDProcess;

public class LEDSubsystemImplBTF extends SubsystemBase implements LEDSubsystem {

  private final AddressableLED led;
  private final AddressableLEDBuffer buffer;

  private int rainbowWaveOffset = 0;

  /** Creates a new LEDSubsystemImpl for use with the WS2812b Adressable LED strips. 
   * @param port the PWM port of the LED strips
   * @param length the amount of LEDs in the total strip
  */
  public LEDSubsystemImplBTF(int port, int length) {
    led = new AddressableLED(port);
    led.setLength(length);
    buffer = new AddressableLEDBuffer(length);
    led.setData(buffer);
    led.start();
  }

  @Override
  public void periodic() {  }

  @Override
  public void off() {
    setAllLEDs(0, 0, 0);
  }

  @Override
  public void setProcess(LEDProcess process) {
    switch (process) {
      case OFF:
        off();
        return;
      case DEFAULT:
        defaultColor();
        return;
      case RAINBOW:
        rainbow(false);
        return;
      case AUTONOMOUS:
        rainbow(true);
        return;
      case ALLIANCE_COLOR:
        allainceColor();
        return;
      default:
        setAllLEDs(process.getRed(), process.getGreen(), process.getBlue());
        return;
    }
  }

  private void setAllLEDs(int red, int green, int blue) {
    for (int i = 0; i < buffer.getLength(); i++) {
      buffer.setRGB(i, red, green, blue);
    }
  }

  private void defaultColor() {
    if (DriverStation.isAutonomous()) {
      setProcess(LEDProcess.AUTONOMOUS);
    } else {
      setProcess(LEDProcess.ALLIANCE_COLOR);
    }
  }

  private void allainceColor() {
    if (DriverStation.getAlliance() == DriverStation.Alliance.Red) {
      setProcess(LEDProcess.RED_ALLIANCE);
    } else {
      setProcess(LEDProcess.BLUE_ALLIANCE);
    }
  }

  private void rainbow(boolean wave) {
    for (int i = 0; i < buffer.getLength(); i++) {
      final int hue = (rainbowWaveOffset + (i * 180 / buffer.getLength())) % 180;
      buffer.setHSV(i, hue, 255, 128);
    }
    
    if (wave) {
      rainbowWaveOffset += 3;
      rainbowWaveOffset %= 180;
    }
  }
}
