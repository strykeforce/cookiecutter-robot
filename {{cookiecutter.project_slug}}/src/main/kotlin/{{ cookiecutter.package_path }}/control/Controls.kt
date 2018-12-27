package {{cookiecutter.package_name}}.control

class Controls {

{% if cookiecutter.use_driver_controls == 'y' %}
    val driverControls = DriverControls(0)
{% endif %}

{% if cookiecutter.use_game_controls == 'y' %}
    val gameControls = GameControls(1)
{% endif %}

}
