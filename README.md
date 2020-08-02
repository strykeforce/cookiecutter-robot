# Deprecated

This project is deprecated and no longer updated for FRC seasons past 2019. Use the WPILib new project creator installed by [WPILib](https://github.com/wpilibsuite/allwpilib/releases).

# Original README
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

**Note:** Kotlin cookiecutters are not yet updated for 2019 season.

## Hardware Assumptions

The template generates a robot project that assumes the following hardware is in use.

**NOTE:** Do not assume PID parameters will work for your hardware. You must test and tune as needed.

-   [Third Coast Swerve Drive](https://www.strykeforce.org/resources/Mechanical_Design_Description_of_Stryke_Force_Swerve_Drive_Units.pdf)
-   Interlink X Flight Simulator driver controls on USB port 0 of Driver Station
-   Logitech F310 Game controls on USB port 1 of Driver Station



## Quickstart

You must have Python and Pip installed. Install the latest Cookiecutter if you haven't yet:

    pip install -U cookiecutter

Generate a FRC robot project:

    cookiecutter gh:strykeforce/cookiecutter-robot

Then:

-   Format source code: `./gradlew spotlessApply` (if Spotless code formatting enabled)
-   Build project: `./gradlew build`
-   Deploy to robot: `./gradlew deploy`

## License

This project is licensed under the terms of the [MIT License](/LICENSE)
