package ch.nte.wro.sensors;

import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class RGBColorSensorChecker {
	
	private SampleProvider color;
	private float[] status;
	private SensorModes sensor;
	
	public RGBColorSensorChecker(SensorModes sensor) {
		this.sensor = sensor;
		convertSensor();
	}
	
	private void convertSensor() {
		SampleProvider color = sensor.getMode("RGB");
		float[] status = new float[color.sampleSize()];
		this.color = color;
		this.status = status;
	}
	
	public void checkSensor() {
		color.fetchSample(status, 0);
	}
	
	public String getColor() {
		float red = status[0];
		float green = status[1];
		float blue = status[2];
		String returnWert = "none";
		
		if((red >= 0.01 && red <= 0.02)&&(green >= 0.01 && green <= 0.02)&&(blue >= 0.01 && blue <= 0.025)) {
			returnWert = "black";
		}
		
		if((red >= 0.01 && red <= 0.02)&&(green >= 0.03 && green <= 0.06)&&(blue >= 0.01 && blue <= 0.03)) {
			returnWert = "green";
		}
		
		if((red >= 0.1 && red <= 0.15)&&(green >= 0.01 && green <= 0.02)&&(blue >= 0.01 && blue <= 0.03)) {
			returnWert = "red";
		}
		
		if((red >= 0.15 && red <= 0.2)&&(green >= 0.1 && green <= 0.14)&&(blue >= 0.01 && blue <= 0.04)) {
			returnWert = "yellow";
		}
		
		if((red >= 0.01 && red <= 0.3)&&(green >= 0.02 && green <= 0.04)&&(blue >= 0.06 && blue <= 0.09)) {
			returnWert = "blue";
		}
		
		return returnWert;
	}
	
	public float[] getRGB() {
		float red = status[0];
		float green = status[1];
		float blue = status[2];
		float[] returnWert = {red, green, blue};
		return(returnWert);
	}
	
	public boolean isThereABlock() {
		float red = status[0];
		float green = status[1];
		float blue = status[2];
		if((red >= 0.008)&&(green >= 0.008)&&(blue >= 0.008)) {
			return true;
		}
		return false;
	}

}
