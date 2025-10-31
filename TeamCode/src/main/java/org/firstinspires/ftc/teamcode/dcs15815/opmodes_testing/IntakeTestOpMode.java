package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;

@Disabled
@TeleOp(name = "Intake Test", group = "Testing")
public class IntakeTestOpMode extends LinearOpMode {
	public CRServo servoUpperLeft, servoUpperRight, servoMiddleLeft, servoMiddleRight, servoLower;
	DcMotor motor;


	public void setPower(double ul, double ur, double ml, double mr, double l) {
		servoUpperLeft.setPower(ul);
		servoUpperRight.setPower(ur);
		servoMiddleLeft.setPower(ml);
		servoMiddleRight.setPower(mr);
		servoLower.setPower(l);
	}
	@Override
	public void runOpMode() {
		motor = hardwareMap.dcMotor.get("motor_big_wheel");
		motor.setDirection(DcMotor.Direction.FORWARD);


		servoUpperLeft = hardwareMap.crservo.get("intake_upper_right");
		servoUpperRight = hardwareMap.crservo.get("intake_upper_left");
		servoMiddleLeft = hardwareMap.crservo.get("intake_medium_left");
		servoMiddleRight = hardwareMap.crservo.get("intake_medium_right");
		servoLower = hardwareMap.crservo.get("intake_servo_lower");

		waitForStart();

		while (opModeIsActive()) {
			if (gamepad1.dpad_up) {
				setPower(1, -1, -1, 1, 1);
				motor.setPower(-0.3);
			} else if (gamepad1.dpad_down) {
				setPower(-1, 1, 1, -1, -1);
				motor.setPower(0.3);
			} else if (gamepad1.dpad_left || gamepad1.dpad_right) {
				setPower(0, 0, 0, 0, 0);
				motor.setPower(0);

			}


		}


	}

}