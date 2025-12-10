package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.DecodeAutonomousOpMode;

@Disabled
@Autonomous(name = "Blue 3sm", group = "3", preselectTeleOp = "Driver Operated")
public class Blue3AutonomousNoDSMOpMode extends DecodeAutonomousOpMode {

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
	}

	;

	enum StateMachineState {
		STARTUP_SHOOTER,
		MOVE_TO_SHOOT_PRELOADED,
		SHOOT_PRELOADED,
		DRIVE_TO_LINE_1,
		INTAKE_LINE_1,
		MOVE_TO_SHOOT_SECOND_THREE,
		SHOOT_SECOND_THREE,
		DRIVE_TO_LINE_2,
		INTAKE_LINE_2,
		BACKUP_ON_LINE_2,
		MOVE_TO_SHOOT_THIRD_THREE,
		SHOOT_THIRD_THREE,
		DRIVE_OFF_LINE,

//		ROTATE_FOR_LINE_1_CAPTURE,
		DONE
	}
//
//	static final Pose2D STARTING_POSITION = new Pose2D(DistanceUnit.INCH, 50, 50, AngleUnit.DEGREES, -135);
//	static final Pose2D FIRST_SHOOTING_POSITION = new Pose2D(DistanceUnit.INCH, 41, 41, AngleUnit.DEGREES, -135);
//	static final Pose2D START_OF_LINE_1 = new Pose2D(DistanceUnit.INCH, 7, 32, AngleUnit.DEGREES, 90);
//	static final Pose2D END_OF_LINE_1 = new Pose2D(DistanceUnit.INCH, 7, 50, AngleUnit.DEGREES, 90);
//
//	static final Pose2D START_OF_LINE_2 = new Pose2D(DistanceUnit.INCH, -7, 32, AngleUnit.DEGREES, 90);
//	static final Pose2D END_OF_LINE_2 = new Pose2D(DistanceUnit.INCH, -7, 50, AngleUnit.DEGREES, 90);
//


	//	static final Pose2D START_OF_LINE_1_CORRECT_HEADING = new Pose2D(DistanceUnit.INCH, 4, 24, AngleUnit.DEGREES, 90);

//	static final Pose2D STARTING_POSITION = new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0);
//	static final Pose2D FIRST_SHOOTING_POSITION = new Pose2D(DistanceUnit.INCH, 6, 0, AngleUnit.DEGREES, 0);
//	static final Pose2D START_OF_LINE_1 = new Pose2D(DistanceUnit.INCH, 24, -12, AngleUnit.DEGREES, -135);

	Pose2D target;
	String data1, data2;

	@Override
	public void performAutonomous() {

		bot.navigation.setPosition(DecodeConfiguration.BLUE3_START);
		StateMachineState currentState = StateMachineState.STARTUP_SHOOTER;

		telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
		telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
		telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
		telemetry.addData("err", bot.navigation.totalError);


		while (opModeIsActive()) {
			bot.navigation.updatePosition();
			// these two should be in a prestate

			switch (currentState) {
				case STARTUP_SHOOTER:
					bot.navigation.setPosition("BLUE3_START");
					bot.shooter.raiseDeflector();
					bot.shooter.changeShooterPower(DecodeConfiguration.SHOOTER_MOTOR_POWER_AUTON);
					currentState = StateMachineState.MOVE_TO_SHOOT_PRELOADED;
					telemetry.addLine("Finished STARTUP_SHOOTER");
					break;

				case MOVE_TO_SHOOT_PRELOADED:
//					target = FIRST_SHOOTING_POSITION;
					if (bot.navigation.driveToFromCurrent(DecodeConfiguration.BLUE3_SHOOT_PRELOAD.asPose2D())) {
						currentState = StateMachineState.SHOOT_PRELOADED;
						telemetry.addLine("Finished MOVE_TO_SHOOT_PRELOADED");
					}
					break;

				case SHOOT_PRELOADED:
					bot.shooter.shootAutonomously();
					currentState = StateMachineState.DRIVE_TO_LINE_1;
					telemetry.addLine("Finished SHOOT_PRELOADED");
					break;

				case DRIVE_TO_LINE_1:
//					target = START_OF_LINE_1;
					if (bot.navigation.driveToFromCurrent(DecodeConfiguration.BLUE_LINE1_START.asPose2D())) {
						currentState = StateMachineState.INTAKE_LINE_1;
						bot.intake.turnOn();
						telemetry.addLine("Finished DRIVE_TO_LINE_1");

					}
					telemetry.addData("where", "DRIVE_TO_LINE_1");
					break;

				case INTAKE_LINE_1:
//					target = END_OF_LINE_1;
					if (bot.navigation.driveToFromCurrent(DecodeConfiguration.BLUE_LINE1_END.asPose2D())) {
						currentState = StateMachineState.MOVE_TO_SHOOT_SECOND_THREE;
						sleep(DecodeConfiguration.INTAKE_TIME_WAIT_AUTONOMOUS);

						telemetry.addLine("Finished INTAKE_LINE_1");
						bot.intake.turnOff();

					}
					telemetry.addData("where", "INTAKE_LINE_1");

					break;

				case MOVE_TO_SHOOT_SECOND_THREE:
//					target = FIRST_SHOOTING_POSITION;
					if (bot.navigation.driveToFromCurrent(DecodeConfiguration.BLUE3_SHOOT_PRELOAD.asPose2D())) {
						bot.stopDriving();
						currentState = StateMachineState.SHOOT_SECOND_THREE;
						telemetry.addLine("Finished MOVE_TO_SHOOT_SECOND_THREE");

					}
					break;

				case SHOOT_SECOND_THREE:
					bot.shooter.shootAutonomously();
					currentState = StateMachineState.DRIVE_TO_LINE_2;
					break;

				case DRIVE_TO_LINE_2:
//					target = START_OF_LINE_1;
					if (bot.navigation.driveToFromCurrent(DecodeConfiguration.BLUE_LINE2_START.asPose2D())) {
						currentState = StateMachineState.INTAKE_LINE_2;
						bot.intake.turnOn();
						telemetry.addLine("Finished DRIVE_TO_LINE_2");

					}
					telemetry.addData("where", "DRIVE_TO_LINE_2");
					break;

				case INTAKE_LINE_2:
//					target = END_OF_LINE_2;
					if (bot.navigation.driveToFromCurrent(DecodeConfiguration.BLUE_LINE2_END.asPose2D(), 0, .8 * DecodeConfiguration.DRIVETRAIN_POWER_MAX_AUTONOMOUS)) {
						currentState = StateMachineState.BACKUP_ON_LINE_2;
						sleep(DecodeConfiguration.INTAKE_TIME_WAIT_AUTONOMOUS);

						telemetry.addLine("Finished INTAKE_LINE_2");
						bot.intake.turnOff();

					}
					telemetry.addData("where", "INTAKE_LINE_2");

					break;

				case BACKUP_ON_LINE_2:
//					target = FIRST_SHOOTING_POSITION;
					if (bot.navigation.driveToFromCurrent(DecodeConfiguration.BLUE_LINE2_START.asPose2D())) {
						bot.stopDriving();
						currentState = StateMachineState.MOVE_TO_SHOOT_THIRD_THREE;
//						telemetry.addLine("Finished MOVE_TO_SHOOT_THIRD_THREE");

					}
					break;

				case MOVE_TO_SHOOT_THIRD_THREE:
//					target = FIRST_SHOOTING_POSITION;
					if (bot.navigation.driveToFromCurrent(DecodeConfiguration.BLUE3_SHOOT_PRELOAD.asPose2D())) {
						bot.stopDriving();
						currentState = StateMachineState.SHOOT_THIRD_THREE;
						telemetry.addLine("Finished MOVE_TO_SHOOT_THIRD_THREE");

					}
					break;

				case SHOOT_THIRD_THREE:
					bot.shooter.shootAutonomously();
					currentState = StateMachineState.DRIVE_OFF_LINE;
					break;

				case DRIVE_OFF_LINE:
//					target = FIRST_SHOOTING_POSITION;
					if (bot.navigation.driveToFromCurrent(DecodeConfiguration.BLUE3_END.asPose2D())) {
						bot.stopDriving();
						currentState = StateMachineState.DONE;
						telemetry.addLine("Finished MOVE_TO_SHOOT_THIRD_THREE");

					}
					break;

				case DONE:
					bot.stopDriving();
					bot.intake.turnOff();
					bot.shooter.turnOff();
					bot.shooter.lowerDeflector();
					break;

				default:
					bot.stopDriving();
			}
//			data1 = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}",
//				   bot.navigation.getPosition().getX(DistanceUnit.INCH),
//				   bot.navigation.getPosition().getY(DistanceUnit.INCH),
//				   bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
//			data2 = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}",
//				   target.getX(DistanceUnit.INCH),
//				   target.getY(DistanceUnit.INCH),
//				   target.getHeading(AngleUnit.DEGREES));
//
//			telemetry.addData("Position", data1);
//			telemetry.addData("Target", data2);
//			telemetry.addData("err", bot.navigation.totalError);
//			telemetry.addData("-----", "-------");
//			telemetry.addData("ib x", bot.navigation.inBoundsX);
//			telemetry.addData("ib y", bot.navigation.inBoundsY);
//			telemetry.addData("ib h", bot.navigation.inBoundsH);
//			telemetry.addData("-----", "-------");
//			telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
//			telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
//			telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
//			if (target != null) {
//				telemetry.addData("-----", "-------");
//				telemetry.addData("tx", target.getX(DistanceUnit.INCH));
//				telemetry.addData("ty", target.getY(DistanceUnit.INCH));
//				telemetry.addData("th", target.getHeading(AngleUnit.DEGREES));
//				telemetry.addData("err", bot.navigation.totalError);
//			}
//			telemetry.update();



		}

	}
}
