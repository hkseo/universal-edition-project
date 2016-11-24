
class Pathfinder implements Behavior {
	Position[] waypoints = {new Position(200, 200), new Position(0, 0)}; 
	int curWaypoint = 0; 
	
	public Pathfinder() {
		// idk if it needs a constructor 
	}
	
	public boolean checkActive() { 
		// Robot.getPosition() && Robot.deliveredPizza; 
		return true; // default mode if nothing else works.	
	}
	
	public void act(int direction) { 
		// calculate a path
		double dx = waypoints[curWaypoint].x - Robot.position.x, dy = waypoints[curWaypoint].y - Robot.position.y;  
		if (dx*dx+dy*dy < 5) {
			// close enough. 
			if (Math.atan2(dy, dx) > 10) 
				Robot.drive(200, -200);
			else if (Math.atan2(dy,  dx) < -10)
				Robot.drive(-200, 200);
			else
				curWaypoint++; 
		}
		else if (Robot.ticksSinceLastObstacle > 10) {
			// pivot toward target position
			Robot.ticksSinceLastObstacle++;
			
			if (Math.atan2(dy, dx) > 0)
				Robot.drive(200, -200);
			else
				Robot.drive(-200, 200);
		}
		else { 
			// drive forward to clear obstacle
			Robot.ticksSinceLastObstacle++; 
			Robot.drive(200, 200);
		}
	}
}
