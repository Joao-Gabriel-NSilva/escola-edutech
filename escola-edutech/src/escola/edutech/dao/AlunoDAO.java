package escola.edutech.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import escola.edutech.modelo.Aluno;

public class AlunoDAO {

	private static File arquivo = new File("dados/alunos.csv");
	private static List<String> cgms = new ArrayList<>();

	public static void adicionar(Aluno aluno) {
		pathBuilder();
		try (FileWriter writer = new FileWriter(arquivo, true)) {
			try (PrintWriter saida = new PrintWriter(writer, true)) {
				saida.println(aluno.getNome() + ";" + aluno.getEmail() + ";" + aluno.getTurma() + ";" + aluno.getCgm()
						+ ";" + aluno.getTurno() + ";" + aluno.getStatus());
				cgms.add(aluno.getCgm());
				System.out.println(cgms);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Aluno> listar() {
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
			if (!arquivo.exists()) {
				Files.createDirectories(Paths.get("dados"));
				try (FileWriter writer = new FileWriter(arquivo, true)) {}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean jaExiste(String outroCgm) {
		for (String cgm : cgms) {
			if(cgm.equals(outroCgm)) {
				return true;
			} 
		}
		return false;
	}

}
