package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;


@Autonomous(name = "Blue 1", group = "1", preselectTeleOp="Driver Operated")
public class Blue1AutonomousOpMode extends DecodeAutonomousOpMode {

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
	};



	@Override
	public void performAutonomous() {

	// Backup from the goal

		bot.shooter.turnOn();
		if (bot.voltageSensor.getVoltage() < 12.7) {
			bot.shooter.changeShooterPower(1);
		}
		bot.driveToBotRelativePositionWithTimeout(-24, 0, 0, 750, 5000);

	// Shoot the three pre-loaded artifacts

		bot.shooter.shootAndUpdateArtifactCount();

//		if (!bot.isReadyToShoot()) {
			bot.intake.advanceCarouselUntilReady();
			bot.shooter.shootAndUpdateArtifactCount();
//		}

//		if (!bot.isReadyToShoot()) {
			bot.intake.advanceCarouselUntilReady();
			bot.shooter.shootAndUpdateArtifactCount();
//		}

		if (!bot.shooter.isReadyToShoot()) {
			bot.intake.advanceCarousel();
			bot.shooter.shootAndUpdateArtifactCount();
		}

	// Back-up farther from the goal to get into a better position to rotate and strafe

		bot.navigation.resetOtosAndResetOrigin();
		bot.driveToBotRelativePositionWithTimeout(-10, 0, 0, 750, 3000);
		bot.navigation.resetOtosAndResetOrigin();

	// Turn on the intake as soon as reasonable to give it time to spin up

		bot.intake.turnOn();

	// Rotate to be perpendicular with alliance wall

		bot.driveToBotRelativePositionWithTimeout(0, 0, 140, 750, 3000);
		bot.navigation.resetOtosAndResetOrigin();

	// Strafe away from the goal ("right" on blue) to align with first spike mark

		bot.driveToBotRelativePositionWithTimeout(0, 20, 0, 750, 3000);
		bot.navigation.resetOtosAndResetOrigin();

	// Backup and intake the first line of balls
		bot.driveToBotRelativePositionWithTimeout(-32, 0, 0, 750, 3000);

	// Time to wait for balls to get in
		sleep(1000);

	// Strafe towards the goal
		bot.intake.turnOff();
		if (bot.intake.areTooManyArtifactsLoaded()) {
			bot.intake.reverseCarousel();
			sleep(600);
			bot.intake.turnOff();
		}
		bot.intake.deAdvanceCarousel(0.2);
		bot.driveToBotRelativePositionWithTimeout(0, -31, 0, 750, 3000);

		bot.navigation.resetOtosAndResetOrigin();

	// Rotate to align with goal for shooting

		bot.driveToBotRelativePositionWithTimeout(0, 0, -145, 750, 3000);
		bot.navigation.resetOtosAndResetOrigin();
		bot.shooter.changeShooterPower(0.8);
//		bot.shooter.turnOn();
		bot.driveToBotRelativePositionWithTimeout(0, 6, 0, 750, 3000);

	// Shoot the additional three

		bot.shooter.shootAndUpdateArtifactCount();

//		if (!bot.isReadyToShoot()) {
			bot.intake.advanceCarouselUntilReady();
			bot.shooter.shootAndUpdateArtifactCount();
//		}

//		if (!bot.isReadyToShoot()) {
			bot.intake.advanceCarouselUntilReady();
			bot.shooter.shootAndUpdateArtifactCount();
//		}

		if (!bot.shooter.isReadyToShoot()) {
			bot.intake.advanceCarousel();
			bot.shooter.shootAndUpdateArtifactCount();
		}
		bot.shooter.turnOff();

		bot.shooter.lowerLift();
		sleep(500);


	}

}
