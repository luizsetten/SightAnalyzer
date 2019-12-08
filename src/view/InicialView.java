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
import java.io.FileNotFoundException;

import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;

public class InicialView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPotenciaDoAparelho;
	private JTextField textFieldAntenuacaoConector;
	private JTextField textFieldFrequencia;
	private JTextField textFieldAtenuacaoCabo;
	private JTextField textFieldAlturaTorre1;
	private JTextField textFieldGanhoAntena1;
	private JTextField textFieldAlturaTorre2;
	private JTextField textFieldGanhoAntena2;
	private JTextField txtSelecioneUmaBase;
	private JTextField textFieldSensibilidade;
	private JTextField textFieldPeirp;
	private JTextField textFieldLOS;
	private JTextField textFieldPtx;
	private JTextField textFieldPrx;
	private JTextField textFieldMargem;
	private InicialController InicialController = new InicialController();

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

	public InicialView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo( null );
		
		JLabel lblPotnciaDoAparelho = new JLabel("Pot\u00EAncia do aparelho [W] :");
		lblPotnciaDoAparelho.setBounds(10, 70, 167, 14);
		contentPane.add(lblPotnciaDoAparelho);
		
		JLabel lblAtenuaoDosConectorres = new JLabel("Atenua\u00E7\u00E3o do conector [dB] :");
		lblAtenuaoDosConectorres.setBounds(10, 95, 167, 14);
		contentPane.add(lblAtenuaoDosConectorres);
		
		JLabel lblFrequencia = new JLabel("Frequ\u00EAncia [Mhz] :");
		lblFrequencia.setBounds(10, 120, 155, 14);
		contentPane.add(lblFrequencia);
		
		JLabel lblAtenuaoDoCabo = new JLabel("Atenua\u00E7\u00E3o do cabo [dB/m] : ");
		lblAtenuaoDoCabo.setBounds(10, 145, 167, 14);
		contentPane.add(lblAtenuaoDoCabo);
		
		JLabel lblAlturaDaTorre = new JLabel("Altura da torre 1 [m]:");
		lblAlturaDaTorre.setBounds(317, 70, 155, 14);
		contentPane.add(lblAlturaDaTorre);
		
		JLabel lblGanhoDaAntena = new JLabel("Ganho da antena 1 [dBi] :");
		lblGanhoDaAntena.setBounds(317, 97, 155, 14);
		contentPane.add(lblGanhoDaAntena);
		
		JLabel lblAlturaDaTorre_1 = new JLabel("Altura da torre 2 [m] :");
		lblAlturaDaTorre_1.setBounds(317, 123, 155, 14);
		contentPane.add(lblAlturaDaTorre_1);
		
		JLabel lblGanhoDaAntena_1 = new JLabel("Ganho da antena 2 [dBi] :");
		lblGanhoDaAntena_1.setBounds(317, 148, 155, 14);
		contentPane.add(lblGanhoDaAntena_1);
		
		textFieldPotenciaDoAparelho = new JTextField();
		
		textFieldPotenciaDoAparelho.setBounds(187, 67, 86, 20);
		contentPane.add(textFieldPotenciaDoAparelho);
		textFieldPotenciaDoAparelho.setColumns(10);
		
		textFieldAntenuacaoConector = new JTextField();
		textFieldAntenuacaoConector.setBounds(187, 92, 86, 20);
		contentPane.add(textFieldAntenuacaoConector);
		textFieldAntenuacaoConector.setColumns(10);
		
		textFieldFrequencia = new JTextField();
		textFieldFrequencia.setBounds(187, 117, 86, 20);
		contentPane.add(textFieldFrequencia);
		textFieldFrequencia.setColumns(10);
		
		textFieldAtenuacaoCabo = new JTextField();
		textFieldAtenuacaoCabo.setBounds(187, 142, 86, 20);
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
		textFieldAlturaTorre2.setBounds(482, 120, 86, 20);
		contentPane.add(textFieldAlturaTorre2);
		textFieldAlturaTorre2.setColumns(10);
		
		textFieldGanhoAntena2 = new JTextField();
		textFieldGanhoAntena2.setBounds(482, 145, 86, 20);
		contentPane.add(textFieldGanhoAntena2);
		textFieldGanhoAntena2.setColumns(10);
		
		JLabel lblVerifiqueOsDados = new JLabel("");
		lblVerifiqueOsDados.setForeground(Color.RED);
		lblVerifiqueOsDados.setBounds(228, 228, 111, 14);
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
		
		btnAbrir.setBounds(482, 11, 86, 23);
		contentPane.add(btnAbrir);
		
		txtSelecioneUmaBase = new JTextField();
		txtSelecioneUmaBase.setBounds(107, 12, 365, 20);
		contentPane.add(txtSelecioneUmaBase);
		txtSelecioneUmaBase.setColumns(10);
		
		JLabel lblBaseDeDados = new JLabel("Base de dados:");
		lblBaseDeDados.setBounds(10, 15, 111, 14);
		contentPane.add(lblBaseDeDados);
		
		
		JLabel lblSensibilidadeMnimadb = new JLabel("Sensibilidade m\u00EDnima [dBm] :");
		lblSensibilidadeMnimadb.setBounds(10, 170, 167, 14);
		contentPane.add(lblSensibilidadeMnimadb);
		
		textFieldSensibilidade = new JTextField();
		textFieldSensibilidade.setBounds(187, 167, 86, 20);
		contentPane.add(textFieldSensibilidade);
		textFieldSensibilidade.setColumns(10);
		
		JLabel lblPeirpdbm = new JLabel("Peirp [dBm] :");
		lblPeirpdbm.setBounds(10, 303, 111, 14);
		contentPane.add(lblPeirpdbm);
		
		JLabel lblApresentaLineOf = new JLabel("Apresenta Line of Sight:");
		lblApresentaLineOf.setBounds(343, 276, 144, 14);
		contentPane.add(lblApresentaLineOf);
		
		JLabel lblPtxdbm = new JLabel("Ptx[dBm] :");
		lblPtxdbm.setBounds(10, 276, 66, 14);
		contentPane.add(lblPtxdbm);
		
		JLabel lblPrxdbm = new JLabel("Prx[dBm] :");
		lblPrxdbm.setBounds(10, 331, 66, 14);
		contentPane.add(lblPrxdbm);
		
		JLabel lblMargemdb = new JLabel("Margem [dBm] :");
		lblMargemdb.setBounds(343, 303, 94, 14);
		contentPane.add(lblMargemdb);
		
		textFieldPeirp = new JTextField();
		textFieldPeirp.setEditable(false);
		textFieldPeirp.setBounds(86, 300, 144, 20);
		contentPane.add(textFieldPeirp);
		textFieldPeirp.setColumns(10);
		
		textFieldLOS = new JTextField();
		textFieldLOS.setText("");
		textFieldLOS.setEditable(false);
		textFieldLOS.setBounds(519, 273, 49, 20);
		contentPane.add(textFieldLOS);
		textFieldLOS.setColumns(10);
		
		textFieldPtx = new JTextField();
		textFieldPtx.setEditable(false);
		textFieldPtx.setBounds(86, 272, 144, 20);
		contentPane.add(textFieldPtx);
		textFieldPtx.setColumns(10);
		
		textFieldPrx = new JTextField();
		textFieldPrx.setEditable(false);
		textFieldPrx.setBounds(86, 328, 144, 20);
		contentPane.add(textFieldPrx);
		textFieldPrx.setColumns(10);
		
		textFieldMargem = new JTextField();
		textFieldMargem.setEditable(false);
		textFieldMargem.setBounds(437, 300, 131, 20);
		contentPane.add(textFieldMargem);
		textFieldMargem.setColumns(10);
		
		JButton btnGrfico = new JButton("Gr\u00E1fico");
		btnGrfico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InicialController.geraGrafico();	
			}
		});
		btnGrfico.setBounds(482, 327, 86, 23);
		contentPane.add(btnGrfico);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 253, 558, 2);
		contentPane.add(separator);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
				InicialController.setDados(//(String endereco, Float potencia, Float aConector, int frequencia, Float aCabo, Float hTorre1, Float gAntena1, Float hTorre2, Float gTorre2)
						txtSelecioneUmaBase.getText(), 
						Double.valueOf(textFieldPotenciaDoAparelho.getText()), 
						Double.valueOf(textFieldAntenuacaoConector.getText()),
						Integer.valueOf(textFieldFrequencia.getText()),
						Double.valueOf(textFieldAtenuacaoCabo.getText()),
						Double.valueOf(textFieldAlturaTorre1.getText()),
						Double.valueOf(textFieldGanhoAntena1.getText()),
						Double.valueOf(textFieldAlturaTorre2.getText()),
						Double.valueOf(textFieldGanhoAntena2.getText()),
						Double.valueOf(textFieldSensibilidade.getText())
						); 
				lblVerifiqueOsDados.setText("");
				textFieldPtx.setText(String.valueOf(InicialController.calcPtx()));
				textFieldPeirp.setText(String.valueOf(InicialController.calcPeirp()));
				textFieldPrx.setText(String.valueOf(InicialController.calcPrx()));
				textFieldMargem.setText(String.valueOf(InicialController.calcMargem()));
				if(InicialController.getPercentual() < 0.4) {
					textFieldLOS.setText("Sim");
				} else {
					textFieldLOS.setText("Não");
				}
				} catch (Exception e1) {
					lblVerifiqueOsDados.setText("Verifique os dados!");
				}
			}
		});
		btnCalcular.setBounds(242, 201, 89, 23);
		contentPane.add(btnCalcular);
	
		
		
	}
}
