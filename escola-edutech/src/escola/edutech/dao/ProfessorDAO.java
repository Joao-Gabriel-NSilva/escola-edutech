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

import escola.edutech.modelo.Professor;

public class ProfessorDAO {

	private static File arquivoProfessores = new File("dados/professores.csv");
	private static File arquivoLogin = new File("dados/logins");

	public static void adicionar(Professor professor) {
		pathBuilder(arquivoProfessores);
		try (FileWriter writer = new FileWriter(arquivoProfessores, true)) {
			try (PrintWriter saida = new PrintWriter(writer, true)) {
				saida.println(professor.getNome() + ";" + professor.getEmail() + ";" + professor.getTurmas() + ";"
						+ professor.getTurno());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
					listaTurmas.add(turma);
				}
				
				String turno = linhaScanner.next();

				professores.add(new Professor(nome, email, listaTurmas, turno));
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
}
