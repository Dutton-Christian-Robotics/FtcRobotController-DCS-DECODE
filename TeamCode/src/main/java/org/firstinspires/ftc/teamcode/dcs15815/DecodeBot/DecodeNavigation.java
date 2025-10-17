package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDelayedSequence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DecodeNavigation extends DefenderBotSystem {

    public SparkFunOTOS otos;


    public DecodeNavigation(HardwareMap hm, DefenderBot b) {
        super(hm, b);

        otos = hardwareMap.get(SparkFunOTOS.class, "otos");

        otos.setLinearUnit(DistanceUnit.INCH);
        otos.setAngularUnit(AngleUnit.DEGREES);

        SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(0, 0, 0);
//        SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(0, 0, 90);
        otos.setOffset(offset);

        otos.setLinearScalar(DecodeConfiguration.NAVIGATION_LINEAR_SCALE);
        otos.setAngularScalar(DecodeConfiguration.NAVIGATION_ANGULAR_SCALE);

        otos.calibrateImu();
        otos.resetTracking();

        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(0, 0, 0);
        otos.setPosition(currentPosition);

        // Get the hardware and firmware version
//        SparkFunOTOS.Version hwVersion = new SparkFunOTOS.Version();
//        SparkFunOTOS.Version fwVersion = new SparkFunOTOS.Version();
//        otos.getVersionInfo(hwVersion, fwVersion);


    }

    public void resetOtos() {
        otos.calibrateImu();
        otos.resetTracking();
    }

    public void resetOtosAndResetOrigin() {
        otos.calibrateImu();
        otos.resetTracking();
        otos.setPosition(new SparkFunOTOS.Pose2D(0, 0, 0));
    }

    public void resetOriginKeepRotation() {
        SparkFunOTOS.Pose2D currentPose = otos.getPosition();
        otos.setPosition(new SparkFunOTOS.Pose2D(0, 0, currentPose.h));
    }
    public void resetOrigin() {
        otos.setPosition(new SparkFunOTOS.Pose2D(0, 0, 0));
    }

    public static double calculateYawError(double currentYawInDegrees, double targetYawInDegrees) {
        // Normalize angles to the range of -180 to 180 degrees
        currentYawInDegrees = normalizeAngle(currentYawInDegrees);
        targetYawInDegrees = normalizeAngle(targetYawInDegrees);

        // Calculate the difference between the current and target yaw
        double yawError = targetYawInDegrees - currentYawInDegrees;

        // Adjust for the shortest path to the target yaw
        if (yawError > 180) {
            yawError -= 360;
        } else if (yawError < -180) {
            yawError += 360;
        }

        return yawError;
    }

    private static double normalizeAngle(double angleInDegrees) {
        while (angleInDegrees > 180) {
            angleInDegrees -= 360;
        }
        while (angleInDegrees < -180) {
            angleInDegrees += 360;
        }
        return angleInDegrees;
    }

    public static double scalePower(double rawPower, double maxPower, double bounds) {
        return scalePower(rawPower, maxPower, -bounds, bounds, 3);
    }

    public static double scalePower(double rawPower, double maxPower, double lowerBounds, double upperBounds, double precision) {
        double precisionFactor = Math.pow(10, precision);
        return Range.clip(
                Math.floor((rawPower / maxPower) * precisionFactor) / precisionFactor,
                lowerBounds,
                upperBounds
        );

    }




}