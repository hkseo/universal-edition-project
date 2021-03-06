import lejos.hardware.Button; 

public class Main {
	public static void main(String[] args) {
		Behavior[] behaviours = {
		  new Avoider(10), // avoider arg is "too close"  
		  new Pathfinder()
		}; 

		System.out.println("STARTING MAIN");
		Button.waitForAnyPress();
		Robot.gyro.reset();
		while (Button.ESCAPE.isUp()) {
			for(int i=0; i<behaviours.length; i++){
				if(behaviours[i].checkActive()) { 
					behaviours[i].act(1);
					break;
				}
			}
			Robot.updateState(); 
//			System.out.println(Robot.position);
			
			if (Button.UP.isDown()) 
				System.out.println(Robot.position);
		}
		
		System.out.println(Robot.position); 
	}
}