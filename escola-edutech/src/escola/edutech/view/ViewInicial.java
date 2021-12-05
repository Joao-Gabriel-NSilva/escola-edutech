package escola.edutech.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewInicial {

	private JFrame frmMenuInicial;

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
					ViewInicial window = new ViewInicial();
					window.frmMenuInicial.setVisible(true);
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
		frmMenuInicial = new JFrame();
		frmMenuInicial.setTitle("Menu Inicial");
		frmMenuInicial.getContentPane().setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		frmMenuInicial.setBounds(100, 100, 650, 500);
		frmMenuInicial.setLocation(600, 200);
		frmMenuInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuInicial.setResizable(false);
		frmMenuInicial.getContentPane().setLayout(null);
		
		JButton btnCadastrarAluno = new JButton("CADASTRAR ALUNO");
		btnCadastrarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenuInicial.setVisible(false);
				ViewCadastroAluno.main(null);
			}
		});
		btnCadastrarAluno.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		btnCadastrarAluno.setBounds(197, 114, 249, 57);
		frmMenuInicial.getContentPane().add(btnCadastrarAluno);
		
		JButton btnConsultarCadastro = new JButton("CONSULTAR CADASTRO");
		btnConsultarCadastro.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		btnConsultarCadastro.setBounds(197, 219, 249, 57);
		frmMenuInicial.getContentPane().add(btnConsultarCadastro);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenuInicial.setVisible(false);
				ViewLogin.main(null);
			}
		});
		btnLogout.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		btnLogout.setBounds(0, 0, 77, 27);
		frmMenuInicial.getContentPane().add(btnLogout);
		
		JButton btnAdicionarTurma = new JButton("ADICIONAR TURMA");
		btnAdicionarTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenuInicial.setVisible(false);
				ViewAdicionarTurma.main(null);
			}
		});
		btnAdicionarTurma.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		btnAdicionarTurma.setBounds(197, 320, 249, 57);
		frmMenuInicial.getContentPane().add(btnAdicionarTurma);
	}
}
