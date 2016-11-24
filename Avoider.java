public class Avoider implements Behavior {
  public float tooClose = 10; 
  
  public Avoider(float tooClose) {
    this.tooClose = tooClose; 
  }
  
  public boolean checkActive() { 
    return Robot.sonic < this.tooClose; 
  }
  
  public void act(int direction) {
	  Robot.ticksSinceLastObstacle = 0;
	  Robot.tachoReset();
	  if(direction > 0)
	      Robot.drive(200, -200); 
	    else
	      Robot.drive(-200, 200); 
  }
}
