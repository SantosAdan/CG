package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import pacote25901.MODEL.Reta;

public class RetaController {
	private Graphics desenho;

	// CONSTRUCTOR
	public RetaController(Graphics desenho) {
		this.desenho = desenho;
	}

	// PRINTA NO CONSOLE AS RETAS NO ARRAYLIST
	public void printList(ArrayList<Reta> retasList) {
		for (int i = 0; i < retasList.size(); i++) {
			System.out.println("Point Inicial: (" + retasList.get(i).getpStart().getX() + ", "
					+ retasList.get(i).getpStart().getY() + ") - " + "Point Final: ("
					+ retasList.get(i).getpEnd().getX() + ", " + retasList.get(i).getpEnd().getY() + ")");
		}
	}
	
	// CALLS THE DRAWRETA CORRECT ALGORITHM
	public void drawReta(Reta reta, Color cor, int algorithmType) {
		
		switch(algorithmType) {
			case 1:
				drawRetaClassico(reta, cor);
				break;
			case 2:
				drawRetaDDASimples(reta, cor);
				break;
			case 3:
				drawRetaDDAInteiro(reta, cor);
		}
	}
	
	// DRAWS LINE USING THE CLASSIC ALGORITHM
	public void drawRetaClassico(Reta reta, Color cor) {
		int x1,y1,x2,y2;
		double m, b;
				
		Point p1, p2;
		p1 = reta.getpStart();
		p2 = reta.getpEnd();
		
		x1 = (int) p1.getX();
		y1 = (int) p1.getY();
		x2 = (int) p2.getX();
		y2 = (int) p2.getY();
		
		desenho.setColor(cor);	
		//Retas horizontais
		if (p1.getY() == p2.getY()){

			if (x1 < x2) {
				for (int x = x1; x <= x2; x++){
					desenho.drawLine(x, y1, x, y1);
				}
			} 
			else{
				for (int x = x2; x <= x1; x++){
					desenho.drawLine(x, y2, x, y2);
				}
			}
		}
		//Retas verticais
		else if (p1.getX() == p2.getX()){

			if(y1 < y2){
				for(int y = y1; y <= y2; y++){
					desenho.drawLine(x1, y, y1, y);
				}
			} 
			else{
				for(int y = y2; y <= y1; y++){
					desenho.drawLine(x2, y, x2, y);
				}
			}
		} 
		else{
			//Retas inclinadas
			m = getCoefAng(reta);
			b = getB(reta);
			double y;
			int compare = compareTo(reta.getpStart(), reta.getpEnd());
			if (compare == -1){
				for (int x = x1; x <= x2; x++){
					y = (m * x + b);
					desenho.drawLine(x, (int) Math.round(y), x, (int) Math.round(y));
				}
			} 
			else{
				for (int x = x2; x <= x1; x++){
					y = (m * x + b);
	
					desenho.drawLine(x, (int) Math.round(y), x, (int) Math.round(y));
				}
			}
		}
		
		
	}
	
	//Valor de b
	public double getB(Reta r){
		return (r.getpStart().getY() - (getCoefAng(r) * r.getpStart().getX()));
	}
	
	//Coeficiente angular
	public double getCoefAng (Reta r){
		return (double) ((r.getpEnd().getY() - r.getpStart().getY())/(r.getpEnd().getX() - r.getpStart().getX()));
	}
	
	public int compareTo(Point p1, Point p2){
		
		double distanciaP1, distanciaP2;
		
		distanciaP1 = Math.sqrt(Math.pow(p1.getX(), 2) + Math.pow(p1.getX(), 2));
		distanciaP2 = Math.sqrt(Math.pow(p2.getX(), 2) + Math.pow(p2.getY(), 2));
		if(distanciaP1 < distanciaP2)
			return -1;
		else if (distanciaP1 > distanciaP2)
			return 1;
			
		return 0;
	}
	
	// DRAWS LINE USING THE SIMPLE DDA ALGORITHM
	public void drawRetaDDASimples(Reta reta, Color cor){
		
		double x1,y1,x2,y2,dx,dy,len,xInc,yInc,fx,fy;
		Point p1, p2;
		desenho.setColor(cor);
		p1 = reta.getpStart();
		p2 = reta.getpEnd();
		x1 = p1.getX();
		y1 = p1.getY();
		x2 = p2.getX();
		y2 = p2.getY();
		
		dx=(double)(x2-x1);
	    dy=(double)(y2-y1);
	    
	    len=Math.abs(dx);
	    if(Math.abs(dy)>Math.abs(dx))len=Math.abs(dy);
	    
	    if((x1==x2)&& dy<0)len=dy;
	    
	    xInc=dx/len;            //x-increment
	    yInc=dy/len;            //y-increment    
	    if((x1==x2)&& dy<0)yInc=Math.abs(dy)/len;
	    fx=(double)x1;
	    fy=(double)y1;
	    double i=1;

	    if(x1!=x2){
	        while(i<=len){
	            desenho.drawLine((int)Math.floor(fx),(int)Math.floor(fy),
	                       (int)Math.floor(fx),(int)Math.floor(fy));
	            fx=fx+xInc;
	            fy=fy+yInc;
	            i=i+1;
	        }
	    }
	    else if(x1==x2){
	        i=(double)y1;
	        while(i!=(double)y2){
	        desenho.drawLine((int)Math.floor(fx),(int)Math.floor(fy),
	                    (int)Math.floor(fx),(int)Math.floor(fy));
	        fx=fx+xInc;
	        fy=fy+yInc;
	        i=i+yInc;
	       }
	    }
	}

	// DRAWS LINE USING THE INTEGER DDA ALGORITHM (BRESSENHAM)
	public void drawRetaDDAInteiro(Reta reta, Color corR) {
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

		desenho.setColor(corR);
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
	public ArrayList<Point> getPointsOnReta(Reta reta) {
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

	// COLORS FIRST LINE FROM ARRAY
	public void selectReta(ArrayList<Reta> retasList) {
		Point p1 = retasList.get(0).getpStart();
		Point p2 = retasList.get(0).getpEnd();

		Reta reta = new Reta(p1, p2);

		drawRetaDDAInteiro(reta, Color.WHITE);
	}

	// REDRAWS WHOLE ARRAY OF LINES
	public void disSelectReta(ArrayList<Reta> retasList) {
		for (Reta r : retasList) {
			drawRetaDDAInteiro(r, Color.BLACK);
		}
	}

	// FIND PERPENDIULAR LINES
	public int findPerpendicular(Point p1, Point p2, Point p3, Point p4) {

		double x1, y1, x2, y2, x3, y3, x4, y4, m1, m2;

		x1 = p1.getX();
		y1 = p1.getY();
		x2 = p2.getY();
		y2 = p2.getY();
		x3 = p3.getY();
		y3 = p3.getY();
		x4 = p4.getY();
		y4 = p4.getY();

		m1 = (y1 - y2) / (x1 - x2);
		m2 = (y3 - y4) / (x3 - x4);

		if (x1 == x2) {
			if (m2 == 0) {
				return 1;
			} else {
				return 0;
			}
		} else if (x3 == x4) {
			if (m1 == 0) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (m2 * m1 == -1) {
				return 1;
			} else
				return 0;
		}
	}

	// CHECK IF THERE'S A PERPENDICULAR LINE TO SELECTED LINE
	public void checkPerpendicular(Reta selectedReta, ArrayList<Reta> retasList) {
		Point p1, p2, p3, p4;
		int check = 0;

		for (Reta reta : retasList) {

			p1 = selectedReta.getpStart();
			p2 = selectedReta.getpEnd();
			p3 = reta.getpStart();
			p4 = reta.getpEnd();

			check = findPerpendicular(p1, p2, p3, p4);

			if (check == 1) {
				drawRetaDDAInteiro(selectedReta, Color.YELLOW);
				drawRetaDDAInteiro(reta, Color.YELLOW);
			}
		}
		
		if (check == 0)
			System.out.println("Nenhuma reta é perpendicular a reta escolhida.");
	}

	// DRAWS A CROSS ON POINT
	public void drawCrossOnPoint(Point point) {
		// Desenha o X no Point Inicial
		Point p1 = new Point((int) point.getX() - 5, (int) point.getY() - 5);
		Point p2 = new Point((int) point.getX() + 5, (int) point.getY() + 5);
		Point p3 = new Point((int) point.getX() - 5, (int) point.getY() + 5);
		Point p4 = new Point((int) point.getX() + 5, (int) point.getY() - 5);

		Reta reta = new Reta(p1, p2);
		Reta reta2 = new Reta(p3, p4);

		drawRetaDDAInteiro(reta, Color.blue);
		drawRetaDDAInteiro(reta2, Color.blue);
	}

	// HIGHLITHENS THE CROSSING POINTS
	public void highlightCrossingPoints(Reta reta, ArrayList<Reta> retasList) {
		ArrayList<Point> pointsList = new ArrayList<Point>();

		pointsList = getPointsOnReta(reta);

		for (Reta reta2 : retasList) {
			if (reta == reta2) {
				// Não faz nada
			} else {
				ArrayList<Point> pointsList2 = new ArrayList<Point>();

				pointsList2 = getPointsOnReta(reta2);

				for (Point Point : pointsList) {
					for (Point Point2 : pointsList2) {

						if (Point.getX() == Point2.getX() && Point.getY() == Point2.getY()) {
							drawCrossOnPoint(Point2);
						}

					}
				}
			}
		}
	}
}
