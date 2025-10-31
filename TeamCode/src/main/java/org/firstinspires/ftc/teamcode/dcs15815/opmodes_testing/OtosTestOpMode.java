package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;

@TeleOp(name = "OTOS Test", group = "Testing")
public class OtosTestOpMode extends LinearOpMode {
    SparkFunOTOS myOtos;
    DecodeBot bot;

    private void configureOtos() {

        myOtos = hardwareMap.get(SparkFunOTOS.class, "otos");

        telemetry.addLine("Configuring OTOS...");
        telemetry.update();

        myOtos.setLinearUnit(DistanceUnit.INCH);
        myOtos.setAngularUnit(AngleUnit.DEGREES);

        SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(0, 0, 0);
        myOtos.setOffset(offset);

        myOtos.setLinearScalar(0.978);
        myOtos.setAngularScalar(0.997);

        myOtos.calibrateImu();
        myOtos.resetTracking();

        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(0, 0, 0);
        myOtos.setPosition(currentPosition);

        // Get the hardware and firmware version
        SparkFunOTOS.Version hwVersion = new SparkFunOTOS.Version();
        SparkFunOTOS.Version fwVersion = new SparkFunOTOS.Version();
        myOtos.getVersionInfo(hwVersion, fwVersion);

        telemetry.addLine("OTOS configured! Press start to get position data!");
        telemetry.addLine();
        telemetry.addLine(String.format("OTOS Hardware Version: v%d.%d", hwVersion.major, hwVersion.minor));
        telemetry.addLine(String.format("OTOS Firmware Version: v%d.%d", fwVersion.major, fwVersion.minor));
        telemetry.update();
    }

    @Override
    public void runOpMode() {

        bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);

        configureOtos();

        waitForStart();

        while (opModeIsActive()) {
            // Get the latest position, which includes the x and y coordinates, plus the
            // heading angle
            SparkFunOTOS.Pose2D pos = myOtos.getPosition();

            // Reset the tracking if the user requests it
            if (gamepad1.y) {
                myOtos.resetTracking();
            }

            // Re-calibrate the IMU if the user requests it
            if (gamepad1.x) {
                myOtos.calibrateImu();
            }

            if (gamepad1.right_stick_x < 0) {
                bot.drivetrain.drive(0, 0, -0.5);
            } else if (gamepad1.right_stick_x > 0) {
                bot.drivetrain.drive(0, 0, 0.5);
            } else {
                bot.drivetrain.stopDriving();
            }

            if (gamepad1.left_stick_y < 0) {
                bot.drivetrain.drive(gamepad1.left_stick_y * -1, 0, 0);
            } else if (gamepad1.left_stick_y > 0) {
                bot.drivetrain.drive(gamepad1.left_stick_y * -1, 0, 0);
            } else {
                bot.drivetrain.stopDriving();
            }

            // Inform user of available controls
            telemetry.addLine("Press Y on Gamepad 1 to reset tracking");
            telemetry.addLine("Press X on Gamepad 1 to calibrate the IMU");
            telemetry.addLine();

            // Log the position to the telemetry
            telemetry.addData("X coordinate", pos.x);
            telemetry.addData("Y coordinate", pos.y);
            telemetry.addData("Heading angle", pos.h);

            // Update the telemetry on the driver station
            telemetry.update();

        }


    }

}