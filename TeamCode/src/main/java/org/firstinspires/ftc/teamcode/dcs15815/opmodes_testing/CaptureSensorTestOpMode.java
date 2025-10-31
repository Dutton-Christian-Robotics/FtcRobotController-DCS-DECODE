package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;

@Disabled
@TeleOp(name = "Capture Sensor Test", group = "Testing")
public class CaptureSensorTestOpMode extends LinearOpMode {
	public DecodeBot bot;

	@Override
	public void runOpMode() {

		bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);


		waitForStart();
		bot.intake.startMonitoringCapture();
		bot.intake.turnOn();


		while (opModeIsActive()) {

			telemetry.addData("capture", bot.intake.sensorCapture.isPressed());
			telemetry.addData("artifacts", bot.intake.numberOfArtifactsLoaded);
			telemetry.update();


		}
		bot.intake.turnOff();
		bot.intake.stopMonitoringCapture();


	}

}