package itunsicherheit.passwordmanager.gui.core;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class PasswordManagerGUI extends JFrame {
	
	protected JPanel panel;
	
	public PasswordManagerGUI(String title, Dimension preferredSize) {
		init(title, preferredSize);
	}
	
	public PasswordManagerGUI(String title, Dimension preferredSize, int preferredCloseOperation) {
		setDefaultCloseOperation(preferredCloseOperation);
		init(title, preferredSize);
	}
	
	protected abstract void initializeComponents();

	private void init(String title, Dimension preferredSize) {
		panel = new JPanel();
		
		initializeComponents();
		
		setContentPane(panel);
		setTitle(title);
		setPreferredSize(preferredSize);
		pack();
	}
}
