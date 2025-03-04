package ch.nte.wro.motion;

import ch.nte.wro.status.RoboData;
import ch.nte.wro.status.RoboStatus;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class UnloadLightAreaGreen {

	public UnloadLightAreaGreen(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
		new ZangeDown();
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*900)/speed);
		new motorsOFF(mLeft, mRight);
		
		RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
		mZange.setSpeed(1000);
		mZange.backward();
		Delay.msDelay(1000);
		mZange.stop();
		
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*800)/speed);
		new motorsOFF(mLeft, mRight);
		
		mZange.setSpeed(1000);
		mZange.backward();
		Delay.msDelay(2000);
		mZange.stop();
		mZange.close();
		RoboStatus.zangeUp = true;
	}
	
}
