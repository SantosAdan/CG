package pacote25901.VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pacote25901.CONTROLLER.AppController;
import pacote25901.CONTROLLER.ArcController;
import pacote25901.CONTROLLER.CircleController;
import pacote25901.CONTROLLER.EllipseController;
import pacote25901.CONTROLLER.RetaController;
import pacote25901.MODEL.Arc;
import pacote25901.MODEL.Circle;
import pacote25901.MODEL.Ellipse;
import pacote25901.MODEL.Line;

public class StartPanel implements MouseListener, MouseMotionListener {
	private JFrame baseFrame;
	private JPanel basePanel, outputPanel;

	private JButton btnEnd, btnClear, btnCircle;
	private Graphics desenho;

	private Point pStart, pEnd;

	// CONSTRUCTOR
	public StartPanel(AppController appCtrl) {
		JPanel buttonPanel;
		JPanel titlePanel;

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

		// PANEL TITLE
		JLabel titulo, subtitulo;
		titulo = new JLabel("TRABALHO 3 - Algoritmos de Círculos e Elipses - 25901");
		titulo.setForeground(Color.black);
		titulo.setFont(new Font("Dialog", Font.BOLD, 20));
		titlePanel.add(titulo);

		subtitulo = new JLabel("(Arcos, Retas, Círculos e Elipses)");
		subtitulo.setForeground(Color.black);
		subtitulo.setFont(new Font("Dialog", Font.BOLD, 12));
		titlePanel.add(subtitulo);
		
		// BOTÃO PARA LIMPAR A TELA
		btnCircle = addAButton("Desenhar Círculo", "btnCircle", buttonPanel);
		btnCircle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RetaController ctrlReta = new RetaController(startGraphics());
				
				Point center = ctrlReta.getCenterOfRectangle(pStart, pEnd);
				// IT RETURNS THE DIAMETER, SO WE HAVE TO DIVIDE FOR 2
				int radius = ctrlReta.getDiagonal(pStart, pEnd)/2;
				Circle circle = new Circle(center, radius);
				
				CircleController ctrlCircle = new CircleController(startGraphics());
				ctrlCircle.drawCircleDDAInteiro(circle, Color.BLUE);
			}

		});

		// BOTÃO PARA LIMPAR A TELA
		btnClear = addAButton("Limpar", "btnEnd", buttonPanel);
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearOutputPanel(outputPanel);
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
		basePanel.add(outputPanel, BorderLayout.CENTER);
		basePanel.add(buttonPanel, BorderLayout.PAGE_END);

		baseFrame.setVisible(true);
		
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
	}

	// METODO PARA INICIAR PAINEL DE DESENHO
	public Graphics startGraphics() {

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
		pStart = e.getPoint();
		
		drawCircleOnPoint(pStart, Color.RED);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pEnd = e.getPoint();
		drawCircleOnPoint(pEnd, Color.RED);
		
		// Clear circle on start and end points
		drawCircleOnPoint(pStart, Color.lightGray);
		drawCircleOnPoint(pEnd, Color.lightGray);
		
		// Calls Draw Rectangle
		RetaController ctrlReta = new RetaController(startGraphics());
		ctrlReta.drawRectangle(pStart, pEnd, Color.BLACK);
		
		Line r1 = ctrlReta.getLines(1);
		Line r2 = ctrlReta.getLines(2);
		int factor = ctrlReta.getRoundCornerFactor(pStart, pEnd);
		
		// Calls Draw Corners
		ArcController ctrlArc = new ArcController(startGraphics());
		Arc arc1 = new Arc(new Point((int)r1.getpEnd().getX(), (int) r1.getpEnd().getY()+factor), factor);
		ctrlArc.drawArcQuarter(arc1, Color.BLACK, 1);
		
		Arc arc2 = new Arc(new Point((int)r1.getpStart().getX(), (int) r1.getpStart().getY()+factor), factor);
		ctrlArc.drawArcQuarter(arc2, Color.BLACK, 2);
		
		Arc arc3 = new Arc(new Point((int)r2.getpStart().getX(), (int) r2.getpStart().getY()-factor), factor);
		ctrlArc.drawArcQuarter(arc3, Color.BLACK, 3);
		
		Arc arc4 = new Arc(new Point((int)r2.getpEnd().getX(), (int) r2.getpEnd().getY()-factor), factor);
		ctrlArc.drawArcQuarter(arc4, Color.BLACK, 4);
		
		// Get values to mount ellipse
		Point center = ctrlReta.getCenterOfRectangle(pStart, pEnd);
		int x = (int) (ctrlReta.getXAxis(pStart, pEnd)/Math.sqrt(2));
		int y = (int) (ctrlReta.getYAxis(pStart, pEnd)/Math.sqrt(2));
		
		// Calls Draw Ellipse 
		EllipseController ctrlEllipse = new EllipseController(startGraphics());
		Ellipse ellipse = new Ellipse(center, x, y);
		ctrlEllipse.drawEllipseDDAInteiro(ellipse, Color.RED);
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
