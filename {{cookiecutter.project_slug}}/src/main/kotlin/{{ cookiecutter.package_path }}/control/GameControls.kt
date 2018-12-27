package {{cookiecutter.package_name}}.control

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.command.Command
{% if cookiecutter.use_logger == 'y' -%}
import {{cookiecutter.package_name}}.command.LogCommand
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
{% else %}
import edu.wpi.first.wpilibj.command.PrintCommand
{% endif %}

/** This assumes the use of a Logitech F310 controller.  */
class GameControls(port: Int) {

    private val joystick: Joystick = Joystick(port)

    private fun <E : Enum<E>> log(control: E): Command {
{% if cookiecutter.use_logger == 'y' -%}    
    return LogCommand(logger, "$control")
{% else -%}
    return PrintCommand("$control")
{% endif -%}
    }


    enum class Axis private constructor(val id: Int) {
        LEFT_X(0),
        LEFT_Y(0),
        RIGHT_X(0),
        RIGHT_Y(0),
        TUNER(0),
        LEFT_TRIGGER(0),
        RIGHT_TRIGGER(0)
    }

    enum class Shoulder private constructor(val id: Int) {
        LEFT(0),
        RIGHT(0)
    }

    enum class Button private constructor(val id: Int) {
        A(0),
        B(0),
        X(0),
        Y(0),
        START(0),
        BACK(0)
    }

    enum class DPad private constructor(val id: Int) {
        UP(0),
        DOWN(0),
        LEFT(0),
        RIGHT(0)
    }

}
