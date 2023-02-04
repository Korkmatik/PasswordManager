package itunsicherheit.passwordmanager.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import itunsicherheit.passwordmanager.Password;
import itunsicherheit.passwordmanager.controllers.OpenDBController;
import itunsicherheit.passwordmanager.gui.core.PasswordManagerGUI;

public class UnlockGUI extends PasswordManagerGUI {
	
	private JButton btnOpenDB;
	private JButton btnConfirm;
	private JButton btnNew;
	private JLabel lblPwd;
	private JPasswordField pwd;
	
	private String path;
	
	public UnlockGUI() {
		super("Password Manager", new Dimension(400, 200));
	}
	

	@Override
	protected void initializeComponents() {
		createButtons();
		createPasswordInput();
		initPanel();		
	}

	private void initPanel() {
		panel.setLayout(new GridBagLayout());	
		
		placeElement(lblPwd, 1, 0, 2, null);
		placeElement(pwd, 1, 1, 2, null);
		placeElement(btnOpenDB, 1, 2, 2, new Insets(10, 0, 0, 0));
		placeElement(btnConfirm, 1, 3, 1, new Insets(20, 0, 0, 0));
		placeElement(btnNew, 2, 3, 1, new Insets(20, 0, 0, 0));
	}

	private void createPasswordInput() {
		lblPwd = new JLabel("Password:");
		lblPwd.setHorizontalAlignment(JLabel.CENTER);
		pwd = new JPasswordField(20);
	}

	private void createButtons() {
		createOpenDBButton();
		createConfirmButton();
		createNewButton();
	}


	private void createNewButton() {
		btnNew = new JButton("New Database");
		btnNew.addActionListener(e -> {
			NewDatabaseGUI newDB = new NewDatabaseGUI();
			newDB.setVisible(true);
		});
	}


	private void createConfirmButton() {
		btnConfirm = new JButton("Open");
		btnConfirm.addActionListener(e -> {
			OpenDBController controller = new OpenDBController(path);
			
			String password = pwd.getText();
			System.out.println("Password in GUI: " + password);
			
			try {
				if (controller.checkPassword(password)) {
					PasswordsGUI passwords = new PasswordsGUI(password, controller.getSqlite());
					passwords.setVisible(true);
					dispose();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
	}


	private void createOpenDBButton() {
		btnOpenDB = new JButton("Select Database");
		btnOpenDB.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			
			chooser.setCurrentDirectory(new File("."));
			
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				path = chooser.getSelectedFile().getAbsolutePath();
				System.out.println(path);
			}
		});
	}

	private void placeElement(JComponent component, int gridx, int gridy, int gridwidth, Insets insets) {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		
		if (insets != null) {
			gbc.insets = insets;
		}
		
		panel.add(component, gbc);
	}
}
