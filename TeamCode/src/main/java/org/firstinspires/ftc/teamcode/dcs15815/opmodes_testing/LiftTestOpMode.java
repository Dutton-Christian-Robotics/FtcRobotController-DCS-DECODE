package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Lift Test", group = "Testing")
public class LiftTestOpMode extends LinearOpMode {
    Servo leftServo, rightServo;

    @Override
    public void runOpMode() {

        leftServo = hardwareMap.servo.get("servo_lift_left");
        rightServo = hardwareMap.servo.get("servo_lift_right");
        double position = 0;

        waitForStart();
        while (!isStopRequested()) {
            if (gamepad1.dpad_up) {

                leftServo.setPosition(1);
                rightServo.setPosition(0);
            } else if (gamepad1.dpad_down) {
                leftServo.setPosition(0);
                rightServo.setPosition(1);

            }



        }

    }

}
