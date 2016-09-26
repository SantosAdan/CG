package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import pacote25901.MODEL.Ellipse;

public class EllipseController {
	private Graphics desenho;

	// CONSTRUCTOR
	public EllipseController(Graphics desenho) {
		this.desenho = desenho;
	}

	// BRESENHAM'S DRAW CIRCLE ALGORITHM
	public void drawEllipseDDAInteiro(Ellipse ellipse, Color color) {
		int x, y, p, px, py;
		int xAxisSquared = (int) Math.pow(ellipse.gethAxis(), 2);
		int yAxisSquared = (int) Math.pow(ellipse.getvAxis(), 2);
		
		x = 0;
		y = (int) ellipse.getvAxis();
		px = 0;
		py = 2*xAxisSquared*y;
		
		ellipsePlotPoint(ellipse.getCenter(), x, y, color);
		
		/* Region 1 */
		p = (int) (yAxisSquared - (xAxisSquared*ellipse.getvAxis()) + (0.25+xAxisSquared));
		while(px < py) {
			x++;
			px += (2*yAxisSquared);
			
			if(p < 0) {
				p += yAxisSquared + px;
			}
			else {
				y--;
				py -= (2*xAxisSquared);
				p += yAxisSquared + px - py;
			}
			ellipsePlotPoint(ellipse.getCenter(), x, y, color);
		}
		
		/* Region2 */
        p = (int)(yAxisSquared*(x+0.5)*(x+0.5)+xAxisSquared*(y-1)*(y-1)-xAxisSquared*yAxisSquared);
        while(y > 0){
            y--;
            py -= (2*xAxisSquared);
            
            if(p > 0) {
                p += xAxisSquared - py;
            }
            else{
                x++;
                px += (2*yAxisSquared);
                p += xAxisSquared + px - py;
            }
            ellipsePlotPoint(ellipse.getCenter(), x, y, color);
        }
	}

	public void ellipsePlotPoint(Point center, int x, int y, Color color) {
		desenho.setColor(color);

		desenho.drawLine((int) center.getX() + x, (int) center.getY() + y, (int) center.getX() + x,
				(int) center.getY() + y);
		desenho.drawLine((int) center.getX() - x, (int) center.getY() + y, (int) center.getX() - x,
				(int) center.getY() + y);
		desenho.drawLine((int) center.getX() + x, (int) center.getY() - y, (int) center.getX() + x,
				(int) center.getY() - y);
		desenho.drawLine((int) center.getX() - x, (int) center.getY() - y, (int) center.getX() - x,
				(int) center.getY() - y);
	}
}
