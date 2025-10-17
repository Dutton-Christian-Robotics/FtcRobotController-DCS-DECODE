package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.NautilusBot.NautilusNavigation;

public class DefenderPIDController {
    boolean usesAngles = false;
    double kP = 0;
    double kI = 0;
    double kD = 0;
    double integralSum = 0;
    double integralSumLimit = 0.25;
    private double lastError = 0;
    ElapsedTime timer = new ElapsedTime();

    public DefenderPIDController(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public void setUsesAngles(boolean b) {
        usesAngles = b;
    }

    public double calculate(double referencePosition, double currentPosition) {
        double error;
        if (usesAngles) {
            error = NautilusNavigation.calculateYawError(currentPosition, referencePosition);
        } else {
            error = referencePosition - currentPosition;
        }

        // This integral originally was a plus: I think it needs to be *
//		integralSum += error + timer.seconds();
        integralSum += error * timer.seconds();
        if (integralSum > integralSumLimit) {
            integralSum = integralSumLimit;
        }
        if (integralSum < -integralSumLimit) {
            integralSum = -integralSumLimit;
        }

        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();
        double outputPower = (error * kP) + (derivative * kD) + (integralSum * kI);
        return outputPower;
    }
}