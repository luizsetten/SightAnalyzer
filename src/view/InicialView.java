package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InicialController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InicialView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPotenciaDoAparelho;
	private JTextField textFieldAntenuacaoConector;
	private JTextField textFieldNumeroConectores;
	private JTextField textFieldAtenuacaoCabo;
	private JTextField textFieldAlturaTorre1;
	private JTextField textFieldGanhoAntena1;
	private JTextField textFieldAlturaTorre2;
	private JTextField textFieldGanhoAntena2;
	private JTextField txtSelecioneUmaBase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicialView frame = new InicialView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicialView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPotnciaDoAparelho = new JLabel("Pot\u00EAncia do aparelho [W] :");
		lblPotnciaDoAparelho.setBounds(10, 70, 167, 14);
		contentPane.add(lblPotnciaDoAparelho);
		
		JLabel lblAtenuaoDosConectorres = new JLabel("Atenua\u00E7\u00E3o do conector [dB] :");
		lblAtenuaoDosConectorres.setBounds(10, 95, 167, 14);
		contentPane.add(lblAtenuaoDosConectorres);
		
		JLabel lblNmeroDeConectores = new JLabel("N\u00FAmero de conectores:");
		lblNmeroDeConectores.setBounds(10, 120, 155, 14);
		contentPane.add(lblNmeroDeConectores);
		
		JLabel lblAtenuaoDoCabo = new JLabel("Atenua\u00E7\u00E3o do cabo [dB/m] : ");
		lblAtenuaoDoCabo.setBounds(10, 145, 167, 14);
		contentPane.add(lblAtenuaoDoCabo);
		
		JLabel lblAlturaDaTorre = new JLabel("Altura da torre 1 [m]:");
		lblAlturaDaTorre.setBounds(317, 70, 155, 14);
		contentPane.add(lblAlturaDaTorre);
		
		JLabel lblGanhoDaAntena = new JLabel("Ganho da antena 1 [dB] :");
		lblGanhoDaAntena.setBounds(317, 97, 155, 14);
		contentPane.add(lblGanhoDaAntena);
		
		JLabel lblAlturaDaTorre_1 = new JLabel("Altura da torre 2 [m] :");
		lblAlturaDaTorre_1.setBounds(317, 170, 155, 14);
		contentPane.add(lblAlturaDaTorre_1);
		
		JLabel lblGanhoDaAntena_1 = new JLabel("Ganho da antena 2 [dB] :");
		lblGanhoDaAntena_1.setBounds(317, 195, 155, 14);
		contentPane.add(lblGanhoDaAntena_1);
		
		textFieldPotenciaDoAparelho = new JTextField();
		
		textFieldPotenciaDoAparelho.setBounds(175, 67, 86, 20);
		contentPane.add(textFieldPotenciaDoAparelho);
		textFieldPotenciaDoAparelho.setColumns(10);
		
		
		
		textFieldAntenuacaoConector = new JTextField();
		textFieldAntenuacaoConector.setBounds(175, 92, 86, 20);
		contentPane.add(textFieldAntenuacaoConector);
		textFieldAntenuacaoConector.setColumns(10);
		
		textFieldNumeroConectores = new JTextField();
		textFieldNumeroConectores.setBounds(175, 117, 86, 20);
		contentPane.add(textFieldNumeroConectores);
		textFieldNumeroConectores.setColumns(10);
		
		textFieldAtenuacaoCabo = new JTextField();
		textFieldAtenuacaoCabo.setBounds(175, 142, 86, 20);
		contentPane.add(textFieldAtenuacaoCabo);
		textFieldAtenuacaoCabo.setColumns(10);
		
		textFieldAlturaTorre1 = new JTextField();
		textFieldAlturaTorre1.setBounds(482, 67, 86, 20);
		contentPane.add(textFieldAlturaTorre1);
		textFieldAlturaTorre1.setColumns(10);
		
		textFieldGanhoAntena1 = new JTextField();
		textFieldGanhoAntena1.setBounds(482, 94, 86, 20);
		contentPane.add(textFieldGanhoAntena1);
		textFieldGanhoAntena1.setColumns(10);
		
		textFieldAlturaTorre2 = new JTextField();
		textFieldAlturaTorre2.setBounds(482, 167, 86, 20);
		contentPane.add(textFieldAlturaTorre2);
		textFieldAlturaTorre2.setColumns(10);
		
		textFieldGanhoAntena2 = new JTextField();
		textFieldGanhoAntena2.setBounds(482, 192, 86, 20);
		contentPane.add(textFieldGanhoAntena2);
		textFieldGanhoAntena2.setColumns(10);
		
		JLabel lblVerifiqueOsDados = new JLabel("");
		lblVerifiqueOsDados.setForeground(Color.RED);
		lblVerifiqueOsDados.setBounds(242, 286, 111, 14);
		contentPane.add(lblVerifiqueOsDados);

		
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JFileChooser filechooser = new JFileChooser();
				filechooser.setDialogTitle("Selecione um banco de dados");
				filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int i = filechooser.showOpenDialog(null);
				
				if(i == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					txtSelecioneUmaBase.setText(file.getPath());
				}
				
			}
		});
		
		btnAbrir.setBounds(502, 11, 66, 23);
		contentPane.add(btnAbrir);
		
		txtSelecioneUmaBase = new JTextField();
		//txtSelecioneUmaBase.setText("Selecione uma base de dados");
		txtSelecioneUmaBase.setBounds(131, 12, 359, 20);
		contentPane.add(txtSelecioneUmaBase);
		txtSelecioneUmaBase.setColumns(10);
		
		JLabel lblBaseDeDados = new JLabel("Base de dados:");
		lblBaseDeDados.setBounds(10, 15, 111, 14);
		contentPane.add(lblBaseDeDados);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				
				boolean frame = InicialController.setDados(
						txtSelecioneUmaBase.getText(), 
						Float.valueOf(textFieldPotenciaDoAparelho.getText()), 
						Float.valueOf(textFieldAntenuacaoConector.getText()),
						Integer.valueOf(textFieldNumeroConectores.getText()),
						Float.valueOf(textFieldAtenuacaoCabo.getText()),
						Float.valueOf(textFieldAlturaTorre1.getText()),
						Float.valueOf(textFieldGanhoAntena1.getText()),
						Float.valueOf(textFieldAlturaTorre2.getText()),
						Float.valueOf(textFieldGanhoAntena2.getText())
						
						); //(String endereco, Float potencia, Float aConector, int numConector, Float aCabo, Float hTorre1, Float gAntena1, Float hTorre2, Float gTorre2)
				lblVerifiqueOsDados.setText("Dados verificados!");
				} catch (Exception e1) {
					lblVerifiqueOsDados.setText("Verifique os dados!");
				}
			}
		});
		btnCalcular.setBounds(251, 252, 89, 23);
		contentPane.add(btnCalcular);
		

		
	
		
		
	}
}
