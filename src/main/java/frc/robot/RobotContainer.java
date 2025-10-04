// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.MotorConstants;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  TalonFX motor = new TalonFX(31);
  CommandXboxController controller = new CommandXboxController(0);
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {


    /*
     * Controller Choices:
     *
     * controller.a()
     * controller.b()
     * controller.x()
     * controller.y()
     *
     * controller.povUp()
     * controller.povRight()
     * controller.povDown()
     * controller.povLeft()
     *
     * controller.leftBumper()
     * controller.rightBumper()
     *
     * controller.leftTrigger( *triggerThreshold* )
     * controller.rightTrigger( *triggerThreshold* )
     *
     * controller.button().onTrue( *function* )
     * controller.button().whileTrue( *function* )
     * controller.button().onFalse( *function* )
     * controller.button().whileFalse( *function* )
    */

    /*
     * Motor Choices
     *
     * Simple
     *
     * motor.set( *speed* )
     * motor.setInverted( *isInverted* )
     *
     *
     * Advanced
     * 
     * motor.setNeutralMode(NeutralModeValue. *value* )
     * motor.setControl(new Follower( *motorID*, *inverted* ))
     *
    */

    controller.a().onTrue(
      new InstantCommand(() -> {
        motor.set(MotorConstants.onTrueSpeed);
      })
    ).onFalse(
      new InstantCommand(() -> {
        motor.set(MotorConstants.onFalseSpeed);
      })
    );


  }

  public Command getAutonomousCommand() {
    return null;
  }
}
