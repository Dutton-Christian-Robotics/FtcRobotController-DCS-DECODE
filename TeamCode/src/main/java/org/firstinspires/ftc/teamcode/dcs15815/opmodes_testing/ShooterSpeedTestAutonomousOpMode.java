package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeShooter;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.DecodeAutonomousOpMode;

@Disabled
@Autonomous(name = "Shooter Speed Test", group = "Testing")
public class ShooterSpeedTestAutonomousOpMode extends DecodeAutonomousOpMode {

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
	};


	@Override
	public void performAutonomous() {

		DecodeConfiguration.SHOOTER_LIFT_TIME_SLEEP = 325;
		DecodeConfiguration.SHOOTER_TIME_BETWEEN_SHOTS = 10;
		DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_POWER_ADVANCE = 0.6;
		DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_TIME_ADVANCE = 400;

		bot.useSpeech = false;
		bot.shooter.turnOn();
		bot.shooter.changeShooterPower(0.8);

//		bot.shooter.shootRight();


		bot.shooter.shootAndUpdateArtifactCount();

		bot.intake.advanceCarouselUntilReady();
		bot.shooter.shootAndUpdateArtifactCount();

		bot.intake.advanceCarouselUntilReady();

		bot.shooter.shootAndUpdateArtifactCount();

		if (!bot.shooter.isReadyToShoot()) {
			bot.intake.advanceCarousel();
			bot.shooter.shootAndUpdateArtifactCount();
		}

		bot.shooter.turnOff();
		sleep(2000);



	}

}
