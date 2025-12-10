package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeShootState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderStateMachine;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DriveToPositionState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.RunOnceState;

@Autonomous(name = "Red Near 9a", group = "9a", preselectTeleOp = "Driver Operated")
public class Red3AutonomousOpMode extends DecodeAutonomousOpMode {

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.RED);
	}

	DefenderStateMachine sm = new DefenderStateMachine(bot);

	@Override
	public void setup() {


		sm
	   	.setOpMode(this)
		.setDebugging(true)
	   	.startWithState(
			RunOnceState
				   .make(() -> {
						bot.navigation.setPosition("RED1_START");
						bot.shooter.raiseDeflector();
						bot.shooter.changeShooterPower(DecodeConfiguration.RED3_SHOOTER_MOTOR_POWER);
				   })
				   .setLabel("START")
		).andThen(
			DriveToPositionState
				   .where("RED1_SHOOT_PRELOAD")
				   .setLabel("DRIVE TO SHOOT PRELOAD")
		).andThen(
			DecodeShootState
				   .make()
				   .setLabel("SHOOT PRELOAD")
		).andThen(
			DriveToPositionState
				   .where("RED_LINE1_START")
				   .setLabel("DRIVE TO LINE 1")
		).andThen(
			DriveToPositionState
				   .where("RED_LINE1_END", DecodeConfiguration.INTAKE_TIME_WAIT_AUTONOMOUS)
				   .setPowerOverride(DecodeConfiguration.RED3_DRIVETRAIN_POWER_INTAKE)
				   .setBeforeStart(() -> {
					   bot.intake.turnOn();
				   })
				   .setBeforeStop(() -> {
					   bot.intake.turnOff();
				   })
				   .setLabel("INTAKE LINE 1")
		).andThen(
			DriveToPositionState
				   .where("RED1_SHOOT_PRELOAD")
				   .setLabel("DRIVE TO SHOOT")
		).andThen(
			DecodeShootState
				   .make()
				   .setLabel("SHOOT SECOND THREE")
		).andThen(
			DriveToPositionState
				.where("RED_LINE2_START")
				.setLabel("DRIVE TO LINE 2")
	   ).andThen(
			 DriveToPositionState
			    .where("RED_LINE2_END", DecodeConfiguration.INTAKE_TIME_WAIT_AUTONOMOUS)
				    .setPowerOverride(DecodeConfiguration.RED3_DRIVETRAIN_POWER_INTAKE)
				    .setBeforeStart(() -> {
					    bot.intake.turnOn();
				    })
				    .setBeforeStop(() -> {
					    bot.intake.turnOff();
				    })
				    .setLabel("INTAKE LINE 2")
	   ).andThen(
			 DriveToPositionState
				    .where("RED_LINE2_START")
				    .setLabel("DRIVE AWAY FROM LINE 2")
	   ).andThen(
			 DriveToPositionState
				    .where("RED1_SHOOT_PRELOAD")
				    .setLabel("DRIVE TO SHOOT THIRD THREE")
	   ).andThen(
			 DecodeShootState
				    .make()
				    .setLabel("SHOOT THIRD THREE")
	   ).andThen(
			 DriveToPositionState
				    .where("RED3_END")
				    .setLabel("MOVE OFF LINE")


		).andThen(
			RunOnceState
				   .make(() -> {
						bot.stopDriving();
						bot.intake.turnOff();
						bot.shooter.turnOff();
						bot.shooter.lowerDeflector();
				   })
				   .setLabel("DONE")
		);


	}

	@Override
	public void performAutonomous() {
		while (opModeIsActive() && !sm.isFinished()) {
			sm.run();
		}
		telemetry.addLine("All done!");
		telemetry.update();

	}
}
