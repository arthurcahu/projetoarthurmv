package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import static javax.swing.JOptionPane.showMessageDialog;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans participantes = new JavaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			participantes(request, response);

		} else if (action.equals("/insert")) {
			novoParticipantes(request, response);

		} else if (action.equals("/select")) {
			listarParticipantes(request, response);

		} else if (action.equals("/update")) {
			editarParticipantes(request, response);

		} else if (action.equals("/delete")) {
			removerParticipantes(request, response);

		} else{
			response.sendRedirect("index.html");

		}

	}

	// Lista de participantes
	protected void participantes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarParticipantes();
		// Encaminhar a lista ao documento cafemv.jsp
		request.setAttribute("participantes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("cafemv.jsp");
		rd.forward(request, response);

	}

	// Novo participante
	protected void novoParticipantes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Boolean erro = false;
		String read2 = "select count(*) from Listadeparticipantes where cpf = ?";
		try {
			Connection con = dao.conectar ();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, request.getParameter("cpf"));
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			if (rs.getInt(1) > 0){
				throw new Exception("CPF já cadastrado.");
			}
			con.close();
			

		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null,e,"Janela", JOptionPane.ERROR_MESSAGE);
		}
		
		
		// setar as variáveis JavaBeans
		participantes.setNome(request.getParameter("nome"));
		participantes.setCPF(request.getParameter("cpf"));
		participantes.setContribuicao(request.getParameter("contribuicao"));
		// invocar o método inserirParticipante passando o objeto participantes
		if (erro) {
			dao.inserirParticipante(participantes);
		}
		// redirecionar para o documento cafemv.jsp
		response.sendRedirect("main");

	}

// Editar Participante
	protected void listarParticipantes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato que será editado
		String idcon = request.getParameter("idcon");
		
		// Setar variável JavaBeans
		participantes.setIdcon(idcon);
		
		// Executar o método selecionarParticipante (DAO)
		dao.selecionarParticipante(participantes);
		
		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idcon", participantes.getIdcon());
		request.setAttribute("nome", participantes.getNome());
		request.setAttribute("cpf", participantes.getCPF());
		request.setAttribute("contribuicao", participantes.getContribuicao());

		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}
	
	protected void editarParticipantes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	/*	String read2 = "select count(*) from Listadeparticipantes where cpf = ? and idcon <> ?";
		try {
			Connection con = dao.conectar ();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, request.getParameter("cpf"));
			pst.setString(2, request.getParameter("idcon"));
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			if (rs.getInt(1) > 0){
				throw new Exception("CPF já cadastrado.");
			}
			con.close();
		
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null,e,"Janela", JOptionPane.INFORMATION_MESSAGE);
		}
		
		*/
			// Setar as variáveis JavaBeans
			participantes.setIdcon(request.getParameter("idcon"));
			participantes.setNome(request.getParameter("nome"));
			participantes.setCPF(request.getParameter("cpf"));
			participantes.setContribuicao(request.getParameter("contribuicao"));
			// Executar o método alterarParticipante
			dao.alterarParticipante(participantes);
			// Redirecionar para o documento cafemv.jsp (atualizando as alterações)
			response.sendRedirect("main");
			
	}

	// Remover um participante
	protected void removerParticipantes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do participante a ser excluido (validador.js)
		String idcon = request.getParameter("idcon");
		// setar a variável idcon JavaBeans
		participantes.setIdcon(idcon);
		// executar o método deletarParticipante (DAO) passando o objeto participante
		dao.deletarParticipante(participantes);
		// Redirecionar para o documento cafemv.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}
}
