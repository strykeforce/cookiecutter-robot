package {{cookiecutter.package_name}}.command;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogCommand extends InstantCommand {

  private final Logger logger;
  private final String message;

  public LogCommand(Logger logger, String message) {
    this.logger = logger;
    this.message = message;
  }

  public LogCommand(String message) {
    this(LoggerFactory.getLogger(LogCommand.class), message);
  }

  @Override
  protected void initialize() {
    logger.info(message);
  }
}
