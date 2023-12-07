package org.libertas.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private Connection connection;
	
	public Conexao() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

	    	String url = "jdbc:mysql://localhost:3306/medicos";
            String user = "root";
            String pwd = "1234";
	        connection = DriverManager.getConnection(url, user, pwd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void desconectar() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
