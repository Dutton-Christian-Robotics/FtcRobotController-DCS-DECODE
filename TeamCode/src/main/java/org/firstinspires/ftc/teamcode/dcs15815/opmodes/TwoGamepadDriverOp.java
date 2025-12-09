package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAnalogModifier;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@TeleOp(name = "Driver Operated", group = "Driver")
public class TwoGamepadDriverOp extends LinearOpMode {
	DecodeBot bot;
	public double currentShooterPower = 1;
	public boolean changeConfiguration = false;
	public boolean autoAdvanceCarousel = true;

	public DefenderAnalogModifier gamepad1RightStickXModifier;

	DefenderDebouncer shooterSpeedLowDebouncer = new DefenderDebouncer(500, () -> {
		currentShooterPower = 0.8;
		bot.shooter.changeShooterPower(currentShooterPower);
	});

	DefenderDebouncer shooterSpeedFullDebouncer = new DefenderDebouncer(500, () -> {
		currentShooterPower = 1;
		bot.shooter.changeShooterPower(currentShooterPower);
	});

	DefenderDebouncer shooterDeflectorDebouncer = new DefenderDebouncer(500, () -> {
		if (bot.shooter.isDeflectorRaised) {
			bot.shooter.lowerDeflector();
		} else {
			bot.shooter.raiseDeflector();
		}
	});

	DefenderDebouncer startShootDebouncer = new DefenderDebouncer(500, () -> {
		bot.shooter.beginShooting();
	});

	DefenderDebouncer stopShootDebouncer = new DefenderDebouncer(500, () -> {
		bot.shooter.stopShooting();
	});

	DefenderDebouncer startIntakeDebouncer = new DefenderDebouncer(500, () -> {
		bot.intake.turnOn();
	});


	DefenderDebouncer stopIntakeDebouncer = new DefenderDebouncer(500, () -> {
		bot.intake.turnOff();
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

		gamepad1RightStickXModifier =  new DefenderAnalogModifier(
			DecodeConfiguration.GAMEPAD1_RIGHT_STICK_X_CURVE,
			DecodeConfiguration.GAMEPAD1_RIGHT_STICK_X_MAX
		);

		waitForStart();

		while (opModeIsActive()) {

		/* ----------------------------------------------------------------------------------------
			This IF block will run when gamepdad2 START has been pressed and we are
			 now in configuration mode, which lets us change some of the behavior of the bot
			 and controls.
		   ---------------------------------------------------------------------------------------- */

			if (changeConfiguration) {

			/* ----------------------------------------------------------------------------------------
				This code is useful for practice when we don't run an autonomous and thus
				don't have information about what alliance we're on.
			   ---------------------------------------------------------------------------------------- */

				if (gamepad2.aWasPressed()) {
					DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.RED);

				} else if (gamepad2.bWasPressed()) {
					DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);

				}

			/* ----------------------------------------------------------------------------------------
				Toggling the Easter Egg speech mode. Some of this is less useful with the
				hardware changes we made leading up to States.
			   ---------------------------------------------------------------------------------------- */

				if (gamepad2.yWasPressed()) {
					bot.useSpeech = !bot.useSpeech;
				}

			/* ----------------------------------------------------------------------------------------
				Seems unlikely our drive team would be using this during match play, but
				this allows resetting or changing our artifact count. Remember that when
				we're > 4, our lights turn purple.
			   ---------------------------------------------------------------------------------------- */

				if (gamepad2.dpadLeftWasPressed() || gamepad2.dpadRightWasPressed()) {
					bot.intake.setNumberOfArtifactsLoaded(0);

				} else if (gamepad2.dpadUpWasPressed()) {
					bot.intake.increaseArtifactCount();

				} else if (gamepad2.dpadDownWasPressed()) {
					bot.intake.decreaseArtifactCount();
				}

				if (gamepad2.backWasPressed()) {
					autoAdvanceCarousel = !autoAdvanceCarousel;
				}


			} else {

			/* ----------------------------------------------------------------------------------------
				This is the code for when we're NOT in configuration mode.
			   ---------------------------------------------------------------------------------------- */

				if (gamepad2.left_stick_y != 0 || gamepad2.left_stick_x != 0) {
					bot.intake.turnOn();

				} else if (gamepad2.xWasPressed()) {
					bot.intake.reverse();

				} else if (gamepad2.right_stick_y != 0 || gamepad2.right_stick_x != 0) {
					bot.intake.turnOff();
				}

				if (gamepad2.left_trigger > 0 && !bot.intake.isIntakeOn) {
					startIntakeDebouncer.run();

				} else if (gamepad2.left_trigger == 0 && bot.intake.isIntakeOn) {
					stopIntakeDebouncer.run();
				}

				if (gamepad2.right_trigger > 0 && !bot.shooter.isGateOpen) {
					startShootDebouncer.run();

				} else if (gamepad2.right_trigger == 0 && bot.shooter.isGateOpen) {
					stopShootDebouncer.run();
				}


				if (gamepad2.bWasPressed()) {
					bot.shooter.turnOff();
				}
//				if (gamepad2.yWasPressed()) {
//					advanceCarouselDebouncer.run();
//				}

			/* ----------------------------------------------------------------------------------------
				For some reason, when we tried tihis function with the yWasPressed, it wasn't
				properly letting us toggle. So for a quick fix, we switched back to a debouncer.
			   ---------------------------------------------------------------------------------------- */

				if (gamepad2.y) {
					shooterDeflectorDebouncer.run();

				}

				if (gamepad2.dpadDownWasPressed() || gamepad2.dpadUpWasPressed()
					   || gamepad2.dpadLeftWasPressed() || gamepad2.dpadRightWasPressed()) {
					bot.intake.deAdvanceCarousel();
				}

				if (gamepad2.leftBumperWasPressed()) {
					shooterSpeedLowDebouncer.run();

				} else if (gamepad2.right_bumper) {
					shooterSpeedFullDebouncer.run();
				}

			}


		/* ----------------------------------------------------------------------------------------
			This needs to be outside of the if (changeConfiguration) loop so that it runs
			no matter which mode we're in.
		   ---------------------------------------------------------------------------------------- */


			if (gamepad2.startWasPressed()) {
				changeConfiguration = !changeConfiguration;
			}




		/* ----------------------------------------------------------------------------------------
			This is the only code that
			what controls now make which changes.
		   ---------------------------------------------------------------------------------------- */


			bot.drivetrain.driveNoProportional(gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1RightStickXModifier.modify(gamepad1.right_stick_x));

		/* ----------------------------------------------------------------------------------------
			When we're in configuration mode, we show additional telemetry information about
			what controls now make which changes.
		   ---------------------------------------------------------------------------------------- */

			if (changeConfiguration) {
				telemetry.addData("CHANGE CONFIGURATION", "===================");
				if (changeConfiguration) {
					telemetry.addData("*Count", "Up | Down | 0 = Left | 0 = Right");
					telemetry.addData("*Alliance", "Red = A | Blue = B");
					telemetry.addData("*Voice", "Toggle = Y");
//					telemetry.addData("*Carousel", "Toggle Auto Advance = Back");
				}
				telemetry.addData("==================", "===================");
			}
			telemetry.addData("Shooter Power", currentShooterPower);
			telemetry.addData("Deflector", bot.shooter.isDeflectorRaised ? "UP" : "down");
			telemetry.addData("Alliance", bot.allianceColor());
			telemetry.addData("Voice", bot.useSpeech ? "on" : "off");
			telemetry.addData("Configuration", changeConfiguration ? "Exit = Start" : "Change = Start");

//			telemetry.addData("x", gamepad1.right_stick_x);
//			telemetry.addData("x mod", gamepad1RightStickXModifier.modify(gamepad1.right_stick_x));
//			telemetry.addData("Color", bot.shooter.readyArtifactColor());
//			telemetry.addData("Shooter L", bot.shooter.motorLeft.getVelocity());
//			telemetry.addData("Shooter R", bot.shooter.motorRight.getVelocity());
//			telemetry.addData("Artifacts", bot.intake.numberOfArtifactsLoaded);
//			telemetry.addData("Carousel", autoAdvanceCarousel ? "auto advance" : "manual advance");

			telemetry.update();

		}


	}

}