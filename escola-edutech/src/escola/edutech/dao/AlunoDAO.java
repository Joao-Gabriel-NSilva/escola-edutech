package escola.edutech.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import escola.edutech.modelo.Aluno;
import escola.edutech.view.ViewLogin;

public class AlunoDAO {

	public static File ARQUIVO_ALUNOS_CADASTRADOS = new File("dados/alunosCadastrados.csv");
	public static File ARQUIVO_CGMS = new File("dados/cgms.csv");
	private static List<String> cgms = new ArrayList<>();

	public static boolean adicionar(Aluno aluno, File arquivo, boolean addCgm) {
		pathBuilder();
		try (FileWriter writer = new FileWriter(arquivo, true)) {
			try (PrintWriter saida = new PrintWriter(writer, true)) {
				saida.println(aluno.getNome() + ";" + aluno.getEmail() + ";" + aluno.getTurma() + ";" + aluno.getCgm()
						+ ";" + aluno.getTurno() + ";" + aluno.getStatus());
				if(addCgm) {
					adicionaCgm(aluno.getCgm());
				}
				ProfessorDAO.adicionaAluno(ViewLogin.PROFESSOR_LOGADO, aluno);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Aluno> listar(File arquivo) {
		pathBuilder();
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			Scanner scanner = new Scanner(arquivo, "UTF-8");
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useDelimiter(";");

				String nome = linhaScanner.next();
				String email = linhaScanner.next();
				String turma = linhaScanner.next();
				String cgm = linhaScanner.next();
				String turno = linhaScanner.next();
				String status = linhaScanner.next();

				alunos.add(new Aluno(nome, email, turma, cgm, turno, status));
				linhaScanner.close();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return alunos;
	}

	private static void pathBuilder() {
		try {
			if (!ARQUIVO_ALUNOS_CADASTRADOS.exists()) {
				Files.createDirectories(Paths.get("dados"));
				try (FileWriter writer = new FileWriter(ARQUIVO_ALUNOS_CADASTRADOS, true)) {
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static boolean jaExiste(Aluno aluno) {
		listarCgms();
		return new HashSet<Aluno>(listar(ARQUIVO_ALUNOS_CADASTRADOS)).contains(aluno);
	}

	private static void adicionaCgm(String cgm) {
		try (FileWriter writer = new FileWriter(ARQUIVO_CGMS, true)) {
			try (PrintWriter saida = new PrintWriter(writer, true)) {
				saida.println(cgm);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void listarCgms() {
		try {
			Scanner scanner = new Scanner(ARQUIVO_ALUNOS_CADASTRADOS, "UTF-8");
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				Scanner linhaScanner = new Scanner(linha);
				String cgm = linhaScanner.next();
				cgms.add(cgm);
				linhaScanner.close();
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
