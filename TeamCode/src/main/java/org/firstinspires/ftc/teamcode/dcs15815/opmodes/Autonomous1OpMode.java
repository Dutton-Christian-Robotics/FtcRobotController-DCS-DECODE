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
		bot.driveToBotRelativePositionWithTimeout(-18, 0, 0, 750, 5000);
		bot.stopDriving();


		bot.shooter.turnOn();
		sleep(1000);
		bot.shooter.shoot();
		bot.intake.advanceCarousel();
		bot.shooter.shoot();
		bot.intake.advanceCarousel();
		bot.shooter.shoot();
		bot.intake.advanceCarousel();

		bot.drive(-0.3, 0, 0);
		sleep(1200);
		bot.stopDriving();
		bot.drive(0, 0, -0.3);
		sleep(1700);
		bot.stopDriving();
		bot.drive(0, -0.3, 0);
		sleep(1350);
		bot.stopDriving();

		bot.intake.turnOn();
		bot.drive(-0.3, 0, 0);
		sleep(1800);
		bot.stopDriving();
		sleep(1000);
//		stopIntake();


		// Drive forward away from
		bot.drive(0.3, 0, 0);
		sleep (1700);
		bot.stopDriving();

		bot.drive(0, 0, 0.3);
		sleep(1700);
		bot.stopDriving();
		bot.drive(0.3, 0, 0);
		sleep(1700);
		bot.stopDriving();
		bot.intake.turnOff();

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
