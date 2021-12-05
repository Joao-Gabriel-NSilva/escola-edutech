package escola.edutech.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import escola.edutech.dao.AlunoDAO;
import escola.edutech.dao.ProfessorDAO;
import escola.edutech.modelo.Aluno;
import escola.edutech.modelo.Professor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewConsultaAlunos {

	private JFrame frame;
	private JTable tabela;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JComboBox<String> comboBoxProfessores;
	private JButton btnVoltar;

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
		scrollPane.setBounds(28, 76, 852, 337);
		frame.getContentPane().add(scrollPane);
		
		tabela = new JTable();
		scrollPane.setViewportView(tabela);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setShowVerticalLines(true);
		tabela.setShowHorizontalLines(true);
		tabela.setFont(new Font("SansSerif", Font.PLAIN, 13));
		tabela.setBackground(SystemColor.inactiveCaptionBorder);
		
		comboBoxProfessores = new JComboBox<String>();
		comboBoxProfessores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				limparTabela();
				preencherTabela();
			}
		});
		comboBoxProfessores.setBounds(567, 48, 313, 26);
		comboBoxProfessores.addItem("Todos os alunos");
		List<Professor> listaProfessores = ProfessorDAO.listar();
		for (Professor professor : listaProfessores) {
			comboBoxProfessores.addItem("Alunos do professor " + professor.getNome());
		}
		frame.getContentPane().add(comboBoxProfessores);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ViewInicial.main(null);
			}
		});
		btnVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnVoltar.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
		btnVoltar.setBounds(0, 0, 77, 33);
		frame.getContentPane().add(btnVoltar);
		
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
		File arquivo;
		if(comboBoxProfessores.getSelectedItem().toString().equals("Todos os alunos")) {
			arquivo = AlunoDAO.ARQUIVO_ALUNOS_CADASTRADOS;
		} else {
			String nomeProfessor = comboBoxProfessores.getSelectedItem().toString().substring(20);
			arquivo = new File("dados/alunosDoProfessor" + nomeProfessor + ".csv");
		}
		Collection<Aluno> alunos = new TreeSet<Aluno>(AlunoDAO.listar(arquivo));
		try {
			for (Aluno aluno : alunos) {
				modelo.addRow(new Object[] { aluno.getNome(), aluno.getEmail(), aluno.getTurma(), aluno.getCgm(), 
						aluno.getTurno(), aluno.getStatus()});
			}
		} catch (Exception e) {
			
		}
	}
	
	private void limparTabela() {
		List<Aluno> alunos = AlunoDAO.listar(AlunoDAO.ARQUIVO_ALUNOS_CADASTRADOS);
		try {
			modelo.getDataVector().clear();
//			modelo.addRow(new Object[] { "", "", "", "", "" });
//			for (Aluno aluno : alunos) {
//				modelo.addRow(new Object[] { "", "", "", "", "" });
//			}
		} catch (Exception e) {
			
		}
	}
}
