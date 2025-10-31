package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@Disabled
@TeleOp(name = "Intake and Shoot Test", group = "Testing")
public class IntakeAndShootTestOpMode extends LinearOpMode {
	public CRServo servoUpperLeft, servoUpperRight, servoMiddleLeft, servoMiddleRight, servoLower;
	DcMotor motorBigWheel, motorShooterLeft, motorShooterRight;
	Servo servoLiftLeft, servoLiftRight;

	DefenderDebouncer xDebouncer = new DefenderDebouncer(500, () -> {
		shoot();
	});

	DefenderDebouncer aDebouncer = new DefenderDebouncer(500, () -> {
		startShooter();
	});

	DefenderDebouncer bDebouncer = new DefenderDebouncer(500, () -> {
		stopShooter();
	});


	public void startIntake() {
		setPower(1, -1, -1, 1, 1);
		motorBigWheel.setPower(-0.3);
	}

	public void reverseIntake() {
		setPower(-1, 1, 1, -1, -1);
		motorBigWheel.setPower(0.3);
	}

	public void stopIntake() {
		setPower(0, 0, 0, 0, 0);
		motorBigWheel.setPower(0);

	}

	public void startShooter() {
		motorShooterLeft.setPower(1);
		motorShooterRight.setPower(1);
	}

	public void reverseShooter() {
		motorShooterLeft.setPower(-1);
		motorShooterRight.setPower(-1);
	}

	public void stopShooter() {
		motorShooterLeft.setPower(0);
		motorShooterRight.setPower(0);
	}

	public void shoot() {
		servoLiftLeft.setPosition(1);
		servoLiftRight.setPosition(0);
		sleep(1000);
		servoLiftLeft.setPosition(0);
		servoLiftRight.setPosition(1);

	}


	public void setPower(double ul, double ur, double ml, double mr, double l) {
		servoUpperLeft.setPower(ul);
		servoUpperRight.setPower(ur);
		servoMiddleLeft.setPower(ml);
		servoMiddleRight.setPower(mr);
		servoLower.setPower(l);
	}
	@Override
	public void runOpMode() {
		motorBigWheel = hardwareMap.dcMotor.get("motor_big_wheel");
		motorBigWheel.setDirection(DcMotor.Direction.FORWARD);

		servoLiftLeft = hardwareMap.servo.get("lift_servo_left");
		servoLiftRight = hardwareMap.servo.get("lift_servo_right");

		motorShooterLeft = hardwareMap.dcMotor.get("motor_launch_left");
		motorShooterRight = hardwareMap.dcMotor.get("motor_launch_right");

//        liftServoLeft = hardwareMap.servo.get("lift_servo_left");
//        liftServoRight = hardwareMap.servo.get("lift_servo_right");
//
		motorShooterLeft.setDirection(DcMotor.Direction.FORWARD);
		motorShooterRight.setDirection(DcMotor.Direction.REVERSE);


		servoUpperLeft = hardwareMap.crservo.get("intake_upper_right");
		servoUpperRight = hardwareMap.crservo.get("intake_upper_left");
		servoMiddleLeft = hardwareMap.crservo.get("intake_medium_left");
		servoMiddleRight = hardwareMap.crservo.get("intake_medium_right");
		servoLower = hardwareMap.crservo.get("intake_servo_lower");

		waitForStart();

		while (opModeIsActive()) {
			if (gamepad1.dpad_up) {
				startIntake();

			} else if (gamepad1.dpad_down) {
				reverseIntake();

			} else if (gamepad1.dpad_left || gamepad1.dpad_right) {
				stopIntake();

			} else if (gamepad1.x) {
				xDebouncer.run();

			} else if (gamepad1.a) {
				aDebouncer.run();

			} else if (gamepad1.b) {
				bDebouncer.run();
			}


		}


	}

}