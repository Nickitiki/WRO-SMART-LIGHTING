package ch.nte.wro.main;

import ch.nte.wro.linefollower.LinefollowerUntilLight;
import ch.nte.wro.motion.CountLines;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.motion.ZangeDown;
import ch.nte.wro.motion.ZangeUp;
import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.motion.motorsON;
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
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay(700);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, 110, mLeft, mRight);
		new LinefollowerUntilLight((int) (speed*1.5), mLeft, mRight, 80);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay(1500);
		new motorsOFF(mLeft, mRight);
		new ZangeUp();
		new ZangeDown();
		
	}
}
