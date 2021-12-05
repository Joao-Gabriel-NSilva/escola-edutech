package escola.edutech.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

import escola.edutech.dao.ProfessorDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ViewAdicionarTurma {

	private JFrame frame;
	private JTextField textFieldCodigo;
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
					ViewAdicionarTurma window = new ViewAdicionarTurma();
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
	public ViewAdicionarTurma() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Adicionar turma");
		frame.getContentPane().setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		frame.setBounds(100, 100, 650, 279);
		frame.setLocation(650, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		textFieldCodigo.setColumns(10);
		textFieldCodigo.setBounds(226, 74, 330, 30);
		frame.getContentPane().add(textFieldCodigo);
		
		JLabel lblCodigoDaTurma = new JLabel("Código da turma:");
		lblCodigoDaTurma.setFont(new Font("Arial", Font.PLAIN, 19));
		lblCodigoDaTurma.setBounds(57, 77, 151, 23);
		frame.getContentPane().add(lblCodigoDaTurma);
		
		JButton botaoAdicionar = new JButton("ADICIONAR");
		botaoAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProfessorDAO.adicionaTurma(ViewLogin.PROFESSOR_LOGADO, textFieldCodigo.getText().strip());
					JOptionPane.showMessageDialog(null, "A turma '" + textFieldCodigo.getText().strip() + 
							"' foi adicionada!", "", JOptionPane.INFORMATION_MESSAGE);
				} catch (RuntimeException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		botaoAdicionar.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		botaoAdicionar.setBounds(239, 153, 157, 30);
		frame.getContentPane().add(botaoAdicionar);
		
		btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ViewInicial.main(null);
			}
		});
		btnVoltar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnVoltar.setFont(new Font("Arial Narrow", Font.PLAIN, 13));
		btnVoltar.setBounds(0, 0, 77, 30);
		frame.getContentPane().add(btnVoltar);
	}
}
