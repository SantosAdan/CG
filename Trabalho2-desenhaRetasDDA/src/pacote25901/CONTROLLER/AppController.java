package pacote25901.CONTROLLER;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pacote25901.VIEW.StartPanel;

public class AppController implements ActionListener 
{
	private StartPanel startPanel;
	
	// CONSTRUCTOR
	public AppController()
	{
		startPanel = new StartPanel(this);
		startPanel.showPanel();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
