package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Disabled
@TeleOp(name = "Voltage Test", group = "Testing")
public class VoltageTestOpMode extends LinearOpMode {

    @Override
    public void runOpMode() {

        VoltageSensor vs = hardwareMap.voltageSensor.iterator().next();

        waitForStart();

        while (!isStopRequested()) {

            telemetry.addData("V", vs.getVoltage());
            telemetry.update();

        }

    }

}
