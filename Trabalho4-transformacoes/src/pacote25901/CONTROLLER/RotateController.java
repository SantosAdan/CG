package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import pacote25901.MODEL.Line;
import pacote25901.MODEL.RotacaoMatriz;

public class RotateController {
	private Graphics output;
	private float matriz[][] = new float[3][3];
	private Line r1, r2, r3, r4;
	
	private RotacaoMatriz matrizRotacao= new RotacaoMatriz();
	
	// CONSTRUTOR	
	public RotateController ( Graphics output )
	{
		this.output = output;
	}
	
	public void base(float cos, float sin){
		matrizRotacao.setCos(cos);
		matrizRotacao.setSin(sin);
	}
	
	public void formaMatriz(){
		// 1� linha:
		matriz[0][0] = matrizRotacao.getCos();
		matriz[0][1] = -matrizRotacao.getSin();
		matriz[0][2] = 0;
	
		// 2� linha:
		matriz[1][0] = matrizRotacao.getSin();
		matriz[1][1] = matrizRotacao.getCos();
		matriz[1][2] = 0;
	
		// 3� linha:
		matriz[2][0] = 0;
		matriz[2][1] = 0;
		matriz[2][2] = 1;
		
		matrizRotacao.setMatrizRotacao(matriz);
	}
	
	public void imprimeMatriz(){
		
		for(int linha=0; linha < matrizRotacao.getMatrizRotacao().length; linha++){
            for(int coluna=0; coluna < matrizRotacao.getMatrizRotacao()[0].length; coluna++){
                System.out.print(matrizRotacao.getMatrizRotacao()[linha][coluna]+ " ");
            }
        System.out.println();
        }
		
	}
	
	public void rotacaoOrigem(Point pIni, Point pFim, LineController retaCtrl){
		int xIni, yIni, xFim, yFim;
		
		xIni = (int) pIni.getX();
		xFim = (int) pFim.getX();
		yIni = (int) pIni.getY();
		yFim = (int) pFim.getY();
		
		//Faz o controle dos pontos
		if(xIni > xFim || yIni > yFim) {
			int aux = xIni;
			xIni = xFim;
			xFim = aux;
			
			aux = yIni;
			yIni = yFim;
			yFim = aux;
		}
		

		//RETA DE CIMA
		r1 = new Line(new Point(xIni, yIni), new Point(xFim, yIni)); 
		//RETA DA ESQUERDA
		r2 = new Line(new Point(xIni, yIni), new Point(xIni, yFim));
		//RETA DA DILine
		r3 = new Line(new Point(xFim, yIni), new Point(xFim, yFim));
		//RETA DE BAIXO
		r4 = new Line(new Point(xIni, yFim), new Point(xFim, yFim));
		
		desenhaBresenhamRotacao(r1, Color.WHITE);
		desenhaBresenhamRotacao(r2, Color.WHITE);
		desenhaBresenhamRotacao(r3, Color.WHITE);
		desenhaBresenhamRotacao(r4, Color.WHITE);
		
	}
	
	public void rotacaoReferencia(Point pIni, Point pFim, LineController retaCtrl, Point referencia){
		int xIni, yIni, xFim, yFim;
		
		
		xIni = (int) (pIni.getX() + referencia.getX());
		yIni = (int) (pIni.getY() + referencia.getY());
		xFim = (int) (pFim.getX() + referencia.getX());
		yFim = (int) (pFim.getY() + referencia.getY());
		Point pontoinicialnovo = new Point(xIni,yIni);
		Point pontofinalnovo = new Point(xFim,yFim);
		rotacaoOrigem(pontoinicialnovo, pontofinalnovo, retaCtrl);
	}
	
	
	private void desenhaBresenhamRotacao(Line reta, Color corR){
		
		Point p1, p2;
		int x1, x2, y1, y2, dx, dy;

		p1 = reta.getpStart();
		p2 = reta.getpEnd();

		x1 = (int) (p1.getX()*matrizRotacao.getCos() - p1.getY()*matrizRotacao.getSin());
		y1 = (int) (p1.getY()*matrizRotacao.getCos() + p1.getX()*matrizRotacao.getSin());
		x2 = (int) (p2.getX()*matrizRotacao.getCos() - p2.getY()*matrizRotacao.getSin());
		y2 = (int) (p2.getY()*matrizRotacao.getCos() + p2.getX()*matrizRotacao.getSin());

		dx = Math.abs(x2 - x1);
		dy = Math.abs(y2 - y1);

		int sx = (x1 < x2) ? 1 : -1;
		int sy = (y1 < y2) ? 1 : -1;

		int err = dx - dy;

		output.setColor(corR);
		while (true) {
			output.drawLine(x1, y1, x1, y1);

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
}
