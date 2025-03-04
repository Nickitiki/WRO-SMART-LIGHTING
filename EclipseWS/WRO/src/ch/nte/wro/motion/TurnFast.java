package ch.nte.wro.motion;

import ch.nte.wro.sensors.GyroSensorChecker;
import ch.nte.wro.status.RoboData;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class TurnFast {
	
	private float angle;
	private int speed;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private float anfangswertSensor = 0;
	
	public TurnFast(int speed, float angle, RegulatedMotor mLeft, RegulatedMotor mRight) {
		this.angle = angle;
		this.speed = speed;
		this.mLeft = mLeft;
		this.mRight = mRight;
		exec();
	}

	private void exec() {
		Delay.msDelay(100);
		GyroSensorChecker gs = new GyroSensorChecker();
		gs.checkSensor();
		anfangswertSensor = gs.getAngle();
		
		angle = anfangswertSensor + angle;
		
		mRight.setSpeed(speed);
		mLeft.setSpeed(speed);
		
		if(angle<anfangswertSensor) {
			if(RoboData.invertMotorDirection) {
				mRight.forward();
				mLeft.backward();
			} else {
				mRight.backward();
				mLeft.forward();
			}
			mRight.setSpeed(speed);
			mLeft.setSpeed(speed);
		} else {
			if(RoboData.invertMotorDirection) {
				mRight.backward();
				mLeft.forward();
			} else {
				mRight.forward();
				mLeft.backward();
			}
		}
		mRight.setSpeed(speed);
		mLeft.setSpeed(speed);
		gs.checkSensor();
		LCD.clear();
		LCD.drawString(String.valueOf(gs.getAngle()), 0, 0);
		
		double multiplikator = 1;
		
		if(angle < anfangswertSensor) {
			while(gs.getAngle() > angle) {
				
				if(gs.getAngle() > angle/2) {
					multiplikator = multiplikator + 0.02;
				} else {
					if(multiplikator > 1) {
						multiplikator = multiplikator - 0.02;
					}
				}
				
				mLeft.setSpeed((int) Math.round(speed*multiplikator));
				mRight.setSpeed((int) Math.round(speed*multiplikator));
				
				gs.checkSensor();
				LCD.clear();
				LCD.drawString(String.valueOf(gs.getAngle()), 1, 0);
			}
		} else {
			while(gs.getAngle() < angle) {
				
				if(gs.getAngle() < angle*2) {
					multiplikator = multiplikator + 0.012;
				} else {
					if(multiplikator > 1) {
						multiplikator = multiplikator - 0.02;
					}
				}
				
				mLeft.setSpeed((int) Math.round(speed*multiplikator));
				mRight.setSpeed((int) Math.round(speed*multiplikator));
				
				gs.checkSensor();
				LCD.clear();
				LCD.drawString(String.valueOf(gs.getAngle()), 1, 0);
			}
		}
		
		new motorsOFF(mLeft, mRight);
	}
}
