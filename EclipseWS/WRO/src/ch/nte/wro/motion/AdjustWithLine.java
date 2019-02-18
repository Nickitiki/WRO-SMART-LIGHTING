package ch.nte.wro.motion;

import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.RoboData;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class AdjustWithLine {
	
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private float worth1;
	private float worth2;
	private float konstante = 100;
	
	
	public AdjustWithLine(RegulatedMotor mLeft, RegulatedMotor mRight) {
		this.mLeft = mLeft;
		this.mRight = mRight;
		adjust();
	}
	
	private void adjust() {
		LightIntensitySensorChecker lLeft = new LightIntensitySensorChecker(GlobalSensors.colorSensorLeft);
		LightIntensitySensorChecker lRight = new LightIntensitySensorChecker(GlobalSensors.colorSensorRight);
		
		lLeft.checkSensor();
		lRight.checkSensor();
		
		worth1 = lLeft.getIntensity();
		worth2 = lRight.getIntensity();
		
		mLeft.setSpeed(50);
		mRight.setSpeed(50);
		
		if(RoboData.invertMotorDirection) {
			mLeft.forward();
			mRight.forward();
		} else {
			mLeft.backward();
			mRight.backward();
		}
		
		int delay = Math.round(worth1-worth2*konstante);
		if(delay<0) {
			delay = delay - delay - delay;
		}
		Delay.msDelay(delay);
		
		mLeft.setSpeed(0);
		mRight.setSpeed(0);
		mLeft.stop();
		mRight.stop();
		
		if(worth1-worth2 > 0) {
			if(!RoboData.invertMotorDirection) {
				mLeft.backward();
				mRight.forward();
			} else {
				mLeft.forward();
				mRight.backward();
			}
			lLeft.checkSensor();
			lRight.checkSensor();
			while(lLeft.getIntensity() != lRight.getIntensity()) {
				mLeft.setSpeed(0);
				mRight.setSpeed(0);
				mLeft.stop();
				mRight.stop();
			}
		}
	}

}
