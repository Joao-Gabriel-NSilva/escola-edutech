package escola.edutech.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Collection;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import escola.edutech.dao.AlunoDAO;
import escola.edutech.modelo.Aluno;

public class ViewConsultaAlunos {

	private JFrame frame;
	private JTable tabela;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConsultaAlunos window = new ViewConsultaAlunos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewConsultaAlunos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Menu Inicial");
		frame.getContentPane().setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		frame.setBounds(100, 100, 914, 500);
		frame.setLocation(600, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 28, 852, 337);
		frame.getContentPane().add(scrollPane);
		
		tabela = new JTable();
		scrollPane.setViewportView(tabela);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setShowVerticalLines(true);
		tabela.setShowHorizontalLines(true);
		tabela.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tabela.setBackground(SystemColor.inactiveCaptionBorder);
		
		modelo = (DefaultTableModel) tabela.getModel();
		modelo.addColumn("NOME");
		modelo.addColumn("EMAIL");
		modelo.addColumn("TURMA");
		modelo.addColumn("CGM");
		modelo.addColumn("TURNO");
		modelo.addColumn("STATUS");
		
		preencherTabela();
	}
	
	private void preencherTabela() {
		Collection<Aluno> alunos = new TreeSet<Aluno>(AlunoDAO.listar(AlunoDAO.ARQUIVO_ALUNOS_CADASTRADOS));
		try {
			for (Aluno aluno : alunos) {
				modelo.addRow(new Object[] { aluno.getNome(), aluno.getEmail(), aluno.getTurma(), aluno.getCgm(), 
						aluno.getTurno(), aluno.getStatus()});
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
