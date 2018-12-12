package {{ cookiecutter.package_name }};

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
{% if cookiecutter.use_thirdcoast_swerve=='y'-%}
import {{cookiecutter.package_name}}.subsystem.DriveSubsystem;
{% endif -%}
{% if cookiecutter.use_logger == 'y' -%}
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
{% endif -%}
{% if cookiecutter.use_thirdcoast_telemetry == 'y' -%}
import org.strykeforce.thirdcoast.telemetry.TelemetryController;
import org.strykeforce.thirdcoast.telemetry.TelemetryService;
{% endif -%}
import java.util.Date;

public class Robot extends TimedRobot {
{% if cookiecutter.use_thirdcoast_swerve == 'y' -%}
  public static final DriveSubsystem DRIVE = new DriveSubsystem();
{% endif %}
{% if cookiecutter.use_thirdcoast_telemetry == 'y' -%}
  public static final TelemetryService TELEMETRY = new TelemetryService(TelemetryController::new);
{% endif %}
{% if cookiecutter.use_logger == 'y' -%}
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
{% endif %}

  @Override
  public void robotInit() {
  {% if cookiecutter.use_logger == 'y' -%}
    logger.info("Today is {}", new Date());
  {% else -%}
    System.out.println("Today is " + new Date().toString());
  {% endif -%}
  {% if cookiecutter.use_thirdcoast_telemetry == 'y' -%}
    TELEMETRY.start();
  {% endif -%}
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

}
