package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class DecodeEffects extends DefenderBotSystem {
	RevBlinkinLedDriver leds;
	public DecodeEffectsLiveStatusRunnable liveStatusRunnable;

	public DecodeEffects(HardwareMap hm, DefenderBot b) {
		super(hm, b);
		leds = hm.get(RevBlinkinLedDriver.class, DecodeConfiguration.EFFECTS_LEDS_NAME);

	}

	public void startLiveStatus() {
		DecodeBot b = (DecodeBot)bot;
		liveStatusRunnable = new DecodeEffectsLiveStatusRunnable();
		liveStatusRunnable.setEffects(this);
		liveStatusRunnable.setShooter(b.shooter);
		liveStatusRunnable.setIntake(b.intake);
		liveStatusRunnable.setEffects(this);
		Thread monitorCaptureThread = new Thread(liveStatusRunnable);
		monitorCaptureThread.start();
	}

	public void stopLiveStatus() {
		if (liveStatusRunnable != null) {
			liveStatusRunnable.doStop();
		}
	}

	public void setPattern(RevBlinkinLedDriver.BlinkinPattern p) {
		leds.setPattern(p);
	}

	public void solidBlue() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
	}

	public void solidRed() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
	}


	public void scanBlue() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.SHOT_BLUE);
	}

	public void black() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
	}

	public void solidOrange() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
	}

	public void solidPurple() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.VIOLET);
	}

	public void scanRed() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.SHOT_RED);
	}

	public void heartbeatBlue() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE);
	}

	public void heartbeatRed() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED);
	}
	public void heartbeatGray() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_GRAY);
	}

	public void strobeGold() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_GOLD);
	}

	public void strobeWhite() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_WHITE);
	}

	public void beatsGreen() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_FOREST_PALETTE);
	}

	public void wavesGreen() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_FOREST_PALETTE);
	}

	public void wavesParty() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_PARTY_PALETTE);
	}


	public void rainbow() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE);

	}


}
