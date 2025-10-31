package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
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
    public boolean useSpeech = true;
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

    // On DecodeBot, the OTOS is mounted so that the y axis measures forward and backward
    public void driveToBotRelativePosition(double targetY, double targetX, double targetH) {
        driveToBotRelativePosition(targetY, targetX, targetH, abortOpMode,
                DecodeConfiguration.NAVIGATION_Y_KP, DecodeConfiguration.NAVIGATION_Y_KI, DecodeConfiguration.NAVIGATION_Y_KD,
                DecodeConfiguration.NAVIGATION_X_KP, DecodeConfiguration.NAVIGATION_X_KI, DecodeConfiguration.NAVIGATION_X_KD,
                DecodeConfiguration.NAVIGATION_R_KP, DecodeConfiguration.NAVIGATION_R_KI, DecodeConfiguration.NAVIGATION_R_KD,
                DecodeConfiguration.NAVIGATION_Y_MAXPOWER, DecodeConfiguration.NAVIGATION_X_MAXPOWER, DecodeConfiguration.NAVIGATION_R_MAXPOWER
        );
    }
    public void driveToBotRelativePosition(double targetY, double targetX, double targetH, BooleanSupplier abort) {
        driveToBotRelativePosition(targetY, targetX, targetH, abort,
                DecodeConfiguration.NAVIGATION_Y_KP, DecodeConfiguration.NAVIGATION_Y_KI, DecodeConfiguration.NAVIGATION_Y_KD,
                DecodeConfiguration.NAVIGATION_X_KP, DecodeConfiguration.NAVIGATION_X_KI, DecodeConfiguration.NAVIGATION_X_KD,
                DecodeConfiguration.NAVIGATION_R_KP, DecodeConfiguration.NAVIGATION_R_KI, DecodeConfiguration.NAVIGATION_R_KD,
                DecodeConfiguration.NAVIGATION_Y_MAXPOWER, DecodeConfiguration.NAVIGATION_X_MAXPOWER, DecodeConfiguration.NAVIGATION_R_MAXPOWER
        );
    }
    public void driveToBotRelativePosition(double targetY, double targetX, double targetH, BooleanSupplier abort, double y_maxPower, double x_maxPower, double r_maxPower) {
        driveToBotRelativePosition(targetY, targetX, targetH, abort,
                DecodeConfiguration.NAVIGATION_Y_KP, DecodeConfiguration.NAVIGATION_Y_KI, DecodeConfiguration.NAVIGATION_Y_KD,
                DecodeConfiguration.NAVIGATION_X_KP, DecodeConfiguration.NAVIGATION_X_KI, DecodeConfiguration.NAVIGATION_X_KD,
                DecodeConfiguration.NAVIGATION_R_KP, DecodeConfiguration.NAVIGATION_R_KI, DecodeConfiguration.NAVIGATION_R_KD,
                y_maxPower, x_maxPower, r_maxPower
        );
    }
    public void driveToBotRelativePosition(double targetY, double targetX, double targetH, BooleanSupplier abort,
                                           double y_kP, double y_kI, double y_kD,
                                           double x_kP, double x_kI, double x_kD,
                                           double r_kP, double r_kI, double r_kD,
                                           double y_maxPower, double x_maxPower, double r_maxPower) {

        double error = 0;
        double yError = 0, xError = 0, rError = 0, sumError;
        double yPower = 0, xPower = 0, rPower = 0, maxPower;
        double forward = 0, strafe = 0, rotate = 0;
        SparkFunOTOS.Pose2D pose;

        DefenderPIDController xPID = new DefenderPIDController(x_kP, x_kI, x_kD);
        DefenderPIDController yPID = new DefenderPIDController(y_kP, y_kI, y_kD);
        DefenderPIDController rPID = new DefenderPIDController(r_kP, r_kI, r_kD);
        rPID.setUsesAngles(true);

        boolean y_include = true;
        boolean x_include = true;
        boolean r_include = true;

        boolean keepLooping = true;
        boolean isSettling = false;
        ElapsedTime settlingTimer = new ElapsedTime();

        do {
            pose = navigation.otos.getPosition();
            if (y_include) {
                yError = targetY - pose.y;
                if (yError == 0) {
                    y_include = false;
                }
            }
            if (x_include) {
                xError = targetX - pose.x;
                if (xError == 0) {
                    x_include = false;
                }
            }
            if (r_include) {
                rError = DecodeNavigation.calculateYawError(-pose.h, targetH);
                if (rError == 0) {
                    r_include = false;
                }
            }

            if (abort.getAsBoolean()) {
                keepLooping = false;
            } else if (
                    !isSettling &&
                            (Math.abs(yError) <= DecodeConfiguration.NAVIGATION_TOLERANCE_Y) &&
                            (Math.abs(xError) <= DecodeConfiguration.NAVIGATION_TOLERANCE_X) &&
                            (Math.abs(rError) <= DecodeConfiguration.NAVIGATION_TOLERANCE_R)
            ) {
                isSettling = true;
                settlingTimer.reset();

            } else if (isSettling && (settlingTimer.milliseconds() > DecodeConfiguration.NAVIGATION_SETTLING_TIME)) {
                if (
                        (Math.abs(yError) <= DecodeConfiguration.NAVIGATION_TOLERANCE_Y) &&
                                (Math.abs(xError) <= DecodeConfiguration.NAVIGATION_TOLERANCE_X) &&
                                (Math.abs(rError) <= DecodeConfiguration.NAVIGATION_TOLERANCE_R)
                ) {
                    keepLooping = false;
                } else {
                    isSettling = false;
                }
            }


            sumError = yError + xError + rError;

            if (y_include) {
                yPower = yPID.calculate(targetY, pose.y);
            } else {
                yPower = 0;
            }
            if (x_include) {
                xPower = xPID.calculate(targetX, pose.x);
            } else {
                xPower = 0;
            }
            if (r_include) {
                rPower = rPID.calculate(targetH, -pose.h);
            } else {
                rPower = 0;
            }

            // Not sure this needed to be a sum, nor why I had it as such.
//            maxPower = Math.max(Math.abs(yPower) + Math.abs(xPower) + Math.abs(rPower), 1);
            maxPower = Math.max(Math.abs(yPower), 1);
            maxPower = Math.max(maxPower, Math.abs(xPower));
            maxPower = Math.max(maxPower, Math.abs(rPower));


            forward = DecodeNavigation.scalePower(yPower, maxPower, y_maxPower);
            strafe = DecodeNavigation.scalePower(xPower, maxPower, x_maxPower);
            rotate = DecodeNavigation.scalePower(rPower, maxPower, r_maxPower);

//            drivetrain.driveNoProportional(forward, strafe, rotate);
            drivetrain.drive(forward, strafe, rotate);

            if (useDebugging) {

                telemetry.addData("now", pose.x + ", " + pose.y + ", " + pose.h);
                telemetry.addData("target", targetX + ", " + targetY + ", " + targetH);
                //            telemetry.addData("x", strafe);
                //            telemetry.addData("y", forward);
                //            telemetry.addData("h", rotate);

                telemetry.addData("y", yError + " : " + yPower + " : " + forward);
                telemetry.addData("x", xError + " : " + xPower + " : " + strafe);
                telemetry.addData("h", rError + " : " + rPower + " : " + rotate);
                telemetry.addData("sum error", sumError);

                telemetry.addData("y tol", (Math.abs(yError) > DecodeConfiguration.NAVIGATION_TOLERANCE_Y));
                telemetry.addData("x tol", (Math.abs(xError) > DecodeConfiguration.NAVIGATION_TOLERANCE_X));
                telemetry.addData("r tol", (Math.abs(rError) > DecodeConfiguration.NAVIGATION_TOLERANCE_R));
                telemetry.addData("abort", !abort.getAsBoolean());
                telemetry.addData("isSettling", isSettling);
                telemetry.addData("keepLooping", keepLooping);

                telemetry.update();
            }

        } while (keepLooping);
//        } while (
//            (
//                (Math.abs(yError) > DecodeConfiguration.NAVIGATION_TOLERANCE_Y) ||
//                (Math.abs(xError) > DecodeConfiguration.NAVIGATION_TOLERANCE_X) ||
//                (Math.abs(rError) > DecodeConfiguration.NAVIGATION_TOLERANCE_R)
//            ) && !abort.getAsBoolean()
//        );
        drivetrain.stopDriving();
//        } while (sumError > 0.1);

    }

    public void driveToBotRelativePositionWithTimeout(
            double targetY, double targetX, double targetH,
            double initialDelay, double maxTime
    ) {
        ElapsedTime timer = new ElapsedTime();
        driveToBotRelativePosition(targetY, targetX, targetH, () -> {
            SparkFunOTOS.Pose2D velocity = navigation.otos.getVelocity();
            return (Math.abs(velocity.x) < 1 && Math.abs(velocity.y) < 1 && Math.abs(velocity.h) < 1 && timer.milliseconds() > initialDelay) || (timer.milliseconds() > maxTime) || abortOpMode.getAsBoolean();
        });
        navigation.resetOrigin();
    }

    public void correctForAngle(double tolerance) {
        SparkFunOTOS.Pose2D pose = navigation.otos.getPosition();
        double angle = pose.h;

        if (Math.abs(angle) > tolerance) {
            driveToBotRelativePosition(0, 0, angle);
        }
        navigation.resetOrigin();

    }


    public double roundDouble(double n) {
        return roundDouble(n, 3);
    }
    public double roundDouble(double n, int i) {
        double f = Math.pow(10, i);
        return Math.floor(n * f) / f;
    }


    public boolean hasArtifacts() {
        return intake.hasArtifacts();
    }

    public int numberOfArtifactsLoaded() {
        return intake.numberOfArtifactsLoaded;
    }

    public boolean isReadyToShoot() {
        return shooter.isReadyToShoot();
    }






}