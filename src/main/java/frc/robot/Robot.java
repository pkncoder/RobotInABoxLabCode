package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  private TalonFX boxMotor = new TalonFX(1);

  public Robot() {
    m_robotContainer = new RobotContainer();
    boxMotor.setPosition(0.0);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // Initalize the limelight helpers class
    LimelightHelpers limelightHelpers = new LimelightHelpers();

    // Put the 't' values to smartdashboard
    SmartDashboard.putNumber("Limelight TX", limelightHelpers.getTx());
    SmartDashboard.putNumber("Limelight TY", limelightHelpers.getTy());
    SmartDashboard.putNumber("Limelight TA", limelightHelpers.getTa());
    SmartDashboard.putBoolean("Limelight Has Target", limelightHelpers.hasTarget());

    // Put the distance to smartdashboard
    SmartDashboard.putNumber("April Tag Distance", limelightHelpers.getDistance());

    
    // Get the box motor's encoder value, and put it to smartdashboard
    double boxMotorEncoderValue = boxMotor.getPosition().getValueAsDouble();
    SmartDashboard.putNumber("Box Motor Encoder Value", boxMotorEncoderValue);



    // -----------------------------------------------------------------------------------------------------



    // Get the angle offset from the limelight helpers
    double targetAngleDegrees = limelightHelpers.getAprilTagAngleOffsetX();

    // Get the motor degree value
    double motorRotations = boxMotor.getPosition().getValueAsDouble();
    double motorDegrees = motorRotations * 360.0;

    // Calculate the degree error
    double error = targetAngleDegrees - motorDegrees;

    // Get the speed
    double kP = 0.0007; // The P in PID 
    double motorSpeed = kP * error;

    // Clamp for safety
    motorSpeed = Math.max(Math.min(motorSpeed, 0.03), -0.03);

    // Set the speed of the box motor, but only if the limelight has a target
    boxMotor.set(limelightHelpers.hasTarget() ? motorSpeed : 0.0);



    // -----------------------------------------------------------------------------------------------------


    
    // Output debug information about the limelight targeting
    SmartDashboard.putNumber("Target Angle (deg)", targetAngleDegrees);
    SmartDashboard.putNumber("Motor Angle (deg)", motorDegrees);
    SmartDashboard.putNumber("Error (deg)", error);
    SmartDashboard.putNumber("Motor Output", motorSpeed);

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}

