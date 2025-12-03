package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Disabled
@Autonomous(name = "Blue 0", group = "10", preselectTeleOp="Driver Operated")
public class OldBlue0AutonomousOpMode extends DecodeAutonomousOpMode {

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.BLUE);
	};


	@Override
	public void performAutonomous() {

//		bot.driveToBotRelativePositionWithTimeout(0, -10, 0, 1000, 5000);
//		telemetry.speak("I am now out of the way.");
		sleep(4000);

	}

}
