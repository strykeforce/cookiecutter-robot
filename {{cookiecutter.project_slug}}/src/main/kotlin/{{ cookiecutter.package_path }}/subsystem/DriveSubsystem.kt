package {{cookiecutter.package_name}}.subsystem

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.SPI
import edu.wpi.first.wpilibj.command.Subsystem
import {{cookiecutter.package_name}}.Robot
import {{cookiecutter.package_name}}.command.TeleOpDriveCommand
import org.strykeforce.thirdcoast.swerve.SwerveDrive
import org.strykeforce.thirdcoast.swerve.SwerveDrive.DriveMode
import org.strykeforce.thirdcoast.swerve.SwerveDriveConfig
import org.strykeforce.thirdcoast.swerve.Wheel
{% if cookiecutter.use_logger == 'y' %}
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
{% endif %}


private const val DRIVE_SETPOINT_MAX = 0.0
private const val ROBOT_LENGTH = 1.0
private const val ROBOT_WIDTH = 1.0

class DriveSubsystem : Subsystem() {

    private val swerve = swerveDrive()

    override fun initDefaultCommand() {
        defaultCommand = TeleOpDriveCommand()
    }

    fun setDriveMode(mode: DriveMode) = swerve.setDriveMode(mode)

    fun drive(forward: Double, strafe: Double, azimuth: Double) = swerve.drive(forward, strafe, azimuth)

}

private fun swerveDrive() = SwerveDrive(SwerveDriveConfig().apply {
    wheels = wheels()
    gyro = AHRS(SPI.Port.kMXP)
    length = ROBOT_LENGTH
    width = ROBOT_WIDTH
    gyroLoggingEnabled = true
    summarizeTalonErrors = false
})

private fun wheels(): Array<Wheel> {
    val azimuthConfig = TalonSRXConfiguration().apply {
        primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative
        continuousCurrentLimit = 10
        peakCurrentLimit = 0
        peakCurrentDuration = 0
        slot_0.apply {
            kP = 10.0
            kI = 0.0
            kD = 100.0
            kF = 1.0
            integralZone = 0
            allowableClosedloopError = 0
        }
        motionAcceleration = 10_000
        motionCruiseVelocity = 800
    }


    val driveConfig = TalonSRXConfiguration().apply {
        primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative
        continuousCurrentLimit = 40
        peakCurrentLimit = 0
        peakCurrentDuration = 0
    }
{% if cookiecutter.use_thirdcoast_telemetry == 'y' -%}
    val telemetryService = Robot.TELEMETRY
    telemetryService.stop()
{% endif %}
    val timeout = 10

    return Array(4) {
        Wheel(
            TalonSRX(it).apply {
                configAllSettings(azimuthConfig, timeout)
{% if cookiecutter.use_thirdcoast_telemetry == 'y' -%}                
                telemetryService.register(this)
{%endif %}
            },
            TalonSRX(it + 10).apply {
                configAllSettings(driveConfig, timeout)
                setNeutralMode(NeutralMode.Brake)
{% if cookiecutter.use_thirdcoast_telemetry == 'y' -%}
                telemetryService.register(this)
{% endif %}
            }, DRIVE_SETPOINT_MAX
        )
    }

}
