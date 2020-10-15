package br.unip.chacara.si.tcc.scrm.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unip.chacara.si.tcc.scrm.dao.*;
import br.unip.chacara.si.tcc.scrm.model.*;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 * 
 * @author Anderval
 */

@WebServlet("/")
public class ReceitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReceitaDAO receitaDAO;

	public void init() {
		receitaDAO = new ReceitaDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertReceita(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateReceita(request, response);
				break;
			case "/list-farm":
				listReceitaFarm(request, response);
				break;
			case "/loginMed":
				loginUsuarioMed(request, response);;
				break;
			case "/loginFarm":
				loginUsuarioFarm(request, response);;
				break;
			case "/list":
				listReceita(request, response);;
				break;
			default:
				listReceita(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listReceita(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int crm = Integer.parseInt(request.getParameter("crm"));
		List<Receita> listReceita = receitaDAO.selectAllReceitasMed(crm);
		request.setAttribute("listReceita", listReceita);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/receita-list-med.jsp");
		dispatcher.forward(request, response);
	}

	private void listReceitaFarm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String cpf = request.getParameter("cpf");
		List<Receita> listReceita = receitaDAO.selectAllReceitasFarm(cpf);
		request.setAttribute("listReceita", listReceita);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/receita-list-farm.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/receita-form-med.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Receita existingReceita = receitaDAO.selectReceita(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/receita-update-farm.jsp");
		request.setAttribute("receita", existingReceita);
		dispatcher.forward(request, response);

	}

	private void insertReceita(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nomePaciente = request.getParameter("nome");
		String itensReceita = request.getParameter("itens");
		String cpfPaciente = request.getParameter("cpf");
		int crm = Integer.parseInt(request.getParameter("crm"));
		Receita newReceita = new Receita(nomePaciente, cpfPaciente, itensReceita, true, crm);
		receitaDAO.insertReceita(newReceita);
		response.sendRedirect("sucess-med.jsp");
	}


	private void updateReceita(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String nomePaciente = request.getParameter("nome");
		String cpfPaciente = request.getParameter("cpf");
		int crm = Integer.parseInt(request.getParameter("crm"));
		String itensReceita = request.getParameter("itens");
		int crf = Integer.parseInt(request.getParameter("crf"));

		Receita receitaSelect = receitaDAO.selectReceita(id);

		if (receitaSelect.validar(cpfPaciente, crm, itensReceita) == "True") {

			Receita re = new Receita(id, nomePaciente, cpfPaciente, itensReceita, false, crm, crf);
			receitaDAO.updateReceita(re);
			response.sendRedirect("sucess-farm.jsp");
		} else {
			response.sendRedirect("error-farm.jsp");
		}

	}

	private void loginUsuarioMed(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int username = Integer.parseInt(request.getParameter("username"));
		String password = request.getParameter("password");

		UsuarioDAO usuarioDao = new UsuarioDAO();

		Usuario usuario = usuarioDao.selectLoginMed(username, password);
		String destPage = "login-med.jsp";

		if (usuario != null) {
			HttpSession session = request.getSession();
			session.setAttribute("crm", usuario.getIdentificador());
			session.setAttribute("nome", usuario.getNome());
			session.setAttribute("especialidade", usuario.getEspecialidade());
			session.setAttribute("senha", usuario.getSenha());
			destPage = "receita-form-med.jsp";
		} else {
			String message = "Invalid email/password";
			request.setAttribute("message", message);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}
	
	private void loginUsuarioFarm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int username = Integer.parseInt(request.getParameter("username"));
		String password = request.getParameter("password");

		UsuarioDAO usuarioDao = new UsuarioDAO();

		Usuario usuario = usuarioDao.selectLoginFarm(username, password);
		String destPage = "login-farm.jsp";

		if (usuario != null) {
			HttpSession session = request.getSession();
			session.setAttribute("crf", usuario.getIdentificador());
			session.setAttribute("nome", usuario.getNome());
			session.setAttribute("especialidade", usuario.getEspecialidade());
			session.setAttribute("senha", usuario.getSenha());
			destPage = "pesquisa-receita-farm.jsp";
		} else {
			String message = "Invalid email/password";
			request.setAttribute("message", message);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}
}
