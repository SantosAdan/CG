package pacote25901.MODEL;

import java.awt.Point;

public class Line {
	private Point pStart, pEnd;

	public Line(Point pStart, Point pEnd) {
		this.pStart = pStart;
		this.pEnd = pEnd;
	}
	
	//Getters and Setters
	public Point getpStart() {
		return pStart;
	}

	public void setpStart(Point pStart) {
		this.pStart = pStart;
	}

	public Point getpEnd() {
		return pEnd;
	}

	public void setpEnd(Point pEnd) {
		this.pEnd = pEnd;
	}
}
