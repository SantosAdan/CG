package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;

import pacote25901.MODEL.Arc;

public class ArcController {
	private Graphics desenho;

	// CONSTRUCTOR
	public ArcController(Graphics desenho) {
		this.desenho = desenho;
	}
	
	public void drawArcQuarter(Arc arc, Color color, int quarter) {
		int x, y, x0, y0, err;
		
		x0 = (int) arc.getCenter().getX();
		y0 = (int) arc.getCenter().getY();
		
		x = arc.getRadius();
		y = 0;
		err = 0;
		
		desenho.setColor(color);
		while(x >= y) {
			
			switch(quarter) {
				case 1:
					// 1º Quadrante
					desenho.drawLine(x0 + y, y0 - x, x0 + y, y0 - x);
					desenho.drawLine(x0 + x, y0 - y, x0 + x, y0 - y);
					break;
				case 2:
					// 2º Quadrante
					desenho.drawLine(x0 - x, y0 - y, x0 - x, y0 - y);
					desenho.drawLine(x0 - y, y0 - x, x0 - y, y0 - x);
					break;
				case 3:
					// 3º Quadrante
					desenho.drawLine(x0 - y, y0 + x, x0 - y, y0 + x);
					desenho.drawLine(x0 - x, y0 + y, x0 - x, y0 + y);
					break;
				case 4:
					// 4º Quadrante
					desenho.drawLine(x0 + x, y0 + y, x0 + x, y0 + y);
					desenho.drawLine(x0 + y, y0 + x, x0 + y, y0 + x);
					break;
			}
			
						
			y += 1;
	        err += 1 + 2*y;
	        if (2*(err-x) + 1 > 0)
	        {
	            x -= 1;
	            err += 1 - 2*x;
	        }
		}
	}
}
