package pacote25901.MODEL;

import java.awt.Point;

public class EscalamentoMatriz {
	private float matrizEscalamento[][] = new float[3][3];
	private float Sx, Sy;
	private Point pIni, pFim;
	
	public EscalamentoMatriz(){
		
	}
	
	public Point getpIni() {
		return pIni;
	}

	public void setpIni(Point pIni) {
		this.pIni = pIni;
	}

	public Point getpFim() {
		return pFim;
	}

	public void setpFim(Point pFim) {
		this.pFim = pFim;
	}
	
	public float[][] getMatrizEscalamento() {
		return matrizEscalamento;
	}
	public void setMatrizEscalamento(float[][] matrizEscalamento) {
		this.matrizEscalamento = matrizEscalamento;
	}
	public float getSx() {
		return Sx;
	}
	public void setSx(float sx) {
		Sx = sx;
	}
	public float getSy() {
		return Sy;
	}
	public void setSy(float sy) {
		Sy = sy;
	}
}
