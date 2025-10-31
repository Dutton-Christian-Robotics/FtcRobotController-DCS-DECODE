package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@TeleOp(name = "Driver Operated", group = "Driver")
public class TeleOpMode extends LinearOpMode {
	DecodeBot bot;
	public double currentShooterPower = 1;

//	DefenderDebouncer yDebouncer = new DefenderDebouncer(500, () -> {
//		motorBigWheel.setPower(-0.3);
//		sleep(500);
//		motorBigWheel.setPower(0);
//
//	});
//
//	DefenderDebouncer startShooterDebouncer = new DefenderDebouncer(500, () -> {
//		startShooter();
//	});
//
//	DefenderDebouncer stopShooterDebouncer = new DefenderDebouncer(500, () -> {
//		stopShooter();
//	});
//
	DefenderDebouncer shooterSpeedLowDebouncer = new DefenderDebouncer(500, () -> {
//		currentShooterPower = Math.max(0.1, currentShooterPower - 0.1);
		currentShooterPower = 0.8;
		bot.shooter.changeShooterPower(currentShooterPower);
//		startShooter();
	});

	DefenderDebouncer shooterSpeedFullDebouncer = new DefenderDebouncer(500, () -> {
//		currentShooterPower = Math.min(1, currentShooterPower + 0.1);
		currentShooterPower = 1;
		bot.shooter.changeShooterPower(currentShooterPower);
//		startShooter();
	});
//
//
//	DefenderDebouncer startIntakeDebouncer = new DefenderDebouncer(500, () -> {
//		startIntake();
//	});
//
//	DefenderDebouncer stopIntakeDebouncer = new DefenderDebouncer(500, () -> {
//		stopIntake();
//	});
//
//	DefenderDebouncer reverseIntakeDebouncer = new DefenderDebouncer(500, () -> {
//		reverseIntake();
//	});
//
//
//
	DefenderDebouncer shootDebouncer = new DefenderDebouncer(500, () -> {
		bot.shooter.shootAndUpdateArtifactCount();
	});
//
	DefenderDebouncer advanceCarouselDebouncer = new DefenderDebouncer(500, () -> {
		bot.intake.advanceCarousel();

	});


//	public void startIntake() {
//		setPower(1, -1, -1, 1, 1);
//		motorBigWheel.setPower(-0.3);
//	}
//
//	public void reverseIntake() {
//		setPower(-1, 1, 1, -1, -1);
//		motorBigWheel.setPower(0.3);
//	}
//
//	public void stopIntake() {
//		setPower(0, 0, 0, 0, 0);
//		motorBigWheel.setPower(0);
//
//	}

//	public void startShooter() {
//		motorShooterLeft.setPower(currentShooterPower);
//		motorShooterRight.setPower(currentShooterPower);
//	}
//
//	public void reverseShooter() {
//		motorShooterLeft.setPower(-1 * currentShooterPower);
//		motorShooterRight.setPower(-1 * currentShooterPower);
//	}
//
//	public void stopShooter() {
//		motorShooterLeft.setPower(0);
//		motorShooterRight.setPower(0);
//	}

//	public void shoot() {
//		servoLiftLeft.setPosition(1);
//		servoLiftRight.setPosition(0);
//		sleep(1000);
//		servoLiftLeft.setPosition(0);
//		servoLiftRight.setPosition(1);
//
//	}


//	public void setPower(double ul, double ur, double ml, double mr, double l) {
//		servoUpperLeft.setPower(ul);
//		servoUpperRight.setPower(ur);
//		servoMiddleLeft.setPower(ml);
//		servoMiddleRight.setPower(mr);
//		servoLower.setPower(l);
//	}
	@Override
	public void runOpMode() {

		bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);
		bot.intake.startMonitoringCapture();
		bot.intake.setNumberOfArtifactsLoaded(0);

		waitForStart();

		while (opModeIsActive()) {
//			if (gamepad2.dpad_up) {
			if (gamepad2.left_stick_y != 0 || gamepad2.left_stick_x != 0) {
				bot.intake.turnOn();
//				startIntakeDebouncer.run();

			} else if (gamepad2.xWasPressed()) {
				bot.intake.reverse();

			} else if (gamepad2.right_stick_y != 0 || gamepad2.right_stick_x != 0) {
				bot.intake.turnOff();
			}

			if (gamepad2.left_trigger > 0) {
				advanceCarouselDebouncer.run();
			}

			if (gamepad2.right_trigger > 0) {
				shootDebouncer.run();
			}

			if (gamepad2.bWasPressed()) {
				bot.shooter.turnOff();
			}

			if (gamepad2.leftBumperWasPressed()) {
				shooterSpeedLowDebouncer.run();
			} else if (gamepad2.right_bumper) {
				shooterSpeedFullDebouncer.run();
			}

			if (gamepad2.yWasPressed()) {
				advanceCarouselDebouncer.run();
//				yDebouncer.run();
			}

			bot.drivetrain.drive(gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.right_stick_x);

			telemetry.addData("Shooter Power", currentShooterPower);
			telemetry.addData("Artifacts", bot.intake.numberOfArtifactsLoaded);
			telemetry.addData("Color", bot.shooter.readyArtifactColor());
			telemetry.update();

		}


	}

}