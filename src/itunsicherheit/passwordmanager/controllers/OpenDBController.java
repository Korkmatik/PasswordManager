package itunsicherheit.passwordmanager.controllers;

import java.sql.SQLException;

import itunsicherheit.passwordmanager.Password;
import itunsicherheit.passwordmanager.db.SqliteDB;
import itunsicherheit.passwordmanager.models.MetadataModel;

public class OpenDBController {
	
	SqliteDB sqlite;
	
	public OpenDBController(String path) {
		this.sqlite = new SqliteDB(path);
	}

	public boolean checkPassword(String password) throws SQLException {
		System.out.println("Password in Controller: " + password);
		
		MetadataModel metadata = new MetadataModel(sqlite);
		
		String salt = metadata.getSalt();
		System.out.println("salt: " +  salt);
		String pwdHash = metadata.getHash();
		System.out.println("Hash: " + pwdHash);
		
		Password pwd = new Password(password, salt);
		return pwd.checkHash(pwdHash);
	}
	
	public SqliteDB getSqlite() {
		return this.sqlite;
	}
}
