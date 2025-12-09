package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
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

	@Override
	public void run() {
		DecodeBot bot = (DecodeBot)stateMachine.bot;

		try {
			if (bot.navigation.driveToFromCurrent(target.asPose2D())) {
				isFinished = true;
				if (holdTime > 0) {
					bot.sleep(holdTime);
				}
			}

		} catch (Exception e) {
			System.out.println("Problem trying to driveToPosition: " + e.toString());
		} finally {
			isFinished = true;
		}
	}
}
