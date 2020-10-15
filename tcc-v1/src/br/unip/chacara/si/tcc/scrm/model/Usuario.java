package br.unip.chacara.si.tcc.scrm.model;


/**
 * User.java This is a model class represents a User entity
 * 
 * @author Anderval
 * @author Felipe
 *
 */
public class Usuario {
	protected int identificador;
	protected String senha;
	protected String nome;
	protected String especialidade;
	
	
	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public Usuario() {
	}

	public Usuario(int identificador, String senha, String nome) {
		super();
		this.identificador = identificador;
		this.senha = senha;
		this.nome = nome;
	}
	
	public Usuario(int identificador, String nome, String especialidade, String senha) {
		super();
		this.identificador = identificador;
		this.nome = nome;
		this.especialidade = especialidade;
		this.senha = senha;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	


}
