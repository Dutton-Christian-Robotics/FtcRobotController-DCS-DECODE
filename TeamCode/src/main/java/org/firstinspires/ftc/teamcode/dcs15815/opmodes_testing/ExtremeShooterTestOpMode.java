package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeShooter;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.DecodeAutonomousOpMode;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.TeleOpMode;

@Disabled
@TeleOp(name = "Shooter Test", group = "Testing")
public class ExtremeShooterTestOpMode extends TeleOpMode {

	DecodeBot bot;

	@Override
	public void runOpMode() {

		bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);

		waitForStart();

		while (opModeIsActive()) {

			if (gamepad1.leftBumperWasPressed()) {
				bot.shooter.changeShooterPower(0.8);

			} else if (gamepad1.rightBumperWasPressed()) {
				bot.shooter.changeShooterPower(1);
			}
			if (gamepad1.left_trigger > 0) {
				bot.shooter.extremeLeft();
			} else if (gamepad1.right_trigger > 0) {
				bot.shooter.extremeRight();
			} else if (gamepad1.yWasPressed()) {
			bot.shooter.biasStraight();
			}


			if (gamepad1.xWasPressed()) {
				bot.shooter.shoot();
			}


			if (bot.shooter.shooterDirection == DecodeShooter.ShooterDirection.RIGHT_EXTREME) {
				telemetry.addData("Direction", "right");
			} else if (bot.shooter.shooterDirection == DecodeShooter.ShooterDirection.LEFT_EXTREME) {
				telemetry.addData("Direction", "left");
			} else if (bot.shooter.shooterDirection == DecodeShooter.ShooterDirection.STRAIGHT) {
				telemetry.addData("Direction", "straight");
			}

				   telemetry.update();
		}


	}

}
