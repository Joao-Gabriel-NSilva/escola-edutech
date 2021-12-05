package escola.edutech.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import escola.edutech.modelo.Aluno;
import escola.edutech.modelo.Professor;
import escola.edutech.view.ViewLogin;

public class ProfessorDAO {

	private static File arquivoProfessores = new File("dados/professores.csv");
	private static File arquivoLogin = new File("dados/logins.csv");
	private static File arquivoAlunosProfessor;

	public static boolean adicionar(Professor professor) {
		pathBuilder(arquivoProfessores);
		try (FileWriter writer = new FileWriter(arquivoProfessores, true)) {
			try (PrintWriter saida = new PrintWriter(writer, true)) {
				saida.println(professor.getNome() + ";" + professor.getEmail() + ";" + professor.getTurmas() + ";"
						+ professor.getTurno());
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Professor> listar() {
		pathBuilder(arquivoProfessores);
		List<Professor> professores = new ArrayList<Professor>();
		try {
			Scanner scanner = new Scanner(arquivoProfessores, "UTF-8");
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useDelimiter(";");

				String nome = linhaScanner.next();
				String email = linhaScanner.next();
				String turmas = linhaScanner.next();
				
				List<String> listaTurmas = new ArrayList<>();
				for (String turma : turmas.split(",", 9)) {
					if(turma.startsWith("[")) {
						turma = turma.replace("[", "");
					} else if(turma.endsWith("]")) {
						turma = turma.replace("]", "");
					}
					turma = turma.strip();
					listaTurmas.add(turma);
				}
				
				String turno = linhaScanner.next();

				professores.add(new Professor(nome, email, listaTurmas, turno, false));
				linhaScanner.close();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return professores;
	}

	private static void pathBuilder(File arquivo) {
		try {
			if (!arquivoProfessores.exists()) {
				Files.createDirectories(Paths.get("dados"));
				try (FileWriter writer = new FileWriter(arquivo, true)) {
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void salvaLogin(String email, String senha) {
		pathBuilder(arquivoLogin);
		
		try (FileWriter writer = new FileWriter(arquivoLogin, true)) {
			try (PrintWriter saida = new PrintWriter(writer, true)) {
				saida.println(email + ";" + senha);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, String> listaLogins() {
		pathBuilder(arquivoLogin);
		Map<String, String> listaLogins = new HashMap<>();
		try {
			Scanner scanner = new Scanner(arquivoLogin, "UTF-8");
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useDelimiter(";");

				String email = linhaScanner.next();
				String senha = linhaScanner.next();

				listaLogins.put(email, senha);
				linhaScanner.close();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listaLogins;
	}
	
	public static void adicionaTurma(Professor professorLogado, String turma) {
		if(turma.strip().isEmpty()) {
			throw new RuntimeException("Informe o código da turma!");
		} else if(turma.length() < 9 | turma.length() > 9) {
			throw new RuntimeException("O código da turma deve conter nove digitos! Exemplo: 3A2021EED (série + ano + EED");
		} else {
			
			List<Professor> professores = listar();
			try {
				new FileWriter(arquivoProfessores).close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			for (Professor professorArquivo : professores) {
				if(professorLogado.equals(professorArquivo)) {
					professorLogado.getTurmas().add(turma);
					adicionar(new Professor(professorLogado.getNome(), professorLogado.getEmail(), 
							professorLogado.getTurmas(), professorLogado.getTurno(), false));
				} else {
					adicionar(professorArquivo);
				}
			}
		}
	}
	
	public static void adicionaAluno(Professor professorLogado, Aluno aluno) {
		try {
			arquivoAlunosProfessor = new File("dados/alunosDoProfessor" + ViewLogin.PROFESSOR_LOGADO.getNome()+ ".csv");
		} catch(Exception e) {
			
		}
		AlunoDAO.adicionar(aluno, arquivoAlunosProfessor, false, false);
	}
	
	public static List<Aluno> listarAlunos() {
		return AlunoDAO.listar(arquivoAlunosProfessor);
	}
}

