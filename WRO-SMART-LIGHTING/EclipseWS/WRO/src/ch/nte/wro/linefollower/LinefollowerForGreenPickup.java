package ch.nte.wro.linefollower;

import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.sensors.LightIntensitySensorChecker;
import ch.nte.wro.sensors.RGBColorSensorChecker;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.RoboData;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class LinefollowerForGreenPickup {
	
	private int speed;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private boolean running = false;
	private int kp; 
	private float ki = 0.0F;
	private float errI = 0;
	private int lights = 0;
	private int whiteFields = 0;

	public LinefollowerForGreenPickup(int speed, RegulatedMotor mLeft, RegulatedMotor mRight, int kp) {
		this.kp = kp;
		this.speed = speed;
		this.mLeft = mLeft;
		this.mRight = mRight;
		followLine();
	}
	
	private void followLine() {
		running = true;
		LightIntensitySensorChecker lLeft = new LightIntensitySensorChecker(GlobalSensors.colorSensorLeft);
		LightIntensitySensorChecker lRight = new LightIntensitySensorChecker(GlobalSensors.colorSensorRight);
		RGBColorSensorChecker cFront = new RGBColorSensorChecker(GlobalSensors.colorSensorFront);
		mLeft.setSpeed(speed);
		mRight.setSpeed(speed);
		if(RoboData.invertMotorDirection) {
			mLeft.backward();
			mRight.backward();
			mLeft.setSpeed(speed);
			mRight.setSpeed(speed);
		} else {
			mLeft.forward();
			mRight.forward();
			mLeft.setSpeed(speed);
			mRight.setSpeed(speed);
		}
		
		while(running) {
			lLeft.checkSensor();
			lRight.checkSensor();
			cFront.checkSensor();
			
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
			
			if(cFront.isThereABlock()) {
				lights++;
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
				Delay.msDelay(1000);
			}
			
			if((lLeft.getIntensity() + lRight.getIntensity()) > 1) {
				whiteFields++;
				mLeft.setSpeed(speed);
				mRight.setSpeed(speed);
				if(whiteFields > 0) {
					new motorsOFF(mLeft, mRight);
					running = false;
				}
			}
		}
	}
	
	public int getCountedBlocks() {
		return lights;
	}
}
