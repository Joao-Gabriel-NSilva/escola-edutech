package escola.edutech.modelo;


public class Aluno implements Comparable<Aluno>{

	private String nome;
	private String email;
	private String turma;
	private String cgm;
	private String turno;
	private String status;

	public Aluno(String nome, String email, String turma, String cgm, String turno, String status) {
		this.nome = nome;
		if(verificaEmail(email)) {
			this.email = email;
		} else {
			throw new RuntimeException("O email deve pertencer ao dominio @escola.pr.gov.br");
		}
		this.turma = turma;
		this.cgm = cgm;
		this.cgm = cgm;
		this.turno = turno;
		this.status = status;
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

}
