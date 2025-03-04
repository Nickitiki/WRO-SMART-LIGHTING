package ch.nte.wro.main;

import ch.nte.wro.linefollower.LinefollowerUntilJunction;
import ch.nte.wro.linefollower.LinefollowerUntilLight;
import ch.nte.wro.linefollower.LinefollowerUntilWhiteGround;
import ch.nte.wro.motion.CountLines;
import ch.nte.wro.motion.PickUpLight;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.motion.TurnWithOneMotor;
import ch.nte.wro.motion.ZangeDown;
import ch.nte.wro.motion.ZangeUp;
import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.motion.motorsON;
import ch.nte.wro.motion.motorsOnUntilJunction;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.LightArrangement;
import ch.nte.wro.status.RoboData;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class TrashAufräumen {
	
	private RegulatedMotor mLeft;
	private RegulatedMotor mRight;
	private int speed;
	private boolean area1Green = true;
	private boolean area1Blue;
	
	public TrashAufräumen(RegulatedMotor mLeft, RegulatedMotor mRight, int speed) {
		this.mLeft = mLeft;
		this.mRight = mRight;
		this.speed = speed;
		
		area1Green = driveToArea1Green();
		if(area1Green) {
			//Auf A1G ist ein Licht
			LightArrangement.area1Green = "black";
			drivefromA1GtoK2();
			
		} else {
			//Auf A1Y ist ein Licht
			driveToArea1Yellow();
			LightArrangement.area1Yellow = "black";
			driveFromA1YtoK2();
		}	
		
		mLeft.close();
		mRight.close();
		
		mLeft = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
		mRight = new EV3LargeRegulatedMotor(RoboData.portMotorRight);
		
		area1Blue = driveToArea1Blue();
		if(area1Blue) {
			//Auf A1B ist ein Licht
			LightArrangement.area1Blue = "black";
			mLeft.close();
			mRight.close();
			mLeft = new EV3LargeRegulatedMotor(RoboData.portMotorLeft);
			mRight = new EV3LargeRegulatedMotor(RoboData.portMotorRight);
			driveFromA1BToK4();
			mLeft.setSpeed(speed);
			mRight.setSpeed(speed);
			new Turn(speed, 90, mLeft, mRight);
			new motorsON(speed, mLeft, mRight, false);
			Delay.msDelay((50*700)/speed);
			new motorsOFF(mLeft, mRight);
			new ZangeDown();
			
		} else {
			//Auf A1R ist ein Licht
			LightArrangement.area1Red = "balck";
			driveFromA1BToK4();
			driveToArea1Red();
		}
		
		driveFromTrashToK3();
	}
	
	private boolean driveToArea1Green() {
		new Turn(speed, -20, mLeft, mRight);
		new CountLines(speed*2, mLeft, mRight, GlobalSensors.colorSensorLeft, 5);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*700)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, 110, mLeft, mRight);
		LinefollowerUntilLight lfunl = new LinefollowerUntilLight(speed, mLeft, mRight, 80);
		if(lfunl.isThereALight()) {
			new PickUpLight(speed, mLeft, mRight);
			return true;
		}
		return false;
	}
	
	private boolean driveToArea1Yellow() {
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*1500)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, -90, mLeft, mRight);
		new LinefollowerUntilLight(speed*3, mLeft, mRight, 60);
		new PickUpLight(speed, mLeft, mRight);
		return true;
	}
	
	private void drivefromA1GtoK2() {
		new Turn(speed, 180, mLeft, mRight);
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 60);
		new ZangeDown();
	}
	
	private void driveFromA1YtoK2() {
		new Turn(speed, 180, mLeft, mRight);
		new LinefollowerUntilLight(speed*3, mLeft, mRight, 60);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*1000)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, 90, mLeft, mRight);
		new LinefollowerUntilJunction(speed*3, mLeft, mRight, 60);
		new ZangeDown();
	}
	
	private boolean driveToArea1Blue() {
		LinefollowerUntilLight lfunl = new LinefollowerUntilLight((int) Math.round(speed*1.5), mLeft, mRight, 80);
		if(lfunl.isThereALight()) {
			new PickUpLight(speed, mLeft, mRight);
			return true;
		}
		new ZangeUp();
		return false;
	}
	
	private void driveFromA1BToK4() {
		new motorsOnUntilJunction(speed*2, mLeft, mRight, false);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*700)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, 90, mLeft, mRight);
		new LinefollowerUntilLight(speed*3, mLeft, mRight, 60);
	}
	
	private boolean driveToArea1Red() {
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*500)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn(speed, -90, mLeft, mRight);
		new ZangeDown();
		new LinefollowerUntilLight(speed, mLeft, mRight, 60);
		new PickUpLight(speed, mLeft, mRight);
		new Turn(speed, 180, mLeft, mRight);
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 60);
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*1000)/speed);
		new motorsOFF(mLeft, mRight);
		new ZangeDown();
		return false;
	}
	
	private void driveFromTrashToK3() {
		new motorsON(speed, mLeft, mRight, false);
		Delay.msDelay((50*2200)/speed);
		new motorsOFF(mLeft, mRight);
		new TurnWithOneMotor(speed*2, 90, mLeft, mRight);
		new LinefollowerUntilJunction(speed*2, mLeft, mRight, 80);
		new LinefollowerUntilWhiteGround(speed*2, mLeft, mRight, 60);
	}
}
