package escola.edutech.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import escola.edutech.dao.ProfessorDAO;
import escola.edutech.modelo.Professor;

public class ViewLogin {

	private JFrame frame;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JTextField textFieldSenha;
	public static Professor PROFESSOR_LOGADO;

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
					ViewLogin window = new ViewLogin();
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
	public ViewLogin() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldEmail.setFocusable(false);
				passwordField.setFocusable(false);
				textFieldSenha.setFocusable(false);
			}
		});
		frame.setTitle("LOGIN");
		frame.getContentPane().setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		frame.setBounds(100, 100, 650, 500);
		frame.setLocation(600, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBemVindo = new JLabel("BEM VINDO PROFESSOR!");
		lblBemVindo.setBounds(0, 0, 634, 41);
		lblBemVindo.setFont(new Font("Arial Narrow", Font.PLAIN, 35));
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblBemVindo);
		
		textFieldEmail = new JTextField();
		textFieldEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldEmail.setFocusable(true);
			}
		});
		textFieldEmail.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldEmail.setBounds(152, 186, 330, 30);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setFocusable(true);
			}
		});
		passwordField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		passwordField.setBounds(152, 259, 330, 30);
		frame.getContentPane().add(passwordField);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 19));
		lblEmail.setBounds(66, 194, 64, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 19));
		lblSenha.setBounds(66, 267, 64, 14);
		frame.getContentPane().add(lblSenha);
		
		JButton botaoEntrar = new JButton("ENTRAR");
		botaoEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Map<String, String> logins = ProfessorDAO.listaLogins();
				String senha;
				if(passwordField.isEditable()) {
					senha = passwordField.getText().strip();
				} else {
					senha = textFieldSenha.getText().strip();
				}
				try {
					if(logins.containsKey(textFieldEmail.getText().strip()) & 
							logins.get(textFieldEmail.getText().strip()).equals(senha)) {
						List<Professor> professores = ProfessorDAO.listar();
						for (Professor professor : professores) {
							if(professor.getEmail().equals(textFieldEmail.getText().strip())) {
								PROFESSOR_LOGADO = professor;
							}
						}
						frame.setVisible(false);
						ViewInicial.main(null);
					} else {
						JOptionPane.showMessageDialog(null, "Verifique se as informa????es fornecidas est??o corretas", 
								"Falha no login", JOptionPane.WARNING_MESSAGE);
					}
				} catch(NullPointerException exc) {
					JOptionPane.showMessageDialog(null, "Verifique se as informa????es fornecidas est??o corretas", 
							"Falha no login", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		botaoEntrar.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		botaoEntrar.setBounds(254, 343, 125, 30);
		frame.getContentPane().add(botaoEntrar);
		
		JLabel lblSemCadastro = new JLabel("N??o est?? cadastrado?");
		lblSemCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewCadastroProfessor.main(null);
				frame.setVisible(false);
			}
		});
		lblSemCadastro.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(lblSemCadastro.getForeground() == Color.BLACK) {
					lblSemCadastro.setForeground(Color.BLUE);
				} else {
					lblSemCadastro.setForeground(Color.BLACK);
				}
			}
		});
		lblSemCadastro.setForeground(Color.BLACK);
		lblSemCadastro.setFont(new Font("Arial", Font.PLAIN, 15));
		lblSemCadastro.setBounds(245, 390, 143, 14);
		frame.getContentPane().add(lblSemCadastro);
		
		JCheckBox chckbxMostrarSenha = new JCheckBox("Mostrar senha");chckbxMostrarSenha.addActionListener(new ActionListener() {
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
		chckbxMostrarSenha.setBounds(506, 265, 111, 23);
		frame.getContentPane().add(chckbxMostrarSenha);
		
		textFieldSenha = new JTextField();
		textFieldSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldSenha.setFocusable(true);
			}
		});
		textFieldSenha.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldSenha.setColumns(10);
		textFieldSenha.setBounds(152, 259, 330, 30);
		frame.getContentPane().add(textFieldSenha);
	}
}
