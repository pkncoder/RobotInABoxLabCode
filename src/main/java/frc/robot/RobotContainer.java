// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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


    controller.a().onTrue(
      new InstantCommand(() -> {motor.set(1.0);})
    ).onFalse(
      new InstantCommand(() -> {motor.set(0.0);})
    );


  }

  public Command getAutonomousCommand() {
    return null;
  }
}
