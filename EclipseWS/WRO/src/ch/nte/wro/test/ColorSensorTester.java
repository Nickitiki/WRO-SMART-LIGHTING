package ch.nte.wro.test;

import ch.nte.wro.sensors.RGBColorSensorChecker;
import ch.nte.wro.status.GlobalSensors;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class ColorSensorTester {

	public static void main(String[] args) {
		
		RGBColorSensorChecker cFront = new RGBColorSensorChecker(GlobalSensors.colorSensorFront);
		
		while(true) {
			cFront.checkSensor();
			LCD.clear();
			LCD.drawString(cFront.getColor(), 0, 0);
			LCD.drawString(String.valueOf(cFront.isThereABlock()), 0, 1);
			Delay.msDelay(100);
		}
		
		/*
		SensorModes sensor = GlobalSensors.colorSensorFront;
		SampleProvider light = sensor.getMode("RGB");
		float[] status = new float[light.sampleSize()];
		
		
		while(true) {
			light.fetchSample(status, 0);
			LCD.drawString(String.valueOf(status[0]), 0, 0);
			LCD.drawString(String.valueOf(status[1]), 0, 1);
			LCD.drawString(String.valueOf(status[2]), 0, 2);
			Delay.msDelay(100);
		}
		*/
	}
}
