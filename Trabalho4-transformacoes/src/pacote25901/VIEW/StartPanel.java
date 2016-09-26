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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
import pacote25901.CONTROLLER.CircleController;
import pacote25901.CONTROLLER.EscalonamentoController;
import pacote25901.CONTROLLER.LineController;
import pacote25901.CONTROLLER.RotateController;
import pacote25901.CONTROLLER.TranslacaoController;
import pacote25901.MODEL.Circle;

public class StartPanel implements MouseListener, MouseMotionListener {
	private JFrame windowFrame;
	private JPanel basePanel, outPanel; 
	private JPanel outputPanel, inputPanel;

	private JButton btnEnd, btnClear, btnTrans, btnEscal, btnRotate;
	private Graphics desenho;

	private Point pStart, pEnd, referencia;
	private boolean drawFlag = true;
	String comando1 = new String();
	String comando2 = new String();

	// CONSTRUCTOR
	public StartPanel(AppController appCtrl) {
		JPanel buttonPanel, radioPanel, radioPanel2;
		JPanel titlePanel;
		
		JRadioButton radio1, radio2, radio3, radio4;
		ButtonGroup group1 = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();

		// LAYOUT
		windowFrame = new JFrame();
		windowFrame.setLayout(new BoxLayout(windowFrame.getContentPane(), BoxLayout.Y_AXIS));

		windowFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // FITS PANEL TO THE ACTUAL MONITOR SIZE
		windowFrame.setUndecorated(true); // TURN OFF ALL THE PANEL BORDERS

		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
		outPanel = new JPanel();
		outPanel.setLayout(new BorderLayout());

		// TITLE PANEL
		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(0, 30));

		// INPUT PANEL
		inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.setBackground(Color.lightGray);
				
		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout());
		outputPanel.setBackground(Color.darkGray);

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 50));
		
		// RADIO PANEL
		radioPanel = new JPanel();
		radioPanel.setPreferredSize(new Dimension(120, 130));
		radioPanel.setLayout(new GridLayout(2, 1));
		radioPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Referência"));
		
		radioPanel2 = new JPanel();
		radioPanel2.setPreferredSize(new Dimension(120, 130));
		radioPanel2.setLayout(new GridLayout(2, 1));
		radioPanel2.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tipo"));

		// PANEL TITLE
		JLabel titulo;
		titulo = new JLabel("TRABALHO 4 - Transformações - 25901");
		titulo.setForeground(Color.black);
		titulo.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePanel.add(titulo);
		
		// RADIO BUTTONS
		radio1 = new JRadioButton("Origem");
		radio1.setMnemonic(KeyEvent.VK_B);
	    radio1.setActionCommand("origem");
	    radio1.setSelected(true);
	    radio1.addActionListener(appCtrl);
	    
	    radio2 = new JRadioButton("Referência");
		radio2.setMnemonic(KeyEvent.VK_B);
	    radio2.setActionCommand("referencia");
	    radio2.setSelected(false);
	    radio2.addActionListener(appCtrl);
	    
		// ADDING RADIO BUTTONS to LEFT PANEL
		radioPanel.add(radio1);
		radioPanel.add(radio2);

	    radio3 = new JRadioButton("Padrão");
		radio3.setMnemonic(KeyEvent.VK_B);
	    radio3.setActionCommand("padrao");
	    radio3.setSelected(true);
	    radio3.addActionListener(appCtrl);
	    
	    radio4 = new JRadioButton("Sucessivas");
		radio4.setMnemonic(KeyEvent.VK_B);
	    radio4.setActionCommand("sucessivas");
	    radio4.setSelected(false);
	    radio4.addActionListener(appCtrl);
	    
		// ADDING RADIO BUTTONS to LEFT PANEL
		radioPanel2.add(radio3);
		radioPanel2.add(radio4);
	    
	    // TRANSLAÇÃO
	    btnTrans = addAButton("Translacao", "btnTrans", buttonPanel);
		btnTrans.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				clearOutputPanel(outputPanel);
				
				LineController lineCtrl = new LineController(startGraphicsOutput());
				TranslacaoController transCtrl = new TranslacaoController(desenho);
				
		
				comando1 = group1.getSelection().getActionCommand();
				comando2 = group2.getSelection().getActionCommand();
				
				appCtrl.translacao(lineCtrl, transCtrl, comando1, comando2, pStart, pEnd, referencia);
				
			}
	
		});
	
		//Escalamento
		btnEscal = addAButton("Escalamento", "btnEscal", buttonPanel);
		btnEscal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearOutputPanel(outputPanel);
				
				LineController ctrlReta = new LineController(startGraphicsOutput());
				
				EscalonamentoController escalamentoctrl = new EscalonamentoController(desenho);
				
				comando1 = group1.getSelection().getActionCommand();
				comando2 = group2.getSelection().getActionCommand();
				
				appCtrl.escalamento(ctrlReta, escalamentoctrl, comando1, comando2, pStart, pEnd, referencia);
				
			}

		});
			
		//Rotacao
		btnRotate = addAButton("Rotacao", "btnRotate", buttonPanel);
		btnRotate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearOutputPanel(outputPanel);
				
				LineController ctrlReta = new LineController(startGraphicsOutput());
				RotateController rotacaoctrl = new RotateController(desenho);
				
				comando1 = group1.getSelection().getActionCommand();
				comando2 = group2.getSelection().getActionCommand();
				
				appCtrl.rotacao(ctrlReta, rotacaoctrl, comando1, comando2, pStart, pEnd, referencia);
			}
		});

		// BOTÃO PARA LIMPAR A TELA
		btnClear = addAButton("Limpar", "btnEnd", buttonPanel);
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearOutputPanel(inputPanel);
				clearOutputPanel(outputPanel);
				drawFlag = true;
			}

		});

		btnEnd = addAButton("Fechar", "btnEnd", buttonPanel);
		btnEnd.addActionListener((ActionListener) appCtrl);
	    
	    group1.add(radio1);
	    group1.add(radio2);
	    group2.add(radio3);
	    group2.add(radio4);

		// MOUSE LISTENERS
		inputPanel.addMouseListener(this);
		inputPanel.addMouseMotionListener(this);

		// VISIBLE PANELS
		windowFrame.add(basePanel);
		windowFrame.add(outPanel);

		basePanel.add(titlePanel, BorderLayout.PAGE_START);
		basePanel.add(radioPanel, BorderLayout.WEST);
		basePanel.add(inputPanel, BorderLayout.CENTER);
		
		outPanel.add(outputPanel, BorderLayout.CENTER);
		outPanel.add(radioPanel2, BorderLayout.WEST);
		outPanel.add(buttonPanel, BorderLayout.PAGE_END);

		windowFrame.setVisible(true);
			
		JOptionPane.showMessageDialog(null,
				"Clique em uma área da tela para marcar o primeiro ponto e arraste o mouse até o local do segundo ponto.");

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
		outPanel.setVisible(true);
	}

	// METODO PARA INICIAR PAINEL DE DESENHO
	public Graphics startGraphics() {

		desenho = inputPanel.getGraphics();

		return (desenho);
	}

	// METODO PARA INICIAR PAINEL DE RESULTADO
	public Graphics startGraphicsOutput() {

		desenho = outputPanel.getGraphics();

		return (desenho);
	}

	// DESENHA CÍRCULO PARA MARCAR O PONTO
	public void drawCircleOnPoint(Point p, Color color) {
		CircleController ctrlCircle = new CircleController(startGraphics());
		Circle circle = new Circle(p, 5);

		ctrlCircle.drawCircleDDAInteiro(circle, color);
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
		if(drawFlag == true) {
			pStart = e.getPoint();
			drawCircleOnPoint(pStart, Color.RED);
		}
		else {
			referencia = e.getPoint();
			drawCircleOnPoint(referencia, Color.RED);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(drawFlag == true) {
			pEnd = e.getPoint();
			drawCircleOnPoint(pEnd, Color.RED);
	
			// Clear circles on points
			drawCircleOnPoint(pStart, Color.lightGray);
			drawCircleOnPoint(pEnd, Color.lightGray);
						
			// Calls Draw Rectangle
			LineController ctrlReta = new LineController(startGraphics());
			ctrlReta.drawRectangle(pStart, pEnd, Color.BLACK);
			
			
			drawFlag = false;
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
