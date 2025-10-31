package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@Disabled
@TeleOp(name = "Multi Servo Test", group = "Testing")
public class MultiServoTestOpMode extends LinearOpMode {
    ServoTestSettings[] servos = {
        new ServoTestSettings("lift_servo_right", hardwareMap.servo.get("lift_servo_right"), 0),
        new ServoTestSettings("lift_servo_left", hardwareMap.servo.get("lift_servo_left"), 0)
    };
     ServoTestSettings currentServo;
    int currentIndex = 0;

//    DefenderDebouncer yDebouncer = new DefenderDebouncer(500, () -> {
//         currentServo.keepRunning = !currentServo.keepRunning;
//    });

    DefenderDebouncer xDebouncer = new DefenderDebouncer(500, () -> {
         currentServo.setPosition(0);
    });

//    DefenderDebouncer aDebouncer = new DefenderDebouncer(500, () -> {
//         currentCrServo.toggleDirection();
//    });

    DefenderDebouncer upDebouncer = new DefenderDebouncer(500, () -> {
         currentServo.increasePosition(0.1);
    });

    DefenderDebouncer downDebouncer = new DefenderDebouncer(500, () -> {
         currentServo.decreasePosition(0.1);
    });

    DefenderDebouncer leftDebouncer = new DefenderDebouncer(500, () -> {
//         currentServo.stopBeforeSwitch(currentServo.keepRunning);
         currentIndex--;
         if (currentIndex < 0) {
              currentIndex = servos.length - 1;
         }
         currentServo = servos[currentIndex];
//         if (currentServo.keepRunning) {
//              currentServo.resume();
//         }


    });

     DefenderDebouncer rightDebouncer = new DefenderDebouncer(500, () -> {
//          currentServo.stopBeforeSwitch(currentServo.keepRunning);
          currentIndex++;
          if (currentIndex >= servos.length) {
               currentIndex = 0;
          }
          currentServo = servos[currentIndex];
//          if (currentServo.keepRunning) {
//               currentServo.resume();
//          }


     });

    @Override
    public void runOpMode() {
       currentServo = servos[currentIndex];


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

//            } else if (gamepad1.y) {
//                 yDebouncer.run();

            } else if (gamepad1.x) {
                 xDebouncer.run();

//            } else if (gamepad1.a) {
//                 aDebouncer.run();
            }

            telemetry.addData("Servo", currentServo.name);
            telemetry.addData("Position", currentServo.position);
//            telemetry.addData("Direction", currentCrServo.direction == DcMotor.Direction.FORWARD ? "forward" : "reverse");
//            telemetry.addData("Keep Running", currentCrServo.keepRunning ? "yes" : "no");


            telemetry.addData("---------------", "--------");
             telemetry.addData("Position", "Up | Down");
            telemetry.addData("Servos", "Left | Right");
            telemetry.addData("Zero", "X");
//            telemetry.addData("Keep Running", "Y");
//            telemetry.addData("Direction", "A");


            telemetry.update();

        }

    }

}
