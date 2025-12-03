package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;

@TeleOp(name = "Pinpoint Test", group = "Testing")
public class PinpointTestOpMode extends LinearOpMode {
    DecodeBot bot;



    @Override
    public void runOpMode() {

        bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);
        waitForStart();



    }

}