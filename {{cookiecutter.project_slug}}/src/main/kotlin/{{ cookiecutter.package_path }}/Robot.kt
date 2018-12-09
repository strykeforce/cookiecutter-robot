package {{ cookiecutter.package_name }}

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler

class Robot : TimedRobot() {

    override fun robotInit() {
    }

    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
    }
}
