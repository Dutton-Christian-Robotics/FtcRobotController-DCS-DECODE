package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderState;

public class DecodeShootState extends DefenderState {

	public double boost = 0;

	public DecodeShootState() {
		super();
	}

	public static DecodeShootState make() {
		return new DecodeShootState();
	}

	public DecodeShootState setBoost(double n) {
		boost = n;
		return this;
	}

	@Override
	public void run() {
		DecodeBot bot = (DecodeBot)stateMachine.bot;
		bot.shooter.shootAutonomously(boost);
		isFinished = true;
	}
}
