package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@Disabled
@TeleOp(name = "Multi CrServo Test", group = "Testing")
public class MultiCrServoTestOpMode extends LinearOpMode {
    CrServoTestSettings[] crServos = {
        new CrServoTestSettings("intake_upper_right", hardwareMap.crservo.get("intake_upper_right"), 0),
        new CrServoTestSettings("intake_upper_left", hardwareMap.crservo.get("intake_upper_left"), 0),
        new CrServoTestSettings("intake_medium_left", hardwareMap.crservo.get("intake_medium_left"), 0),
        new CrServoTestSettings("intake_medium_right", hardwareMap.crservo.get("intake_medium_right"), 0),
        new CrServoTestSettings("intake_servo_lower", hardwareMap.crservo.get("intake_servo_lower"), 0),
    };
     CrServoTestSettings currentCrServo;
    int currentIndex = 0;

    DefenderDebouncer yDebouncer = new DefenderDebouncer(500, () -> {
         currentCrServo.keepRunning = !currentCrServo.keepRunning;
    });

    DefenderDebouncer xDebouncer = new DefenderDebouncer(500, () -> {
         currentCrServo.setPower(0);
    });

//    DefenderDebouncer aDebouncer = new DefenderDebouncer(500, () -> {
//         currentCrServo.toggleDirection();
//    });

    DefenderDebouncer upDebouncer = new DefenderDebouncer(500, () -> {
         currentCrServo.increasePower(0.1);
    });

    DefenderDebouncer downDebouncer = new DefenderDebouncer(500, () -> {
         currentCrServo.decreasePower(0.1);
    });

    DefenderDebouncer leftDebouncer = new DefenderDebouncer(500, () -> {
         currentCrServo.stopBeforeSwitch(currentCrServo.keepRunning);
         currentIndex--;
         if (currentIndex < 0) {
              currentIndex = crServos.length - 1;
         }
         currentCrServo = crServos[currentIndex];
         if (currentCrServo.keepRunning) {
              currentCrServo.resume();
         }


    });

     DefenderDebouncer rightDebouncer = new DefenderDebouncer(500, () -> {
          currentCrServo.stopBeforeSwitch(currentCrServo.keepRunning);
          currentIndex++;
          if (currentIndex >= crServos.length) {
               currentIndex = 0;
          }
          currentCrServo = crServos[currentIndex];
          if (currentCrServo.keepRunning) {
               currentCrServo.resume();
          }


     });

    @Override
    public void runOpMode() {
       currentCrServo = crServos[currentIndex];


        waitForStart();

        while (!isStopRequested()) {
            if (gamepad1.dpad_up) {
                 upDebouncer.run();

            } else if (gamepad1.dpad_down) {
                 downDebouncer.run();

            } else if (gamepad1.dpad_left) {
                  leftDebouncer.run();

            } else if (gamepad1.dpad_right) {
                 rightDebouncer.run();

            } else if (gamepad1.y) {
                 yDebouncer.run();

            } else if (gamepad1.x) {
                 xDebouncer.run();

//            } else if (gamepad1.a) {
//                 aDebouncer.run();
            }

            telemetry.addData("Motor", currentCrServo.name);
            telemetry.addData("Power", currentCrServo.power);
//            telemetry.addData("Direction", currentCrServo.direction == DcMotor.Direction.FORWARD ? "forward" : "reverse");
            telemetry.addData("Keep Running", currentCrServo.keepRunning ? "yes" : "no");


            telemetry.addData("---------------", "--------");
             telemetry.addData("Power", "Up | Down");
            telemetry.addData("Servos", "Left | Right");
            telemetry.addData("Stop", "X");
            telemetry.addData("Keep Running", "Y");
//            telemetry.addData("Direction", "A");


            telemetry.update();

        }

    }

}
