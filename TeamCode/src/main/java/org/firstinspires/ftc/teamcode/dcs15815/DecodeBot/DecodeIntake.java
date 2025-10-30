package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class DecodeIntake extends DefenderBotSystem {
	HardwareMap hwMap;
	public CRServo servoUpperLeft, servoUpperRight, servoMiddleLeft, servoMiddleRight, servoLower;
	public DcMotor motorCarousel;

	DecodeIntake(HardwareMap hm, DefenderBot b) {
		super(hm, b);

		servoUpperLeft = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_UPPER_LEFT_NAME);
		servoUpperRight = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_UPPER_RIGHT_NAME);
		servoMiddleLeft = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_MIDDLE_LEFT_NAME);
		servoMiddleRight = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_MIDDLE_RIGHT_NAME);
		servoLower = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_LOWER_NAME);

		motorCarousel = hm.dcMotor.get(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_NAME);
		motorCarousel.setDirection(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_DIRECTION);
	}

	public void setServoPower(double p) {
		setServoPowerForEach(
			   p * DecodeConfiguration.INTAKE_SERVO_UPPER_LEFT_DIRECTION,
			   p * DecodeConfiguration.INTAKE_SERVO_UPPER_RIGHT_DIRECTION,
			   p * DecodeConfiguration.INTAKE_SERVO_MIDDLE_LEFT_DIRECTION,
			   p * DecodeConfiguration.INTAKE_SERVO_MIDDLE_RIGHT_DIRECTION,
			   p * DecodeConfiguration.INTAKE_SERVO_LOWER_DIRECTION
		);
	}

	public void setServoPowerForEach(double ul, double ur, double ml, double mr, double l) {
		servoUpperLeft.setPower(ul);
		servoUpperRight.setPower(ur);
		servoMiddleLeft.setPower(ml);
		servoMiddleRight.setPower(mr);
		servoLower.setPower(l);
	}

	public void turnOff() {
		setServoPower(0);
		turnOffCarousel();
	}

	public void turnOn() {
		setServoPower(DecodeConfiguration.INTAKE_SERVO_POWER_MAX);
		turnOnCarousel();
	}

	public void reverse() {
		setServoPower(- 1 * DecodeConfiguration.INTAKE_SERVO_POWER_MAX);
		reverseCarousel();
	}

	public void turnOnCarousel() {
		motorCarousel.setPower(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_POWER);
	}

	public void reverseCarousel() {
		motorCarousel.setPower(-1 * DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_POWER);
	}

	public void turnOffCarousel() {
		motorCarousel.setPower(0);
	}

	public void advanceCarousel() {
		turnOnCarousel();
		sleep(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_TIME_ADVANCE);
		turnOffCarousel();
	}


}