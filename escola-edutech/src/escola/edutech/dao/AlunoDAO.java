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

	public static boolean adicionar(Aluno aluno, File arquivo, boolean addCgm, boolean cadastro) {
		pathBuilder(arquivo);
		try (FileWriter writer = new FileWriter(arquivo, true)) {
			try (PrintWriter saida = new PrintWriter(writer, true)) {
				saida.println(aluno.getNome() + ";" + aluno.getEmail() + ";" + aluno.getTurma() + ";" + aluno.getCgm()
						+ ";" + aluno.getTurno() + ";" + aluno.getStatus() + ";" + aluno.getProfessor().getNome() + ";"
						+ aluno.getProfessor().getEmail());
				if (addCgm) {
					adicionaCgm(aluno.getCgm());
				}
				if (cadastro) {
					ProfessorDAO.adicionaAluno(ViewLogin.PROFESSOR_LOGADO, aluno);
				}
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Aluno> listar(File arquivo) {
		pathBuilder(arquivo);
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
				String nomeProfessor = linhaScanner.next();
				String emailProfessor = linhaScanner.next();

				alunos.add(new Aluno(nome, email, turma, cgm, turno, status, false, nomeProfessor, emailProfessor));
				linhaScanner.close();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return alunos;
	}

	private static void pathBuilder(File arquivo) {
		try {
			if (!arquivo.exists()) {
				Files.createDirectories(Paths.get("dados"));
				try (FileWriter writer = new FileWriter(arquivo, true)) {
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

	public static void alterarInformacoes(Aluno aluno, String turma, String status) {
		if(turma.isEmpty() & status.isEmpty()) {
			throw new RuntimeException("Nenhuma nova informação fornecida!");
		} 
		
		if(turma.isEmpty()) {
			turma = aluno.getTurma();
		} else if(turma.length() > 0 & turma.length() < 9 | turma.length() > 9) {
			throw new RuntimeException("O código da turma deve conter nove digitos! Exemplo: 3A2021EED (série + ano + EED");
		}
			
		if (status.isEmpty()) {
			status = aluno.getStatus();
		} else if(status.strip().toLowerCase().equals("ativo") | status.strip().toLowerCase().equals("inativo")){
			status = status.strip().toUpperCase();
		} else {
			throw new RuntimeException("Status inválido! Utilize 'ativo' ou 'inativo'.");
		}
		
		List<Aluno> alunos = listar(ARQUIVO_ALUNOS_CADASTRADOS);
		try {
			new FileWriter(ARQUIVO_ALUNOS_CADASTRADOS).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Aluno aluno2 : alunos) {
			if(aluno.equals(aluno2)) {
				Aluno a = new Aluno(aluno.getNome(), aluno.getEmail(), turma, aluno.getCgm(), aluno.getTurno(), status,
						false, aluno.getNomeProfessor(), aluno.getEmailProfessor());
				adicionar(a, ARQUIVO_ALUNOS_CADASTRADOS, false, false);
			} else {
				adicionar(aluno2, ARQUIVO_ALUNOS_CADASTRADOS, false, false);
			}
		}
		ProfessorDAO.atualizaAlunoProfessor(listar(ARQUIVO_ALUNOS_CADASTRADOS));
		
	}
	
}
