package pacote25901.CONTROLLER;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import pacote25901.MODEL.Line;
import pacote25901.VIEW.StartPanel;

public class AppController implements ActionListener 
{
	private StartPanel startPanel;
	private Graphics desenho;
	private RetaController retaCtrl;
	private ArrayList<Line> retasList;
	
	// CONSTRUCTOR
	public AppController()
	{
		startPanel = new StartPanel(this);
		startPanel.showPanel();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command;
		
		command = e.getActionCommand();
		
		//
		switch(command) {
			case "btnEnd":
				System.exit(0);
				break;
			
			case "btnStart":
				retasList = new ArrayList<Line>();
				desenho = startPanel.startGraphics();
				retaCtrl = new RetaController(desenho);
				//readFromFile();
				//showDraw();	
		}
	}
	
	// LER ARQUIVO DE RETAS
	private String readFromFile()
	{
		String         registro, registro_lido[], nomeArquivoLido,
		               registro_aux, mensagem;
		FileReader     fr;
		BufferedReader br;
		JFileChooser   escolheArquivos;
		File           nomeArquivo, diretorio;
		int            i, xa, ya, xb, yb, qL, resultado, linhaLida[];
		Line           reta;
		Point          pa, pb;

		// ABRINDO ARQUIVO DE DADOS
		escolheArquivos = new JFileChooser();
		diretorio = new File ( "..\\" );
		escolheArquivos.setCurrentDirectory(diretorio);
		escolheArquivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		resultado = escolheArquivos.showOpenDialog(escolheArquivos);
		nomeArquivoLido = null;

		if ( resultado != JFileChooser.CANCEL_OPTION ) {

			nomeArquivo = escolheArquivos.getSelectedFile();

			try {	

				fr        = new FileReader ( nomeArquivo );
				br        = new BufferedReader ( fr );
				linhaLida = new int[4];

				registro  = br.readLine();

				while ( registro != null ) {

					registro_lido = registro.split(" ");
					qL = 0;

					for ( i = 0; i < registro_lido.length; i++ ) {
						registro_aux = registro_lido[i].trim();

						if( registro_aux != null && registro_aux.length() > 0 ) {
							linhaLida[qL] = Integer.parseInt( registro_aux );
							qL++;
						}
					}

					// CRIAR RETA
					xa   = linhaLida[0];
					ya   = linhaLida[1];
					pa   = new Point ( xa, ya );
					xb   = linhaLida[2];
					yb   = linhaLida[3];
					pb   = new Point ( xb, yb );
					reta = new Line ( pa, pb );

					retasList.add(reta);

					// NOVO REGISTRO
					registro = br.readLine();
				}

				br.close();

				nomeArquivoLido = nomeArquivo.toString();

			} catch ( FileNotFoundException e ) {
				mensagem = "File " + nomeArquivo + " does not exist";
				JOptionPane.showMessageDialog( null, mensagem, "", 
                    JOptionPane.INFORMATION_MESSAGE );
			} catch ( IOException e ) {
				mensagem = "Error at file: " + nomeArquivo;
				JOptionPane.showMessageDialog( null, mensagem, "", 
                  JOptionPane.INFORMATION_MESSAGE );
			}
		}

		return nomeArquivoLido;
	}

}
