package ch.nte.wro.linefollower;

import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.RoboData;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class BakwordLinefollowerWithTime {
	
	private int speed;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private boolean running = false;
	private int kp;
	private float ki = 0.0F;
	private float errI = 0;
	private int delayCounter = 0;
	private int delay;

	public BakwordLinefollowerWithTime(int speed, RegulatedMotor mLeft, RegulatedMotor mRight, int kp, int delay) {
		this.kp = kp;
		this.speed = speed;
		this.mLeft = mLeft;
		this.mRight = mRight;
		this.delay = (50*delay)/speed;
		followLine();
	}
	
	private void followLine() {
		running = true;
		LightIntensitySensorChecker lLeft = new LightIntensitySensorChecker(GlobalSensors.colorSensorLeft);
		LightIntensitySensorChecker lRight = new LightIntensitySensorChecker(GlobalSensors.colorSensorRight);
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		if(RoboData.invertMotorDirection) {
			mLeft.forward();
			mRight.forward();
			mLeft.setSpeed(speed);
			mRight.setSpeed(speed);
		} else {
			mLeft.backward();
			mRight.backward();
			mLeft.setSpeed(speed);
			mRight.setSpeed(speed);
		}
		
		while(running) {
			lLeft.checkSensor();
			lRight.checkSensor();
			
			float a = lLeft.getIntensity();
			float b = lRight.getIntensity();
			float errP = a-b;
			
			errI = errI + errP*ki;
			
			LCD.drawString(String.valueOf(errI), 0, 0);
			Delay.msDelay(5);
			
			float err = errP + errI;
			
			if(err < 0) {
				mRight.setSpeed(Math.round(speed-(err*kp)));
				mLeft.setSpeed(speed);
				//Evtl delay
			} else {
				mLeft.setSpeed(Math.round(speed+(err*kp)));
				mRight.setSpeed(speed);
				//Evtl delay
			}
			
			if(delayCounter < delay) {
				Delay.msDelay(1);
				delayCounter++;
			} else {
				new motorsOFF(mLeft, mRight);
				running = false;
			}
			
		}
	}
}
