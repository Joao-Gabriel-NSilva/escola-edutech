package escola.edutech.modelo;

import java.util.ArrayList;
import java.util.List;

import escola.edutech.dao.AlunoDAO;

public class Teste {

	public static void main(String[] args) {
		Aluno aluno1 = new Aluno("João Gabriel", "joao.nascimento.silva@escola.pr.gov.br", "3A2021EED", "12345678", 
				"Manhã", "ATIVO");
		Aluno aluno2 = new Aluno("Gabriel", "Gabriel@escola.pr.gov.br", "3A2021EED", "3523412", 
				"Manhã", "ATIVO");
		Aluno aluno3 = new Aluno("Marcos", "Marcos@escola.pr.gov.br", "3A2021EED", "67474123", 
				"Manhã", "ATIVO");
		Aluno aluno4 = new Aluno("Felipe", "Felipe@escola.pr.gov.br", "3A2021EED", "56734512", 
				"Manhã", "ATIVO");
		Aluno aluno5 = new Aluno("Matheus", "Matheus@escola.pr.gov.br", "3A2021EED", "2465732", 
				"Manhã", "ATIVO");
		
		AlunoDAO.adicionar(aluno1);
		AlunoDAO.adicionar(aluno2);
		AlunoDAO.adicionar(aluno3);
		AlunoDAO.adicionar(aluno4);
		AlunoDAO.adicionar(aluno5);

		List<String> turmas = new ArrayList<>();
		turmas.add("3A2021EED");
		Professor professor1 = new Professor("Rafael", "Rafael@escola.pr.gov.br", turmas,
				"Manhã");
		
		AlunoDAO.listar().forEach(aluno -> {System.out.println(aluno);});
		System.out.println(professor1);
	}

}
