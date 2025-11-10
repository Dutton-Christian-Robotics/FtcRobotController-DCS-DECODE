package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

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

		DecodeConfiguration.SHOOTER_LIFT_TIME_SLEEP = 325;
		DecodeConfiguration.SHOOTER_TIME_BETWEEN_SHOTS = 10;
		DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_POWER_ADVANCE = 0.5;
		DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_TIME_ADVANCE = 400;

		DecodeConfiguration.NAVIGATION_X_MAXPOWER = 0.8;
		DecodeConfiguration.NAVIGATION_Y_MAXPOWER = 0.5;
		DecodeConfiguration.NAVIGATION_R_MAXPOWER = 0.6;

		DecodeConfiguration.NAVIGATION_TOLERANCE_Y = 4;
		DecodeConfiguration.NAVIGATION_Y_KP = 0.4;
		DecodeConfiguration.NAVIGATION_Y_KI = 0.0;
		DecodeConfiguration.NAVIGATION_Y_KD = 0.06;

		DecodeConfiguration.NAVIGATION_X_KP = 0.2;
		DecodeConfiguration.NAVIGATION_X_KI = 0.4;
		DecodeConfiguration.NAVIGATION_X_KD = 0.049;

		DecodeConfiguration.NAVIGATION_R_KP = 0.3;
		DecodeConfiguration.NAVIGATION_R_KI = 0.0;
		DecodeConfiguration.NAVIGATION_R_KD = 0.02;

		bot.shooter.turnOn();
		bot.shooter.changeShooterPower(0.8);

	// Shoot the three pre-loaded artifacts

		bot.shooter.shootAndUpdateArtifactCount();

		bot.intake.advanceCarouselUntilReady();
		bot.shooter.shootAndUpdateArtifactCount();

		sleep(300);

		bot.intake.advanceCarouselUntilReady();
		bot.shooter.shootAndUpdateArtifactCount();

		if (!bot.shooter.isReadyToShoot()) {
			bot.intake.advanceCarousel();
			bot.shooter.shootAndUpdateArtifactCount();
		}

	// Back-up from the goal to get into a better position to rotate and strafe
		bot.driveToBotRelativePositionWithTimeout(-34, 0, 0, 750, 5000);
		bot.navigation.resetOtosAndResetOrigin();

	// Turn on the intake as soon as reasonable to give it time to spin up

		bot.intake.turnOn();

	// Rotate to be perpendicular with alliance wall

		bot.driveToBotRelativePositionWithTimeout(0, 0, 135, 750, 3000); // used to be 130
		bot.navigation.resetOtosAndResetOrigin();

	// Strafe away from the goal ("right" on blue) to align with first spike mark

		bot.driveToBotRelativePositionWithTimeout(0, 17, 0, 750, 3000); // used to be 18
		bot.navigation.resetOtosAndResetOrigin();

	// Backup and intake the first line of balls
		bot.driveToBotRelativePositionWithTimeout(-32, 0, 0, 750, 3000); // used to be -34, then -33

	// Time to wait for balls to get in

		sleep(900); // used to be 1000

	// Strafe towards the goal
		bot.intake.turnOff();
		if (bot.intake.areTooManyArtifactsLoaded()) {
			bot.intake.reverseCarousel();
			sleep(600);
			bot.intake.turnOff();
		}
		bot.intake.deAdvanceCarousel(0.65);
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

		bot.intake.advanceCarouselUntilReady();

		bot.shooter.shootAndUpdateArtifactCount();

		bot.intake.advanceCarouselUntilReady();

		bot.shooter.shootAndUpdateArtifactCount();

		if (!bot.shooter.isReadyToShoot()) {
			bot.intake.advanceCarousel();
			bot.shooter.shootAndUpdateArtifactCount();
		}
		bot.driveToBotRelativePositionWithTimeout(0, -14, 0, 750, 1000);
		bot.shooter.turnOff();

		bot.shooter.lowerLift();
		sleep(500);


	}

}
