package br.unip.chacara.si.tcc.scrm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import br.unip.chacara.si.tcc.scrm.model.*;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Anderval
 * @author Felipe
 *
 */
public class ReceitaDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/TCC?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "I&H31v@L";

	private static final String INSERT_RECEITAS_SQL = "INSERT INTO receituario (cpf_paciente, nome_paciente, data_emissao, itens_receita, status_receita, hash, medico_crm) VALUES (?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_RECEITA_CRM = "select * from receituario where medico_crm = ?";
	private static final String SELECT_RECEITA_CPF = "select * from receituario where cpf_paciente = ? and status_receita = 1";
	private static final String SELECT_RECEITA_ID = "select * from receituario where id_receituario = ?";
	private static final String UPDATE_RECEITAS_SQL = "UPDATE receituario SET status_receita = ?, farmaceutico_crf = ? WHERE id_receituario = ?;";

	public ReceitaDAO() {
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

	public void insertReceita(Receita receita) throws Exception {
		System.out.println(INSERT_RECEITAS_SQL);
		
		LocalDateTime localDateTime = LocalDateTime.now();
		java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECEITAS_SQL)) {
			preparedStatement.setString(1, receita.getCpfPaciente());
			preparedStatement.setString(2, receita.getNomePaciente());
			preparedStatement.setDate(3, date);
			preparedStatement.setString(4, receita.getItensReceita());
			preparedStatement.setBoolean(5, receita.isStatusReceita());
			preparedStatement.setString(6, receita.assinar());
			preparedStatement.setInt(7, receita.getCrm());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Receita selectReceita(int id) {
		Receita receita = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECEITA_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String nomePaciente = rs.getString("nome_paciente");
				String cpfPaciente = rs.getString("cpf_paciente");
				String itensReceita = rs.getString("itens_receita");
				Boolean statusReceita = rs.getBoolean("status_receita");
				int crm = rs.getInt("medico_crm");
				String hashReceita = rs.getString("hash");

				receita = new Receita(id, nomePaciente, cpfPaciente, itensReceita, statusReceita, crm, hashReceita);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return receita;
	}

	public List<Receita> selectAllReceitasMed(int crm) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Receita> receitas = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECEITA_CRM);) {
			preparedStatement.setInt(1, crm);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_receituario");
				String nomePaciente = rs.getString("nome_paciente");
				String cpfPaciente = rs.getString("cpf_paciente");
				String itensReceita = rs.getString("itens_receita");
				Boolean statusReceita = rs.getBoolean("status_receita");
				int crm1 = rs.getInt("medico_crm");
				String hashReceita = rs.getString("hash");
				receitas.add(new Receita(id, nomePaciente, cpfPaciente, itensReceita, statusReceita, crm1, hashReceita));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return receitas;
	}

	public List<Receita> selectAllReceitasFarm(String cpf) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Receita> receitas = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECEITA_CPF);) {
			preparedStatement.setString(1, cpf);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id_receituario");
				String nomePaciente = rs.getString("nome_paciente");
				String cpfPaciente = rs.getString("cpf_paciente");
				String itensReceita = rs.getString("itens_receita");
				Boolean statusReceita = rs.getBoolean("status_receita");
				int crm1 = rs.getInt("medico_crm");
				String hashReceita = rs.getString("hash");
				receitas.add(new Receita(id, nomePaciente, cpfPaciente, itensReceita, statusReceita, crm1, hashReceita));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return receitas;
	}

	public boolean updateReceita(Receita receita) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_RECEITAS_SQL);) {
			statement.setBoolean(1, receita.isStatusReceita());
			statement.setInt(2, receita.getCrf());
			statement.setInt(3, receita.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
