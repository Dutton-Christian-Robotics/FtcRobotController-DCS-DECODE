package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine.DefenderState;

public class DecodeShootState extends DefenderState {

	public DecodeShootState() {
		super();
	}

	public static DecodeShootState make() {
		return new DecodeShootState();
	}

	@Override
	public void run() {
		DecodeBot bot = (DecodeBot)stateMachine.bot;
		bot.shooter.shootAutonomously();
	}
}
