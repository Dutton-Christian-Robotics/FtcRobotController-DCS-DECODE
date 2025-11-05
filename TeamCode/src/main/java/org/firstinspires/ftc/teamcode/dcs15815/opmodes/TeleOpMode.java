package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@TeleOp(name = "Driver Operated", group = "Driver")
public class TeleOpMode extends LinearOpMode {
	DecodeBot bot;
	public double currentShooterPower = 1;
	public boolean changeConfiguration = false;
	public boolean autoAdvanceCarousel = true;

	DefenderDebouncer shooterSpeedLowDebouncer = new DefenderDebouncer(500, () -> {
		currentShooterPower = 0.8;
		bot.shooter.changeShooterPower(currentShooterPower);
	});

	DefenderDebouncer shooterSpeedFullDebouncer = new DefenderDebouncer(500, () -> {
		currentShooterPower = 1;
		bot.shooter.changeShooterPower(currentShooterPower);
	});

	DefenderDebouncer shootDebouncer = new DefenderDebouncer(500, () -> {
		bot.shooter.shootAndUpdateArtifactCount(autoAdvanceCarousel);
	});

	DefenderDebouncer advanceCarouselDebouncer = new DefenderDebouncer(500, () -> {
		bot.intake.advanceCarousel();

	});



	@Override
	public void runOpMode() {

		bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);
		bot.intake.startMonitoringCapture();
		bot.intake.setNumberOfArtifactsLoaded(0);
		bot.effects.startLiveStatus();


		waitForStart();

		while (opModeIsActive()) {

			if (gamepad2.left_stick_y != 0 || gamepad2.left_stick_x != 0) {
				bot.intake.turnOn();


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

			if (changeConfiguration) {
				if (gamepad2.aWasPressed()) {
					DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.RED);

				} else if (gamepad2.bWasPressed()) {
					DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);

				}

				if (gamepad2.yWasPressed()) {
					bot.useSpeech = !bot.useSpeech;
				}

			}

			if (gamepad2.bWasPressed()) {
				bot.shooter.turnOff();
			}



			if (gamepad2.dpadLeftWasPressed() && changeConfiguration) {
				bot.intake.setNumberOfArtifactsLoaded(0);

			} else if (gamepad2.dpadRightWasPressed() && changeConfiguration) {
				bot.intake.setNumberOfArtifactsLoaded(0);

			} else if (gamepad2.dpadUpWasPressed() && changeConfiguration) {
				bot.intake.increaseArtifactCount();

			} else if (gamepad2.dpadDownWasPressed() && changeConfiguration) {
				bot.intake.decreaseArtifactCount();
			}

			if (gamepad2.startWasPressed()) {
				changeConfiguration = !changeConfiguration;
			}


			if (gamepad2.leftBumperWasPressed()) {
				shooterSpeedLowDebouncer.run();
			} else if (gamepad2.right_bumper) {
				shooterSpeedFullDebouncer.run();
			}

			if (gamepad2.yWasPressed() && !changeConfiguration) {
				advanceCarouselDebouncer.run();
			}
			if (gamepad2.backWasPressed() && changeConfiguration) {
				autoAdvanceCarousel = !autoAdvanceCarousel;
			}

			bot.drivetrain.drive(gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.right_stick_x);

			if (changeConfiguration) {
				telemetry.addData("CHANGE CONFIGURATION", "===================");
				if (changeConfiguration) {
					telemetry.addData("*Count", "Up | Down | 0 = Left | 0 = Right");
					telemetry.addData("*Alliance", "Red = A | Blue = B");
					telemetry.addData("*Voice", "Toggle = Y");
				}
				telemetry.addData("==================", "===================");
			}
			telemetry.addData("Shooter Power", currentShooterPower);
			telemetry.addData("Artifacts", bot.intake.numberOfArtifactsLoaded);
			telemetry.addData("Alliance", bot.allianceColor());
			telemetry.addData("Color", bot.shooter.readyArtifactColor());
			telemetry.addData("Carousel", autoAdvanceCarousel ? "auto advance" : "manual advance");
			if (!bot.useSpeech) {
				telemetry.addData("Voice", "OFF");
			}
			telemetry.addData("Configuration", changeConfiguration ? "Exit = Start" : "Change = Start");
			telemetry.update();

		}


	}

}