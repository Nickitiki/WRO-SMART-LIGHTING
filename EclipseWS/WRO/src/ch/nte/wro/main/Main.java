package ch.nte.wro.main;

import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

public class Main {
	
	private static int speed = 75;
	//das Passwort vom SCNBONBO19 ist Leh.44rer96-

	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
		RegulatedMotor mRight = new EV3LargeRegulatedMotor(RoboData.portMotorRight);
		//new RedAndGreenAufräumen(speed, mLeft, mRight);
		new TrashAufräumenAndGreen(mLeft, mRight, speed);
	}
}
