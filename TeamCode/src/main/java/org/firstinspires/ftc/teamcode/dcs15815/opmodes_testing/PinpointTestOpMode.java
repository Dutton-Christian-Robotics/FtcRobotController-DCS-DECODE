package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;

@TeleOp(name = "Pinpoint Test", group = "Testing")
public class PinpointTestOpMode extends LinearOpMode {
    DecodeBot bot;

    static final Pose2D TARGET_1 = new Pose2D(DistanceUnit.INCH, 0, 24, AngleUnit.DEGREES, 90);

    @Override
    public void runOpMode() {


        bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);

        while (!isStarted() && !opModeIsActive()) {
            bot.navigation.updatePosition();

            telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
            telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
            telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
            telemetry.addData("err", bot.navigation.totalError);
            telemetry.update();
        }

        waitForStart();

        while (!bot.navigation.driveToFromCurrent(TARGET_1, 1) && opModeIsActive()) {
            telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
            telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
            telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
            telemetry.addData("err", bot.navigation.totalError);
            telemetry.update();
            bot.navigation.updatePosition();
        }
        bot.drivetrain.stopDriving();


    }

}