package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;

@Disabled
@Autonomous(name = "Bot Intake Test", group = "Testing", preselectTeleOp="TwoGamepadTeleOpMode")
public class BotIntakeTestOpMode extends LinearOpMode {
	public DecodeBot bot;

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;

//	public abstract void setAlliance();

	public void driveWithTimeout(double y, double x, double h, int t1, int t2) {
		bot.driveToBotRelativePositionWithTimeout(y, x, h, t1, t2);
	}


	@Override
	public void runOpMode() {

		bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);
		bot.setUseDebugging(true);
		bot.abortOpMode = () -> isStopRequested();

		waitForStart();

		bot.intake.turnOn();
		sleep(4000);

		bot.intake.turnOff();




//		bot.navigation.resetOtosAndResetOrigin();

//		bot.shooter.startShooter();


	}

}
