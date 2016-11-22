import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.EV3GyroSensor;

public class Robot {
	public static EV3ColorSensor sensor = new EV3ColorSensor(SensorPort.S2);
	public static EV3UltrasonicSensor sonic = new EV3UltrasonicSensor(SensorPort.S3);
	public static EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S4);  
	public static float[] COLOUR_VALUES = { 0.102f, 0.160f, 0.312f, 0.507f, 0.582f }; 
	public static Position position = new Position(0, 0); 
	public static int ticksSinceLastObstacle = 0; 
								
	public static void drive(float l, float r) {
		// B-> to left C-> to right
		Motor.B.setSpeed(Math.abs(l));
		Motor.C.setSpeed(Math.abs(r));
		if (l > 0) {
			Motor.B.forward();
		} else if (l < 0) {
			Motor.B.backward();
		} else {
			Motor.B.stop(true);
		}

		if (r > 0) {
			Motor.C.forward();
		} else if (r < 0) {
			Motor.C.backward();
		} else {
			Motor.C.stop(true);
		}
	}
	
	public static void look(int deg) {
		// always start sensor pointing straight forward. The allowed motion is then [-90,90]
		scaledDeg = ultrasonicGearRatio*deg;
		Motor.D.rotate(scaledDeg);	
	}
	
	/* 
	public static void rotate(float s, int l, int r) {
		// B-> to left C-> to right
		// use s as a base speed for motor B arbitrarily 
		Motor.B.setSpeed(Math.abs(s));
		Motor.C.setSpeed(Math.abs(s));
		Motor.B.rotate(l,true);
		Motor.C.rotate(r);
	} */ 
	
	/* 
	public static void arc(float s, int l, int r) {
		// B-> to left C-> to right
		// use s as a base speed for motor B arbitrarily 
		float speedC = s * r / l; 
		
		Motor.B.setSpeed(Math.abs(s));
		Motor.C.setSpeed(Math.abs(speedC));
		Motor.B.rotate(l,true);
		Motor.C.rotate(r);
	} */ 

	public static float pollColor(boolean log) {
		int sampleSize = sensor.sampleSize();
		float[] redsample = new float[sampleSize];
		sensor.getRedMode().fetchSample(redsample, 0);
		if (log) {
			System.out.print("sensor: ");
			System.out.println(redsample[0]);
		}
		return redsample[0];
	}
	
	public static float pollSonic(boolean log) {
		int sampleSize = sonic.sampleSize();
		float[] sample = new float[sampleSize];
		sonic.fetchSample(sample, 0);
		if (log) {
			System.out.print("sonic: ");
			System.out.println(sample[0]*100);
		}
		return sample[0]*100;
	}

	private static float pollDist(boolean log) {
		float convS = 360f/16.8f;
		float dist = (Motor.C.getTachoCount()+Motor.B.getTachoCount())/2.0f/convS; 
		if (log) {
			System.out.print("dist: ");
			System.out.println(dist);
		}
		return dist;
	}
	private static float pollGyro(boolean log) {
		float[] sample = new float[gyro.sampleSize()];
		gyro.getAngleMode().fetchSample(sample, 0); 
		if (log) {
			System.out.println("gyro: " + sample[0]);
		}
		return sample[0]; 
	}
	private static void tachoReset() { 
		Motor.B.resetTachoCount(); 
		Motor.C.resetTachoCount(); 
	}
	
	public static Position updateState() {
		float dist = Robot.pollDist(true);
		double angle = Math.toRadians(Robot.pollGyro(false));
		Robot.position.increment(dist * Math.cos(angle), dist * Math.sin(angle));
		Robot.tachoReset();
		return Robot.position; 
	}
}
