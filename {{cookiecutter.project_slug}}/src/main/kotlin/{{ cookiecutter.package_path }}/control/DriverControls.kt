package {{cookiecutter.package_name}}.control

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import edu.wpi.first.wpilibj.command.Command
import {{cookiecutter.package_name}}.command.LogCommand
{% if cookiecutter.use_thirdcoast_swerve=='y'-%}
import {{cookiecutter.package_name}}.command.ZeroGyroCommand
{% endif %}
{% if cookiecutter.use_logger == 'y' %}
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
{% else %}
import edu.wpi.first.wpilibj.command.PrintCommand
{% endif %}


/** This assumes the use of an Interlink X Flight Simulator controller.  */
class DriverControls internal constructor(port: Int) {

    private val joystick: Joystick = Joystick(port)

    /** Left stick X (up-down) axis.  */
    val forward: Double
        get() = -joystick.getRawAxis(Axis.LEFT_X.id)

    /** Left stick Y (left-right) axis.  */
    val strafe: Double
        get() = joystick.getRawAxis(Axis.LEFT_Y.id)

    /** Right stick Y (left-right) axis.  */
    val yaw: Double
        get() = joystick.getRawAxis(Axis.RIGHT_Y.id)

    /** Tuner knob.  */
    val tuner: Double
        get() = joystick.getRawAxis(Axis.TUNER.id)

    /** Left slider on back of controller.  */
    val leftBackAxis: Double
        get() = joystick.getRawAxis(Axis.LEFT_BACK.id)

    /** Right slider on back of controller.  */
    val rightBackAxis: Double
        get() = joystick.getRawAxis(Axis.RIGHT_BACK.id)


    init {
        // Shoulder switches
        JoystickButton(joystick, Shoulder.LEFT_DOWN.id).whenPressed(log(Shoulder.LEFT_DOWN))
        JoystickButton(joystick, Shoulder.LEFT_UP.id).whenPressed(log(Shoulder.LEFT_UP))
        JoystickButton(joystick, Shoulder.RIGHT_DOWN.id).whenPressed(log(Shoulder.RIGHT_DOWN))

        // Push-buttons
{% if cookiecutter.use_thirdcoast_swerve=='y'-%}
        JoystickButton(joystick, Button.RESET.id).whenPressed(ZeroGyroCommand())
{% else %}
        JoystickButton(joystick, Button.RESET.id).whenPressed(log(Button.RESET))
{% endif %}
        JoystickButton(joystick, Button.HAMBURGER.id).whenPressed(log(Button.HAMBURGER))
        JoystickButton(joystick, Button.X.id).whenPressed(log(Button.X))
        JoystickButton(joystick, Button.UP.id).whenPressed(log(Button.UP))
        JoystickButton(joystick, Button.DOWN.id).whenPressed(log(Button.DOWN))

        // Trim Switches
        JoystickButton(joystick, Trim.LEFT_X_POS.id).whenPressed(log(Trim.LEFT_X_POS))
        JoystickButton(joystick, Trim.LEFT_X_NEG.id).whenPressed(log(Trim.LEFT_X_NEG))
        JoystickButton(joystick, Trim.LEFT_Y_POS.id).whenPressed(log(Trim.LEFT_Y_POS))
        JoystickButton(joystick, Trim.LEFT_Y_NEG.id).whenPressed(log(Trim.LEFT_Y_NEG))
        JoystickButton(joystick, Trim.RIGHT_X_POS.id).whenPressed(log(Trim.RIGHT_X_POS))
        JoystickButton(joystick, Trim.RIGHT_X_NEG.id).whenPressed(log(Trim.RIGHT_X_NEG))
        JoystickButton(joystick, Trim.RIGHT_Y_POS.id).whenPressed(log(Trim.RIGHT_Y_POS))
        JoystickButton(joystick, Trim.RIGHT_Y_NEG.id).whenPressed(log(Trim.RIGHT_Y_NEG))
    }

    private fun <E : Enum<E>> log(control: E): Command {
{% if cookiecutter.use_logger == 'y' -%}    
    return LogCommand(logger, "$control")
{% else -%}
    return PrintCommand("$control")
{% endif -%}
    }

    enum class Axis private constructor(val id: Int) {
        RIGHT_X(1),
        RIGHT_Y(0),
        LEFT_X(2),
        LEFT_Y(5),
        TUNER(6),
        LEFT_BACK(4),
        RIGHT_BACK(3)
    }

    enum class Shoulder private constructor(val id: Int) {
        RIGHT_DOWN(2),
        LEFT_DOWN(4),
        LEFT_UP(5)
    }

    enum class Button private constructor(val id: Int) {
        RESET(3),
        HAMBURGER(14),
        X(15),
        UP(16),
        DOWN(17)
    }

    enum class Trim private constructor(val id: Int) {
        LEFT_Y_POS(7),
        LEFT_Y_NEG(6),
        LEFT_X_POS(8),
        LEFT_X_NEG(9),
        RIGHT_X_POS(10),
        RIGHT_X_NEG(11),
        RIGHT_Y_POS(12),
        RIGHT_Y_NEG(13)
    }
}
