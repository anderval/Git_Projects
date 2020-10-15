package br.unip.chacara.si.tcc.scrm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import br.unip.chacara.si.tcc.scrm.model.*;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Anderval
 * @author Felipe
 *
 */
public class UsuarioDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/TCC?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "I&H31v@L";

	private static final String SELECT_LOGIN_MED = "select * from medico where crm = ? and senha = ?";
	private static final String SELECT_LOGIN_FARM = "select * from farmaceutico where crf = ? and senha = ?";

	public UsuarioDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public Usuario selectLoginMed(int username, String password) {
		Usuario usuario = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN_MED);) {
			preparedStatement.setInt(1, username);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int crmMedico = rs.getInt("crm");
				String nomeMedico = rs.getString("nome_medico");			
				String especialidade = rs.getString("especialidade");
				String senhaMedico = rs.getString("senha");
				usuario = new Usuario(crmMedico, nomeMedico, especialidade, senhaMedico);
			}
		} catch (SQLException e) {
			
		}
		return usuario;
	}


	public Usuario selectLoginFarm(int username, String password) {
		Usuario usuario = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN_FARM);) {
			preparedStatement.setInt(1, username);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nomeFarmaceutico = rs.getString("nome_farmaceutico");
				int crfFarmaceutico = rs.getInt("crf");
				String passFarmaceutico = rs.getString("senha");
				usuario = new Usuario(crfFarmaceutico, passFarmaceutico, nomeFarmaceutico);
			}
		} catch (SQLException e) {
			
		}
		return usuario;
	}
}
