package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Intake Test", group = "Testing")
public class IntakeTestOpMode extends LinearOpMode {
    CRServo lowerServo, middleLeftServo, middleRightServo, upperLeftServo, upperRightServo;

    @Override
    public void runOpMode() {

        lowerServo = hardwareMap.crservo.get("intake_servo_lower");
        middleLeftServo = hardwareMap.crservo.get("intake_servo_middle_left");
        middleRightServo = hardwareMap.crservo.get("intake_servo_middle_right");
        upperLeftServo = hardwareMap.crservo.get("intake_servo_upper_left");
        upperRightServo = hardwareMap.crservo.get("intake_servo_upper_left");

        waitForStart();

        while (!isStopRequested()) {
            if (gamepad1.dpad_up) {
                lowerServo.setPower(-1);
                middleLeftServo.setPower(-1);
                middleRightServo.setPower(-1);
                upperLeftServo.setPower(-1);
                upperRightServo.setPower(-1);

            } else if (gamepad1.dpad_down) {
                lowerServo.setPower(1);
                middleLeftServo.setPower(1);
                middleRightServo.setPower(1);
                upperLeftServo.setPower(1);
                upperRightServo.setPower(1);

            } else if (gamepad1.dpad_left || gamepad1.dpad_right) {
                lowerServo.setPower(0);
                middleLeftServo.setPower(0);
                middleRightServo.setPower(0);
                upperLeftServo.setPower(0);
                upperRightServo.setPower(0);

            }
        }

    }

}
