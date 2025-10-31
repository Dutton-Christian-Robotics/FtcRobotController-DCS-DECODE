package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

public class DecodeShooter extends DefenderBotSystem {
	HardwareMap hwMap;
	public Servo servoLeft, servoRight;
	public DcMotor motorLeft, motorRight;
	public double currentShooterPower = DecodeConfiguration.SHOOTER_MOTOR_POWER_START;
	public ColorRangeSensor sensorReady;


	DecodeShooter(HardwareMap hm, DefenderBot b) {
		super(hm, b);

		motorLeft = hm.dcMotor.get(DecodeConfiguration.SHOOTER_MOTOR_LEFT_NAME);
		motorRight = hm.dcMotor.get(DecodeConfiguration.SHOOTER_MOTOR_RIGHT_NAME);

		motorLeft.setDirection(DecodeConfiguration.SHOOTER_MOTOR_LEFT_DIRECTION);
		motorRight.setDirection(DecodeConfiguration.SHOOTER_MOTOR_RIGHT_DIRECTION);


		servoLeft = hm.servo.get(DecodeConfiguration.SHOOTER_SERVO_LIFT_LEFT_NAME);
		servoRight = hm.servo.get(DecodeConfiguration.SHOOTER_SERVO_LIFT_RIGHT_NAME);

		sensorReady = hm.get(ColorRangeSensor.class, DecodeConfiguration.SHOOTER_SENSOR_READY_NAME);
	}

	public void turnOn() {
		motorLeft.setPower(currentShooterPower);
		motorRight.setPower(currentShooterPower);
	}

	public void turnOff() {
		motorLeft.setPower(0);
		motorRight.setPower(0);
	}

	public void changeShooterPower(double p) {
		currentShooterPower = Math.min(
			   Math.max(
				p,
				DecodeConfiguration.SHOOTER_MOTOR_POWER_MIN
			   ),
			   DecodeConfiguration.SHOOTER_MOTOR_POWER_MAX
		);
		turnOn();

	}



	public void raiseLift() {
		servoLeft.setPosition(DecodeConfiguration.SHOOTER_SERVO_LIFT_LEFT_POSITION_UP);
		servoRight.setPosition(DecodeConfiguration.SHOOTER_SERVO_LIFT_RIGHT_POSITION_UP);
	}

	public void lowerLift() {
		servoLeft.setPosition(DecodeConfiguration.SHOOTER_SERVO_LIFT_LEFT_POSITION_DOWN);
		servoRight.setPosition(DecodeConfiguration.SHOOTER_SERVO_LIFT_RIGHT_POSITION_DOWN);
	}

	public void shoot() {
		raiseLift();
		sleep(DecodeConfiguration.SHOOTER_LIFT_TIME_SLEEP);
		lowerLift();
		sleep(150);
	}

	public void shootAndUpdateArtifactCount() {
		shoot();
		DecodeBot dbot = (DecodeBot) bot;
		if (!isReadyToShoot()) {
			dbot.intake.decreaseArtifactCount();
		}
	}


	public boolean isReadyToShoot() {
		return sensorReady.getLightDetected() > DecodeConfiguration.SHOOTER_SENSOR_READY_THRESHOLD_LIGHT;
	}

	public String readyArtifactColor() {
		if (!isReadyToShoot()) {
			return "n/a";
		} else if (sensorReady.green() > sensorReady.blue()) {
			return "green";
		} else if (sensorReady.blue() > sensorReady.green()) {
			return "purple";
		} else {
			return "unknown";
		}
	}




}