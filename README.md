# cookiecutter-robot

A [cookiecutter](https://github.com/audreyr/cookiecutter) template to create a new FRC robot project in Java or Kotlin. With the right hardware, this will produce a working swerve-drive robot.

See examples of usage in [thirdcoast-examples](https://github.com/strykeforce/thirdcoast-examples) repo and other resources at [strykeforce.org](https://strykeforce.org/resources/).

## Features

All features can be optionally enabled.

-   Configure team number, robot name and package name
-   Select Java or Kotlin for implementation
-   [Third Coast Swerve Drive](https://github.com/strykeforce/thirdcoast)
-   [Third Coast Telemetry](https://github.com/strykeforce/thirdcoast)
-   Interlink X Flight Simulator driver controls
-   Logitech F310 Game controls
-   [Logback logging](https://logback.qos.ch)
-   [Spotless code formatting](https://github.com/diffplug/spotless) configured for Google java formatting standard

**Note:** Kotlin cookiecutters are not complete yet.

## Quickstart

Install the latest Cookiecutter if you haven't yet:

    pip install -U cookiecutter

Generate a FRC robot project:

    cookiecutter gh:strykeforce/cookiecutter-robot

Then:

-   Format source code: `./gradlew spotlessApply`
-   Build project: `./gradlew build`
-   Deploy to robot: `./gradlew deploy`

## License

This project is licensed under the terms of the [MIT License](/LICENSE)
