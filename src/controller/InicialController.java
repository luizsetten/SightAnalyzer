package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import view.GraficoView;

public class InicialController {
	private String endereco;
	private Double potencia, aConector, aCabo;
	private Double hTorre1, gAntena1, hTorre2, gAntena2, sensibilidade;
	private int frequencia;
	
	private  final Exception FileException = null;
	private Float es[];
	

	public boolean setDados(String endereco, Double potencia, Double aConector, int frequencia, Double aCabo, Double hTorre1, Double gAntena1, Double hTorre2, Double gAntena2, Double sensibilidade) throws java.lang.Exception{
		try {

			this.endereco = endereco;
			this.potencia = potencia;
			this.aConector = aConector;
			this.frequencia = frequencia;
			this.aCabo = aCabo;
			this.hTorre1 = hTorre1;
			this.gAntena1 = gAntena1;
			this.hTorre2 = hTorre2;
			this.gAntena2 = gAntena2;
			this.sensibilidade = sensibilidade;
			
			return true;			
		} catch(Exception e) {
			throw FileException;		
		}			
	}
	


	
	public Double calcPtx() {
		return 10*Math.log10(potencia*1000);
	}
	
	public Double calcaCaboTotal1() {
		Double aTotal = (double) (2*aConector+(aCabo*hTorre1));
		return aTotal;
	}
	
	public Double calcaCaboTotal2() {
		Double aTotal = (double) (2*aConector+(aCabo*hTorre2));
		return aTotal;
	}
	
	public Double calcPeirp() {
		return calcPtx()-calcaCaboTotal1();
	}
	
	public Double atenuacaoLivre() throws FileNotFoundException {
		obtemRelevo();
		
		return 6.5;
	}
	
	public Double atenuacaoObstrucao() {
		
		return 10.2;
	}
	
	public Double calcPrx() throws FileNotFoundException {
		obtemRelevo();
		return calcPeirp()-calcaCaboTotal2()-atenuacaoLivre()-atenuacaoObstrucao();
	}
	
	public Double calcMargem() throws FileNotFoundException {
		
		return calcPrx()-sensibilidade;
	}
	
	public void obtemRelevo() throws FileNotFoundException {
		    StringBuilder contentBuilder = new StringBuilder();
		    ArrayList<Float[]> dados = new ArrayList<Float[]>();
		    try (BufferedReader br = new BufferedReader(new FileReader(endereco))){
		        String sCurrentLine;
		        while ((sCurrentLine = br.readLine()) != null) 
		        {
		            contentBuilder.append(sCurrentLine).append("\n");
		        }
		    } 
		    catch (IOException e) 
		    {
		        e.printStackTrace();
		    }
		    StringTokenizer tk = new StringTokenizer(contentBuilder.toString());
		    while(tk.hasMoreTokens()) {
		    	es = null;
		    	Float distancia = Float.valueOf(tk.nextToken(";"));
		    	Float altura = Float.valueOf(tk.nextToken("\n")); 
		    	es[0] = distancia;
		    	es[1] = altura;
		    	dados.add(es);
		    }
		}
		

		
		


	
	public void geraGrafico() {
		ArrayList<Float[]> dados = new ArrayList<Float[]>();
		
		
		
		
		Float[] e = {(float) 12.4,(float) 12.5,(float) 12.6,(float) 12.7,(float) 12.8};
		Float[] e1 = {(float) 12.0,(float) 12.5,(float) 12.6,(float) 12.7,(float) 12.8};
		Float[] e2 = {(float) 11.5,(float) 12.5,(float) 12.6,(float) 12.7,(float) 12.8};
		dados.add(e);
		dados.add(e1);
		dados.add(e2);
		
		
		GraficoView grafico = new GraficoView("Gráfico", "", dados);
		grafico.pack();
		grafico.setVisible(true);
	}
}
