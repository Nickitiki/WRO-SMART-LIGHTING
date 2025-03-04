package ch.nte.wro.sensors;

import ch.nte.wro.status.GlobalSensors;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class GyroSensorChecker {
	
	private SampleProvider gyro;
	private float[] status;
	
	public GyroSensorChecker() {
		convertSensor();
	}
	
	
	private void convertSensor() {
		
		SensorModes sensor = GlobalSensors.gyroSensor;
		SampleProvider gyro = sensor.getMode("Angle");
		float[] status = new float[gyro.sampleSize()];
		this.gyro = gyro;
		this.status = status;
	}
	
	public void checkSensor() {
		gyro.fetchSample(status, 0);
	}
	
	public float getAngle() {
		return status[0];
	}

}
