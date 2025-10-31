package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing.ComplexDriveRobot;


@Disabled
@Autonomous(name = "Red 1 (bad)", group = "1", preselectTeleOp="TwoGamepadTeleOpMode")
public class BadAutonomous1OpMode extends LinearOpMode {
//	public DecodeBot bot;
ComplexDriveRobot bot;

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;
	DcMotor motorBigWheel, motorShooterLeft, motorShooterRight;
	Servo servoLiftLeft, servoLiftRight;
	public CRServo servoUpperLeft, servoUpperRight, servoMiddleLeft, servoMiddleRight, servoLower;

	public double currentShooterPower = 1;

//	public abstract void setAlliance();

	public void startShooter() {
		motorShooterLeft.setPower(currentShooterPower);
		motorShooterRight.setPower(currentShooterPower);
	}

	public void reverseShooter() {
		motorShooterLeft.setPower(-1 * currentShooterPower);
		motorShooterRight.setPower(-1 * currentShooterPower);
	}

	public void stopShooter() {
		motorShooterLeft.setPower(0);
		motorShooterRight.setPower(0);
	}

	public void shoot() {
		servoLiftLeft.setPosition(1);
		servoLiftRight.setPosition(0);
		sleep(1100);
		servoLiftLeft.setPosition(0);
		servoLiftRight.setPosition(1);

	}

	public void advanceCarousel() {
		motorBigWheel.setPower(-0.3);
		sleep(500);
		motorBigWheel.setPower(0);
	}

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

	public void setPower(double ul, double ur, double ml, double mr, double l) {
		servoUpperLeft.setPower(ul);
		servoUpperRight.setPower(ur);
		servoMiddleLeft.setPower(ml);
		servoMiddleRight.setPower(mr);
		servoLower.setPower(l);
	}

	@Override
	public void runOpMode() {

		bot = new ComplexDriveRobot(hardwareMap);

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





//		bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);
//		bot.abortOpMode = () -> isStopRequested();

//		setAlliance();
//		if (DefenderAlliance.getInstance().isRed()) {
//			telemetry.addData("Alliance", "RED");
//		} else if (DefenderAlliance.getInstance().isBlue()) {
//			telemetry.addData("Alliance", "BLUE");
//		} else {
//			telemetry.addData("Alliance", "unknown");
//		}
//		telemetry.update();
//
//		if (DefenderAlliance.getInstance().isRed()) {
//			bot.effects.scanRed();
//		} else if (DefenderAlliance.getInstance().isBlue()) {
//			bot.effects.scanBlue();
//		} else {
//			bot.effects.wavesParty();
//		}

		waitForStart();

		bot.drive(-0.3, 0, 0);
		sleep(800);
		bot.stopDriving();


		startShooter();
		sleep(1000);
		shoot();
		advanceCarousel();
		shoot();
		advanceCarousel();
		shoot();
		stopShooter();

		bot.drive(-0.3, 0, 0);
		sleep(1200);
		bot.stopDriving();
		bot.drive(0, 0, -0.3);
		sleep(1700);
		bot.stopDriving();
		bot.drive(0, -0.3, 0);
		sleep(1350);
		bot.stopDriving();

		startIntake();
		bot.drive(-0.3, 0, 0);
		sleep(1800);
		bot.stopDriving();
		sleep(1000);
//		stopIntake();


		// Drive forward away from
		bot.drive(0.3, 0, 0);
		sleep (1700);
		bot.stopDriving();

		bot.drive(0, 0, 0.3);
		sleep(1700);
		bot.stopDriving();
		bot.drive(0.3, 0, 0);
		sleep(1700);
		bot.stopDriving();
		stopIntake();

		startShooter();
		shoot();
		advanceCarousel();
		shoot();
		advanceCarousel();
		shoot();
		stopShooter();




//		bot.navigation.resetOtosAndResetOrigin();

//		bot.shooter.startShooter();


	}

}
