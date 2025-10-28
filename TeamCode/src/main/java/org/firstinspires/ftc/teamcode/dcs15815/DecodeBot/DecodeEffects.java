package org.firstinspires.ftc.teamcode.dcs15815.DecodeBot;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class DecodeEffects extends DefenderBotSystem {
	RevBlinkinLedDriver leds;

	public DecodeEffects(HardwareMap hm, DefenderBot b) {
		super(hm, b);
		leds = hm.get(RevBlinkinLedDriver.class, DecodeConfiguration.EFFECTS_LEDS_NAME);

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

	public void scanRed() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.SHOT_RED);
	}

	public void heartbeatBlue() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_BLUE);
	}

	public void heartbeatRed() {
		setPattern(RevBlinkinLedDriver.BlinkinPattern.HEARTBEAT_RED);
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
