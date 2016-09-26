package pacote25901.MODEL;

import java.awt.Point;

public class TranslacaoMatriz {
	private float matrizTranslacao[][] = new float[3][3];
	private int H, V;
	private Point pIni, pFim;
	
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

	public TranslacaoMatriz(){
		
	}

	public float[][] getMatrizTranslacao() {
		return matrizTranslacao;
	}

	public void setMatrizTranslacao(float[][] matrizTranslacao) {
		this.matrizTranslacao = matrizTranslacao;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}

	public int getV() {
		return V;
	}

	public void setV(int v) {
		V = v;
	}
}
