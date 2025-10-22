package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class MotorTestSettings {
	String name;
	DcMotor motor;
	DcMotor.Direction direction;
	double power;
	boolean running;
	boolean keepRunning;

	MotorTestSettings(String n, DcMotor m, DcMotor.Direction d, double p) {
		name = n;
		motor = m;
		direction = d;
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

	public void toggleDirection() {
		if (direction == DcMotorSimple.Direction.FORWARD) {
			direction = DcMotorSimple.Direction.REVERSE;

		} else {
			direction = DcMotorSimple.Direction.FORWARD;
		}
	}

	public void setPower(double p) {
		motor.setPower(p);
		running = power != 0;
	}

	public void resume() {
		motor.setPower(power);
	}

	public void stopBeforeSwitch(boolean preservePower) {
		motor.setPower(0);
		if (!preservePower) {
			power = 0;
			running = false;
		}
	}


}
