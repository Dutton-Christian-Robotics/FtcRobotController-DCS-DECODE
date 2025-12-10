package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeShootState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderStateMachine;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DriveToPositionState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.RunOnceState;

@Disabled
@Autonomous(name = "Blue 3x", group = "3", preselectTeleOp = "Driver Operated")
public class Blue3dsmAutonomousOpMode extends DecodeAutonomousOpMode {

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
	}

	DefenderStateMachine sm = new DefenderStateMachine(bot);

	@Override
	public void setup() {

//		DriveToPositionState moveToShootPreloaded = new DriveToPositionState("BLUE1_SHOOT_PRELOAD");

//		DecodeShootState shootPreloaded = new DecodeShootState();

//		DriveToPositionState driveToLine1 = new DriveToPositionState("BLUE_LINE1_START");

//		DriveToPositionState intakeLine1 = new DriveToPositionState("BLUE_LINE1_END");

//		DriveToPositionState driveToShootSecondThree = new DriveToPositionState("BLUE1_SHOOT_PRELOAD");

//		DecodeShootState shootSecondThree = new DecodeShootState();

		sm.startWithState(
			RunOnceState.make(() -> {
				bot.navigation.setPosition("BLUE1_START");
				bot.shooter.raiseDeflector();
				bot.shooter.changeShooterPower(DecodeConfiguration.SHOOTER_MOTOR_POWER_AUTON);
			}).setStateLabel("START")
		).andThen(
			DriveToPositionState.make("BLUE1_SHOOT_PRELOAD").setStateLabel("DRIVE TO SHOOT PRELOAD")
		).andThen(
			DecodeShootState.make().setStateLabel("SHOOT PRELOAD")
		).andThen(
			DriveToPositionState.make("BLUE_LINE1_START").setStateLabel("DRIVE TO LINE 1")
		).andThen(
			DriveToPositionState.make("BLUE_LINE1_END").setStateLabel("INTAKE LINE 1")
		).andThen(
			DriveToPositionState.make("BLUE1_SHOOT_PRELOAD").setStateLabel("DRIVE TO SHOOT")
		).andThen(
			DecodeShootState.make().setStateLabel("SHOOT SECOND THREE")
		);


	}

	@Override
	public void performAutonomous() {
		while (opModeIsActive()) {
			sm.run();
		}

	}
}
