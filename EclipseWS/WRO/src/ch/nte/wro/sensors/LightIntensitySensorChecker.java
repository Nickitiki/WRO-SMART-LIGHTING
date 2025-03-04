package ch.nte.wro.sensors;

import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class LightIntensitySensorChecker {
	
	private SampleProvider light;
	private float[] status;
	private SensorModes sensor;
	
	public LightIntensitySensorChecker(SensorModes sensor) {
		this.sensor = sensor;
		convertSensor();
	}
	
	private void convertSensor() {
		SampleProvider light = sensor.getMode("Red");
		float[] status = new float[light.sampleSize()];
		this.light = light;
		this.status = status;
	}
	
	public void checkSensor() {
		light.fetchSample(status, 0);
	}
	
	public float getIntensity() {
		return status[0];
	}

}
