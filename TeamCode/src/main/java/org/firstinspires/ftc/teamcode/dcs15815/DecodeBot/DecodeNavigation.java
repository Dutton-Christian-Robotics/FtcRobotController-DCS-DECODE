package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import static org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit.MM;
import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;

public class DecodeNavigation extends DefenderBotSystem {

    GoBildaPinpointDriver pinpoint;
    DecodeMecanumDrivetrain drivetrain;

    private enum Direction {
        x,
        y,
        h
    }

    private enum InBounds {
        NOT_IN_BOUNDS,
        IN_X_Y,
        IN_HEADING,
        IN_BOUNDS
    }

    private static double xyTolerance = 12;
    private static double yawTolerance = 0.0349066;

    private static double pGain = 0.008;
    private static double dGain = 0.00001;
    private static double accel = 10.0;

    private static double yawPGain = 5.0;
    private static double yawDGain = 0.0;
    private static double yawAccel = 20.0;

    private final ElapsedTime holdTimer = new ElapsedTime();
    private final ElapsedTime PIDTimer = new ElapsedTime();

    private final PIDLoop xPID = new PIDLoop();
    private final PIDLoop yPID = new PIDLoop();
    private final PIDLoop hPID = new PIDLoop();


    public DecodeNavigation(HardwareMap hm, DefenderBot b) {
        super(hm, b);

        drivetrain = (DecodeMecanumDrivetrain) ((DecodeBot) b).drivetrain;

        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, DecodeConfiguration.NAVIGATION_PINPOINT_NAME);

        pinpoint.setOffsets(DecodeConfiguration.NAVIGATION_PINPOINT_OFFSET_X, DecodeConfiguration.NAVIGATION_PINPOINT_OFFSET_Y, DecodeConfiguration.NAVIGATION_PINPOINT_OFFSET_DISTANCEUNIT);
        pinpoint.setEncoderResolution(DecodeConfiguration.NAVIGATION_POD_TYPE);
        pinpoint.setEncoderDirections(DecodeConfiguration.NAVIGATION_PINPOINT_ENCODERDIRECTION_X, DecodeConfiguration.NAVIGATION_PINPOINT_ENCODERDIRECTION_Y);

        pinpoint.resetPosAndIMU();
    }

    Pose2D currentPosition() {
        return pinpoint.getPosition();
    }

    public void setXYCoefficients(double p, double d, double acceleration, DistanceUnit unit, double tolerance){
        pGain = p;
        dGain = d;
        accel = acceleration;
        xyTolerance = unit.toMm(tolerance);
    }

    public void setYawCoefficients(double p, double d, double acceleration, AngleUnit unit, double tolerance){
        yawPGain = p;
        yawDGain = d;
        yawAccel = acceleration;
        yawTolerance = unit.toRadians(tolerance);
    }

    public boolean driveToFromCurrent(Pose2D targetPosition, double holdTime) {
        return driveTo(pinpoint.getPosition(), targetPosition, DecodeConfiguration.DRIVETRAIN_POWER_MAX_AUTONOMOUS, holdTime);
    }

    public boolean driveToFromCurrent(Pose2D targetPosition, double power, double holdTime) {
        return driveTo(pinpoint.getPosition(), targetPosition, power, holdTime);
    }

    public boolean driveTo(Pose2D currentPosition, Pose2D targetPosition, double holdTime) {
        return driveTo(currentPosition, targetPosition, DecodeConfiguration.DRIVETRAIN_POWER_MAX_AUTONOMOUS, holdTime);
    }

    public boolean driveTo(Pose2D currentPosition, Pose2D targetPosition, double power, double holdTime) {
        boolean atTarget;


        double xPWR = calculatePID(currentPosition, targetPosition, Direction.x);
        double yPWR = calculatePID(currentPosition, targetPosition, Direction.y);
        double hOutput = calculatePID(currentPosition, targetPosition, Direction.h);

        double heading = currentPosition.getHeading(AngleUnit.RADIANS);
        double cosine = Math.cos(heading);
        double sine = Math.sin(heading);

        double xOutput = (xPWR * cosine) + (yPWR * sine);
        double yOutput = (xPWR * sine) - (yPWR * cosine);


        drivetrain.driveWithPinpointValues(xOutput, yOutput, hOutput, power);
//        calculateMecanumOutput(xOutput * power, yOutput * power, hOutput * power);

        if (inBounds(currentPosition, targetPosition) == InBounds.IN_BOUNDS){
            atTarget = true;
        } else {
            holdTimer.reset();
            atTarget = false;
        }

        if (atTarget && holdTimer.time() > holdTime) {
            return true;
        }
        return false;
    }

    private double calculatePID(Pose2D currentPosition, Pose2D targetPosition, Direction direction){
        if(direction == Direction.x){
            double xError = targetPosition.getX(MM) - currentPosition.getX(MM);
            return xPID.calculateAxisPID(xError, pGain, dGain, accel,PIDTimer.seconds());
        }
        if(direction == Direction.y){
            double yError = targetPosition.getY(MM) - currentPosition.getY(MM);
            return yPID.calculateAxisPID(yError, pGain, dGain, accel, PIDTimer.seconds());
        }
        if(direction == Direction.h){
            double hError = targetPosition.getHeading(AngleUnit.RADIANS) - currentPosition.getHeading(AngleUnit.RADIANS);
            return hPID.calculateAxisPID(hError, yawPGain, yawDGain, yawAccel, PIDTimer.seconds());
        }
        return 0;
    }

    private InBounds inBounds(Pose2D currPose, Pose2D trgtPose) {
        boolean xInBounds = currPose.getX(MM) > (trgtPose.getX(MM) - xyTolerance) && currPose.getX(MM) < (trgtPose.getX(MM) + xyTolerance);
        boolean yInBounds = currPose.getY(MM) > (trgtPose.getY(MM) - xyTolerance) && currPose.getY(MM) < (trgtPose.getY(MM) + xyTolerance);
        boolean hInBounds = currPose.getHeading(RADIANS) > (trgtPose.getHeading(RADIANS) - yawTolerance) &&
                currPose.getHeading(RADIANS) < (trgtPose.getHeading(RADIANS) + yawTolerance);

        if (xInBounds && yInBounds && hInBounds) {
            return InBounds.IN_BOUNDS;
        } else if (xInBounds && yInBounds) {
            return InBounds.IN_X_Y;
        } else if (hInBounds) {
            return InBounds.IN_HEADING;
        } else
            return InBounds.NOT_IN_BOUNDS;
    }

    public double calculateTargetHeading(Pose2D currPose, Pose2D trgtPose) {
        double xDelta = trgtPose.getX(MM) - currPose.getX(MM);
        double yDelta = trgtPose.getY(MM) - currPose.getY(MM);

        if(Math.abs(xDelta) > xyTolerance || Math.abs(yDelta) > xyTolerance){
            return Math.atan2(yDelta, xDelta);
        } else {
            return currPose.getHeading(RADIANS);
        }

    }



}

class PIDLoop{
    private double previousError;
    private double previousTime;
    private double previousOutput;

    private double errorR;

    public double calculateAxisPID(double error, double pGain, double dGain, double accel, double currentTime){
        double p = error * pGain;
        double cycleTime = currentTime - previousTime;
        double d = dGain * (previousError - error) / (cycleTime);
        double output = p + d;
        double dV = cycleTime * accel;

        double max = Math.abs(output);
        if(max > 1.0){
            output /= max;
        }

        if((output - previousOutput) > dV){
            output = previousOutput + dV;
        } else if ((output - previousOutput) < -dV){
            output = previousOutput - dV;
        }

        previousOutput = output;
        previousError  = error;
        previousTime   = currentTime;

        errorR = error;

        return output;
    }

}