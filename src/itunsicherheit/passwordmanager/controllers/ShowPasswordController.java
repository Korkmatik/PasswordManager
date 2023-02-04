package itunsicherheit.passwordmanager.controllers;

import java.sql.SQLException;

import itunsicherheit.passwordmanager.db.SqliteDB;
import itunsicherheit.passwordmanager.models.CredentialModel;

public class ShowPasswordController {

	String password;
	SqliteDB sqlite;
	
	public ShowPasswordController(String password, SqliteDB sqlite) {
		this.password = password;
		this.sqlite = sqlite;
	}
	
	public String[][] loadPasswords() throws SQLException {
		CredentialModel credentialModel = new CredentialModel(sqlite);
		
		var data = credentialModel.loadCredentials(password);
		
		String[][] returnData = new String[data.size()][3];
		for (int i = 0; i < data.size(); i++) {
			returnData[i] = data.get(i);
		}
		
		return returnData;
	}
}
