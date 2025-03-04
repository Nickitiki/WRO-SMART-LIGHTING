package ch.nte.wro.test;

import ch.nte.wro.motion.Turn;
import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class ReturnTester {
	
	public static void main(String[] args) {
		RegulatedMotor m1 = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
		RegulatedMotor m2 = new EV3LargeRegulatedMotor(RoboData.portMotorRight);
		new Turn(50, -20, m1, m2);
		Delay.msDelay(1000);
		new Turn(50, 40, m1, m2);
		new Turn(50, -20, m1, m2);
	}

}
