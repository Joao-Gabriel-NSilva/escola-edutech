package escola.edutech.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ViewInicial {

	private JFrame frame;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewInicial window = new ViewInicial();
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
	public ViewInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBemVindo = new JLabel("BEM VINDO");
		lblBemVindo.setBounds(0, 0, 634, 41);
		lblBemVindo.setFont(new Font("Arial Narrow", Font.PLAIN, 35));
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblBemVindo);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldEmail.setBounds(152, 186, 330, 30);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		passwordField = new JPasswordField();
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
		botaoEntrar.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		botaoEntrar.setBounds(254, 343, 125, 30);
		frame.getContentPane().add(botaoEntrar);
	}
}
