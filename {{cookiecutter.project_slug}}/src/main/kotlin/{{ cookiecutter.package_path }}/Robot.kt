package {{ cookiecutter.package_name }}

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
{% if cookiecutter.use_thirdcoast_swerve=='y'-%}
import {{cookiecutter.package_name}}.subsystem.DriveSubsystem
{% endif -%}
{% if cookiecutter.use_thirdcoast_telemetry == 'y' -%}
import org.strykeforce.thirdcoast.telemetry.TelemetryController
import org.strykeforce.thirdcoast.telemetry.TelemetryService
import java.util.*
import java.util.function.Function
{% endif -%}
import java.util.Date

{% if cookiecutter.use_driver_controls == 'y' or cookiecutter.use_driver_controls == 'y' -%}
import {{cookiecutter.package_name}}.control.Controls
{% endif %}

{% if cookiecutter.use_logger == 'y' %}
import mu.KotlinLogging


private val logger = KotlinLogging.logger {}
{% endif %}

class Robot : TimedRobot() {

    override fun robotInit() {
    {% if cookiecutter.use_logger == 'y' %}
        logger.info { "Today is ${Date()}" }
    {% else %}
        println("Today is ${Date()}")
    {% endif %}
    }

    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
    }

    companion object {
{% if cookiecutter.use_thirdcoast_telemetry == 'y' %}
        // Instantiate this before Subsystems because they use telemetry service.
        val TELEMETRY = TelemetryService(Function { TelemetryController(it) })
{% endif %}
{% if cookiecutter.use_thirdcoast_swerve=='y'%}
        val DRIVE = DriveSubsystem()
{% endif %}
{% if cookiecutter.use_driver_controls == 'y' or cookiecutter.use_driver_controls == 'y' %}
        // Controls initialize Commands so this should be instantiated last to prevent
        // NullPointerExceptions in commands that require() Subsystems above.
        val CONTROLS = Controls()
{% endif %}
    }

}
