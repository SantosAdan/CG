package pacote25901.MODEL;

import java.awt.Point;

public class RotacaoMatriz {
	private float matrizRotacao[][] = new float[3][3];
	private float cos, sin;
	Point pIni, pFim;
	
	public RotacaoMatriz() {
		
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
	
	public float[][] getMatrizRotacao() {
		return matrizRotacao;
	}
	public void setMatrizRotacao(float[][] matrizRotacao) {
		this.matrizRotacao = matrizRotacao;
	}
	public float getCos() {
		return cos;
	}
	public void setCos(float cos) {
		this.cos = cos;
	}
	public float getSin() {
		return sin;
	}
	public void setSin(float sin) {
		this.sin = sin;
	}
}
