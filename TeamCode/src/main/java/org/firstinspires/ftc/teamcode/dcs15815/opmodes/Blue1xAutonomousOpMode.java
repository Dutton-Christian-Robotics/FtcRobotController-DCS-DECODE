package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeShootState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderStateMachine;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DriveToPositionState;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.RunOnceState;


@Autonomous(name = "X-Blue 1", group = "X", preselectTeleOp = "Driver Operated")
public class Blue1xAutonomousOpMode extends DecodeAutonomousOpMode {

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
	}

	DefenderStateMachine sm = new DefenderStateMachine(bot);

	@Override
	public void setup() {
		RunOnceState startupShooter = new RunOnceState(() -> {
			bot.navigation.setPosition(50, 50, -135);
			bot.shooter.raiseDeflector();
			bot.shooter.changeShooterPower(0.975);
		});

		DriveToPositionState moveToShootPreloaded = new DriveToPositionState(41, 41, -135);

		DecodeShootState shootPreloaded = new DecodeShootState();

		DriveToPositionState driveToLine1 = new DriveToPositionState(7, 32, 90);

		DriveToPositionState intakeLine1 = new DriveToPositionState(7, 53, 90, 1000);

		DriveToPositionState driveToShootSecondThree = new DriveToPositionState(41, 41, -135);

		DecodeShootState shootSecondThree = new DecodeShootState();

		sm.setState(startupShooter);

		startupShooter.andThen(moveToShootPreloaded);

		moveToShootPreloaded.andThen(shootPreloaded);

		shootPreloaded.andThen(driveToLine1);

		driveToLine1.andThen(intakeLine1);

		intakeLine1.andThen(driveToShootSecondThree);

		driveToShootSecondThree.andThen(shootSecondThree);


	}

	@Override
	public void performAutonomous() {
		while (opModeIsActive()) {
			sm.run();
		}

	}
}
