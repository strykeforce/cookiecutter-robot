package {{cookiecutter.package_name}}.control;

public class Controls {

{% if cookiecutter.use_driver_controls == 'y' %}
  private final DriverControls driverControls = new DriverControls(0);

  public DriverControls getDriverControls() {
    return driverControls;
  }
{% endif %}

{% if cookiecutter.use_game_controls == 'y' %}
  private final GameControls gameControls = new GameControls(1);

  public GameControls getGameControls() {
    return gameControls;
  }
{% endif %}

}
