package escola.edutech.modelo;

import java.util.List;

import escola.edutech.dao.ProfessorDAO;
import escola.edutech.view.ViewLogin;

public class Aluno implements Comparable<Aluno>{

	private String nome;
	private String email;
	private String turma;
	private String cgm;
	private String turno;
	private String status;
	private Professor professor;
	private String nomeProfessor;
	private String emailProfessor;

	public Aluno(String nome, String email, String turma, String cgm, String turno, String status, boolean fazVerificacao,
			String nomeProf, String emailProf) {
		if(fazVerificacao) {
			verificaInformacoes(nome, email, turma, cgm, turno, status);
			this.professor = ViewLogin.PROFESSOR_LOGADO;
		} else {
			this.nome = nome;
			this.email = email;
			this.turma = turma;
			this.cgm = cgm;
			this.turno = turno;
			this.status = status;
			this.nomeProfessor = nomeProf;
			this.emailProfessor = emailProf;
			List<Professor> professores = ProfessorDAO.listar();
			for (Professor professor : professores) {
				if(professor.getNome().equals(nomeProf) & professor.getEmail().equals(emailProf)) {
					this.professor = professor;
				}
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTurma() {
		return turma;
	}

	public String getCgm() {
		return cgm;
	}

	public String getTurno() {
		return turno;
	}

	public String getStatus() {
		return status;
	}
	public Professor getProfessor() {
		return professor;
	}
	
	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public String getEmailProfessor() {
		return emailProfessor;
	}

	@Override
	public int compareTo(Aluno aluno) {
		return this.nome.compareTo(aluno.getNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cgm == null) ? 0 : cgm.hashCode());
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
		Aluno other = (Aluno) obj;
		if (cgm == null) {
			if (other.cgm != null)
				return false;
		} else if (!cgm.equals(other.cgm))
			return false;
		return true;
	}
	
	private boolean verificaInformacoes(String nome, String email, String turma, String cgm, String turno, String status) {
		if(nome.strip().isEmpty()) {
			throw new RuntimeException("Informe o nome do aluno!");
		} else {
			verificaSeHaNumero(nome);
			this.nome = nome.strip();
		}

		if (!email.strip().isEmpty() & email.strip().endsWith("@escola.pr.gov.br")) {
			this.email = email.strip();
		} else {
			throw new RuntimeException("Informe o email do aluno ou verifique se ele pertencer ao dominio @escola.pr.gov.br");
		}
		
		if(turma.strip().isEmpty()) {
			throw new RuntimeException("Informe o código da turma do aluno!");
		} else if(turma.length() < 9 | turma.length() > 9) {
			throw new RuntimeException("O código da turma deve conter nove digitos! Exemplo: 3A2021EED (série + ano + EED");
		} else {
			this.turma = turma.strip().toUpperCase();
		}
		
		if(cgm.strip().isEmpty()) {
			throw new RuntimeException("Informe o cgm do aluno!");
		} else {
			for (String string : cgm.split("")) {
				try {
					Integer.parseInt(string);
				} catch (NumberFormatException e) {
					throw new RuntimeException("Não coloque letras no CGM!");
				}
			}
			this.cgm = cgm.strip();
		}
		
		if(turno.strip().isEmpty()) {
			throw new RuntimeException("Informe o turno do aluno!");
		} else {
			verificaSeHaNumero(turno);
			this.turno = turno;
		}
		
		if(status.strip().isEmpty()) {
			throw new RuntimeException("Informe o status do aluno!");
		} else if(status.strip().toLowerCase().equals("ativo") | status.strip().toLowerCase().equals("inativo")){
			this.status = status.strip().toUpperCase();
		} else {
			throw new RuntimeException("Status inválido! Utilize 'ativo' ou 'inativo'.");
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

}
