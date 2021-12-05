package escola.edutech.modelo;

import java.util.ArrayList;
import java.util.List;

import escola.edutech.dao.ProfessorDAO;

public class Professor {

	private String nome;
	private String email;
	private List<String> turmas = new ArrayList<>();
	private String turno;
	private List<Aluno> alunos;

	public Professor(String nome, String email, List<String> turmas, String turno, boolean fazVerificacao) {
		if(fazVerificacao) {
			verificaInformacoes(nome, email, turmas, turno);
		} else {
			this.nome = nome;
			this.email = email;
			this.turmas = turmas;
			this.turno = turno;
		}
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public List<String> getTurmas() {
		return turmas;
	}

	public String getTurno() {
		return turno;
	}
	
	public List<Aluno> getAlunos(){
		return alunos;
	}

	@Override
	public String toString() {
		return nome;
	}

	private boolean verificaInformacoes(String nome, String email, List<String> turmas, String turno) {
		if (nome.strip().isEmpty()) {
			throw new RuntimeException("Informe seu nome!");
		} else {
			verificaSeHaNumero(nome);
			this.nome = nome.strip();
		}

		if (!email.strip().isEmpty() & email.strip().endsWith("@escola.pr.gov.br")) {
			if (ProfessorDAO.listaLogins().containsKey(email.strip())) {
				throw new RuntimeException("O email já está cadastrado!");
			}
			this.email = email.strip();
		} else {
			throw new RuntimeException("Informe seu email ou verifique se ele pertencer ao dominio @escola.pr.gov.br");
		}

		this.turmas = turmas;

		if (turno.strip().isEmpty()) {
			throw new RuntimeException("Informe seu turno!");
		} else {
			verificaSeHaNumero(turno);
			this.turno = turno;
		}
		return true;
	}

	private void verificaSeHaNumero(String outraString) {
		for (String string : outraString.split("")) {
			try {
				Integer.parseInt(string);
				throw new RuntimeException("Não coloque números no campo indevido! ");
			} catch (NumberFormatException e) {

			}
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
