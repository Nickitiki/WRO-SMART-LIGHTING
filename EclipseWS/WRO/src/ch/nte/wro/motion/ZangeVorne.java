package ch.nte.wro.motion;

import ch.nte.wro.status.RoboData;
import ch.nte.wro.status.RoboStatus;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class ZangeVorne {
	
	public ZangeVorne() {
		if(RoboStatus.zangeUp) {
			return;
			
		} else {
			RegulatedMotor mZange = new EV3MediumRegulatedMotor(RoboData.zangePort);
			mZange.setSpeed(1000);
			mZange.backward();
			Delay.msDelay(500);
			mZange.stop();
			mZange.close();
		}
	}
}
