package pacote25901.MODEL;

import java.awt.Point;

public class Ellipse {
	private Point center;
	private int hAxis, vAxis;
	
	// Constructor
	public Ellipse(Point center, int hAxis, int vAxis) {
		this.center = center;
		this.hAxis = hAxis;
		this.vAxis = vAxis;
	}

	// Getters and Setters
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int gethAxis() {
		return hAxis;
	}

	public void sethAxis(int hAxis) {
		this.hAxis = hAxis;
	}

	public int getvAxis() {
		return vAxis;
	}

	public void setvAxis(int vAxis) {
		this.vAxis = vAxis;
	}
}
