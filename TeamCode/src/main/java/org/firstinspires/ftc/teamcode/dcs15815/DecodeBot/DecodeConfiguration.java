package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPresets;

public class DecodeConfiguration extends DefenderBotConfiguration {

    /* DRIVETRAIN -------------------------------------------------- */

    public static String DRIVETRAIN_MOTOR_BACK_LEFT_NAME = "motor_back_left"; //
    public static String DRIVETRAIN_MOTOR_FRONT_LEFT_NAME = "motor_front_left"; //
    public static String DRIVETRAIN_MOTOR_FRONT_RIGHT_NAME = "motor_front_right"; //
    public static String DRIVETRAIN_MOTOR_BACK_RIGHT_NAME = "motor_back_right"; //

    public static DcMotorSimple.Direction DRIVETRAIN_MOTOR_BACK_LEFT_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_MOTOR_FRONT_LEFT_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_MOTOR_FRONT_RIGHT_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction DRIVETRAIN_MOTOR_BACK_RIGHT_DIRECTION = DcMotorSimple.Direction.REVERSE;

    public static double DRIVETRAIN_POWER_MAX = 1;
    public static int DRIVETRAIN_MAX_TICKS_PER_SECOND = 2800;


    /* INTAKE ---------------------------------------------------------- */

    public static String INTAKE_SERVO_UPPER_LEFT_NAME = "intake_upper_left";
    public static String INTAKE_SERVO_UPPER_RIGHT_NAME = "intake_upper_right";
    public static String INTAKE_SERVO_MIDDLE_LEFT_NAME = "intake_medium_left";
    public static String INTAKE_SERVO_MIDDLE_RIGHT_NAME = "intake_medium_right";
    public static String INTAKE_SERVO_LOWER_NAME = "intake_servo_lower";

    public static String INTAKE_MOTOR_CAROUSEL_NAME = "motor_big_wheel";
    public static DcMotorSimple.Direction INTAKE_MOTOR_CAROUSEL_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static double INTAKE_MOTOR_CAROUSEL_POWER = 0.3;
    public static long INTAKE_MOTOR_CAROUSEL_TIME_ADVANCE = 600; //was 500

    public static int INTAKE_SERVO_UPPER_LEFT_DIRECTION = -1;
    public static int INTAKE_SERVO_UPPER_RIGHT_DIRECTION = 1;
    public static int INTAKE_SERVO_MIDDLE_LEFT_DIRECTION = -1;
    public static int INTAKE_SERVO_MIDDLE_RIGHT_DIRECTION = 1;
    public static int INTAKE_SERVO_LOWER_DIRECTION = 1;

    public static double INTAKE_SERVO_POWER_MAX = 1;

    public static String INTAKE_SENSOR_CAPTURE_NAME = "intake_sensor_capture";

    /* SHOOTER ---------------------------------------------------------- */

    public static String SHOOTER_SERVO_LIFT_LEFT_NAME = "lift_servo_left";
    public static String SHOOTER_SERVO_LIFT_RIGHT_NAME = "lift_servo_right";

    public static String SHOOTER_MOTOR_LEFT_NAME = "motor_launch_left";
    public static String SHOOTER_MOTOR_RIGHT_NAME = "motor_launch_right";

    public static DcMotorSimple.Direction SHOOTER_MOTOR_LEFT_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction SHOOTER_MOTOR_RIGHT_DIRECTION = DcMotorSimple.Direction.REVERSE;

//    public static int SHOOTER_SERVO_LIFT_LEFT_DIRECTION = 1;
//    public static int SHOOTER_SERVO_LIFT_RIGHT_DIRECTION = 1;

    public static double SHOOTER_SERVO_LIFT_LEFT_POSITION_UP = 1;
    public static double SHOOTER_SERVO_LIFT_LEFT_POSITION_DOWN = 0;
    public static double SHOOTER_SERVO_LIFT_RIGHT_POSITION_UP = 0;
    public static double SHOOTER_SERVO_LIFT_RIGHT_POSITION_DOWN = 1;

    public static double SHOOTER_MOTOR_POWER_MAX = 1;
    public static double SHOOTER_MOTOR_POWER_MIN = 0.1;
    public static double SHOOTER_MOTOR_POWER_START = 0.90;
    public static long SHOOTER_LIFT_TIME_SLEEP = 600;

    public static String SHOOTER_SENSOR_READY_NAME = "sensor_lift_ball_color";
    public static double SHOOTER_SENSOR_READY_THRESHOLD_LIGHT = 0.3;


    /* GAMEPADS -------------------------------------------------------- */

//    public static double GAMEPAD2_RIGHT_STICK_CURVE = 2;
//    public static double GAMEPAD2_RIGHT_STICK_MAX = 1;
//
//    public static double GAMEPAD1_LEFT_STICK_Y_CURVE = 2.5;
//    public static double GAMEPAD1_LEFT_STICK_Y_MAX = 1;
//
//    public static double GAMEPAD1_RIGHT_STICK_X_CURVE = 2;
//    public static double GAMEPAD1_RIGHT_STICK_X_MAX = 0.5;

    /* EFFECTS -------------------------------------------------------- */

    public static String EFFECTS_LEDS_NAME = "effects_leds"; // Control Hub, Servo 5


    /* NAVIGATION -------------------------------------------------------- */

//    public static String IMU_SENSOR_NAME = "imu";
//    public static AxesOrder IMU_AXES_ORDER = AxesOrder.XYZ;
//
    public static double NAVIGATION_TOLERANCE = 1.0;
//
    public static double NAVIGATION_ANGULAR_SCALE = 0.997;
    public static double NAVIGATION_LINEAR_SCALE = 0.978;

    public static double NAVIGATION_TOLERANCE_R = 1.0;
    public static double NAVIGATION_TOLERANCE_X = 1.0;
    public static double NAVIGATION_TOLERANCE_Y = 7.0; // in degrees

    public static double NAVIGATION_X_KP = 0.2;
    public static double NAVIGATION_X_KI = 0.4;
    public static double NAVIGATION_X_KD = 0.03;

    public static double NAVIGATION_Y_KP = 0.5;
    public static double NAVIGATION_Y_KI = 4.5;
    public static double NAVIGATION_Y_KD = 0.12;

    public static double NAVIGATION_R_KP = 0.2;
    public static double NAVIGATION_R_KI = 1.0;
    public static double NAVIGATION_R_KD = 0.003;

    public static double NAVIGATION_X_MAXPOWER = 0.6;
    public static double NAVIGATION_Y_MAXPOWER = 0.3;
    public static double NAVIGATION_R_MAXPOWER = 0.3;

    public static long NAVIGATION_SETTLING_TIME = 400; //was 500



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