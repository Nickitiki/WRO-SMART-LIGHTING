package ch.nte.wro.test;

import ch.nte.wro.motion.ZangeDown;
import ch.nte.wro.status.RoboStatus;

public class Ev3MiddleRegulatedMotortester {

	public static void main(String[] args) {
		RoboStatus.zangeUp = true;
		new ZangeDown();
	}

}
