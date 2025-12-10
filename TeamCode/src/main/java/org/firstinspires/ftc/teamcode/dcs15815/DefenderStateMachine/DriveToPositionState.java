package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;

public class DriveToPositionState extends DefenderState {
	private DefenderBotPosition target;
	private boolean stopAfterDriving = true;
	private long holdTime = 0;
	private double powerFactor = 1;
	private double powerOverride = 0;


	public DriveToPositionState(DefenderBotPosition p) {
		super();
		target = p;
//		holdTime = 0;
//		powerFactor = 1;
//		powerOverride = 0;

	}

	public DriveToPositionState(DefenderBotPosition p, long t) {
		super();
		target = p;
		holdTime = t;
//		powerFactor = 1;
//		powerOverride = 0;
	}

	public DriveToPositionState(double x, double y, double h, long t) {
		super();
		target = new DefenderBotPosition(x, y, h);
		holdTime = t;
//		powerFactor = 1;
//		powerOverride = 0;
	}

	public DriveToPositionState(double x, double y, double h) {
		super();
		target = new DefenderBotPosition(x, y, h);
		holdTime = 0;
//		powerFactor = 1;
//		powerOverride = 0;
	}

	public DriveToPositionState(String configName, long t) {
		super();
		try {
			target = (DefenderBotPosition) DecodeConfiguration.class.getField(configName).get(DecodeConfiguration.class);
			holdTime = t;
//			powerFactor = 1;
//			powerOverride = 0;
		} catch (Exception e) {
			System.out.println("Could not find that position.");
		}
	}

	public static DriveToPositionState make(DefenderBotPosition p) {
		return new DriveToPositionState(p);
	}

	public static DriveToPositionState make(DefenderBotPosition p, long t) {
		return new DriveToPositionState(p, t);
	}

	public static DriveToPositionState make(double x, double y, double h, long t) {
		return new DriveToPositionState(x, y, h, t);
	}

	public static DriveToPositionState make(double x, double y, double h) {
		return new DriveToPositionState(x, y, h);
	}

	public static DriveToPositionState make(String configName) {
		return new DriveToPositionState(configName, 0);
	}

	public static DriveToPositionState make(String configName, long t) {
		return new DriveToPositionState(configName, t);
	}

	public static DriveToPositionState where(String configName) {
		return new DriveToPositionState(configName, 0);
	}

	public static DriveToPositionState where(String configName, long t) {
		return new DriveToPositionState(configName, t);
	}

	public DriveToPositionState setPowerFactor(double n) {
		powerFactor = n;
		return this;
	}

	public DriveToPositionState setPowerOverride(double n) {
		powerOverride = n;
		return this;
	}



	@Override
	public void run() {
		DecodeBot bot = (DecodeBot) stateMachine.bot;

		bot.navigation.updatePosition();
		if (stateMachine.isDebugging) {
			if (stateLabel != null) {
				stateMachine.opMode.telemetry.addData("Driving: ", powerFactor);
			}
			stateMachine.opMode.telemetry.addData("PF: ", powerFactor);
			stateMachine.opMode.telemetry.addData("PO: ", powerOverride);
		}

		double power;
		if (powerOverride > 0) {
			power = powerOverride;
		} else {
			power = powerFactor * DecodeConfiguration.DRIVETRAIN_POWER_MAX_AUTONOMOUS;
		}

		try {
			if (stateMachine.isDebugging) {
				stateMachine.opMode.telemetry.addData("Power: ", power);
				stateMachine.opMode.telemetry.update();
			}
			if (bot.navigation.driveToFromCurrent(target.asPose2D(), power, 0)) {
				if (stateMachine.isDebugging) {
					stateMachine.addTelemetryLine("Arrived at target");
				}
				isFinished = true;
				if (holdTime > 0) {
					if (stateMachine.isDebugging) {
						stateMachine.addTelemetryLine("Holding" + holdTime);
					}
					bot.sleep(holdTime);
				}
			}

		} catch (Exception e) {
			isFinished = true;

		} finally {


		}
	}
}