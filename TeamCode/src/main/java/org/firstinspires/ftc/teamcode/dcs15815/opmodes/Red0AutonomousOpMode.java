package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeBot;
import org.firstinspires.ftc.teamcode.dcs15815.DecodeBot.DecodeConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAlliance;


@Autonomous(name = "Red 0", group = "10", preselectTeleOp="Driver Operated")
public class Red0AutonomousOpMode extends LinearOpMode {
	public DecodeBot bot;

	DefenderAlliance.Color allianceColor = DefenderAlliance.Color.UNKNOWN;


	@Override
	public void runOpMode() {

		bot = new DecodeBot(hardwareMap, DecodeConfiguration.class, telemetry);
		bot.intake.setNumberOfArtifactsLoaded(3);
//		bot.setUseDebugging(true);
		bot.abortOpMode = () -> isStopRequested();



//		setAlliance();
//		if (DefenderAlliance.getInstance().isRed()) {
//			telemetry.addData("Alliance", "RED");
//		} else if (DefenderAlliance.getInstance().isBlue()) {
//			telemetry.addData("Alliance", "BLUE");
//		} else {
//			telemetry.addData("Alliance", "unknown");
//		}
//		telemetry.update();
//
//		if (DefenderAlliance.getInstance().isRed()) {
//			bot.effects.scanRed();
//		} else if (DefenderAlliance.getInstance().isBlue()) {
//			bot.effects.scanBlue();
//		} else {
//			bot.effects.wavesParty();
//		}

		waitForStart();

		bot.driveToBotRelativePositionWithTimeout(0, 3, 0, 1000, 5000);

//		bot.navigation.resetOtosAndResetOrigin();

//		bot.shooter.startShooter();


	}

}
