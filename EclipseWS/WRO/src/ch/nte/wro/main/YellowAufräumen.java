package ch.nte.wro.main;

import ch.nte.wro.linefollower.LinefollowerUntilHalfJunction;
import ch.nte.wro.linefollower.LinefollowerUntilJunction;
import ch.nte.wro.linefollower.LinefollowerUntilLight;
import ch.nte.wro.linefollower.LinefollowerUntilWhiteGround;
import ch.nte.wro.linefollower.LinefollowerWithTime;
import ch.nte.wro.motion.CountLines;
import ch.nte.wro.motion.Turn;
import ch.nte.wro.motion.UnloadLight2;
import ch.nte.wro.motion.ZangeDown;
import ch.nte.wro.motion.ZangeUp;
import ch.nte.wro.motion.motorsOFF;
import ch.nte.wro.motion.motorsON;
import ch.nte.wro.motion.motorsOnUntilJunction;
import ch.nte.wro.status.GlobalSensors;
import ch.nte.wro.status.LightArrangement;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class YellowAufräumen {
	
	
	public YellowAufräumen(RegulatedMotor mLeft, RegulatedMotor mRight, int speed) {
		
		new CountLines(speed*2, mLeft, mRight, GlobalSensors.colorSensorLeft, 2);
		new Turn(speed, -90, mLeft, mRight);
		new LinefollowerUntilJunction(speed, mLeft, mRight, 80);
		new motorsON(speed, mLeft, mRight, true);
		Delay.msDelay((50*850)/speed);
		new motorsOFF(mLeft, mRight);
		new Turn((int) (speed*0.75), -90, mLeft, mRight);
		new LinefollowerUntilWhiteGround((int) (speed*0.75), mLeft, mRight, 80);
		new motorsON((int) (speed*1.4), mLeft, mRight, true);
		Delay.msDelay((50*1000)/speed);
		new motorsOFF(mLeft, mRight);
		new LinefollowerUntilWhiteGround((int) (speed*0.75), mLeft, mRight, 80);
		new ZangeUp();
		new motorsON(speed*2, mLeft, mRight, true);
		Delay.msDelay((50*1500)/(speed*2));
		new motorsOFF(mLeft, mRight);		
		new Turn(speed, -90, mLeft, mRight);
		
		if(LightArrangement.area1Yellow == null) {
			new LinefollowerWithTime(speed*2, mLeft, mRight, 80, ((75*1500)/(speed*2)));
			new Turn(speed, 90, mLeft, mRight);
			new CountLines(speed, mLeft, mRight, GlobalSensors.colorSensorLeft, 1);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			Delay.msDelay(300);
			new Turn(speed, 90, mLeft, mRight);
			new LinefollowerUntilWhiteGround(speed*2, mLeft, mRight, 60);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*1000)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, -90, mLeft, mRight);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*1500)/speed);
			new motorsOFF(mLeft, mRight);
			new LinefollowerUntilLight(speed*2, mLeft, mRight, 80);
			new motorsON(speed*2, mLeft, mRight, false);
			Delay.msDelay((50*3500)/(speed*2));
			new motorsOFF(mLeft, mRight);
			new UnloadLight2(speed, mRight, mLeft);
			new motorsON(speed*2, mLeft, mRight, false);
			Delay.msDelay((50*3000)/(speed*2));
			new motorsOFF(mLeft, mRight);
			new Turn(speed, -90, mLeft, mRight);
			new ZangeDown();
			new motorsON(speed*2, mLeft, mRight, false);
			Delay.msDelay((50*350)/(speed*2));
			new motorsOFF(mLeft, mRight);
			new Turn(speed, -90, mLeft, mRight);
			new LinefollowerUntilLight(speed*2, mLeft, mRight, 60);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*1000)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, 90, mLeft, mRight);
			new LinefollowerUntilJunction(speed*2, mLeft, mRight, 60);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*600)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, -90, mLeft, mRight);
			new LinefollowerUntilWhiteGround(speed*2, mLeft, mRight, 60);
			
		} else {
			new LinefollowerUntilHalfJunction(speed*2, mLeft, mRight, 60); 
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			new LinefollowerUntilHalfJunction(speed*2, mLeft, mRight, 60); 
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*750)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, 90, mLeft, mRight);
			new LinefollowerUntilJunction(speed*2, mLeft, mRight, 60);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			new LinefollowerUntilJunction(speed*2, mLeft, mRight, 60); 
			new LinefollowerWithTime(speed*2, mLeft, mRight, 60, ((50*1000)/(speed*2)));
			new Turn(speed, 90, mLeft, mRight);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*2500)/speed);
			new motorsOFF(mLeft, mRight);
			new ZangeDown();
			new motorsOnUntilJunction(speed*2, mLeft, mRight, false);
			new motorsON(speed, mLeft, mRight, true);
			Delay.msDelay((50*500)/speed);
			new motorsOFF(mLeft, mRight);
			new Turn(speed, 90, mLeft, mRight);
			new LinefollowerUntilWhiteGround(speed*2, mLeft, mRight, 60);
		}	
			
	}


}
