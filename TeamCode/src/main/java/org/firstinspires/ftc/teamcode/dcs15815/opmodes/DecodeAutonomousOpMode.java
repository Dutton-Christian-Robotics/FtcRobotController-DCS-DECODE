package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;


public abstract class DecodeAutonomousOpMode extends LinearOpMode {
    public DecodeBot bot;
    DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

    public abstract void setAlliance();


    @Override
    public void runOpMode() {
        bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);
        bot.intake.setNumberOfArtifactsLoaded(3);
        bot.intake.startMonitoringCapture();


        bot.abortOpMode = () -> isStopRequested();

        setAlliance();
        if (DefenderAlliance.getInstance().isRed()) {
            telemetry.addData("Alliance", "RED");
        } else if (DefenderAlliance.getInstance().isBlue()) {
            telemetry.addData("Alliance", "BLUE");
        } else {
            telemetry.addData("Alliance", "unknown");
        }
        telemetry.update();

        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.scanRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.scanBlue();
        } else {
            bot.effects.wavesParty();
        }

        waitForStart();
        bot.navigation.resetOtosAndResetOrigin();

        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.heartbeatRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.heartbeatBlue();
        } else {
            bot.effects.wavesParty();
        }

        // DO SPECIFIC OPMODE STUFF HERE
        performAutonomous();

        if (DefenderAlliance.getInstance().isRed()) {
            bot.effects.solidRed();
        } else if (DefenderAlliance.getInstance().isBlue()) {
            bot.effects.solidBlue();
        } else {
            bot.effects.wavesParty();
        }

    }

    public abstract void performAutonomous();

}
