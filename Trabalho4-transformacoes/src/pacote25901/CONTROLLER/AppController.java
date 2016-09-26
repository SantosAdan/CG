package pacote25901.CONTROLLER;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import pacote25901.MODEL.Line;
import pacote25901.VIEW.StartPanel;

public class AppController implements ActionListener {
	private StartPanel startPanel;
	public static int x = 1;

	int counter1 = 0, counter2 = 0, counter3 = 0;
	int contadorTranslacao = 0, contadorEscalamento = 0;
	int auxH, auxV;

	Point pIniaux, pFimaux;

	private float matriz[][] = new float[3][3];
	int h, v;
	float sx, sy;
	float ang;

	// CONSTRUCTOR
	public AppController() {
		startPanel = new StartPanel(this);
		startPanel.showPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command;

		command = e.getActionCommand();

		//
		switch (command) {
		case "btnEnd":
			System.exit(0);
			break;
		}
	}

	public void translacao(LineController LineCtrl, TranslacaoController TransCtrl, String comando1, String comando2,
			Point pIni, Point pFim, Point referencia) {
		String V = JOptionPane.showInputDialog("Valor do deslocamento VERTICAL:");
		String H = JOptionPane.showInputDialog("Valor do deslocamento HORIZONTAL:");

		if (comando1 == "origem") {
			if (comando2 == "padrao") {
				TransCtrl.base(Integer.parseInt(H), Integer.parseInt(V));
				TransCtrl.formaMatriz();
				TransCtrl.translacaoOrigem(pIni, pFim, LineCtrl);
			} 
			else if (comando2 == "sucessivas") {
				if (counter1 == 0) {
					TransCtrl.base(Integer.parseInt(H), Integer.parseInt(V));
					TransCtrl.formaMatriz();
					TransCtrl.translacaoOrigem(pIni, pFim, LineCtrl);
					counter1 = 1;
					h = Integer.parseInt(H);
					v = Integer.parseInt(V);
				} 
				else {
					TransCtrl.base(Integer.parseInt(H) + h, Integer.parseInt(V) + v);
					h = Integer.parseInt(H) + h;
					v = Integer.parseInt(V) + v;
					TransCtrl.formaMatriz();
					TransCtrl.translacaoOrigem(pIni, pFim, LineCtrl);
				}

			}
		} 
		else {
			if (comando2 == "padrao") {
				TransCtrl.base(Integer.parseInt(H), Integer.parseInt(V));
				TransCtrl.formaMatriz();
				TransCtrl.translacaoReferencia(pIni, pFim, LineCtrl, referencia);
			} 
			else if (comando2 == "sucessivas") {
				matriz = TranslacaoController.matrizTranslacao.getMatrizTranslacao();
				if (counter1 == 0) {
					TransCtrl.base(Integer.parseInt(H), Integer.parseInt(V));
					TransCtrl.formaMatriz();
					TransCtrl.translacaoReferencia(pIni, pFim, LineCtrl, referencia);
					counter1 = 1;
					h = Integer.parseInt(H);
					v = Integer.parseInt(V);
				} 
				else {
					TransCtrl.base(Integer.parseInt(H) + h, Integer.parseInt(V) + v);
					h = Integer.parseInt(H) + h;
					v = Integer.parseInt(V) + v;
					TransCtrl.formaMatriz();
					TransCtrl.translacaoReferencia(pIni, pFim, LineCtrl, referencia);
				}

			}
		}
	}

	public void escalamento(LineController LineCtrl, EscalonamentoController escaCtrl, String comando1, String comando2,
			Point pIni, Point pFim, Point referencia) {
		String Sy = JOptionPane.showInputDialog("Valor de Sy:");
		String Sx = JOptionPane.showInputDialog("Valor de Sx:");

		if (comando1 == "origem") {
			if (comando2 == "padrao") {
				escaCtrl.base(Float.parseFloat(Sx), Float.parseFloat(Sy));
				escaCtrl.formaMatriz();
				escaCtrl.escalamentoOrigem(pIni, pFim, LineCtrl);
			} 
			else if (comando2 == "sucessivas") {
				if (counter2 == 0) {
					escaCtrl.base(Float.parseFloat(Sx), Float.parseFloat(Sy));
					escaCtrl.formaMatriz();
					escaCtrl.escalamentoOrigem(pIni, pFim, LineCtrl);
					counter2 = 1;
					sx = Float.parseFloat(Sx);
					sy = Float.parseFloat(Sy);
				} 
				else {
					escaCtrl.base(Float.parseFloat(Sx) * sx, Float.parseFloat(Sy) * sy);
					sx = Float.parseFloat(Sx) * sx;
					sy = Float.parseFloat(Sy) * sy;
					escaCtrl.formaMatriz();
					escaCtrl.escalamentoOrigem(pIni, pFim, LineCtrl);
				}

			}
		} 
		else {
			if (comando2 == "padrao") {
				escaCtrl.base(Float.parseFloat(Sx), Float.parseFloat(Sy));
				escaCtrl.formaMatriz();
				escaCtrl.escalamentoReferencia(pIni, pFim, LineCtrl, referencia);
			} 
			else if (comando2 == "sucessivas") {
				if (counter2 == 0) {
					escaCtrl.base(Float.parseFloat(Sx), Float.parseFloat(Sy));
					escaCtrl.formaMatriz();
					escaCtrl.escalamentoReferencia(pIni, pFim, LineCtrl, referencia);
					counter2 = 1;
					sx = Float.parseFloat(Sx);
					sy = Float.parseFloat(Sy);
				} 
				else {
					escaCtrl.base(Float.parseFloat(Sx) * sx, Float.parseFloat(Sy) * sy);
					sx = Float.parseFloat(Sx) * sx;
					sy = Float.parseFloat(Sy) * sy;
					escaCtrl.formaMatriz();
					escaCtrl.escalamentoReferencia(pIni, pFim, LineCtrl, referencia);
				}
			}
		}
	}

	public void rotacao(LineController LineCtrl, RotateController rotateCtrl, String comando1, String comando2, Point pIni,
			Point pFim, Point referencia) {
		String rad = JOptionPane.showInputDialog("Valor do ângulo(em graus):");
		float angulo = Float.parseFloat(rad);

		if (comando1 == "origem") {
			if (comando2 == "padrao") {
				rotateCtrl.base((float) Math.cos(Math.toRadians(angulo)), (float) Math.sin(Math.toRadians(angulo)));
				rotateCtrl.formaMatriz();
				rotateCtrl.rotacaoOrigem(pIni, pFim, LineCtrl);
			} 
			else if (comando2 == "sucessivas") {
				if (counter3 == 0) {
					rotateCtrl.base((float) Math.cos(Math.toRadians(angulo)),
							(float) Math.sin(Math.toRadians(angulo)));
					rotateCtrl.formaMatriz();
					rotateCtrl.rotacaoOrigem(pIni, pFim, LineCtrl);
					counter3 = 1;
					ang = angulo;
				} 
				else {
					ang = ang + angulo;
					rotateCtrl.base((float) Math.cos(Math.toRadians(ang)), (float) Math.sin(Math.toRadians(ang)));
					rotateCtrl.formaMatriz();
					rotateCtrl.rotacaoOrigem(pIni, pFim, LineCtrl);
				}
			}

		} 
		else {
			if (comando2 == "padrao") {
				rotateCtrl.base((float) Math.cos(Math.toRadians(angulo)), (float) Math.sin(Math.toRadians(angulo)));
				rotateCtrl.formaMatriz();
				rotateCtrl.rotacaoReferencia(pIni, pFim, LineCtrl, referencia);
			} 
			else if (comando2 == "sucessivas") {
				if (counter3 == 0) {
					rotateCtrl.base((float) Math.cos(Math.toRadians(angulo)),
							(float) Math.sin(Math.toRadians(angulo)));
					rotateCtrl.formaMatriz();
					rotateCtrl.rotacaoReferencia(pIni, pFim, LineCtrl, referencia);
					counter3 = 1;
					ang = angulo;
				} 
				else {
					ang = ang + angulo;
					rotateCtrl.base((float) Math.cos(Math.toRadians(ang)), (float) Math.sin(Math.toRadians(ang)));
					rotateCtrl.formaMatriz();
					rotateCtrl.rotacaoReferencia(pIni, pFim, LineCtrl, referencia);
				}
			}
		}
	}
}
