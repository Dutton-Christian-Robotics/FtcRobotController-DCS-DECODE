package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

import java.util.function.BooleanSupplier;

public class DecodeBot extends DefenderBot {

    public DecodeIntake intake;
    public DecodeEffects effects;
    public DecodeShooter shooter;
    public DecodeMecanumDrivetrain drivetrain;
    public DecodeNavigation navigation;
    public VoltageSensor voltageSensor;


    private boolean useDebugging = false;
    public boolean useSpeech = false;
    public BooleanSupplier abortOpMode = () -> false;


    public DecodeBot(HardwareMap hm, Class configClass, Telemetry t) {
        super(hm, configClass, t);

        intake = addSystem(new DecodeIntake(hm, this));
        effects = addSystem(new DecodeEffects(hm, this));
        shooter = addSystem(new DecodeShooter(hm, this));
        drivetrain = addSystem(new DecodeMecanumDrivetrain(hm, this));

        navigation = addSystem(new DecodeNavigation(hm, this));

        voltageSensor = hm.voltageSensor.iterator().next();
    }

    public void setUseDebugging(boolean b) {
        useDebugging = b;
    }

    public void setUseSpeech(boolean b) {
        useSpeech = b;
    }



    public boolean hasArtifacts() {
        return intake.hasArtifacts();
    }

    public int numberOfArtifactsLoaded() {
        return intake.numberOfArtifactsLoaded;
    }

    public boolean isReadyToShoot() {
//        return shooter.isReadyToShoot();
        return true;
    }






}