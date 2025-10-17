package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPresets;

public class DecodeConfiguration extends DefenderBotConfiguration {

    /* DRIVETRAIN -------------------------------------------------- */

//    public static String DRIVETRAIN_BACK_LEFT_MOTOR_NAME = "back_left_motor"; // Control Hub, Motor 0
//    public static String DRIVETRAIN_FRONT_LEFT_MOTOR_NAME = "front_left_motor"; // Control Hub, Motor 1
//    public static String DRIVETRAIN_FRONT_RIGHT_MOTOR_NAME = "front_right_motor"; // Control Hub, Motor 2
//    public static String DRIVETRAIN_BACK_RIGHT_MOTOR_NAME = "back_right_motor"; // Control Hub, Motor 3
    public static DcMotorSimple.Direction DRIVETRAIN_BACK_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONT_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONT_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction DRIVETRAIN_BACK_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;

    public static double DRIVETRAIN_POWER_MAX = 1;
    public static int DRIVETRAIN_MAX_TICKS_PER_SECOND = 2800;

    /* SHOULDER -------------------------------------------------------- */

//    public static String SHOULDER_LEFT_MOTOR_NAME = "shoulder_left_motor"; // Expansion Hub, Motor 0
//    public static String SHOULDER_RIGHT_MOTOR_NAME = "shoulder_right_motor"; // Expansion Hub, Motor 1
//    public static DcMotorSimple.Direction SHOULDER_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
//    public static DcMotorSimple.Direction SHOULDER_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
//    public static String SHOULDER_DOWN_SENSOR = "shoulder_down_sensor"; // Control Hub, Digital 0
//    public static String SHOULDER_UP_SENSOR = "shoulder_up_sensor"; // Control Hub, Digital 2
//    public static double SHOULDER_POWER_MAX = 1;
//    public static double SHOULDER_POWER_NORMAL = 0.6;
//    public static int SHOULDER_ASCEND_PREP_MAX = 3200;
//
//    public static int SHOULDER_POSITION_MAX = 3200;
//    public static int SHOULDER_POSITION_MIN = 0;
//    public static int SHOULDER_POSITION_ASCENDED = -200;
//    public static int SHOULDER_POSITION_AUTON_INTAKE = -125;
//    public static int SHOULDER_POSITION_AUTON_TRAVEL = 500;
//
//    public static int SHOULDER_POSITION_AUTON_SPECIMEN_INTAKE_PRE = 3010;
//    public static int SHOULDER_POSITION_AUTON_SPECIMEN_INTAKE_POST = 3010;
//
//    public static int SHOULDER_POSITION_AUTON_SPECIMEN_DELIVERY_PRE = 3333;
//    public static int SHOULDER_POSITION_AUTON_SPECIMEN_DELIVERY_POST = 3333;





//    public static int SHOULDER_POSITION_DELTA = 250;

    /* ARM -------------------------------------------------------- */

//    public static String ARM_LEFT_MOTOR_NAME = "arm_left_motor"; // Expansion Hub, Motor 2
//    public static String ARM_RIGHT_MOTOR_NAME = "arm_right_motor"; // Expansion Hub, Motor 3
//    public static DcMotorSimple.Direction ARM_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
//    public static DcMotorSimple.Direction ARM_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
//    public static String ARM_RETRACTED_SENSOR = "arm_retracted_sensor"; // Expansion Hub, Digital 0
//    public static double ARM_POWER_MAX = 1;
//    public static double ARM_POWER_NORMAL = 0.6;
//    public static int ARM_POSITION_ASCENDED = -400;
//
//    public static int ARM_POSITION_MIN = 0;
//    public static int ARM_POSITION_MAX = 3100; // 3200 appears to cause battery fuse to blow
//    public static int ARM_POSITION_DELTA = 125;
//    public static int ARM_POSITION_AUTON_INTAKE = -20;
//
//    public static int ARM_POSITION_AUTON_SPECIMEN_INTAKE_PRE = 1103;
//    public static int ARM_POSITION_AUTON_SPECIMEN_INTAKE_POST = 0;
//
//    public static int ARM_POSITION_AUTON_SPECIMEN_DELIVERY_PRE = 2917; // started with 2917
//    public static int ARM_POSITION_AUTON_SPECIMEN_DELIVERY_POST = 1600;


    // all the way out 3000

    /* WRIST -------------------------------------------------------- */

//    public static String WRIST_SERVO_NAME = "wrist_servo"; // Expansion Hub, Servo 0
//    public static double WRIST_SERVO_POSITION_TOP = 0.8;
//    public static double WRIST_SERVO_POSITION_AUTON_INTAKE = 0.6;
//    public static double WRIST_SERVO_POSITION_TOPMIDDLE = 0.4;
//    public static double WRIST_SERVO_POSITION_MIDDLE = 0.3;
//    public static double WRIST_SERVO_POSITION_BOTTOM = 0.15;
//    public static double WRIST_POSITION_DELTA = 0.1;
//
//    public static DefenderPresets<Double> WRIST_PRESETS = new DefenderPresets<>(
//            NautilusConfiguration.WRIST_SERVO_POSITION_TOP,
//            NautilusConfiguration.WRIST_SERVO_POSITION_AUTON_INTAKE,
//
//            NautilusConfiguration.WRIST_SERVO_POSITION_TOPMIDDLE,
////            NautilusConfiguration.WRIST_SERVO_POSITION_MIDDLE,
//            NautilusConfiguration.WRIST_SERVO_POSITION_BOTTOM
//    );

    /* INTAKE -------------------------------------------------------- */

//    public static String INTAKE_LEFT_SERVO_NAME = "intake_left_servo"; // Expansion Hub, Servo 1
//    public static String INTAKE_RIGHT_SERVO_NAME = "intake_right_servo"; // Expansion Hub, Servo 2
//    public static String INTAKE_LOADED_SENSOR = "intake_loaded_sensor"; // Expansion Hub, Digital 0
//
//    public static double INTAKE_LEFT_IN_POWER = 1;
//    public static double INTAKE_RIGHT_IN_POWER = -1;
//    public static double INTAKE_LEFT_OUT_POWER = -0.4;
//    public static double INTAKE_RIGHT_OUT_POWER = 0.4;
//    public static double INTAKE_LEFT_OUT_TO_LEFT_POWER = -0.8;
//    public static double INTAKE_RIGHT_OUT_TO_LEFT_POWER = 0.0;


    /* GAMEPADS -------------------------------------------------------- */

//    public static double GAMEPAD2_RIGHT_STICK_CURVE = 2;
//    public static double GAMEPAD2_RIGHT_STICK_MAX = 1;
//
//    public static double GAMEPAD1_LEFT_STICK_Y_CURVE = 2.5;
//    public static double GAMEPAD1_LEFT_STICK_Y_MAX = 1;
//
//    public static double GAMEPAD1_RIGHT_STICK_X_CURVE = 2;
//    public static double GAMEPAD1_RIGHT_STICK_X_MAX = 0.5;


    /* NAVIGATION -------------------------------------------------------- */

//    public static String IMU_SENSOR_NAME = "imu";
//    public static AxesOrder IMU_AXES_ORDER = AxesOrder.XYZ;
//
//    public static double NAVIGATION_TOLERANCE = 1.0;
//
    public static double NAVIGATION_ANGULAR_SCALE = 0.997;
    public static double NAVIGATION_LINEAR_SCALE = 0.978;
//
//    public static double NAVIGATION_TOLERANCE_R = 1.0;
//    public static double NAVIGATION_TOLERANCE_X = 1.0;
//    public static double NAVIGATION_TOLERANCE_Y = 7.0; // in degrees
//
//    public static double NAVIGATION_X_KP = 0.2;
//    public static double NAVIGATION_X_KI = 0.4;
//    public static double NAVIGATION_X_KD = 0.03;
//
//    public static double NAVIGATION_Y_KP = 0.5;
//    public static double NAVIGATION_Y_KI = 4.5;
//    public static double NAVIGATION_Y_KD = 0.12;
//
//    public static double NAVIGATION_R_KP = 0.2;
//    public static double NAVIGATION_R_KI = 1.0;
//    public static double NAVIGATION_R_KD = 0.003;
//
//    public static double NAVIGATION_X_MAXPOWER = 0.6; // was 0.6
//    public static double NAVIGATION_Y_MAXPOWER = 0.3; // was .4
//    public static double NAVIGATION_R_MAXPOWER = 0.4; // was 0.3
//
//    public static long NAVIGATION_SETTLING_TIME = 500;

    /* EFFECTS -------------------------------------------------------- */

//    public static String EFFECTS_LEDS_NAME = "effects_leds"; // Control Hub, Servo 5


    /* PRESETS -------------------------------------------------------- */

//    public static NautilusManipulatorPosition PREPARE_TO_ASCEND_POSITION = new NautilusManipulatorPosition(
//            NautilusConfiguration.SHOULDER_ASCEND_PREP_MAX, 2900, NautilusConfiguration.WRIST_SERVO_POSITION_TOP
//    );
//
//    public static NautilusManipulatorPosition ASCENDED_POSITION = new NautilusManipulatorPosition(
//            0, 0, NautilusConfiguration.WRIST_SERVO_POSITION_TOP
//    );



    /* METHODS -------------------------------------------------------- */

    public DecodeConfiguration() {
        super();
    }

}