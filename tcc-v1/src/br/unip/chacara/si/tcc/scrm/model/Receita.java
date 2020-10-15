package br.unip.chacara.si.tcc.scrm.model;

import java.security.MessageDigest;

/**
 * User.java This is a model class represents a User entity
 * 
 * @author Anderval
 * @author Felipe
 *
 */
public class Receita {
	protected int id;
	protected String nomePaciente;
	protected String cpfPaciente;
	protected String itensReceita;
	protected String hashReceita;
	protected boolean statusReceita;
	protected int crm;
	protected int idHospital;
	protected int crf;

	public Receita() {
	}

	public Receita(String nomePaciente, String cpfPaciente, String itensReceita, boolean statusReceita, int crm) {
		super();
		this.nomePaciente = nomePaciente;
		this.cpfPaciente = cpfPaciente;
		this.itensReceita = itensReceita;
		this.statusReceita = statusReceita;
		this.crm = crm;
		this.idHospital = idHospital;
	}

	public Receita(int id, String nomePaciente, String cpfPaciente, String itensReceita, boolean statusReceita,
			int crm) {
		super();
		this.id = id;
		this.nomePaciente = nomePaciente;
		this.cpfPaciente = cpfPaciente;
		this.itensReceita = itensReceita;
		this.statusReceita = statusReceita;
		this.crm = crm;
		this.idHospital = idHospital;
	}

	public Receita(int id, String nomePaciente, String cpfPaciente, String itensReceita, boolean statusReceita, int crm,
			int crf) {
		super();
		this.id = id;
		this.nomePaciente = nomePaciente;
		this.cpfPaciente = cpfPaciente;
		this.itensReceita = itensReceita;
		this.statusReceita = statusReceita;
		this.crm = crm;
		this.crf = crf;
	}

	public Receita(String nomePaciente, String cpfPaciente, String itensReceita, boolean statusReceita, int crm,
			String hashReceita) {
		super();
		this.hashReceita = hashReceita;
		this.nomePaciente = nomePaciente;
		this.cpfPaciente = cpfPaciente;
		this.itensReceita = itensReceita;
		this.statusReceita = statusReceita;
		this.crm = crm;
		this.idHospital = idHospital;
	}

	public Receita(int id, String nomePaciente, String cpfPaciente, String itensReceita, boolean statusReceita, int crm,
			String hashReceita) {
		super();
		this.id = id;
		this.hashReceita = hashReceita;
		this.nomePaciente = nomePaciente;
		this.cpfPaciente = cpfPaciente;
		this.itensReceita = itensReceita;
		this.statusReceita = statusReceita;
		this.crm = crm;
		this.idHospital = idHospital;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getCpfPaciente() {
		return cpfPaciente;
	}

	public void setCpfPaciente(String cpfPaciente) {
		this.cpfPaciente = cpfPaciente;
	}

	public String getItensReceita() {
		return itensReceita;
	}

	public void setItensReceita(String itensReceita) {
		this.itensReceita = itensReceita;
	}

	public String getHashReceita() {
		return hashReceita;
	}

	public void setHashReceita(String hashReceita) {
		this.hashReceita = hashReceita;
	}

	public boolean isStatusReceita() {
		return statusReceita;
	}

	public void setStatusReceita(boolean statusReceita) {
		this.statusReceita = statusReceita;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public int getIdHospital() {
		return idHospital;
	}

	public void setIdHospital(int idHospital) {
		this.idHospital = idHospital;
	}

	public int getCrf() {
		return crf;
	}

	public void setCrf(int crf) {
		this.crf = crf;
	}

	public String assinar() throws Exception {
		String assinaturaValida = getCpfPaciente() + getCrm() + getItensReceita();

		// Criando o objeto MessageDigest criptografia SHA-256
		MessageDigest assinatura = MessageDigest.getInstance("SHA-256");

		// Passando os dados para o objeto MessageDigest
		assinatura.update(assinaturaValida.getBytes());

		// Computando a assinatura digest
		byte[] digest = assinatura.digest();

		// Convertendo o array de bytes em HexString
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < digest.length; i++) {
			hexString.append(Integer.toHexString(0xFF & digest[i]));
		}

		return hexString.toString();
	}

	public String validar(String cpf, int crm, String itens) throws Exception {
		String assinaturaValida = cpf + crm + itens;
		// Criando o objeto MessageDigest criptografia SHA-256
		MessageDigest assinatura = MessageDigest.getInstance("SHA-256");

		// Passando os dados para o objeto MessageDigest
		assinatura.update(assinaturaValida.getBytes());

		// Computando a assinatura digest
		byte[] digest = assinatura.digest();

		// Convertendo o array de bytes em HexString
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < digest.length; i++) {
			hexString.append(Integer.toHexString(0xFF & digest[i]));
		}

		// Assinatura recuperada do bd utilizando o código inserido pelo farmaceútico
		String assinaturaRealizada = getHashReceita();

		// Verificando a assinatura computada é igual a que está no banco de dados
		if (hexString.toString().equals(assinaturaRealizada)) {
			return ("True");
		} else {
			return ("False");
		}
	}
}
