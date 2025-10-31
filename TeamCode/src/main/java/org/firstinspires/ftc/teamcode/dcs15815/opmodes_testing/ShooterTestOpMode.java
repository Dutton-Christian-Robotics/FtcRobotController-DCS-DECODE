package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;

@Disabled
@TeleOp(name = "Shooter Test", group = "Testing")
public class ShooterTestOpMode extends LinearOpMode {
    DecodeBot bot;

    @Override
    public void runOpMode() {
        bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);
        bot.intake.setNumberOfArtifactsLoaded(3);

        waitForStart();

        while (!isStopRequested()) {
            if (gamepad1.a) {
                bot.shooter.turnOn();

            } else if (gamepad1.b) {
                bot.shooter.turnOff();

            }
            if (gamepad1.yWasPressed()) {
                bot.intake.advanceCarousel();
            }

            if (gamepad1.x) {
                bot.shooter.shootAndUpdateArtifactCount();

            } else if (gamepad1.dpadDownWasPressed()) {
                double shooterMotorPower = bot.shooter.currentShooterPower;
                shooterMotorPower -= 0.1;
                if (shooterMotorPower < 0) {
                    shooterMotorPower = 1;
                }
                bot.shooter.changeShooterPower(shooterMotorPower);

            } else if (gamepad1.dpadUpWasPressed()) {
                double shooterMotorPower = bot.shooter.currentShooterPower;
                shooterMotorPower += 0.1;
                if (shooterMotorPower > 1) {
                    shooterMotorPower = 1;
                }
                bot.shooter.changeShooterPower(shooterMotorPower);

            }

            telemetry.addData("Power", bot.shooter.currentShooterPower);
            telemetry.addData("Ready", bot.shooter.isReadyToShoot());
            telemetry.addData("Artifacts", bot.intake.numberOfArtifactsLoaded);
            telemetry.addData("Color", bot.shooter.readyArtifactColor());
            telemetry.update();

        }

    }

}
