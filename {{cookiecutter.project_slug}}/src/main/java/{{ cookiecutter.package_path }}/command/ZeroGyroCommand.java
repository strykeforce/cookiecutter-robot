package {{cookiecutter.package_name}}.command;

import edu.wpi.first.wpilibj.command.InstantCommand;
import {{cookiecutter.package_name}}.Robot;
import {{cookiecutter.package_name}}.subsystem.DriveSubsystem;

public final class ZeroGyroCommand extends InstantCommand {

  private final static DriveSubsystem swerve = Robot.DRIVE;

  public ZeroGyroCommand() {
    requires(swerve);
  }

  @Override
  protected void initialize() {
    swerve.zeroGyro();
  }
}
