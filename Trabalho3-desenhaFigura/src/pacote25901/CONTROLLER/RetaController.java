package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import pacote25901.MODEL.Line;

public class RetaController {
	private Graphics desenho;
	private Line r1, r2, r3, r4;

	// CONSTRUCTOR
	public RetaController(Graphics desenho) {
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
	public ArrayList<Point> getPointsOnReta(Line reta) {
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

	// DRAWS A CROSS ON POINT
	public void drawCrossOnPoint(Point point) {
		// Desenha o X no Point Inicial
		Point p1 = new Point((int) point.getX() - 5, (int) point.getY() - 5);
		Point p2 = new Point((int) point.getX() + 5, (int) point.getY() + 5);
		Point p3 = new Point((int) point.getX() - 5, (int) point.getY() + 5);
		Point p4 = new Point((int) point.getX() + 5, (int) point.getY() - 5);

		Line reta = new Line(p1, p2);
		Line reta2 = new Line(p3, p4);

		drawRetaDDAInteiro(reta, Color.blue);
		drawRetaDDAInteiro(reta2, Color.blue);
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
		
		int factor = getRoundCornerFactor(pStart, pEnd);
		
		r1 = new Line(new Point(xStart+factor, yStart), new Point(xEnd-factor, yStart)); // TOP
		r2 = new Line(new Point(xStart, yStart+factor), new Point(xStart, yEnd-factor)); // LEFT
		r3 = new Line(new Point(xEnd, yStart+factor), new Point(xEnd, yEnd-factor)); // RIGHT
		r4 = new Line(new Point(xStart+factor, yEnd), new Point(xEnd-factor, yEnd)); // BOTTOM
		
		drawRetaDDAInteiro(r1, color);
		drawRetaDDAInteiro(r2, color);
		drawRetaDDAInteiro(r3, color);
		drawRetaDDAInteiro(r4, color);
		
	}
	
	public Line getLines(int option) {
		Line line = r1;
		
		switch(option) {
			case 1:
				line = r1;
				break;
			case 2:
				line = r4;
				break;
		}
		
		return line;
	}
	
	public int getRoundCornerFactor(Point pStart, Point pEnd) {
		
		Point p1, p2, p3, p4;
		int factor, length;
		
		int xStart = (int) pStart.getX();
		int xEnd = (int) pEnd.getX();
		
		int yStart = (int) pStart.getY();
		int yEnd = (int) pEnd.getY();
		
		p1 = new Point(xStart, yStart);
		p2 = new Point(xEnd, yStart);
		p3 = new Point(xStart, yStart);
		p4 = new Point(xStart, yEnd);
		
		int lengthHor = (int) Math.abs(p1.getX() - p2.getX());
		int lengthVer = (int) Math.abs(p3.getY() - p4.getY());
		
		if(lengthHor >= lengthVer) {
			length = lengthVer;
			factor = Math.abs(length/10-lengthHor)/12;
		}
		else {
			length = lengthHor;
			factor = Math.abs(length/10-lengthVer)/12;
		}
		
		return factor;
	}
	
	// GET THE CENTER OF THE RECTANGLE
	public Point getCenterOfRectangle(Point pStart, Point pEnd) {
		int xStart, xEnd, yStart, yEnd;
		
		xStart = (int) pStart.getX();
		xEnd = (int) pEnd.getX();
		
		yStart = (int) pStart.getY();
		yEnd = (int) pEnd.getY();
		
		return new Point((xStart+xEnd)/2, (yStart+yEnd)/2);

	}
	
	// GET THE RECTANGLE DIAGONAL
	public int getDiagonal(Point pStart, Point pEnd) {
		int xStart, xEnd, yStart, yEnd;
		
		xStart = (int) pStart.getX();
		xEnd = (int) pEnd.getX();
		
		yStart = (int) pStart.getY();
		yEnd = (int) pEnd.getY();
		
		return (int) Math.sqrt(Math.pow(xStart-xEnd,2)+ Math.pow(yStart-yEnd,2));
			
	}
	
	// GET THE SIDE OF RECTANGLE WITH LESS LENGTH
		public int getMinorSideOfRectangle(Point pStart, Point pEnd) {
			int xStart, xEnd, yStart, yEnd;
			
			xStart = (int) pStart.getX();
			xEnd = (int) pEnd.getX();
			
			yStart = (int) pStart.getY();
			yEnd = (int) pEnd.getY();
			
			if(Math.abs(xStart-xEnd) >= Math.abs(yStart-yEnd))
				return Math.abs(yStart-yEnd)-2;
			else
				return Math.abs(xStart-xEnd)-2;
				
		}
	
	public int getXAxis(Point pStart, Point pEnd) {
		return Math.abs( (int)(pStart.getX() - pEnd.getX()));
	}
	
	public int getYAxis(Point pStart, Point pEnd) {
		return Math.abs( (int)(pStart.getY() - pEnd.getY()));
	}
	
}
