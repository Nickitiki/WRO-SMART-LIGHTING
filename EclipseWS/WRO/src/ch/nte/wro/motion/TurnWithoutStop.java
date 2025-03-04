package ch.nte.wro.motion;

import ch.nte.wro.sensors.GyroSensorChecker;
import ch.nte.wro.status.RoboData;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;

public class TurnWithoutStop {
	
	private float angle;
	private int speed;
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private float anfangswertSensor = 0;
	
	public TurnWithoutStop(int speed, float angle, RegulatedMotor mLeft, RegulatedMotor mRight) {
		this.angle = angle;
		this.speed = speed;
		this.mLeft = mLeft;
		this.mRight = mRight;
		exec();
	}
	
	private void exec() {
		GyroSensorChecker gs = new GyroSensorChecker();
		gs.checkSensor();
		anfangswertSensor = gs.getAngle();
		
		angle = anfangswertSensor + angle;
		
		mRight.setSpeed(speed);
		mLeft.setSpeed(speed);
		
		if(angle<anfangswertSensor) {
			if(RoboData.invertMotorDirection) {
				mRight.setSpeed(speed/2);
				mRight.backward();
				mLeft.backward();
			} else {
				mRight.setSpeed(speed/2);
				mRight.forward();
				mLeft.forward();
			}
		} else {
			if(RoboData.invertMotorDirection) {
				mLeft.setSpeed(speed/2);
				mRight.backward();
				mLeft.backward();
			} else {
				mLeft.setSpeed(speed/2);
				mRight.forward();
				mLeft.forward();
			}
		}
		gs.checkSensor();
		LCD.clear();
		LCD.drawString(String.valueOf(gs.getAngle()), 0, 0);
		
		if(angle < anfangswertSensor) {
			while(gs.getAngle() > angle) {
				gs.checkSensor();
				LCD.clear();
				LCD.drawString(String.valueOf(gs.getAngle()), 1, 0);
			}
		} else {
			while(gs.getAngle() < angle) {
				gs.checkSensor();
				LCD.clear();
				LCD.drawString(String.valueOf(gs.getAngle()), 1, 0);
			}
		}
		
		new motorsOFF(mLeft, mRight);
	}
}
