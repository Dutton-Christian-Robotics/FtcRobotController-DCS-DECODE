package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;

public class DriveToPositionState extends DefenderState {
	private DefenderBotPosition target;
	private boolean stopAfterDriving = true;
	private long holdTime = 0;


	public DriveToPositionState(DefenderBotPosition p) {
		super();
		target = p;
		holdTime = 0;
	}

	public DriveToPositionState(DefenderBotPosition p, long t) {
		super();
		target = p;
		holdTime = t;
	}

	public DriveToPositionState(double x, double y, double h, long t) {
		super();
		target = new DefenderBotPosition(x, y, h);
		holdTime = t;
	}

	public DriveToPositionState(double x, double y, double h) {
		super();
		target = new DefenderBotPosition(x, y, h);
		holdTime = 0;
	}

	public DriveToPositionState(String configName) {
		super();
		try {
			target = (DefenderBotPosition) DecodeConfiguration.class.getField(configName).get(DecodeConfiguration.class);
			holdTime = 0;
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
		return new DriveToPositionState(configName);
	}

	@Override
	public void run() {
		DecodeBot bot = (DecodeBot) stateMachine.bot;

		bot.navigation.updatePosition();

		try {

			if (bot.navigation.driveToFromCurrent(target.asPose2D())) {
				isFinished = true;
				if (holdTime > 0) {
					bot.sleep(holdTime);
				}
			}

		} catch (Exception e) {
			isFinished = true;

		} finally {


		}
	}
}