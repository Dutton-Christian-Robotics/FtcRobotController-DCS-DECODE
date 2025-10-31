package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;


@Autonomous(name = "Red 1", group = "1", preselectTeleOp="TwoGamepadTeleOpMode")
public class Autonomous1OpMode extends LinearOpMode {
	public DecodeBot bot;

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

//	public abstract void setAlliance();

	public void driveWithTimeout(double y, double x, double h, int t1, int t2) {
		bot.driveToBotRelativePositionWithTimeout(y, x, h, t1, t2);
	}


	@Override
	public void runOpMode() {

		bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);
		bot.setUseDebugging(true);
		bot.abortOpMode = () -> isStopRequested();



//		setAlliance();
//		if (DefenderAlliance.getInstance().isRed()) {
//			telemetry.addData("Alliance", "RED");
//		} else if (DefenderAlliance.getInstance().isBlue()) {
//			telemetry.addData("Alliance", "BLUE");
//		} else {
//			telemetry.addData("Alliance", "unknown");
//		}
//		telemetry.update();
//
//		if (DefenderAlliance.getInstance().isRed()) {
//			bot.effects.scanRed();
//		} else if (DefenderAlliance.getInstance().isBlue()) {
//			bot.effects.scanBlue();
//		} else {
//			bot.effects.wavesParty();
//		}

		waitForStart();

//		bot.drive(-0.3, 0, 0);
//		sleep(800);
		bot.navigation.resetOtosAndResetOrigin();
		bot.driveToBotRelativePositionWithTimeout(-24, 0, 0, 750, 5000);
		bot.stopDriving();


		bot.shooter.turnOn();
		sleep(1000);
		bot.shooter.shoot();
		bot.intake.advanceCarousel();
		bot.shooter.shoot();
		bot.intake.advanceCarousel();
		bot.shooter.shoot();
		bot.intake.advanceCarousel();

		bot.navigation.resetOtosAndResetOrigin();

		bot.driveToBotRelativePositionWithTimeout(-10, 0, 0, 750, 3000);
		bot.navigation.resetOtosAndResetOrigin();

		bot.intake.turnOn();

		bot.driveToBotRelativePositionWithTimeout(0, 0, -140, 750, 3000);
		bot.navigation.resetOtosAndResetOrigin();



		bot.driveToBotRelativePositionWithTimeout(0, -22, 0, 750, 3000);

		bot.navigation.resetOtosAndResetOrigin();

		// Backup and intake the first line of balls
		bot.driveToBotRelativePositionWithTimeout(-28, 0, 0, 750, 3000);

		// Time to wait for balls to get in
		sleep(1700);

		// Strafe towards the goal
		bot.driveToBotRelativePositionWithTimeout(0, 31, 0, 750, 3000);

//		bot.intake.turnOff();
		bot.navigation.resetOtosAndResetOrigin();

		bot.driveToBotRelativePositionWithTimeout(0, 87, 0, 750, 3000);

		//
		bot.shooter.turnOn();
		bot.shooter.shoot();
		bot.intake.advanceCarousel();
		bot.shooter.shoot();
		bot.intake.advanceCarousel();
		bot.shooter.shoot();
		bot.shooter.turnOff();




//		bot.navigation.resetOtosAndResetOrigin();

//		bot.shooter.startShooter();


	}

}
