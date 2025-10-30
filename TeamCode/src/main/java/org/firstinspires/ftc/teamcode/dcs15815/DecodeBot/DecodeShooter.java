package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class DecodeShooter extends DefenderBotSystem {
	HardwareMap hwMap;
	public Servo servoLeft, servoRight;
	public DcMotor motorLeft, motorRight;
	public double currentShooterPower = DecodeConfiguration.SHOOTER_MOTOR_POWER_START;

	DecodeShooter(HardwareMap hm, DefenderBot b) {
		super(hm, b);

		motorLeft = hm.dcMotor.get(DecodeConfiguration.SHOOTER_MOTOR_LEFT_NAME);
		motorRight = hm.dcMotor.get(DecodeConfiguration.SHOOTER_MOTOR_RIGHT_NAME);

		motorLeft.setDirection(DecodeConfiguration.SHOOTER_MOTOR_LEFT_DIRECTION);
		motorRight.setDirection(DecodeConfiguration.SHOOTER_MOTOR_RIGHT_DIRECTION);


		servoLeft = hm.servo.get(DecodeConfiguration.SHOOTER_SERVO_LIFT_LEFT_NAME);
		servoRight = hm.servo.get(DecodeConfiguration.SHOOTER_SERVO_LIFT_RIGHT_NAME);
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
	}




}