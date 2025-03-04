package ch.nte.wro.motion;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class PickUpLight {
	
	public PickUpLight(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay(1500);
		new motorsOFF(mLeft, mRight);
		new ZangeUp();
	}

}
