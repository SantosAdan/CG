package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import pacote25901.MODEL.Line;
import pacote25901.MODEL.TranslacaoMatriz;

public class TranslacaoController {
	private Graphics output;
	private float matriz[][] = new float[3][3];
	Line reta = new Line();
	
	public static TranslacaoMatriz matrizTranslacao = new TranslacaoMatriz();

	// CONSTRUTOR	
	public TranslacaoController ( Graphics output )
	{
		this.output = output;
	}
	
	public void base(int H, int V){
		matrizTranslacao.setH(H);
		matrizTranslacao.setV(V);
	}
	
	public void formaMatriz(){
		// 1� linha:
		matriz[0][0] = 1;
		matriz[0][1] = 0;
		matriz[0][2] = matrizTranslacao.getH();
	
		// 2� linha:
		matriz[1][0] = 0;
		matriz[1][1] = 1;
		matriz[1][2] = matrizTranslacao.getV();
	
		// 3� linha:
		matriz[2][0] = 0;
		matriz[2][1] = 0;
		matriz[2][2] = 1;
		
		matrizTranslacao.setMatrizTranslacao(matriz);
	}
	
	public void translacaoOrigem(Point pIni, Point pFim, LineController lineCtrl){
		int xIni, yIni, xFim, yFim;
		xIni = (int) (pIni.getX() + matrizTranslacao.getH());
		yIni = (int) (pIni.getY() + matrizTranslacao.getV());
		xFim = (int) (pFim.getX() + matrizTranslacao.getH());
		yFim = (int) (pFim.getY() + matrizTranslacao.getV());
		Point pontoinicialnovo = new Point(xIni,yIni);
		Point pontofinalnovo = new Point(xFim,yFim);
		lineCtrl.drawRectangle(pontoinicialnovo, pontofinalnovo, Color.WHITE);
	}
	
	public void translacaoReferencia(Point pIni, Point pFim, LineController lineCtrl, Point referencia){
		int xIni, yIni, xFim, yFim;
		xIni = (int) (pIni.getX() + referencia.getX());
		yIni = (int) (pIni.getY() + referencia.getY());
		xFim = (int) (pFim.getX() + referencia.getX());
		yFim = (int) (pFim.getY() + referencia.getY());
		Point pontoinicialnovo = new Point(xIni,yIni);
		Point pontofinalnovo = new Point(xFim,yFim);
		translacaoOrigem(pontoinicialnovo, pontofinalnovo, lineCtrl);
	}
	
	public Point pininovo(Point pIni){
		int xIni, yIni;
		
		xIni = (int) (pIni.getX() + matrizTranslacao.getH());
		yIni = (int) (pIni.getY() + matrizTranslacao.getV());
		Point pontoinicialnovo = new Point(xIni,yIni);
		return pontoinicialnovo;
	}
	
	public Point pfimnovo(Point pFim){
		int xFim, yFim;
		
		xFim = (int) (pFim.getX() + matrizTranslacao.getH());
		yFim = (int) (pFim.getY() + matrizTranslacao.getV());
		Point pontofinalnovo = new Point(xFim,yFim);
		return pontofinalnovo;
	}
}
