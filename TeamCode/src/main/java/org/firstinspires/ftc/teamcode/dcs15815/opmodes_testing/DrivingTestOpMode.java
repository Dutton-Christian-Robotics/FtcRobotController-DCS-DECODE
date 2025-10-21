package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Driving Test", group = "Testing")
public class DrivingTestOpMode extends LinearOpMode {
    private ComplexDriveRobot bot;

    @Override
    public void runOpMode() {

        bot = new ComplexDriveRobot(hardwareMap);

        waitForStart();

        while (!isStopRequested()) {
            bot.drive(-1 * gamepad1.left_stick_y, (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1.right_stick_x);


        }

    }

}