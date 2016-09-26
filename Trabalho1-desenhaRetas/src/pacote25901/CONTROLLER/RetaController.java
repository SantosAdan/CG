package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
			System.out.println("Ponto Inicial: (" + retasList.get(i).getpStart().getX() + ", "
					+ retasList.get(i).getpStart().getY() + ") - " + "Ponto Final: ("
					+ retasList.get(i).getpEnd().getX() + ", " + retasList.get(i).getpEnd().getY() + ")");
		}
	}

	public void drawReta(Reta reta, Color corR) {
		Point p1, p2;
		int x1, x2, y, y1, y2, dx, dy;

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

	public ArrayList<Point> getPointsOnReta(Reta reta) {
		ArrayList<Point> pointsList = new ArrayList<Point>();

		Point p1, p2;
		int x1, x2, y, y1, y2, dx, dy;

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

	// Seleciona uma reta
	public void selectReta(ArrayList<Reta> retasList) {
		Point p1 = retasList.get(0).getpStart();
		Point p2 = retasList.get(0).getpEnd();

		Reta reta = new Reta(p1, p2);

		drawReta(reta, Color.WHITE);
	}

	// Pinta de preto as retas que não estão selecionadas
	public void disSelectReta(ArrayList<Reta> retasList) {
		for (Reta r : retasList) {
			drawReta(r, Color.BLACK);
		}
	}

	// Descobre quais retas são perpendiculares
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

		// checa se as retas sao perpendiculares para retas verticais e horizontais
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

	// Verifica se existem retas perpendiculares a reta selecionada
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
				drawReta(selectedReta, Color.YELLOW);
				drawReta(reta, Color.YELLOW);
			}
		}
		
		if (check == 0)
			System.out.println("Nenhuma reta é perpendicular a reta escolhida.");
	}

	// Método para desenhar um x no ponto desejadoo
	public void drawCrossOnPoint(Point point) {
		// Desenha o X no ponto Inicial
		Point p1 = new Point((int) point.getX() - 5, (int) point.getY() - 5);
		Point p2 = new Point((int) point.getX() + 5, (int) point.getY() + 5);
		Point p3 = new Point((int) point.getX() - 5, (int) point.getY() + 5);
		Point p4 = new Point((int) point.getX() + 5, (int) point.getY() - 5);

		Reta reta = new Reta(p1, p2);
		Reta reta2 = new Reta(p3, p4);

		drawReta(reta, Color.blue);
		drawReta(reta2, Color.blue);
	}

	// Destaca os pontos de cruzamento
	public void highlightCrossingPoints(Reta reta, ArrayList<Reta> retasList) {
		ArrayList<Point> pointsList = new ArrayList<Point>();

		pointsList = getPointsOnReta(reta);

		for (Reta reta2 : retasList) {
			if (reta == reta2) {
				// Não faz nada
			} else {
				ArrayList<Point> pointsList2 = new ArrayList<Point>();

				pointsList2 = getPointsOnReta(reta2);

				for (Point ponto : pointsList) {
					for (Point ponto2 : pointsList2) {

						if (ponto.getX() == ponto2.getX() && ponto.getY() == ponto2.getY()) {
							drawCrossOnPoint(ponto2);
						}

					}
				}
			}
		}
	}
}
