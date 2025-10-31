package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import java.util.function.BooleanSupplier;

public class DecodeIntakeMonitorCaptureRunnable implements Runnable {

	private DecodeIntake intake;
	private boolean doStop = false;

	public void setIntake(DecodeIntake i) {
		intake = i;
	}

	public synchronized void doStop() {
		this.doStop = true;
	}

	private synchronized boolean keepRunning() {
		return this.doStop == false;
	}

	@Override
	public void run() {
		while (keepRunning()) {
			if (!intake.sensorCapture.isPressed()) {
				intake.numberOfArtifactsLoaded++;
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			} else {
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

			}
		}
	}
}
