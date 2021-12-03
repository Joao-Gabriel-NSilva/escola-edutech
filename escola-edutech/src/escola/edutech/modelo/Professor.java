package escola.edutech.modelo;

import java.util.ArrayList;
import java.util.List;

public class Professor {

	private String nome;
	private String email;
	private List<String> turmas = new ArrayList<>();
	private String turno;

	public Professor(String nome, String email, List<String> turmas, String turno) {
		verificaInformacoes(nome, email, turmas, turno);
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

	@Override
	public String toString() {
		return new String(nome + ", " + email + ", " + turmas + ", " + turno);
	}

	private boolean verificaInformacoes(String nome, String email, List<String> turmas, String turno) {
		if(nome.strip().isEmpty()) {
			throw new RuntimeException("Informe seu nome!");
		} else {
			verificaSeHaNumero(nome);
			this.nome = nome.strip();
		}

		if (!email.strip().isEmpty() & email.strip().endsWith("@escola.pr.gov.br")) {
			this.email = email.strip();
		} else {
			throw new RuntimeException("Informe seu email ou verifique se ele pertencer ao dominio @escola.pr.gov.br");
		}
		
		this.turmas = turmas;
		
		if(turno.strip().isEmpty()) {
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
}
