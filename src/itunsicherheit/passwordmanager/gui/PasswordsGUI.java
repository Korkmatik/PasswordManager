package itunsicherheit.passwordmanager.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import itunsicherheit.passwordmanager.controllers.ShowPasswordController;
import itunsicherheit.passwordmanager.db.SqliteDB;
import itunsicherheit.passwordmanager.gui.core.PasswordManagerGUI;

public class PasswordsGUI extends PasswordManagerGUI {
	
	String[][] data = {};
	String[] columnNames = { "Description", "Username", "Password" };
	
	private JButton btnAddPassword;
	private JTable table;
	private JScrollPane sp;
	
	String password;
	SqliteDB sqlite;
	
	public PasswordsGUI(String password, SqliteDB sqlite) {
		super("Password Manager: Passwords", new Dimension(400, 200), JFrame.EXIT_ON_CLOSE);
		
		this.password = password;
		this.sqlite = sqlite;
		
		ShowPasswordController controller = new ShowPasswordController(password, sqlite);
		try {
			data = controller.loadPasswords();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		createTable();
		initPanel();
	}

	private void initPanel() {
		panel.setLayout(new BorderLayout());
		
		panel.add(btnAddPassword, BorderLayout.PAGE_START);
		panel.add(sp, BorderLayout.CENTER);
	}

	private void createTable() {
		table = new JTable(data, columnNames);
		sp = new JScrollPane(table);
	}

	private void createAddPasswordButton() {
		btnAddPassword = new JButton("Add Password");
		btnAddPassword.addActionListener(e -> {
			AddPasswordGUI password = new AddPasswordGUI(this.password, this.sqlite);
			password.setVisible(true);
		});
	}

	@Override
	protected void initializeComponents() {
		createAddPasswordButton();
	}

}
