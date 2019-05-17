package ch.nte.wro.motion;

import ch.nte.wro.status.RoboData;
import ch.nte.wro.status.RoboStatus;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class SmallPickUpLight {
	
	public SmallPickUpLight(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay(1500);
		new motorsOFF(mLeft, mRight);
		RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
		mZange.setSpeed(1000);
		mZange.backward();
		Delay.msDelay(800);
		mZange.stop();
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*2000)/(speed));
		new motorsOFF(mLeft, mRight);
		mZange.backward();
		Delay.msDelay(2200);
		mZange.stop();
		mZange.close();
		RoboStatus.zangeUp = true;
	}
}
