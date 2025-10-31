package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

public class DecodeShooterMonitorReadyRunnable implements Runnable {

	private DecodeShooter shooter;
	private boolean doStop = false;

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
		while (keepRunning()) {
//			if (!shooter.sensorReady.isPressed()) {
//
//				try {
//					Thread.sleep(750);
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}
//			} else {
//				try {
//					Thread.sleep(150);
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}
//
//			}
		}
	}
}
