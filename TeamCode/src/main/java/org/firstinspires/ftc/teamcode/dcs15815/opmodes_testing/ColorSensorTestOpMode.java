package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.opencv.ColorRange;

@Disabled
@TeleOp(name = "Color Sensor Test", group = "Testing")
public class ColorSensorTestOpMode extends LinearOpMode {
    ColorRangeSensor sensor;

    @Override
    public void runOpMode() {

        sensor = hardwareMap.get(ColorRangeSensor.class, "sensor_lift_ball_color");

        waitForStart();

        while (!isStopRequested()) {

            telemetry.addData("Light", sensor.getLightDetected());
            telemetry.addData("Red", sensor.red());
            telemetry.addData("Green", sensor.green());
            telemetry.addData("Blue", sensor.blue());
            telemetry.addData("Alpha", sensor.alpha());
            telemetry.addData("Distance", sensor.getDistance(DistanceUnit.MM));
            telemetry.update();

        }

    }

}
