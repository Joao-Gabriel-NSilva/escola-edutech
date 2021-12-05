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

	private static File ARQUIVO_PROFESSORES = new File("dados/professores.csv");
	private static File ARQUIVO_LOGIN = new File("dados/logins.csv");
	private static File ARQUIVO_ALUNOS_PROFESSOR;

	public static boolean adicionar(Professor professor) {
		pathBuilder(ARQUIVO_PROFESSORES);
		try (FileWriter writer = new FileWriter(ARQUIVO_PROFESSORES, true)) {
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
		pathBuilder(ARQUIVO_PROFESSORES);
		List<Professor> professores = new ArrayList<Professor>();
		try {
			Scanner scanner = new Scanner(ARQUIVO_PROFESSORES, "UTF-8");
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useDelimiter(";");

				String nome = linhaScanner.next();
				String email = linhaScanner.next();
				String turmas = linhaScanner.next();

				List<String> listaTurmas = new ArrayList<>();
				for (String turma : turmas.split(",", 9)) {
					if (turma.startsWith("[")) {
						turma = turma.replace("[", "");
					} else if (turma.endsWith("]")) {
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
			if (!ARQUIVO_PROFESSORES.exists()) {
				Files.createDirectories(Paths.get("dados"));
				try (FileWriter writer = new FileWriter(arquivo, true)) {
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void salvaLogin(String email, String senha) {
		pathBuilder(ARQUIVO_LOGIN);

		try (FileWriter writer = new FileWriter(ARQUIVO_LOGIN, true)) {
			try (PrintWriter saida = new PrintWriter(writer, true)) {
				saida.println(email + ";" + senha);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> listaLogins() {
		pathBuilder(ARQUIVO_LOGIN);
		Map<String, String> listaLogins = new HashMap<>();
		try {
			Scanner scanner = new Scanner(ARQUIVO_LOGIN, "UTF-8");
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
		if (turma.strip().isEmpty()) {
			throw new RuntimeException("Informe o código da turma!");
		} else if (turma.length() < 9 | turma.length() > 9) {
			throw new RuntimeException(
					"O código da turma deve conter nove digitos! Exemplo: 3A2021EED (série + ano + EED");
		} else {

			List<Professor> professores = listar();
			try {
				new FileWriter(ARQUIVO_PROFESSORES).close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			for (Professor professorArquivo : professores) {
				if (professorLogado.equals(professorArquivo)) {
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
			if (ARQUIVO_ALUNOS_PROFESSOR == null) {
				ARQUIVO_ALUNOS_PROFESSOR = new File(
						"dados/alunosDoProfessor" + ViewLogin.PROFESSOR_LOGADO.getNome() + ".csv");

			}
			AlunoDAO.adicionar(aluno, ARQUIVO_ALUNOS_PROFESSOR, false, false);
		} catch (Exception ex) {

		}
	}

	public static List<Aluno> listarAlunos() {
		return AlunoDAO.listar(ARQUIVO_ALUNOS_PROFESSOR);
	}

	public static void atualizaAlunoProfessor(List<Aluno> alunos) {
		if (ARQUIVO_ALUNOS_PROFESSOR == null) {
			ARQUIVO_ALUNOS_PROFESSOR = new File(
					"dados/alunosDoProfessor" + ViewLogin.PROFESSOR_LOGADO.getNome() + ".csv");

		}
		try {
			new FileWriter(ARQUIVO_ALUNOS_PROFESSOR).close();
		} catch (Exception e) {
			
		}
		for (Aluno aluno : alunos) {
			AlunoDAO.adicionar(aluno, ARQUIVO_ALUNOS_PROFESSOR, false, false);
		}
	}

}
