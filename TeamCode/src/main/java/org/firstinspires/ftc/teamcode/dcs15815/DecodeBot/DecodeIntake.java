package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class DecodeIntake extends DefenderBotSystem {
	HardwareMap hwMap;
	public CRServo servoUpperLeft, servoUpperRight, servoMiddleLeft, servoMiddleRight, servoLower;
	public DcMotor motorCarousel;
	public TouchSensor sensorCapture;
	public int numberOfArtifactsLoaded = 0;

	public DecodeIntakeMonitorCaptureRunnable monitorCaptureRunnable;

	DecodeIntake(HardwareMap hm, DefenderBot b) {
		super(hm, b);

		servoUpperLeft = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_UPPER_LEFT_NAME);
		servoUpperRight = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_UPPER_RIGHT_NAME);
		servoMiddleLeft = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_MIDDLE_LEFT_NAME);
		servoMiddleRight = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_MIDDLE_RIGHT_NAME);
		servoLower = hm.crservo.get(DecodeConfiguration.INTAKE_SERVO_LOWER_NAME);

		motorCarousel = hm.dcMotor.get(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_NAME);
		motorCarousel.setDirection(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_DIRECTION);

		sensorCapture = hm.touchSensor.get(DecodeConfiguration.INTAKE_SENSOR_CAPTURE_NAME);
	}

	public void setNumberOfArtifactsLoaded(int n) {
		numberOfArtifactsLoaded = n;
	}

	public boolean hasArtifacts() {
		return numberOfArtifactsLoaded > 0;
	}

	public void decreaseArtifactCount() {
		numberOfArtifactsLoaded = Math.max(0, --numberOfArtifactsLoaded);
	}

	public void increaseArtifactCount() {
		numberOfArtifactsLoaded++;
	}

	public boolean areTooManyArtifactsLoaded() {
		return numberOfArtifactsLoaded > 3;
	}

	public void startMonitoringCapture() {
		monitorCaptureRunnable = new DecodeIntakeMonitorCaptureRunnable();
		monitorCaptureRunnable.setIntake(this);
		Thread monitorCaptureThread = new Thread(monitorCaptureRunnable);
		monitorCaptureThread.start();
	}

	public void stopMonitoringCapture() {
		if (monitorCaptureRunnable != null) {
			monitorCaptureRunnable.doStop();
		}
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

	public String direction() {
		if (motorCarousel.getPower() > 0) {
			return "in";
		} else if (motorCarousel.getPower() < 0) {
			return "out";
		} else {
			return "";
		}
	}

	public void turnOnCarousel() {
		turnOnCarousel(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_POWER);
	}

	public void turnOnCarouselForAdvance() {
		turnOnCarousel(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_POWER_ADVANCE);
	}

	public void turnOnCarousel(double power) {
		motorCarousel.setPower(power);
	}

	public void reverseCarousel() {
		reverseCarousel(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_POWER);
	}

	public void reverseCarouselForDeAdvance() {
		reverseCarousel(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_POWER_ADVANCE);
	}

	public void reverseCarousel(double power) {
		motorCarousel.setPower(-1 * power);
	}

	public void turnOffCarousel() {
		motorCarousel.setPower(0);
	}

	public void advanceCarousel() {
		turnOnCarouselForAdvance();
		sleep(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_TIME_ADVANCE);
		turnOffCarousel();
	}

	public void deAdvanceCarousel() {
		deAdvanceCarousel(1);
	}

	public void deAdvanceCarousel(double timeFraction) {
		reverseCarouselForDeAdvance();
		sleep(Math.round(DecodeConfiguration.INTAKE_MOTOR_CAROUSEL_TIME_ADVANCE * timeFraction));
		turnOffCarousel();
	}

	public void advanceCarouselUntilReady() {
		ElapsedTime timer = new ElapsedTime();
		DecodeShooter shooter = ((DecodeBot) bot).shooter;

		turnOnCarouselForAdvance();
		while (!shooter.isReadyToShoot() && timer.milliseconds() < 3000) {
			sleep(150);
		}
		turnOffCarousel();


	}


}