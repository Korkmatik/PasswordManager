package itunsicherheit.passwordmanager.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import itunsicherheit.passwordmanager.controllers.AddPasswordController;
import itunsicherheit.passwordmanager.db.SqliteDB;
import itunsicherheit.passwordmanager.gui.core.PasswordManagerGUI;

public class AddPasswordGUI extends PasswordManagerGUI {

	private JTextField txtDesription;
	private JTextField txtUsername;
	private JPasswordField pwd;
	private JButton save;
	
	String password;
	SqliteDB sqlite;
	
	public AddPasswordGUI(String password, SqliteDB sqlite) {
		super("Password Manager: Add Password", new Dimension(400, 200), JFrame.DISPOSE_ON_CLOSE);
		
		this.password = password;
		this.sqlite = sqlite;
	}

	@Override
	protected void initializeComponents() {
		createComponents();
		initPanel();
	}
	
	private void initPanel() {
		panel.setLayout(new GridLayout(4, 0));
		
		panel.add(new JLabel("Description:"));
		panel.add(txtDesription);
		panel.add(new JLabel("Username:"));
		panel.add(txtUsername);
		panel.add(new JLabel("Password:"));
		panel.add(pwd);
		panel.add(save);
	}

	private void createComponents() {
		txtDesription = new JTextField();
		txtUsername = new JTextField();
		pwd = new JPasswordField();
		
		save = new JButton("Add Password");
		save.addActionListener(e -> {
			AddPasswordController controller = new AddPasswordController(password, sqlite);
			
			String description = txtDesription.getText();
			String username = txtUsername.getText();
			String password = pwd.getText();
			
			try {
				controller.savePassword(description, username, password);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Could not add credential: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
	}	
}
