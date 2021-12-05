package escola.edutech.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import escola.edutech.dao.AlunoDAO;
import escola.edutech.modelo.Aluno;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewCadastroAluno {

	private JFrame frame;
	private JTextField textFieldNome;
	private JTextField textFieldTurno;
	private JTextField textFieldTurma;
	private JTextField textFieldEmail;
	private JLabel lblStatus;
	private JTextField textFieldStatus;
	private JButton botaoEntrar;
	private JTextField textFieldCgm;
	private JLabel lblCgm;

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
					ViewCadastroAluno window = new ViewCadastroAluno();
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
	public ViewCadastroAluno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		frame.setBounds(100, 100, 650, 500);
		frame.setLocation(600, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 19));
		lblNome.setBounds(87, 72, 64, 14);
		frame.getContentPane().add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(173, 64, 330, 30);
		frame.getContentPane().add(textFieldNome);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 19));
		lblEmail.setBounds(87, 131, 64, 14);
		frame.getContentPane().add(lblEmail);
		
		textFieldTurno = new JTextField();
		textFieldTurno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldTurno.setColumns(10);
		textFieldTurno.setBounds(173, 304, 330, 30);
		frame.getContentPane().add(textFieldTurno);
		
		JLabel lblTurma = new JLabel("Turma:");
		lblTurma.setFont(new Font("Arial", Font.PLAIN, 19));
		lblTurma.setBounds(87, 191, 76, 14);
		frame.getContentPane().add(lblTurma);
		
		textFieldTurma = new JTextField();
		textFieldTurma.setToolTipText("Insira o código de suas turmas separados por vírgula.");
		textFieldTurma.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldTurma.setColumns(10);
		textFieldTurma.setBounds(173, 184, 330, 30);
		frame.getContentPane().add(textFieldTurma);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setFont(new Font("Arial", Font.PLAIN, 19));
		lblTurno.setBounds(87, 311, 64, 14);
		frame.getContentPane().add(lblTurno);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(173, 124, 330, 30);
		frame.getContentPane().add(textFieldEmail);
		
		JLabel lblCadastroDeAlunos = new JLabel("CADASTRO DE ALUNOS");
		lblCadastroDeAlunos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeAlunos.setFont(new Font("Arial Narrow", Font.PLAIN, 35));
		lblCadastroDeAlunos.setBounds(0, 0, 634, 41);
		frame.getContentPane().add(lblCadastroDeAlunos);
		
		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 19));
		lblStatus.setBounds(87, 371, 64, 14);
		frame.getContentPane().add(lblStatus);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldStatus.setColumns(10);
		textFieldStatus.setBounds(173, 364, 330, 30);
		frame.getContentPane().add(textFieldStatus);
		
		botaoEntrar = new JButton("CADASTRAR");
		botaoEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Aluno aluno = new Aluno(textFieldNome.getText(), textFieldEmail.getText(), textFieldTurma.getText(),
							textFieldCgm.getText(), textFieldTurno.getText(), textFieldStatus.getText(), true, 
							ViewLogin.PROFESSOR_LOGADO.getNome(), ViewLogin.PROFESSOR_LOGADO.getEmail());
					if (!AlunoDAO.jaExiste(aluno)) {
						if(AlunoDAO.adicionar(aluno, AlunoDAO.ARQUIVO_ALUNOS_CADASTRADOS, true, true)) {
							JOptionPane.showMessageDialog(null, "Cadastro concluido!", "", JOptionPane.INFORMATION_MESSAGE);
							textFieldNome.setText(null);
							textFieldEmail.setText(null);
							textFieldTurma.setText(null);
							textFieldCgm.setText(null);
							textFieldTurno.setText(null);
							textFieldStatus.setText(null);
						}
					} else {
						JOptionPane.showMessageDialog(null, "O CGM '" + textFieldCgm.getText() + "' " 
					+ "já está no cadastro", "", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (RuntimeException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		botaoEntrar.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		botaoEntrar.setBounds(241, 405, 161, 30);
		frame.getContentPane().add(botaoEntrar);
		
		textFieldCgm = new JTextField();
		textFieldCgm.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldCgm.setColumns(10);
		textFieldCgm.setBounds(173, 244, 330, 30);
		frame.getContentPane().add(textFieldCgm);
		
		lblCgm = new JLabel("CGM:");
		lblCgm.setFont(new Font("Arial", Font.PLAIN, 19));
		lblCgm.setBounds(87, 251, 64, 14);
		frame.getContentPane().add(lblCgm);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ViewInicial.main(null);
			}
		});
		btnVoltar.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
		btnVoltar.setBounds(0, 0, 77, 30);
		frame.getContentPane().add(btnVoltar);
	}
}
