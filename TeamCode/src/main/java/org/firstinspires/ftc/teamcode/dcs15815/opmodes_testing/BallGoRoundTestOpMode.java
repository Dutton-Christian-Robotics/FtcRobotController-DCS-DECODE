package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Disabled
@TeleOp(name = "Big Wheel Test", group = "Testing")
public class BallGoRoundTestOpMode extends LinearOpMode {
    DcMotor motor;
    ColorRangeSensor sensor;

    @Override
    public void runOpMode() {

        motor = hardwareMap.dcMotor.get("motor_big_wheel");
        motor.setDirection(DcMotor.Direction.REVERSE);
        sensor = hardwareMap.get(ColorRangeSensor.class, "sensor_lift_ball_color");

        double power = 0.3;
        double distanceLimit = 50;
        double distance = 0;

        waitForStart();

        while (!isStopRequested()) {
            motor.setPower(power);
            distance = sensor.getDistance(DistanceUnit.MM);
            if (distance > distanceLimit) {
                motor.setPower(0);
            }

            telemetry.addData("Distance", distance);
            telemetry.update();

        }
        motor.setPower(0);

    }

}
