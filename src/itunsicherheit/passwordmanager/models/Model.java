package itunsicherheit.passwordmanager.models;

import java.sql.SQLException;

import itunsicherheit.passwordmanager.db.SqliteDB;

public abstract class Model {

	protected SqliteDB sqlite;
	
	public Model(SqliteDB sqlite) {
		this.sqlite = sqlite;
	}
	
	protected void createGenericTable(String sql) throws SQLException {		
		sqlite.createTable(sql);
	}
}
