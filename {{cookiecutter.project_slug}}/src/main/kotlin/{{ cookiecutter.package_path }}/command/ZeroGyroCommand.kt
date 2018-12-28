package {{cookiecutter.package_name}}.command

import edu.wpi.first.wpilibj.command.InstantCommand
import {{cookiecutter.package_name}}.Robot

class ZeroGyroCommand : InstantCommand() {
    private val swerve = Robot.DRIVE

    init {
        requires(swerve)
    }

    override fun initialize() = swerve.resetGyro()
}