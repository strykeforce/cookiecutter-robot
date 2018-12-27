package {{cookiecutter.package_name}}.command

import edu.wpi.first.wpilibj.command.InstantCommand
import mu.KotlinLogging
import org.slf4j.Logger

class LogCommand(private val logger: Logger, private val message: String) : InstantCommand() {

    constructor(message: String) : this(KotlinLogging.logger {}, message) {}

    override fun initialize() {
        logger.info(message)
    }
}
