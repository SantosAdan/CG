package pacote25901.VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pacote25901.CONTROLLER.AppController;
import pacote25901.CONTROLLER.RetaController;
import pacote25901.MODEL.Reta;

public class StartPanel implements MouseListener, MouseMotionListener {
	private JFrame baseFrame;
	private JPanel basePanel, outputPanel;

	private JButton btnSelect, btnEnd, btnCrossing, btnPerpendicular, btnClear;
	private Graphics desenho;

	private Point pStart, pEnd;
	private Reta selectedReta;
	private ArrayList<Reta> retasList = new ArrayList<Reta>();
	private ArrayList<Reta> retasListCopy = new ArrayList<Reta>();
	private String flag, messageFlag;
	private int algorithmType = 1;

	// CONSTRUCTOR
	public StartPanel(AppController appCtrl) {
		JPanel buttonPanel, radioPanel;
		JPanel titlePanel;
		JRadioButton classicRadio, ddaRadio, bressenhamRadio;
		ButtonGroup group = new ButtonGroup();

		flag = "DRAWING";
		messageFlag = "FIRST";

		// LAYOUT
		baseFrame = new JFrame();
		baseFrame.setLayout(new BoxLayout(baseFrame.getContentPane(), BoxLayout.Y_AXIS));

		baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // FITS PANEL TO THE
															// ACTUAL MONITOR
															// SIZE
		baseFrame.setUndecorated(true); // TURN OFF ALL THE PANEL BORDERS

		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());

		// TITLE PANEL
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(0, 30));

		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout());
		outputPanel.setBackground(Color.lightGray);

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 50));

		// RADIO PANEL
		radioPanel = new JPanel();
		radioPanel.setPreferredSize(new Dimension(150, 130));
		radioPanel.setLayout(new GridLayout(3, 1));
		radioPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Selecionar Algoritmo"));

		// PANEL TITLE
		JLabel titulo, subtitulo;
		titulo = new JLabel("TRABALHO 2 - Algoritmos de Retas - 25901");
		titulo.setForeground(Color.black);
		titulo.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePanel.add(titulo);
		
		subtitulo = new JLabel("(Classico, DDA Simples e DDA Inteiro)");
		subtitulo.setForeground(Color.black);
		subtitulo.setFont(new Font("Dialog", Font.BOLD, 12));
		titlePanel.add(subtitulo);

		// RADIO BUTTONS
		classicRadio = new JRadioButton("Classico", true);
		classicRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				algorithmType = 1;
			}

		});

		ddaRadio = new JRadioButton("DDA Simples", false);
		ddaRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				algorithmType = 2;
			}

		});

		bressenhamRadio = new JRadioButton("DDA Inteiro", false);
		bressenhamRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				algorithmType = 3;
			}

		});

		// Adding RADIO BUTTONS TO SAME GROUP
		group.add(classicRadio);
		group.add(ddaRadio);
		group.add(bressenhamRadio);

		// ADDING RADIO BUTTONS
		radioPanel.add(classicRadio);
		radioPanel.add(ddaRadio);
		radioPanel.add(bressenhamRadio);

		// ADDING BUTTONS
		btnSelect = addAButton("Selecionar Reta", "btnSelect", buttonPanel);
		btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (messageFlag == "FIRST") {
					JOptionPane.showMessageDialog(null,
							"Aperte o botão Selecionar Reta até que a reta desejada seja pintada de branco!");
					messageFlag = "OK";
				}

				if (flag == "DRAWING") {
					retasListCopy = new ArrayList<Reta>(retasList);
				}

				if (retasList.size() != 0) {
					RetaController retaCtrl = new RetaController(startGraphics());
					flag = "SELECTING";
					retaCtrl.disSelectReta(retasListCopy);
					retaCtrl.selectReta(retasList);
					selectedReta = retasList.get(0);
					retasList.remove(0);
				}
			}

		});

		// Botão para destacar os pontos de intersecção da reta escolhida
		btnCrossing = addAButton("Destacar Cruzamentos", "btnCrossing", buttonPanel);
		btnCrossing.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (flag == "SELECTING") {
					RetaController retaCtrl = new RetaController(startGraphics());
					retaCtrl.highlightCrossingPoints(selectedReta, retasListCopy);
					retasList.add(selectedReta);
				}
			}

		});

		// BOTÃO PARA VERIFICAR SE EXISTE RETAS PERPENDICULARES
		btnPerpendicular = addAButton("Retas Perpendiculares", "btnPerpendicular", buttonPanel);
		btnPerpendicular.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (flag == "SELECTING") {
					RetaController retaCtrl = new RetaController(startGraphics());
					retaCtrl.checkPerpendicular(selectedReta, retasListCopy);
				}
			}
		});

		// BOTÃO PARA LIMPAR A TELA
		btnClear = addAButton("Limpar", "btnEnd", buttonPanel);
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearOutputPanel(outputPanel);
				flag = "DRAWING";
				retasList = new ArrayList<Reta>();
			}

		});

		btnEnd = addAButton("Fechar", "btnEnd", buttonPanel);
		btnEnd.addActionListener((ActionListener) appCtrl);

		// MOUSE LISTENERS
		outputPanel.addMouseListener(this);
		outputPanel.addMouseMotionListener(this);

		// VISIBLE PANELS
		baseFrame.add(basePanel);

		basePanel.add(titlePanel, BorderLayout.PAGE_START);
		basePanel.add(radioPanel, BorderLayout.WEST);
		basePanel.add(outputPanel, BorderLayout.CENTER);
		basePanel.add(buttonPanel, BorderLayout.PAGE_END);

		baseFrame.setVisible(true);
	}

	// METODO UTILIZADO PARA ADICIONAR UM BOTAO A UM CONTAINER DO PROGRAMA
	private JButton addAButton(String btnText, String btnController, Container container) {
		JButton btn;

		btn = new JButton(btnText);
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		container.add(btn);

		btn.setActionCommand(btnController);

		return btn;
	}

	// MÉTODO PARA MOSTRAR O FRAME BASICO
	public void showPanel() {
		basePanel.setVisible(true);
	}

	// METODO PARA INICIAR PAINEL DE DESENHO
	public Graphics startGraphics() {

		desenho = outputPanel.getGraphics();

		return (desenho);
	}

	public void drawCrossOnPoint(Point point) {
		// Desenha o X no ponto Inicial
		Point p1 = new Point((int) point.getX() - 3, (int) point.getY() - 3);
		Point p2 = new Point((int) point.getX() + 3, (int) point.getY() + 3);
		Point p3 = new Point((int) point.getX() - 3, (int) point.getY() + 3);
		Point p4 = new Point((int) point.getX() + 3, (int) point.getY() - 3);

		Reta reta = new Reta(p1, p2);
		Reta reta2 = new Reta(p3, p4);
		RetaController crossCtrl = new RetaController(startGraphics());

		crossCtrl.drawReta(reta, Color.RED, 3);
		crossCtrl.drawReta(reta2, Color.RED, 3);
	}

	// LIMPA O PAINEL DE DESENHO
	public void clearOutputPanel(JPanel outputPanel) {
		outputPanel.repaint();
	}

	// ==============================================================================================//
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		pStart = e.getPoint();

		switch (flag) {
		case "DRAWING":
			drawCrossOnPoint(pStart);
			break;
		case "SELECTING":
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pEnd = e.getPoint();
		RetaController retaCtrl = new RetaController(startGraphics());

		if (flag.equals("DRAWING")) {
			drawCrossOnPoint(pEnd);
			Reta reta = new Reta(pStart, pEnd);
			retasList.add(reta);
			retaCtrl.drawReta(reta, Color.BLACK, algorithmType);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
