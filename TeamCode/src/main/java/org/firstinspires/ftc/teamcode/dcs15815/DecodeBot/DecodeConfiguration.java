package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;
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
    public static double DRIVETRAIN_POWER_MAX_AUTONOMOUS = 0.4;
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
    public static double INTAKE_MOTOR_CAROUSEL_POWER_ADVANCE = 0.225; // used to be 0.3
    public static long INTAKE_MOTOR_CAROUSEL_TIME_ADVANCE = 600; //was 500

    public static int INTAKE_SERVO_UPPER_LEFT_DIRECTION = -1;
    public static int INTAKE_SERVO_UPPER_RIGHT_DIRECTION = 1;
    public static int INTAKE_SERVO_MIDDLE_LEFT_DIRECTION = -1;
    public static int INTAKE_SERVO_MIDDLE_RIGHT_DIRECTION = 1;
    public static int INTAKE_SERVO_LOWER_DIRECTION = 1;

    public static double INTAKE_SERVO_POWER_MAX = 1;

    public static String INTAKE_SENSOR_CAPTURE_NAME = "intake_sensor_capture";

    /* SHOOTER ---------------------------------------------------------- */

//    public static String SHOOTER_SERVO_LIFT_LEFT_NAME = "lift_servo_left";
//    public static String SHOOTER_SERVO_LIFT_RIGHT_NAME = "lift_servo_right";

    public static String SHOOTER_SERVO_DEFLECTOR_NAME = "deflector_servo";
    public static String SHOOTER_SERVO_GATE_NAME = "gate_servo";

    public static double SHOOTER_SERVO_DEFLECTOR_POSITION_UP = 0;
    public static double SHOOTER_SERVO_DEFLECTOR_POSITION_DOWN = 1;

    public static double SHOOTER_SERVO_GATE_POSITION_OPEN = 1;
    public static double SHOOTER_SERVO_GATE_POSITION_CLOSED = 0;

    public static String SHOOTER_MOTOR_LEFT_NAME = "motor_launch_left";
    public static String SHOOTER_MOTOR_RIGHT_NAME = "motor_launch_right";

    public static double SHOOTER_MOTOR_VELOCITY_MAX = 2400;

    public static DcMotorSimple.Direction SHOOTER_MOTOR_LEFT_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction SHOOTER_MOTOR_RIGHT_DIRECTION = DcMotorSimple.Direction.REVERSE;

    public static double SHOOTER_MOTOR_LEFT_BIAS_FACTOR = 0.2;
    public static double SHOOTER_MOTOR_RIGHT_BIAS_FACTOR = 0.2;

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
    public static long SHOOTER_TIME_BETWEEN_SHOTS = 150;

    public static long SHOOTER_TIME_AFTER_GATE_OPENS = 150;
    public static long SHOOTER_TIME_FOR_SHOOTING = 2700; //was 2500

//    public static String SHOOTER_SENSOR_READY_NAME = "sensor_lift_ball_color";
//    public static double SHOOTER_SENSOR_READY_THRESHOLD_LIGHT = 0.3;


    /* GAMEPADS -------------------------------------------------------- */

    /* EFFECTS -------------------------------------------------------- */

    public static String EFFECTS_LEDS_NAME = "effects_leds"; // Control Hub, Servo 5


    /* NAVIGATION -------------------------------------------------------- */

//    public static String IMU_SENSOR_NAME = "imu";
//    public static AxesOrder IMU_AXES_ORDER = AxesOrder.XYZ;
//
    public static double NAVIGATION_TOLERANCE = 1.0;

    public static String NAVIGATION_PINPOINT_NAME = "pinpoint";
    public static double NAVIGATION_PINPOINT_OFFSET_X = 87.5;
    public static double NAVIGATION_PINPOINT_OFFSET_Y = -135.0;
    public static DistanceUnit NAVIGATION_PINPOINT_OFFSET_DISTANCEUNIT = DistanceUnit.MM;
    public static GoBildaPinpointDriver.EncoderDirection NAVIGATION_PINPOINT_ENCODERDIRECTION_X = GoBildaPinpointDriver.EncoderDirection.FORWARD;
    public static GoBildaPinpointDriver.EncoderDirection NAVIGATION_PINPOINT_ENCODERDIRECTION_Y = GoBildaPinpointDriver.EncoderDirection.FORWARD;
    public static GoBildaPinpointDriver.GoBildaOdometryPods NAVIGATION_POD_TYPE = GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD;

    public static double NAVIGATION_ANGULAR_SCALE = 0.997;
    public static double NAVIGATION_LINEAR_SCALE = 0.978;

    public static double NAVIGATION_TOLERANCE_R = 1.0;
    public static double NAVIGATION_TOLERANCE_Y = 7.0; // in degrees
    public static double NAVIGATION_TOLERANCE_X = 1.0;

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

    /* GAMEPADS -------------------------------------------------------- */

    public static double GAMEPAD1_RIGHT_STICK_X_CURVE = 4;
    public static double GAMEPAD1_RIGHT_STICK_X_MAX = 1;

    /* PRESETS -------------------------------------------------------- */

//    public static NautilusManipulatorPosition PREPARE_TO_ASCEND_POSITION = new NautilusManipulatorPosition(
//            NautilusConfiguration.SHOULDER_ASCEND_PREP_MAX, 2900, NautilusConfiguration.WRIST_SERVO_POSITION_TOP
//    );
//
//    public static NautilusManipulatorPosition ASCENDED_POSITION = new NautilusManipulatorPosition(
//            0, 0, NautilusConfiguration.WRIST_SERVO_POSITION_TOP
//    );

    /* AUTONOMOUS -------------------------------------------------------- */

    public static double SHOOTER_MOTOR_POWER_AUTON = 0.985;

    public static DefenderBotPosition BLUE_LINE1_START = new DefenderBotPosition(7, 32, 90);
    public static DefenderBotPosition BLUE_LINE1_END = new DefenderBotPosition(7, 53, 90);
    public static DefenderBotPosition BLUE_LINE2_START = new DefenderBotPosition(-15, 32, 90);
    public static DefenderBotPosition BLUE_LINE2_END = new DefenderBotPosition(-15, 58, 90);



    public static DefenderBotPosition BLUE1_START = new DefenderBotPosition(50, 50, -135);
    public static DefenderBotPosition BLUE1_SHOOT_PRELOAD = new DefenderBotPosition(43, 43, -135);
    public static DefenderBotPosition BLUE1_END = new DefenderBotPosition(43, 35, -135);

    public static DefenderBotPosition BLUE2_START = BLUE1_START;
    public static DefenderBotPosition BLUE2_SHOOT_PRELOAD = BLUE1_SHOOT_PRELOAD;
    public static DefenderBotPosition BLUE2_END = BLUE1_END;

    public static DefenderBotPosition BLUE3_START = BLUE1_START;
    public static DefenderBotPosition BLUE3_SHOOT_PRELOAD = BLUE1_SHOOT_PRELOAD;
    public static DefenderBotPosition BLUE3_END = BLUE1_END;



    /* METHODS -------------------------------------------------------- */

    public DecodeConfiguration() {
        super();
    }

}