package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotDrivetrain;


public class DecodeMecanumDrivetrain extends DefenderBotDrivetrain {

	public DcMotorEx backLeft, frontLeft, frontRight, backRight;


	public DecodeMecanumDrivetrain(HardwareMap hm, DefenderBot b) {
		super(hm, b);

		backLeft = hm.get(DcMotorEx.class, DecodeConfiguration.DRIVETRAIN_MOTOR_BACK_LEFT_NAME);
		frontLeft = hm.get(DcMotorEx.class, DecodeConfiguration.DRIVETRAIN_MOTOR_FRONT_LEFT_NAME);
		frontRight = hm.get(DcMotorEx.class, DecodeConfiguration.DRIVETRAIN_MOTOR_FRONT_RIGHT_NAME);
		backRight = hm.get(DcMotorEx.class, DecodeConfiguration.DRIVETRAIN_MOTOR_BACK_RIGHT_NAME);

		backLeft.setDirection(DecodeConfiguration.DRIVETRAIN_MOTOR_BACK_LEFT_DIRECTION);
		frontLeft.setDirection(DecodeConfiguration.DRIVETRAIN_MOTOR_FRONT_LEFT_DIRECTION);
		frontRight.setDirection(DecodeConfiguration.DRIVETRAIN_MOTOR_FRONT_RIGHT_DIRECTION);
		backRight.setDirection(DecodeConfiguration.DRIVETRAIN_MOTOR_BACK_RIGHT_DIRECTION);

		resetEncoders();
	}

	public void resetEncoders() {
		backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
		frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
		frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
		backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

		backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	}

	public void setPower(double bl, double fl, double fr, double br) {
		backLeft.setPower(bl);
		frontLeft.setPower(fl);
		frontRight.setPower(fr);
		backRight.setPower(br);
	}

	public void setPower(double p) {
		setPower(p, p, p, p);
	}

	public void setMotorDirection(DcMotorSimple.Direction bl, DcMotorSimple.Direction fl,
							DcMotorSimple.Direction fr, DcMotorSimple.Direction br) {
		backLeft.setDirection(bl);
		frontLeft.setDirection(fl);
		frontRight.setDirection(fr);
		backRight.setDirection(br);
	}

	public void setMotorDirection(DcMotorSimple.Direction left, DcMotorSimple.Direction right) {
		setMotorDirection(left, left, right, right);
	}

	private void setProportionalPower(double bl, double fl, double fr, double br) {

		// I think the way this is supposed to work is not scaling based on the largest,
		// but instead off of the front left value—which should be he max value

		double largest = DecodeConfiguration.DRIVETRAIN_POWER_MAX;
		largest = Math.max(largest, Math.abs(bl));
		largest = Math.max(largest, Math.abs(fl));
		largest = Math.max(largest, Math.abs(fr));
		largest = Math.max(largest, Math.abs(br));

		backLeft.setPower(bl / largest);
		frontLeft.setPower(fl / largest);
		frontRight.setPower(fr / largest);
		backRight.setPower(br / largest);
	}

	@Override
	public void stopDriving() {
		setPower(0);
	}

	public void drive(double arr[]) {
		drive(arr[0], arr[1], arr[2]);
	}

	public void drive(double forward, double strafe, double rotate) {

		double backLeftPower = forward - strafe + rotate;
		double frontLeftPower = forward + strafe + rotate;
		double frontRightPower = forward - strafe - rotate;
		double backRightPower = forward + strafe - rotate;

		setProportionalPower(backLeftPower, frontLeftPower, frontRightPower, backRightPower);
	}

	public void driveNoProportional(double forward, double strafe, double rotate) {
		double backLeftPower = forward - strafe + rotate;
		double frontLeftPower = forward + strafe + rotate;
		double frontRightPower = forward - strafe - rotate;
		double backRightPower = forward + strafe - rotate;
		setPower(backLeftPower, frontLeftPower, frontRightPower, backRightPower);
	}

	public void driveByVelocity(double forward, double strafe, double rotate) {
//	   setMotorDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD,
//			 DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.REVERSE);

		backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
		frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
		frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
		backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

		double backLeftPower = forward - strafe + rotate;
		double frontLeftPower = forward + strafe + rotate;
		double frontRightPower = forward - strafe - rotate;
		double backRightPower = forward + strafe - rotate;

		double largest = 1;
		largest = Math.max(largest, Math.abs(backLeftPower));
		largest = Math.max(largest, Math.abs(frontLeftPower));
		largest = Math.max(largest, Math.abs(frontRightPower));
		largest = Math.max(largest, Math.abs(backRightPower));

		double maxTicksSecond = DecodeConfiguration.DRIVETRAIN_MAX_TICKS_PER_SECOND;

		backLeft.setVelocity(backLeftPower / largest * maxTicksSecond);
		frontLeft.setVelocity(frontLeftPower / largest * maxTicksSecond);
		frontRight.setVelocity(frontRightPower / largest * maxTicksSecond);
		backRight.setVelocity(backRightPower / largest * maxTicksSecond);
	}

	public boolean frontLeftIsStalled() {
//        return false;
		return frontLeft.getVelocity() == 0;
	}

	public void driveForwardByFrontLeftEncoder(double p, double distance) {
		driveForwardByFrontLeftEncoder(p, distance, false);
	}
	public void driveForwardByFrontLeftEncoder(double p, double distance, boolean useFailSafe) {
		ElapsedTime timer = new ElapsedTime();
		timer.reset();
		boolean shouldExit = false;

		double start = frontLeft.getCurrentPosition();
		while ((frontLeft.getCurrentPosition() < (start + distance)) && !shouldExit) {
			drive(p, 0, 0);
			if (useFailSafe) {
				if (timer.milliseconds() > 1000) {
					shouldExit = shouldExit || frontLeftIsStalled();
				}
			} else {
				shouldExit = false;
			}
		}
		stopDriving();
	}

	public void driveBackwardByFrontLeftEncoder(double p, double distance) {
		driveBackwardByFrontLeftEncoder(p, distance, false);
	}
	public void driveBackwardByFrontLeftEncoder(double p, double distance, boolean useFailSafe) {
		ElapsedTime timer = new ElapsedTime();
		timer.reset();
		boolean shouldExit = false;

		double start = frontLeft.getCurrentPosition();
		while ((frontLeft.getCurrentPosition() > (start - distance)) && !shouldExit) {
			drive(-1 * p, 0, 0);
			if (useFailSafe) {
				if (timer.milliseconds() > 1000) {
					shouldExit = shouldExit || frontLeftIsStalled();
				}
			} else {
				shouldExit = false;
			}
		}
		stopDriving();
	}

	public void turnRightByFrontLeftEncoder(double p, double distance) {
		turnRightByFrontLeftEncoder(p, distance, false);
	}

	public void turnRightByFrontLeftEncoder(double p, double d, boolean useFailSafe) {
		ElapsedTime timer = new ElapsedTime();
		timer.reset();
		boolean shouldExit = false;

		double start = frontLeft.getCurrentPosition();
		while ((frontLeft.getCurrentPosition() < (start + d)) && !shouldExit) {
			drive(0, 0, p);
			if (useFailSafe) {
				if (timer.milliseconds() > 1000) {
					shouldExit = shouldExit || frontLeftIsStalled();
				}
			} else {
				shouldExit = false;
			}

		}
		stopDriving();
	}

	public void turnLeftByFrontLeftEncoder(double p, double distance) {
		turnLeftByFrontLeftEncoder(p, distance, false);
	}

	public void turnLeftByFrontLeftEncoder(double p, double d, boolean useFailSafe) {
		ElapsedTime timer = new ElapsedTime();
		timer.reset();
		boolean shouldExit = false;

		double start = frontLeft.getCurrentPosition();
		while ((frontLeft.getCurrentPosition() > (start - d)) && !shouldExit) {
			drive(0, 0, -1 * p);
			if (useFailSafe) {
				if (timer.milliseconds() > 1000) {
					shouldExit = shouldExit || frontLeftIsStalled();
				}
			} else {
				shouldExit = false;
			}

		}
		stopDriving();
	}

	public void turnRight90ByFrontLeftEncoder(double p) {
		turnRight90ByFrontLeftEncoder(p, false);
	}

	public void turnRight90ByFrontLeftEncoder(double p, boolean useFailSafe) {
		ElapsedTime timer = new ElapsedTime();
		timer.reset();
		boolean shouldExit = false;

		double start = frontLeft.getCurrentPosition();
		while ((frontLeft.getCurrentPosition() < (start + 650)) && !shouldExit) {
			drive(0, 0, p);
			if (useFailSafe) {
				if (timer.milliseconds() > 1000) {
					shouldExit = shouldExit || frontLeftIsStalled();
				}
			} else {
				shouldExit = false;
			}

		}
		stopDriving();
	}

	public void turnLeft90ByFrontLeftEncoder(double p) {
		turnLeft90ByFrontLeftEncoder(p, false);
	}

	public void turnLeft90ByFrontLeftEncoder(double p, boolean useFailSafe) {
		ElapsedTime timer = new ElapsedTime();
		timer.reset();
		boolean shouldExit = false;

		double start = frontLeft.getCurrentPosition();
		while ((frontLeft.getCurrentPosition() > (start - 650)) && !shouldExit) {
			drive(0, 0, -1 * p);
			if (useFailSafe) {
				if (timer.milliseconds() > 1000) {
					shouldExit = shouldExit || frontLeftIsStalled();
				}
			} else {
				shouldExit = false;
			}

		}
		stopDriving();
	}

	public void strafeLeftByFrontLeftEncoder(double p, double distance) {
		strafeLeftByFrontLeftEncoder(p,distance, false);
	}

	public void strafeLeftByFrontLeftEncoder(double p, double distance, boolean useFailSafe) {
		ElapsedTime timer = new ElapsedTime();
		timer.reset();
		boolean shouldExit = false;

		double start = frontLeft.getCurrentPosition();
		while ((frontLeft.getCurrentPosition() > (start - distance)) && !shouldExit) {
			drive(0, -1 * p, 0);
			if (useFailSafe) {
				if (timer.milliseconds() > 1000) {
					shouldExit = shouldExit || frontLeftIsStalled();
				}
			} else {
				shouldExit = false;
			}

		}
		stopDriving();

	}

	public void strafeRightByFrontLeftEncoder(double p, double distance) {
		strafeRightByFrontLeftEncoder(p, distance, false);
	}
	public void strafeRightByFrontLeftEncoder(double p, double distance, boolean useFailSafe) {
		ElapsedTime timer = new ElapsedTime();
		timer.reset();
		boolean shouldExit = false;

		double start = frontLeft.getCurrentPosition();
		while ((frontLeft.getCurrentPosition() < (start + distance)) && !shouldExit) {
			drive(0, 1 * p, 0);
			if (useFailSafe) {
				if (timer.milliseconds() > 1000) {
					shouldExit = shouldExit || frontLeftIsStalled();
				}
			} else {
				shouldExit = false;
			}

		}
		stopDriving();

	}

/*
    Forward:
        1 tile = 500 ticks
    Backward:
        1 tile = 500+ ticks
    Right:
        90º = 650 ticks
    Left:
        90º - 650 ticks

 */

}