package ch.nte.wro.linefollower;

import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.RoboData;
import lejos.robotics.RegulatedMotor;

public class Linefollower2 {
	
	private int speed;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private boolean running = false;
	private float kp = 50;

	public Linefollower2(int speed, RegulatedMotor mLeft, RegulatedMotor mRight) {
		this.speed = speed;
		this.mLeft = mLeft;
		this.mRight = mRight;
		followLine();
	}
	
	private void followLine() {
		running = true;
		LightIntensitySensorChecker lLeft = new LightIntensitySensorChecker(GlobalSensors.colorSensorLeft);
		LightIntensitySensorChecker lRight = new LightIntensitySensorChecker(GlobalSensors.colorSensorRight);
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		if(RoboData.invertMotorDirection) {
			mLeft.backward();
			mRight.backward();
		} else {
			mLeft.forward();
			mRight.forward();
		}
		
		while(running) {
			lLeft.checkSensor();
			lRight.checkSensor();
			
			float a = lLeft.getIntensity();
			float b = lRight.getIntensity();
			float err = a-b;
			
			if(err > 0) {
				mRight.setSpeed(Math.round(speed-(err*kp)));
				mLeft.setSpeed(speed);
				//Evtl delay
			} else {
				mLeft.setSpeed(Math.round(speed+(err*kp)));
				mRight.setSpeed(speed);
				//Evtl delay
			}
		}
	}
}
