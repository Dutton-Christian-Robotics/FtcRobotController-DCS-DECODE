package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Disabled
@Autonomous(name = "Red 0", group = "0", preselectTeleOp="Driver Operated")
public class OldRed0AutonomousOpMode extends DecodeAutonomousOpMode {

	public void setAlliance() {
		DefenderAlliance.getInstance().setColor(DefenderAlliance.Color.RED);
	};


	@Override
	public void performAutonomous() {

//		bot.driveToBotRelativePositionWithTimeout(0, 10, 0, 1000, 5000);
		sleep(4000);

	}

}
