package {{cookiecutter.package_name}}.control;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
{% if cookiecutter.use_logger == 'y' -%}
import {{cookiecutter.package_name}}.command.LogCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
{% else %}
import edu.wpi.first.wpilibj.command.PrintCommand;
{% endif %}

/** This assumes the use of a Logitech F310 controller. */
@SuppressWarnings("unused")
public class GameControls {

  private final Joystick joystick;

{% if cookiecutter.use_logger == 'y' -%}
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
{% endif %}


  public GameControls(int port) {
    joystick = new Joystick(port);
  }

  private <E extends Enum<E>> Command log(E control) {
{% if cookiecutter.use_logger == 'y' -%}    
    return new LogCommand(logger, control.toString());
{% else -%}
    return new PrintCommand(control.toString());
{% endif -%}
  }


  public enum Axis {
    LEFT_X(0),
    LEFT_Y(0),
    RIGHT_X(0),
    RIGHT_Y(0),
    TUNER(0),
    LEFT_TRIGGER(0),
    RIGHT_TRIGGER(0);

    private final int id;

    Axis(int id) {
      this.id = id;
    }
  }

  public enum Shoulder {
    LEFT(0),
    RIGHT(0);

    private final int id;

    Shoulder(int id) {
      this.id = id;
    }
  }

  public enum Button {
    A(0),
    B(0),
    X(0),
    Y(0),
    START(0),
    BACK(0);

    private final int id;

    Button(int id) {
      this.id = id;
    }
  }

  public enum DPad {
    UP(0),
    DOWN(0),
    LEFT(0),
    RIGHT(0);

    private final int id;

    DPad(int id) {
      this.id = id;
    }
  }

}
