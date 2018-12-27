package {{cookiecutter.package_name}}.command


import edu.wpi.first.wpilibj.command.Command
import {{cookiecutter.package_name}}.Robot
import org.strykeforce.thirdcoast.swerve.SwerveDrive.DriveMode.TELEOP
{% if cookiecutter.use_logger == 'y' %}
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
{% endif %}

{% if cookiecutter.use_driver_controls == 'y' -%}
private const val DEADBAND = 0.05
{% endif %}

private val swerve = Robot.DRIVE
{% if cookiecutter.use_driver_controls == 'y' -%}
private val controls = Robot.CONTROLS.driverControls
{% endif %}


class TeleOpDriveCommand : Command() {

    init {
        requires(swerve)
    }

    override fun initialize() = swerve.setDriveMode(TELEOP)

    override fun execute() {
{% if cookiecutter.use_driver_controls == 'y' -%}
        val forward = controls.forward.deadband()
        val strafe = controls.strafe.deadband()
        val azimuth = controls.yaw.deadband()
{% else %}
    // TODO: configure controls
{% if cookiecutter.use_logger=='y'-%}
        logger.error("controls are not configured - robot will not drive!")
{% else %}
        println("controls are not configured - robot will not drive!")
{% endif %}
        val forward = 0.0;
        val strafe = 0.0;
        val azimuth = 0.0;
{% endif %}
        swerve.drive(forward, strafe, azimuth)
    }

    override fun isFinished() = false

    override fun end() = swerve.drive(0.0, 0.0, 0.0)

{% if cookiecutter.use_driver_controls == 'y' -%}
    private fun Double.deadband() = if (Math.abs(this) < DEADBAND) 0.0 else this
{% endif %}
}
