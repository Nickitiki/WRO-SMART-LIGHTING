package ch.nte.wro.test;

import ch.nte.wro.motion.Turn;
import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

public class TurnTester {
	
	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
		RegulatedMotor mRight = new EV3LargeRegulatedMotor(RoboData.portMotorRight);
		new Turn(50, -90, mLeft, mRight);
	}
	
}
