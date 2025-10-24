package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class DecodeIntake extends DefenderBotSystem {
	HardwareMap hwMap;
	public CRServo servoUpperLeft, servoUpperRight, servoMiddleLeft, servoMiddleRight, servoLower;
	private final double maxPower = 1.0;

	DecodeIntake(HardwareMap hm, DefenderBot b) {
		super(hm, b);

		servoUpperLeft = hwMap.crservo.get("intake_upper_right");
		servoUpperRight = hwMap.crservo.get("intake_upper_left");
		servoMiddleLeft = hwMap.crservo.get("intake_medium_left");
		servoMiddleRight = hwMap.crservo.get("intake_medium_right");
		servoLower = hwMap.crservo.get("intake_servo_lower");
	}

	public void setPower(double ul, double ur, double ml, double mr, double l) {
		servoUpperLeft.setPower(ul);
		servoUpperRight.setPower(ur);
		servoMiddleLeft.setPower(ml);
		servoMiddleRight.setPower(mr);
		servoLower.setPower(l);
	}

	public void stopIntake() {
		setPower(0, 0, 0, 0, 0);
	}

	public void startIntake() {
		setPower(1, -1, -1, 1, 1);
	}

	public void reverseIntake() {
		setPower(-1, 1, 1, -1, -1);
	}


}