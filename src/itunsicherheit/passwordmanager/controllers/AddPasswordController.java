package itunsicherheit.passwordmanager.controllers;

import java.sql.SQLException;

import itunsicherheit.passwordmanager.db.SqliteDB;
import itunsicherheit.passwordmanager.models.CredentialModel;

public class AddPasswordController {

	String password;
	SqliteDB sqlite;
	
	public AddPasswordController(String password, SqliteDB sqlite) {
		this.password = password;
		this.sqlite = sqlite;
	}
	
	public void savePassword(String description, String username, String password) throws SQLException {
		CredentialModel credential = new CredentialModel(sqlite);
		credential.saveCredential(this.password, description, username, password);
	}
}
