package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;

public class RunOnceState extends DefenderState {
	private Runnable block;

	public RunOnceState(Runnable b) {
		super();
		block = b;
	}

	@Override
	public void run() {
		block.run();
		isFinished = true;
	}
}
