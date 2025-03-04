package ch.nte.wro.motion;

import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.status.RoboData;
import lejos.hardware.Sound;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class CountLines {
	
	private int actualLinesPassed = 0;
	
	public CountLines(int speed, RegulatedMotor mLeft, RegulatedMotor mRight, SensorModes sensorModes, int lines) {
		LightIntensitySensorChecker lic = new LightIntensitySensorChecker(sensorModes);
		
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		if(RoboData.invertMotorDirection) {
			mLeft.backward();
			mRight.backward();
		} else {
			mLeft.forward();
			mRight.forward();
		}
		
		while(actualLinesPassed <= lines) {
			lic.checkSensor();
			if(lic.getIntensity() < 0.07) {
				actualLinesPassed++;
				Sound.beep();
				if(actualLinesPassed == lines) {
					mLeft.setSpeed(0);
					mRight.setSpeed(0);
					mLeft.stop();
					mRight.stop();
					return;
				} else {
					Delay.msDelay((50*6)/speed);
				}
			}
		}
	}
}
