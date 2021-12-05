package escola.edutech.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import escola.edutech.dao.AlunoDAO;
import escola.edutech.modelo.Aluno;

public class ViewAtualizaAluno {

	private JFrame frmAtualizarAluno;
	private JTextField textFieldCodigo;
	private JButton btnVoltar;
	private JButton btnAtualizar;
	private JTextField textFieldStatus;
	public static Aluno ALUNO;
	private JLabel lblNome;
	private JLabel lblNewLabel;
	private JLabel lblStatusAtual;
	private JLabel lblProfessor;

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
					ViewAtualizaAluno window = new ViewAtualizaAluno();
					window.frmAtualizarAluno.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewAtualizaAluno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtualizarAluno = new JFrame();
		frmAtualizarAluno.setTitle("Atualizar aluno");
		frmAtualizarAluno.getContentPane().setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		frmAtualizarAluno.setBounds(100, 100, 650, 438);
		frmAtualizarAluno.setLocation(600, 200);
		frmAtualizarAluno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAtualizarAluno.setResizable(false);
		frmAtualizarAluno.getContentPane().setLayout(null);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldCodigo.setColumns(10);
		textFieldCodigo.setBounds(218, 211, 330, 30);
		frmAtualizarAluno.getContentPane().add(textFieldCodigo);
		
		JLabel lblCodigoDaTurma = new JLabel("Novo código da turma:");
		lblCodigoDaTurma.setFont(new Font("Arial", Font.PLAIN, 19));
		lblCodigoDaTurma.setBounds(10, 214, 190, 23);
		frmAtualizarAluno.getContentPane().add(lblCodigoDaTurma);
		
		btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String turma = textFieldCodigo.getText().strip().toUpperCase();
					String status = textFieldStatus.getText().strip().toUpperCase();
					
					AlunoDAO.alterarInformacoes(ALUNO, turma, status);
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
				} catch(RuntimeException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAtualizar.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		btnAtualizar.setBounds(243, 326, 157, 30);
		frmAtualizarAluno.getContentPane().add(btnAtualizar);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAtualizarAluno.setVisible(false);
				ViewConsultaAlunos.main(null);
			}
		});
		btnVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnVoltar.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
		btnVoltar.setBounds(0, 0, 77, 30);
		frmAtualizarAluno.getContentPane().add(btnVoltar);
		
		JLabel lblNovoStatus = new JLabel("Novo status:");
		lblNovoStatus.setFont(new Font("Arial", Font.PLAIN, 19));
		lblNovoStatus.setBounds(10, 266, 151, 23);
		frmAtualizarAluno.getContentPane().add(lblNovoStatus);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldStatus.setColumns(10);
		textFieldStatus.setBounds(218, 263, 330, 30);
		frmAtualizarAluno.getContentPane().add(textFieldStatus);
		
		lblNome = new JLabel(ALUNO.getNome());
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Arial", Font.PLAIN, 23));
		lblNome.setBounds(79, 11, 486, 40);
		frmAtualizarAluno.getContentPane().add(lblNome);
		
		lblNewLabel = new JLabel("Turma atual: " + ALUNO.getTurma());
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 19));
		lblNewLabel.setBounds(10, 62, 227, 14);
		frmAtualizarAluno.getContentPane().add(lblNewLabel);
		
		lblStatusAtual = new JLabel("Status atual: " + ALUNO.getStatus());
		lblStatusAtual.setFont(new Font("Arial", Font.PLAIN, 19));
		lblStatusAtual.setBounds(10, 103, 227, 14);
		frmAtualizarAluno.getContentPane().add(lblStatusAtual);
		
		lblProfessor = new JLabel("Professor: " + ALUNO.getNomeProfessor());
		lblProfessor.setFont(new Font("Arial", Font.PLAIN, 19));
		lblProfessor.setBounds(10, 141, 317, 14);
		frmAtualizarAluno.getContentPane().add(lblProfessor);
	}
}
