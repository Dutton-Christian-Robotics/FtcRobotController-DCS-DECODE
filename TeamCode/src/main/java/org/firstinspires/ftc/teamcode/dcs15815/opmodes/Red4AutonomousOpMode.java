package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeShootState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderStateMachine;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DriveToPositionState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.RunOnceState;

@Autonomous(name = "Red Far 3a", group = "3a", preselectTeleOp = "Driver Operated")
public class Red4AutonomousOpMode extends DecodeAutonomousOpMode {

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
						bot.navigation.setPosition("RED4_START");
						bot.shooter.changeShooterPower(DecodeConfiguration.RED4_SHOOTER_MOTOR_POWER);
				   })
				   .setLabel("START")
		).andThen(
			DriveToPositionState
				   .where("RED4_SHOOT_PRELOAD")
				   .setLabel("DRIVE TO SHOOT PRELOAD")
		).andThen(
			DecodeShootState
				   .make()
				   .setLabel("SHOOT PRELOAD")

	   ).andThen(
			 DriveToPositionState
				    .where("RED4_END")
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
