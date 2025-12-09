package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@Disabled
@Autonomous(name = "Blue 1", group = "1", preselectTeleOp = "Driver Operated")
public class Blue1AutonomousOpMode extends DecodeAutonomousOpMode {

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
//		ROTATE_FOR_LINE_1_CAPTURE,
		DONE
	}

	static final Pose2D STARTING_POSITION = new Pose2D(DistanceUnit.INCH, 50, 50, AngleUnit.DEGREES, -135);
	static final Pose2D FIRST_SHOOTING_POSITION = new Pose2D(DistanceUnit.INCH, 41, 41, AngleUnit.DEGREES, -135);
	static final Pose2D START_OF_LINE_1 = new Pose2D(DistanceUnit.INCH, 7, 32, AngleUnit.DEGREES, 90);
	static final Pose2D END_OF_LINE_1 = new Pose2D(DistanceUnit.INCH, 7, 51, AngleUnit.DEGREES, 90);
//	static final Pose2D START_OF_LINE_1_CORRECT_HEADING = new Pose2D(DistanceUnit.INCH, 4, 24, AngleUnit.DEGREES, 90);

//	static final Pose2D STARTING_POSITION = new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0);
//	static final Pose2D FIRST_SHOOTING_POSITION = new Pose2D(DistanceUnit.INCH, 6, 0, AngleUnit.DEGREES, 0);
//	static final Pose2D START_OF_LINE_1 = new Pose2D(DistanceUnit.INCH, 24, -12, AngleUnit.DEGREES, -135);

	Pose2D target;
	String data1, data2;

	@Override
	public void performAutonomous() {

		bot.navigation.setPosition(STARTING_POSITION);
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
					bot.navigation.setPosition(STARTING_POSITION);
					bot.shooter.raiseDeflector();
					bot.shooter.changeShooterPower(0.975);
					currentState = StateMachineState.MOVE_TO_SHOOT_PRELOADED;
					telemetry.addLine("Finished STARTUP_SHOOTER");
					break;

				case MOVE_TO_SHOOT_PRELOADED:
					target = FIRST_SHOOTING_POSITION;
					if (bot.navigation.driveToFromCurrent(FIRST_SHOOTING_POSITION)) {
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
					target = START_OF_LINE_1;
					if (bot.navigation.driveToFromCurrent(START_OF_LINE_1)) {
						currentState = StateMachineState.INTAKE_LINE_1;
						bot.intake.turnOn();
						telemetry.addLine("Finished DRIVE_TO_LINE_1");

					}
					telemetry.addData("where", "DRIVE_TO_LINE_1");
					break;

				case INTAKE_LINE_1:
					target = END_OF_LINE_1;
					if (bot.navigation.driveToFromCurrent(END_OF_LINE_1)) {
						currentState = StateMachineState.MOVE_TO_SHOOT_SECOND_THREE;
						sleep(1000);

						telemetry.addLine("Finished INTAKE_LINE_1");
						bot.intake.turnOff();

					}
					telemetry.addData("where", "INTAKE_LINE_1");

					break;

				case MOVE_TO_SHOOT_SECOND_THREE:
					target = FIRST_SHOOTING_POSITION;
					if (bot.navigation.driveToFromCurrent(FIRST_SHOOTING_POSITION)) {
						bot.stopDriving();
						currentState = StateMachineState.SHOOT_SECOND_THREE;
						telemetry.addLine("Finished MOVE_TO_SHOOT_SECOND_THREE");

					}
					break;

				case SHOOT_SECOND_THREE:
					bot.shooter.shootAutonomously();
					currentState = StateMachineState.DONE;
					break;



//				case ROTATE_FOR_LINE_1_CAPTURE:
//					if (bot.navigation.driveToFromCurrent(START_OF_LINE_1_CORRECT_HEADING, 0)) {
//						currentState = StateMachineState.DONE;
//					}
//					break;

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
			telemetry.addData("-----", "-------");
			telemetry.addData("ib x", bot.navigation.inBoundsX);
			telemetry.addData("ib y", bot.navigation.inBoundsY);
			telemetry.addData("ib h", bot.navigation.inBoundsH);
			telemetry.addData("-----", "-------");
			telemetry.addData("x", bot.navigation.getPosition().getX(DistanceUnit.INCH));
			telemetry.addData("y", bot.navigation.getPosition().getY(DistanceUnit.INCH));
			telemetry.addData("h", bot.navigation.getPosition().getHeading(AngleUnit.DEGREES));
			if (target != null) {
				telemetry.addData("-----", "-------");
				telemetry.addData("tx", target.getX(DistanceUnit.INCH));
				telemetry.addData("ty", target.getY(DistanceUnit.INCH));
				telemetry.addData("th", target.getHeading(AngleUnit.DEGREES));
				telemetry.addData("err", bot.navigation.totalError);
			}
			telemetry.update();



		}

	}
}
