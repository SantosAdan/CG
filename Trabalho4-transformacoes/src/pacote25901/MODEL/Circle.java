package pacote25901.MODEL;

import java.awt.Point;

public class Circle {
	private Point center;
	private int radius;
	
	// Constructor
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	// Getters and Setters
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
