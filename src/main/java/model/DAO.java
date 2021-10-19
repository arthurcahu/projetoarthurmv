package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class DAO {
	/** Módulo de conexão **/
	// parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/dbcafemv?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "123456";

	// método de conexão
	public Connection conectar() {
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}

	}

	/** CRUD CREATE **/
	public void inserirParticipante(JavaBeans participantes) {
		String create = "insert into Listadeparticipantes (nome, cpf, contribuicao) values (?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para a excução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros (?) pelo conteúdo das variáveis JavaBeans
			pst.setString(1, participantes.getNome());
			pst.setString(2, participantes.getCPF());
			pst.setString(3, participantes.getContribuicao());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexão com o banco
			pst.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READ **/
	public ArrayList<JavaBeans> listarParticipantes() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> participantes = new ArrayList<>();
		String read = "select * from Listadeparticipantes order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado enquanto houver participantes
			while (rs.next()) {
				// Variáveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String cpf = rs.getString(3);
				String contribuicao = rs.getString(4);
				// populando o ArrayList
				participantes.add(new JavaBeans(idcon, nome, cpf, contribuicao));
			}
			con.close();
			return participantes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	
	/** CRUD UPDATE **/
	//Selecionar o participante
	public void selecionarParticipante(JavaBeans participante) {
		String read2 = "select * from Listadeparticipantes where idcon = ?";
		try {
			Connection con = conectar ();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, participante.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Setar as variáveis JavaBeans
				participante.setIdcon(rs.getString(1));
				participante.setNome(rs.getString(2));
				participante.setCPF(rs.getString(3));
				participante.setContribuicao(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	//editar o participante
	public void alterarParticipante(JavaBeans participante) {
		String create = "update Listadeparticipantes set nome=?, cpf=?, contribuicao=? where idcon=?";
		try {
			Connection con = conectar ();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, participante.getNome());
			pst.setString(2, participante.getCPF());
			pst.setString(3, participante.getContribuicao());
			pst.setString(4, participante.getIdcon());
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD DELETE **/
	public void deletarParticipante(JavaBeans participante) {
		String delete = "delete from Listadeparticipantes where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, participante.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
