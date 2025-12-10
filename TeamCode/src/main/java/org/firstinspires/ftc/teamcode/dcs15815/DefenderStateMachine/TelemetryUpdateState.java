package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;

public class TelemetryUpdateState extends DefenderState {
	private String label;
	private String data;

	public TelemetryUpdateState(String l) {
		label = l;
		data = null;
	}
	public TelemetryUpdateState(String l, String d) {
		super();
		label = l;
		data = d;
	}

	public static TelemetryUpdateState make(String l) {
		return new TelemetryUpdateState(l);
	}
	public static TelemetryUpdateState make(String l, String d) {
		return new TelemetryUpdateState(l, d);
	}

	@Override
	public void run() {
		if (data == null) {
			stateMachine.bot.telemetry.addLine(label);

		} else {
			stateMachine.bot.telemetry.addData(label, data);
		}
		stateMachine.bot.telemetry.update();
		isFinished = true;
	}
}
