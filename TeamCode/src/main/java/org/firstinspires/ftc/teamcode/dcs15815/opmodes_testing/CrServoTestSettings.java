package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class CrServoTestSettings {
	String name;
	CRServo crServo;
//	DcMotor.Direction direction;
	double power;
	boolean running;
	boolean keepRunning;

	CrServoTestSettings(String n, CRServo m, double p) {
		name = n;
		crServo = m;
//		direction = d;
		power = p;
		running = false;
		keepRunning = false;
	}

	public void increasePower(double increment) {
		power = Math.min(power + increment, 1);
		setPower(power);
	}

	public void decreasePower(double increment) {
		power = Math.max(power - increment, -1);
		setPower(power);
	}

//	public void toggleDirection() {
//		if (direction == DcMotorSimple.Direction.FORWARD) {
//			direction = DcMotorSimple.Direction.REVERSE;
//
//		} else {
//			direction = DcMotorSimple.Direction.FORWARD;
//		}
//	}

	public void setPower(double p) {
		crServo.setPower(p);
		running = power != 0;
	}

	public void resume() {
		crServo.setPower(power);
	}

	public void stopBeforeSwitch(boolean preservePower) {
		crServo.setPower(0);
		if (!preservePower) {
			power = 0;
			running = false;
		}
	}


}
