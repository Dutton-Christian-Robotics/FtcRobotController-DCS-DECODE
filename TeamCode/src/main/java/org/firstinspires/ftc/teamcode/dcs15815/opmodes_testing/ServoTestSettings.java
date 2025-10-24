package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoTestSettings {
	String name;
	Servo servo;
//	DcMotor.Direction direction;
	double position;

	ServoTestSettings(String n, Servo m, double p) {
		name = n;
		servo = m;
//		direction = d;
		position = p;
	}

	public void increasePosition(double increment) {
		position = Math.min(position + increment, 1);
		setPosition(position);
	}

	public void decreasePosition(double increment) {
		position = Math.max(position - increment, 0);
		setPosition(position);
	}

//	public void toggleDirection() {
//		if (direction == DcMotorSimple.Direction.FORWARD) {
//			direction = DcMotorSimple.Direction.REVERSE;
//
//		} else {
//			direction = DcMotorSimple.Direction.FORWARD;
//		}
//	}

	public void setPosition(double p) {
		servo.setPosition(p);
	}

//	public void resume() {
//		servo.setPower(power);
//	}

//	public void stopBeforeSwitch(boolean preservePower) {
//		servo.setPower(0);
//		if (!preservePower) {
//			power = 0;
//			running = false;
//		}
//	}


}
