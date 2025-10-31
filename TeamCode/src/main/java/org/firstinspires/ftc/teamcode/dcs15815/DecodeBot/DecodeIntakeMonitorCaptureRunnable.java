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
				DecodeBot bot = (DecodeBot) intake.bot;
				if (!bot.intake.areTooManyArtifactsLoaded() && bot.intake.direction() == "in") {
					bot.effects.beatsGreen();
				}
				if (bot.intake.direction() == "in") {
					intake.numberOfArtifactsLoaded++;
					if (bot.useSpeech) bot.telemetry.speak("Little Tut says yum yum yum!");
				} else if (bot.intake.direction() == "out") {
					intake.numberOfArtifactsLoaded--;
					if (bot.useSpeech) bot.telemetry.speak("Little Tut does not like that one!");
				}

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
