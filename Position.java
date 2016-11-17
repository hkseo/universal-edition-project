
public class Position {
	public double x, y; 
	public Position(double x, double y) {
		this.x = x; 
		this.y = y; 
	}
	
	public void increment (Position p){
		this.x += p.x; 
		this.y += p.y; 
	}
	
	public void increment(double x, double y) { 
		
	}
	
	public String toString() {
		return "" + this.x + ", " + this.y; 
	}
}
