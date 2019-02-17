package ch.nte.wro.main;

import ch.nte.wro.motion.CountLines;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.status.GlobalSensors;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class TrashAufräumen {
	
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private int speed;
	
	public TrashAufräumen(RegulatedMotor mLeft, RegulatedMotor mRight, int speed) {
		this.mLeft = mLeft;
		this.mRight = mRight;
		this.speed = speed;
		driveToArea1Green();
	}
	private void driveToArea1Green() {
		new Turn(speed, -20, mLeft, mRight);
		new CountLines(speed*2, mLeft, mRight, GlobalSensors.colorSensorLeft, 5);
		mLeft.forward();
		mRight.forward();
		Delay.msDelay(200);
		mLeft.setSpeed(0);
		mRight.setSpeed(0);
		new Turn(speed, 110, mLeft, mRight);
	}
}
