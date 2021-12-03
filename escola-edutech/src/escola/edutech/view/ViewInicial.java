package escola.edutech.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewInicial {

	private JFrame frame;

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
		frame.setLocation(600, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JButton btnCadastrarAluno = new JButton("CADASTRAR ALUNO");
		btnCadastrarAluno.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		btnCadastrarAluno.setBounds(197, 150, 249, 57);
		frame.getContentPane().add(btnCadastrarAluno);
		
		JButton btnConsultarCadastro = new JButton("CONSULTAR CADASTRO");
		btnConsultarCadastro.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		btnConsultarCadastro.setBounds(197, 255, 249, 57);
		frame.getContentPane().add(btnConsultarCadastro);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ViewLogin.main(null);
			}
		});
		btnLogout.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		btnLogout.setBounds(0, 0, 77, 27);
		frame.getContentPane().add(btnLogout);
	}
}
