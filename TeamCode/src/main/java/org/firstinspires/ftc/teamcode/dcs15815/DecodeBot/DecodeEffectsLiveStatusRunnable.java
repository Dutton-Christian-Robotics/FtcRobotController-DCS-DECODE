package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDelayedSequence;

public class DecodeEffectsLiveStatusRunnable implements Runnable {

	private DecodeIntake intake;
	private DecodeShooter shooter;
	private DecodeEffects effects;
	private boolean doStop = false;

	public void setEffects(DecodeEffects e) {
		effects = e;
	}
	public void setIntake(DecodeIntake i) {
		intake = i;
	}
	public void setShooter(DecodeShooter s) {
		shooter = s;
	}

	public synchronized void doStop() {
		this.doStop = true;
	}

	private synchronized boolean keepRunning() {
		return this.doStop == false;
	}

	@Override
	public void run() {
		DecodeBot bot  = (DecodeBot) intake.bot;
		ElapsedTime overloadTimer = new ElapsedTime();
		while (keepRunning()) {
			if (intake.areTooManyArtifactsLoaded()) {
				if (bot.useSpeech && overloadTimer.milliseconds() > 5000) bot.telemetry.speak("Little Tut is too full!");
				overloadTimer.reset();
				effects.solidPurple();

			} else if (shooter.isReadyToShoot() && DefenderAlliance.getInstance().isRed()) {
				effects.solidRed();

			} else if (shooter.isReadyToShoot() && DefenderAlliance.getInstance().isBlue()) {
				effects.solidBlue();

			} else if (DefenderAlliance.getInstance().isRed()) {
				effects.heartbeatRed();

			} else if (DefenderAlliance.getInstance().isBlue()) {
				effects.heartbeatBlue();

			} else {
				effects.rainbow();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
	}
}
