package escola.edutech.modelo;

public class Aluno implements Comparable<Aluno>{

	private String nome;
	private String email;
	private String turma;
	private String cgm;
	private String turno;
	private String status;

	public Aluno(String nome, String email, String turma, String cgm, String turno, String status) {
		verificaInformacoes(nome, email, turma, cgm, turno, status);
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
	
	private boolean verificaEmail(String email) {
		String dominio = "@escola.pr.gov.br";
		
		if(email.endsWith(dominio)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return new String(nome + ", " + email + ", " + turma + ", " + cgm + ", " + turno + ", " + status);
	}
	

	@Override
	public int compareTo(Aluno aluno) {
		return this.cgm.compareTo(aluno.getCgm());
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
			this.turma = turma.strip();
		}
		
		if(cgm.strip().isEmpty()) {
			throw new RuntimeException("Informe o cgm do aluno!");
		} else {
			for (String string : cgm.split("")) {
				try {
					Integer.parseInt(string);
				} catch (NumberFormatException e) {
					throw new RuntimeException("Não coloque letras no CGM! ");
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
