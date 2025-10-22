package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@TeleOp(name = "Multi Motor Test", group = "Testing")
public class MultiMotorTestOpMode extends LinearOpMode {
    MotorTestSettings[] motors = {
        new MotorTestSettings("motor_big_wheel", hardwareMap.dcMotor.get("motor_big_wheel"), DcMotor.Direction.FORWARD, 0),
        new MotorTestSettings("drivetrain_motor_back_left", hardwareMap.dcMotor.get("drivetrain_motor_back_left"), DcMotor.Direction.FORWARD, 0)
    };
    MotorTestSettings currentMotor;
    int currentIndex = 0;

    DefenderDebouncer yDebouncer = new DefenderDebouncer(500, () -> {
         currentMotor.keepRunning = !currentMotor.keepRunning;
    });

    DefenderDebouncer xDebouncer = new DefenderDebouncer(500, () -> {
         currentMotor.setPower(0);
    });

    DefenderDebouncer aDebouncer = new DefenderDebouncer(500, () -> {
         currentMotor.toggleDirection();
    });

    DefenderDebouncer upDebouncer = new DefenderDebouncer(500, () -> {
         currentMotor.increasePower(0.1);
    });

    DefenderDebouncer downDebouncer = new DefenderDebouncer(500, () -> {
         currentMotor.decreasePower(0.1);
    });

    DefenderDebouncer leftDebouncer = new DefenderDebouncer(500, () -> {
         currentMotor.stopBeforeSwitch(currentMotor.keepRunning);
         currentIndex--;
         if (currentIndex < 0) {
              currentIndex = motors.length - 1;
         }
         currentMotor = motors[currentIndex];
         if (currentMotor.keepRunning) {
              currentMotor.resume();
         }


    });

     DefenderDebouncer rightDebouncer = new DefenderDebouncer(500, () -> {
          currentMotor.stopBeforeSwitch(currentMotor.keepRunning);
          currentIndex++;
          if (currentIndex >= motors.length) {
               currentIndex = 0;
          }
          currentMotor = motors[currentIndex];
          if (currentMotor.keepRunning) {
               currentMotor.resume();
          }


     });

    @Override
    public void runOpMode() {
       currentMotor = motors[currentIndex];


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

            } else if (gamepad1.a) {
                 aDebouncer.run();
            }

            telemetry.addData("Motor", currentMotor.name);
            telemetry.addData("Power", currentMotor.power);
            telemetry.addData("Direction", currentMotor.direction == DcMotor.Direction.FORWARD ? "forward" : "reverse");
            telemetry.addData("Keep Running", currentMotor.keepRunning ? "yes" : "no");


            telemetry.addData("---------------", "--------");
             telemetry.addData("Power", "Up | Down");
            telemetry.addData("Motors", "Left | Right");
            telemetry.addData("Stop", "X");
            telemetry.addData("Keep Running", "Y");
            telemetry.addData("Direction", "A");


            telemetry.update();

        }

    }

}
