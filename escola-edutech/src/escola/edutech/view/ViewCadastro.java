package escola.edutech.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import escola.edutech.dao.ProfessorDAO;
import escola.edutech.modelo.Professor;

public class ViewCadastro {

	private JFrame frame;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JTextField textFieldTurmas;
	private JTextField textFieldTurno;
	private JPasswordField passwordField;
	private JLabel lblSenha;
	private JCheckBox chckbxMostrarSenha;
	private JTextField textFieldSenha;
	private JButton botaoEntrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastro window = new ViewCadastro();
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
	public ViewCadastro() {
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
		lblNome.setBounds(88, 111, 64, 14);
		frame.getContentPane().add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(174, 103, 330, 30);
		frame.getContentPane().add(textFieldNome);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 19));
		lblEmail.setBounds(88, 290, 64, 14);
		frame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(174, 283, 330, 30);
		frame.getContentPane().add(textFieldEmail);
		
		JLabel lblTurmas = new JLabel("Turmas:");
		lblTurmas.setFont(new Font("Arial", Font.PLAIN, 19));
		lblTurmas.setBounds(88, 230, 76, 14);
		frame.getContentPane().add(lblTurmas);
		
		textFieldTurmas = new JTextField();
		textFieldTurmas.setToolTipText("Insira o código de suas turmas separados por vírgula.");
		textFieldTurmas.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldTurmas.setColumns(10);
		textFieldTurmas.setBounds(174, 223, 330, 30);
		frame.getContentPane().add(textFieldTurmas);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setFont(new Font("Arial", Font.PLAIN, 19));
		lblTurno.setBounds(88, 170, 64, 14);
		frame.getContentPane().add(lblTurno);
		
		textFieldTurno = new JTextField();
		textFieldTurno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldTurno.setColumns(10);
		textFieldTurno.setBounds(174, 163, 330, 30);
		frame.getContentPane().add(textFieldTurno);
		
		JLabel lblCadastroDeProfessor = new JLabel("CADASTRO DE PROFESSOR");
		lblCadastroDeProfessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeProfessor.setFont(new Font("Arial Narrow", Font.PLAIN, 35));
		lblCadastroDeProfessor.setBounds(0, 0, 634, 41);
		frame.getContentPane().add(lblCadastroDeProfessor);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 19));
		lblSenha.setBounds(88, 351, 64, 14);
		frame.getContentPane().add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		passwordField.setBounds(174, 343, 330, 30);
		frame.getContentPane().add(passwordField);
		
		textFieldSenha = new JTextField();
		textFieldSenha.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldSenha.setColumns(10);
		textFieldSenha.setBounds(174, 343, 330, 30);
		textFieldSenha.setVisible(false);
		frame.getContentPane().add(textFieldSenha);
		
		chckbxMostrarSenha = new JCheckBox("Mostrar senha");
		chckbxMostrarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxMostrarSenha.isSelected()) {
					passwordField.setVisible(false);
					textFieldSenha.setVisible(true);
					textFieldSenha.setText(passwordField.getText());
				} else {
					textFieldSenha.setVisible(false);
					passwordField.setVisible(true);
				}
			}
		});
		chckbxMostrarSenha.setBounds(517, 349, 111, 23);
		frame.getContentPane().add(chckbxMostrarSenha);
		
		botaoEntrar = new JButton("ENTRAR");
		botaoEntrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					
					List<String> listaTurmas = new ArrayList<>();
					for (String turma : textFieldTurmas.getText().split(",", 9)) {
						listaTurmas.add(turma);
					}
					
					if(ProfessorDAO.adicionar(new Professor(textFieldNome.getText(), textFieldEmail.getText(), listaTurmas, 
							textFieldTurno.getText()))) {
						
						String senha;
						if (passwordField.isEnabled()) {
							senha = passwordField.getText();
						} else {
							senha = textFieldSenha.getText();
						}
						
						ProfessorDAO.salvaLogin(textFieldEmail.getText(), senha);
						JOptionPane.showMessageDialog(null, "Cadastro concluido!", "", JOptionPane.WARNING_MESSAGE);
						
						frame.setVisible(false);
						ViewLogin.main(null);
					}
					
				} catch(RuntimeException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		botaoEntrar.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		botaoEntrar.setBounds(254, 401, 125, 30);
		frame.getContentPane().add(botaoEntrar);
	}
}
