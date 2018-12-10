package {{ cookiecutter.package_name }}

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
{% if cookiecutter.use_logger == 'y' %}
import mu.KotlinLogging
import java.util.Date

private val logger = KotlinLogging.logger {}
{% endif %}

class Robot : TimedRobot() {

    override fun robotInit() {
    {% if cookiecutter.use_logger == 'y' %}
        logger.info { "Today is ${Date()}" }
    {% endif %}
    }

    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
    }
}
