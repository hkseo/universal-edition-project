public class Avoider implements Behavior {
  public float tooClose = 10; 
  public Avoider(float tooClose) {
    this.tooClose = tooClose; 
  }
  
  public boolean checkActive() { 
    return Robot.pollSonic(false) < this.tooClose; 
  }
  
  public void act(int direction) {
	  Robot.ticksSinceLastObstacle = 0; 
	  if(direction > 0)
      Robot.drive(100, -100); 
    else
      Robot.drive(-100, 100); 
  }
}
