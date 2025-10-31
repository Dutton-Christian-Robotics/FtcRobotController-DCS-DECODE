package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;


@Autonomous(name = "Red 0", group = "10", preselectTeleOp="Driver Operated")
public class Red0AutonomousOpMode extends DecodeAutonomousOpMode {

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.RED);
	};


	@Override
	public void performAutonomous() {

		bot.driveToBotRelativePositionWithTimeout(0, 10, 0, 1000, 5000);
//		telemetry.speak("I am now out of the way.");
		sleep(4000);

	}

}
