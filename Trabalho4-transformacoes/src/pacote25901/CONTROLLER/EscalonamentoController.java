package pacote25901.CONTROLLER;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import pacote25901.MODEL.EscalamentoMatriz;
import pacote25901.MODEL.Line;

public class EscalonamentoController {
	private Graphics output;
	private float matriz[][] = new float[3][3];
	Line reta = new Line();

	private EscalamentoMatriz matrizEscalamento = new EscalamentoMatriz();
	
	// CONSTRUTOR	
	public EscalonamentoController ( Graphics output )
	{
		this.output = output;
	}
	
	public void base(float Sx, float Sy){
		matrizEscalamento.setSx(Sx);
		matrizEscalamento.setSy(Sy);
	}
	
	public void formaMatriz(){
		// 1� linha:
		matriz[0][0] = matrizEscalamento.getSx();
		matriz[0][1] = 0;
		matriz[0][2] = 0;
	
		// 2� linha:
		matriz[1][0] = 0;
		matriz[1][1] = matrizEscalamento.getSy();
		matriz[1][2] = 0;
	
		// 3� linha:
		matriz[2][0] = 0;
		matriz[2][1] = 0;
		matriz[2][2] = 1;
		
		matrizEscalamento.setMatrizEscalamento(matriz);
	}
	
	public void imprimeMatriz(){
		
		for(int linha=0; linha < matrizEscalamento.getMatrizEscalamento().length; linha++){
            for(int coluna=0; coluna < matrizEscalamento.getMatrizEscalamento()[0].length; coluna++){
                System.out.print(matrizEscalamento.getMatrizEscalamento()[linha][coluna]+ " ");
            }
        System.out.println();
        }
		
	}
	
	public void escalamentoOrigem(Point pIni, Point pFim, LineController retaCtrl){
		int xIni, yIni, xFim, yFim;
		xIni = (int) (pIni.getX()*matrizEscalamento.getSx());
		yIni = (int) (pIni.getY()*matrizEscalamento.getSy());
		xFim = (int) (pFim.getX()*matrizEscalamento.getSx());
		yFim = (int) (pFim.getY()*matrizEscalamento.getSy());
		Point pontoinicialnovo = new Point(xIni,yIni);
		Point pontofinalnovo = new Point(xFim,yFim);
		retaCtrl.drawRectangle(pontoinicialnovo, pontofinalnovo, Color.WHITE);
	}
	
	public void escalamentoReferencia(Point pIni, Point pFim, LineController retaCtrl, Point referencia){
		int xIni, yIni, xFim, yFim;
		xIni = (int) (pIni.getX() + referencia.getX());
		yIni = (int) (pIni.getY() + referencia.getY());
		xFim = (int) (pFim.getX() + referencia.getX());
		yFim = (int) (pFim.getY() + referencia.getY());
		Point pontoinicialnovo = new Point(xIni,yIni);
		Point pontofinalnovo = new Point(xFim,yFim);
		escalamentoOrigem(pontoinicialnovo, pontofinalnovo, retaCtrl);
	}
	
	public Point pininovo(Point pIni, float Sx, float Sy){
		int xIni, yIni;
		
		xIni = (int) (pIni.getX()*Sx);
		yIni = (int) (pIni.getY()*Sy);
		Point pontoinicialnovo = new Point(xIni,yIni);
		return pontoinicialnovo;
	}
	
	public Point pfimnovo(Point pFim, float Sx, float Sy){
		int xFim, yFim;
		
		xFim = (int) (pFim.getX()*Sx);
		yFim = (int) (pFim.getY()*Sy);
		Point pontofinalnovo = new Point(xFim,yFim);
		return pontofinalnovo;
	}
}
