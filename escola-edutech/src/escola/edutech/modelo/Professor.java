package escola.edutech.modelo;

import java.util.ArrayList;
import java.util.List;

public class Professor {

	private String nome;
	private String email;
	private List<String> turmas = new ArrayList<>();
	private String turno;
	
	public Professor(String nome, String email, List<String> turmas, String turno) {
		this.nome = nome;
		if(verificaEmail(email)) {
			this.email = email;
		}
		this.turmas = turmas;
		this.turno = turno;
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
	
	private boolean verificaEmail(String email) {
		String dominio = "@escola.pr.gov.br";
		
		if(email.endsWith(dominio)) {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return new String(nome + ", " + email + ", " + turmas + ", " + turno);
	}

}
