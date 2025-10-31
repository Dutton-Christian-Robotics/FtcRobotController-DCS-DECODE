package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name = "LED Test", group = "Testing")
public class LedTestOpMode extends LinearOpMode {
    RevBlinkinLedDriver leds;
    RevBlinkinLedDriver.BlinkinPattern pattern;
    public void runOpMode() {
        leds = hardwareMap.get(RevBlinkinLedDriver.class, "effects_leds");
        waitForStart();
        while (opModeIsActive()) {
            pattern = RevBlinkinLedDriver.BlinkinPattern.BLUE;
            leds.setPattern(pattern);
            sleep(3000);
            pattern = RevBlinkinLedDriver.BlinkinPattern.RED;
            leds.setPattern(pattern);
            sleep(3000);

            pattern = RevBlinkinLedDriver.BlinkinPattern.STROBE_BLUE;
            leds.setPattern(pattern);
            sleep(3000);
            pattern = RevBlinkinLedDriver.BlinkinPattern.STROBE_RED;
            leds.setPattern(pattern);
            sleep(3000);

            pattern = RevBlinkinLedDriver.BlinkinPattern.SHOT_BLUE;
            leds.setPattern(pattern);
            sleep(3000);
            pattern = RevBlinkinLedDriver.BlinkinPattern.SHOT_RED;
            leds.setPattern(pattern);
            sleep(3000);


            pattern = RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_BLUE;
            leds.setPattern(pattern);
            sleep(3000);
            pattern = RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_RED;
            leds.setPattern(pattern);
            sleep(3000);
        }

    }
}
