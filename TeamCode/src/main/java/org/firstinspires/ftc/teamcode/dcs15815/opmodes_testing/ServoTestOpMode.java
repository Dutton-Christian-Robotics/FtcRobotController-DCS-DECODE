package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo Test", group = "Testing")
public class ServoTestOpMode extends LinearOpMode {
    Servo servo;

    @Override
    public void runOpMode() {

        servo = hardwareMap.servo.get("lift_servo_left");
        double position = 0;

        waitForStart();

        while (!isStopRequested()) {
            if (gamepad1.dpad_left) {
                position = 0;
                servo.setPosition(position);
            } else if (gamepad1.dpad_up) {
                position = 0.5;
                servo.setPosition(position);
            } else if (gamepad1.dpad_right) {
                position = 1;
                servo.setPosition(position);
            }

            telemetry.addData("Position", position);
            telemetry.update();

        }

    }

}
