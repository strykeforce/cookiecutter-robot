package {{ cookiecutter.package_name }}

import edu.wpi.first.wpilibj.RobotBase

/**
  * Main initialization function. Do not perform any initialization here.
  *
  * <p>If you change your main robot class, change the parameter type.
  */
fun main(args : Array<String>) {
  RobotBase.startRobot(::Robot)
}