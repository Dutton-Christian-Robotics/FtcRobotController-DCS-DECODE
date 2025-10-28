package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

import java.util.function.BooleanSupplier;

public class DecodeBot extends  DefenderBot {

    public DecodeIntake intake;
    public DecodeEffects effects;
    public DecodeShooter shooter;
    public DecodeMecanumDrivetrain drivetrain;


//    public NautilusNavigation navigation;
    private boolean useDebugging = false;
    public BooleanSupplier abortOpMode = () -> false;

    //    public DefenderPresets<SBBArmPosition> armPresets;
//    public DefenderDelayedSequence grabPixelSequence;

    public DecodeBot(HardwareMap hm, Class configClass, Telemetry t) {
        super(hm, configClass, t);

        intake = addSystem(DecodeIntake.class);
        effects = addSystem(DecodeEffects.class);
        shooter = addSystem(DecodeShooter.class);
        drivetrain = addSystem(DecodeMecanumDrivetrain.class);

        //        shoulder = addSystem(NautilusShoulder.class);
//        arm = addSystem(NautilusArm.class);
//        intake = addSystem(NautilusIntake.class);
//        wrist = addSystem(NautilusWrist.class);
//        navigation = addSystem(NautilusNavigation.class);
    }

    public void setUseDebugging(boolean b) {
        useDebugging = b;
    }









}