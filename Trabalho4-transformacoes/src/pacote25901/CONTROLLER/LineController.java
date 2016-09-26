package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import pacote25901.MODEL.Line;

public class LineController {
	private Graphics desenho;
	private Line r1, r2, r3, r4;
	ArrayList<Point> pointsList1 = new ArrayList<Point>();
	ArrayList<Point> pointsList2 = new ArrayList<Point>();
	ArrayList<Point> pointsList3 = new ArrayList<Point>();
	ArrayList<Point> pointsList4 = new ArrayList<Point>();

	// CONSTRUCTOR
	public LineController(Graphics desenho) {
		this.desenho = desenho;
	}

	// PRINTA NO CONSOLE AS RETAS NO ARRAYLIST
	public void printList(ArrayList<Line> retasList) {
		for (int i = 0; i < retasList.size(); i++) {
			System.out.println("Point Inicial: (" + retasList.get(i).getpStart().getX() + ", "
					+ retasList.get(i).getpStart().getY() + ") - " + "Point Final: ("
					+ retasList.get(i).getpEnd().getX() + ", " + retasList.get(i).getpEnd().getY() + ")");
		}
	}
	

	// DRAWS LINE USING THE INTEGER DDA ALGORITHM (BRESSENHAM)
	public void drawRetaDDAInteiro(Line reta, Color color) {
		Point p1, p2;
		int x1, x2, y1, y2, dx, dy;

		// INICIALIZANDO VARIAVEIS
		p1 = reta.getpStart();
		p2 = reta.getpEnd();

		x1 = (int) p1.getX();
		y1 = (int) p1.getY();
		x2 = (int) p2.getX();
		y2 = (int) p2.getY();

		// BRESSENHAM
		dx = Math.abs(x2 - x1);
		dy = Math.abs(y2 - y1);

		int sx = (x1 < x2) ? 1 : -1;
		int sy = (y1 < y2) ? 1 : -1;

		int err = dx - dy;

		desenho.setColor(color);
		while (true) {
			desenho.drawLine(x1, y1, x1, y1);

			if (x1 == x2 && y1 == y2) {
				break;
			}

			int e2 = 2 * err;

			if (e2 > -dy) {
				err = err - dy;
				x1 = x1 + sx;
			}

			if (e2 < dx) {
				err = err + dx;
				y1 = y1 + sy;
			}
		}
	}

	// GET ALL POINTS IN GIVEN LINE
	private ArrayList<Point> getPointsOnReta(Line reta) {
		ArrayList<Point> pointsList = new ArrayList<Point>();

		Point p1, p2;
		int x1, x2, y1, y2, dx, dy;

		// INICIALIZANDO VARIAVEIS
		p1 = reta.getpStart();
		p2 = reta.getpEnd();

		x1 = (int) p1.getX();
		y1 = (int) p1.getY();
		x2 = (int) p2.getX();
		y2 = (int) p2.getY();

		// BRESENHAMS
		dx = Math.abs(x2 - x1);
		dy = Math.abs(y2 - y1);

		int sx = (x1 < x2) ? 1 : -1;
		int sy = (y1 < y2) ? 1 : -1;

		int err = dx - dy;

		while (true) {
			pointsList.add(new Point(x1, y1));

			if (x1 == x2 && y1 == y2) {
				break;
			}

			int e2 = 2 * err;

			if (e2 > -dy) {
				err = err - dy;
				x1 = x1 + sx;
			}

			if (e2 < dx) {
				err = err + dx;
				y1 = y1 + sy;
			}
		}

		return pointsList;
	}
	
	// DRAWS THE RECTANGLE BASED ON LEFT TOP POINT AND BOTTOM RIGHT POINT
	public void drawRectangle(Point pStart, Point pEnd, Color color) {
		int xStart, xEnd, yStart, yEnd;
		
		xStart = (int) pStart.getX();
		xEnd = (int) pEnd.getX();
		yStart = (int) pStart.getY();
		yEnd = (int) pEnd.getY();
		
		if(xStart > xEnd || yStart > yEnd) {
			int aux = xStart;
			xStart = xEnd;
			xEnd = aux;
			
			aux = yStart;
			yStart = yEnd;
			yEnd = aux;
		}
		
		r1 = new Line(new Point(xStart, yStart), new Point(xEnd, yStart)); // TOP
		pointsList1 = this.getPointsOnReta(r1);
		r2 = new Line(new Point(xStart, yStart), new Point(xStart, yEnd)); // LEFT
		pointsList1 = this.getPointsOnReta(r2);
		r3 = new Line(new Point(xEnd, yStart), new Point(xEnd, yEnd)); // RIGHT
		pointsList1 = this.getPointsOnReta(r3);
		r4 = new Line(new Point(xStart, yEnd), new Point(xEnd, yEnd)); // BOTTOM
		pointsList1 = this.getPointsOnReta(r4);
		
		drawRetaDDAInteiro(r1, color);
		drawRetaDDAInteiro(r2, color);
		drawRetaDDAInteiro(r3, color);
		drawRetaDDAInteiro(r4, color);
	}
}
