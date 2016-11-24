
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
		this.x += x;
		this.y += y;
	}

	public static Position add(Position p, Position q) {
		return new Position(p.x + q.x, p.y + q.y);
	}

	public String toString() {
		return String.format("x %1.2f y %2.2f", this.x,  this.y); 
	}
}
