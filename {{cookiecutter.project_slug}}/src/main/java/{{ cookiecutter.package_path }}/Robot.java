package {{ cookiecutter.package_name }};

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {

  @Override
  public void robotInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
