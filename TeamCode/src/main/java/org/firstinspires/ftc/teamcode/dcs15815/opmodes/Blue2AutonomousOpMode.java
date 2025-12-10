package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeShootState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderStateMachine;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DriveToPositionState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.RunOnceState;

@Autonomous(name = "Blue Near 6a", group = "6a", preselectTeleOp = "Driver Operated")
public class Blue2AutonomousOpMode extends DecodeAutonomousOpMode {

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
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
						bot.navigation.setPosition("BLUE2_START");
						bot.shooter.raiseDeflector();
						bot.shooter.changeShooterPower(DecodeConfiguration.BLUE3_SHOOTER_MOTOR_POWER);
				   })
				   .setLabel("START")
		).andThen(
			DriveToPositionState
				   .where("BLUE2_SHOOT_PRELOAD")
				   .setLabel("DRIVE TO SHOOT PRELOAD")
		).andThen(
			DecodeShootState
				   .make()
				   .setLabel("SHOOT PRELOAD")
		).andThen(
			DriveToPositionState
				   .where("BLUE_LINE1_START")
				   .setLabel("DRIVE TO LINE 1")
		).andThen(
			DriveToPositionState
				   .where("BLUE_LINE1_END", DecodeConfiguration.INTAKE_TIME_WAIT_AUTONOMOUS)
				   .setPowerOverride(DecodeConfiguration.BLUE3_DRIVETRAIN_POWER_INTAKE)
				   .setBeforeStart(() -> {
					   bot.intake.turnOn();
				   })
				   .setBeforeStop(() -> {
					   bot.intake.turnOff();
				   })
				   .setLabel("INTAKE LINE 1")
		).andThen(
			DriveToPositionState
				   .where("BLUE2_SHOOT_PRELOAD")
				   .setLabel("DRIVE TO SHOOT")
		).andThen(
			DecodeShootState
				   .make()
				   .setLabel("SHOOT SECOND THREE")
	   ).andThen(
			 DriveToPositionState
				    .where("BLUE3_END")
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
