package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;

import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitState extends DefenderState {
	private long waitTime;
	private ElapsedTime timer;

	WaitState(long ms) {
		super();
		waitTime = ms;
		timer = new ElapsedTime();
	}

	@Override
	public void beforeStart() {
		timer.reset();
//        System.out.println("Beginning to wait");
	}

	@Override
	public void run() {
		if (timer.milliseconds() >= waitTime) {
			isFinished = true;
		}
	}


}
