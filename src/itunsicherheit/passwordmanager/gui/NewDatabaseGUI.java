package itunsicherheit.passwordmanager.gui;

import java.awt.Dimension;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import itunsicherheit.passwordmanager.controllers.CreateDatabaseController;
import itunsicherheit.passwordmanager.gui.core.PasswordManagerGUI;

public class NewDatabaseGUI extends PasswordManagerGUI {

	private JButton btnDBPath;
	private JTextField txtDBName;
	private JPasswordField pwd;
	private JButton btnConfirm;
	
	private String path;
	
	public NewDatabaseGUI() {
		super("Password Manager: Add Database", new Dimension(400, 150), JFrame.DISPOSE_ON_CLOSE);
	}
	
	@Override
	protected void initializeComponents() {
		createDbPathButton();
		createDbNameInput();
		createPasswordInput();
		createConfirmButton();
		initPanel();
	}

	private void initPanel() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(btnDBPath);
		panel.add(txtDBName);
		panel.add(pwd);
		panel.add(btnConfirm);
	}

	private void createConfirmButton() {
		btnConfirm = new JButton("Save");
		btnConfirm.setAlignmentX(CENTER_ALIGNMENT);
		
		btnConfirm.addActionListener(e -> {
			String password = new String(pwd.getPassword());
			String dbName = txtDBName.getText();
			
			String completePath = Paths.get(path, dbName).toString();
			
			System.out.println(completePath);
			System.out.println(password);
			
			CreateDatabaseController controller = new CreateDatabaseController();
			try {
				controller.createDatabase(completePath, password);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this,
					    "Error while creating the database: " + e1.getMessage(),
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			dispose();
		});
	}

	private void createPasswordInput() {
		pwd = new JPasswordField(20);
		pwd.setMaximumSize(new Dimension(600, 20));
		pwd.setAlignmentX(CENTER_ALIGNMENT);
	}

	private void createDbNameInput() {
		txtDBName = new JTextField(20);	
		txtDBName.setMaximumSize(new Dimension(600, 20));
		txtDBName.setAlignmentX(CENTER_ALIGNMENT);
	}

	private void createDbPathButton() {
		btnDBPath = new JButton("Select Database Location");
		btnDBPath.setAlignmentX(CENTER_ALIGNMENT);
		
		btnDBPath.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			
			chooser.setCurrentDirectory(new File("."));
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			if (chooser.showDialog(this, "Select Save Directory") == JFileChooser.APPROVE_OPTION) {
				path = chooser.getSelectedFile().getAbsolutePath();
			}			
		});
	}
	
}
