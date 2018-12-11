package {{cookiecutter.package_name}}.command;

import edu.wpi.first.wpilibj.command.Command;
import {{cookiecutter.package_name}}.Robot;
import {{cookiecutter.package_name}}.subsystem.DriveSubsystem;
{% if cookiecutter.use_logger=='y'-%}
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
{% endif -%}

import static org.strykeforce.thirdcoast.swerve.SwerveDrive.DriveMode.TELEOP;

public final class TeleOpDriveCommand extends Command {
{% if cookiecutter.use_logger=='y'-%}
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
{% endif %}

  private final DriveSubsystem drive = Robot.DRIVE;

  public TeleOpDriveCommand() {
    requires(drive);
  }

  @Override
  protected void initialize() {
    drive.setDriveMode(TELEOP);
  }

  @Override
  protected void execute() {
    // TODO: configure controls
{% if cookiecutter.use_logger=='y'-%}
    logger.error("controls are not configured - robot will not drive!");
{% else %}
    System.out.println("controls are not configured - robot will not drive!");
{% endif %}
    double forward = 0.0;
    double strafe = 0.0;
    double azimuth = 0.0;
    drive.drive(forward, strafe, azimuth);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    drive.drive(0.0, 0.0, 0.0);
  }
}
