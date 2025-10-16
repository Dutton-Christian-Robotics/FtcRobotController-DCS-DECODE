package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Shooter Test", group = "Testing")
public class ShooterTestOpMode extends LinearOpMode {
    DcMotor shooterMotorLeft, shooterMotorRight;
    Servo liftServoLeft, liftServoRight;
//    CRServo liftServoLeft, liftServoRight;

    @Override
    public void runOpMode() {
        double shooterMotorPower = 1;
        double shooterServoPower = 1;

        long shooterMotorPowerUpTime = 2000;
        long liftServoRaiseTime = 750;
        long waitTime = 3000;


        shooterMotorLeft = hardwareMap.dcMotor.get("shooter_motor_left");
        shooterMotorRight = hardwareMap.dcMotor.get("shooter_motor_right");

//        liftServoLeft = hardwareMap.servo.get("lift_servo_left");
//        liftServoRight = hardwareMap.servo.get("lift_servo_right");
//
        shooterMotorLeft.setDirection(DcMotor.Direction.FORWARD);
        shooterMotorRight.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

//        shooterMotorLeft.setPower(shooterMotorPower);
//        shooterMotorRight.setPower(shooterMotorPower);
//        sleep(shooterMotorPowerUpTime);

//        liftServoLeft.setPower(shooterServoPower);
//        liftServoRight.setPower(shooterServoPower);
//        liftServoLeft.setPosition(0.5);
//        liftServoRight.setPosition(0.5);
//        sleep(liftServoRaiseTime);

//        sleep(waitTime);

//        shooterMotorLeft.setPower(0);
//        shooterMotorRight.setPower(0);

//        liftServoLeft.setPower(0);
//        liftServoRight.setPower(0);

//        liftServoRight.setPower(0);

        while (!isStopRequested()) {
            if (gamepad1.a) {
                        shooterMotorLeft.setPower(shooterMotorPower);
        shooterMotorRight.setPower(shooterMotorPower);

            } else if (gamepad1.b) {
                shooterMotorLeft.setPower(0);
                shooterMotorRight.setPower(0);
            }
            if (gamepad1.x) {
//                liftServoLeft.setPower(shooterServoPower);
//        liftServoRight.setPower(-1 * shooterServoPower);
//        sleep(liftServoRaiseTime);

            } else if (gamepad1.dpad_down) {
                shooterMotorPower -= 0.1;
                if (shooterMotorPower < 0) {
                    shooterMotorPower = 1;
                }
                shooterMotorLeft.setPower(shooterMotorPower);
                shooterMotorRight.setPower(shooterMotorPower);

            }

            telemetry.addData("Shooter", shooterMotorPower);
            telemetry.update();

        }

    }

}
