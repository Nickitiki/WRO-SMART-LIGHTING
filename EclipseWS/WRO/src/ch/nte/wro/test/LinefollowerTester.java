package ch.nte.wro.test;

import ch.nte.wro.linefollower.Linefollower;
import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

public class LinefollowerTester {

	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
		RegulatedMotor mRight = new EV3LargeRegulatedMotor(RoboData.portMotorRight);

		new Linefollower(100, mLeft, mRight, 60);
	}

}
