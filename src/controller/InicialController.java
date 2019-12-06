package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.PosicaoModel;
import view.GraficoView;

public class InicialController {
	private String endereco;
	private Double potencia, aConector, aCabo;
	private Double hTorre1, gAntena1, hTorre2, gAntena2, sensibilidade;
	private int frequencia;
	
	private  final Exception FileException = null;
	public ArrayList<PosicaoModel> dados = new ArrayList<PosicaoModel>(); 
	

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
		//obtemRelevo();
		return calcPeirp()-calcaCaboTotal2()-atenuacaoLivre()-atenuacaoObstrucao();
	}
	
	public Double calcMargem() throws FileNotFoundException {
		
		return calcPrx()-sensibilidade;
	}
	
	public void obtemRelevo() throws FileNotFoundException {
		    StringBuilder contentBuilder = new StringBuilder();
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
		    	PosicaoModel pm = new PosicaoModel();
		    	
		    	Float distancia = Float.valueOf(tk.nextToken(";"));
		    	Float altura = Float.valueOf(tk.nextToken(";")); 
		    	pm.distancia = distancia;
		    	pm.hRelevo = altura;
		    	dados.add(pm);
		    }
}
	public float comprimentoOnda() {
		return 299792458/this.frequencia; 
	}
	
	public float lineOfSight(Float distancia) {
		
		float hRelevo1 = dados.get(0).hRelevo;
		float hTorre1 = this.hTorre1.floatValue();
		float h1 = hRelevo1 + hTorre1;
		
		float hRelevo2 = dados.get(dados.size()-1).hRelevo;
		float hTorre2 = this.hTorre2.floatValue();
		float h2 = hRelevo2 + hTorre2;
		
		float percurso = dados.get(dados.size()-1).distancia;
		float desnivel = ((h2-h1)/percurso);
		
		return ((desnivel*distancia)+h1);
	}
	
	public float valorDaZona(Float distancia) {
		float distanciafinal = dados.get(dados.size()-1).distancia; //ultima posição ormalmente o tamanho do plano
		float d1 = distancia;
		float d2 = distanciafinal - d1;
		float raio1 = ((d1*d2)/(this.frequencia*distanciafinal));
		float raio = 547 * (float) Math.sqrt(raio1);
		
// cosseno --->>>>>>>    *(((lineOfSight(distanciafinal)-dados.get(0).hRelevo)/distanciafinal)/1000) 
		return (raio );
	}
		
		


	
	public void geraGrafico() {
		ArrayList<Float[]> dados = new ArrayList<Float[]>();
		
		for(PosicaoModel p: this.dados) {
			float raio = valorDaZona(p.distancia);
			float los = lineOfSight(p.distancia);
			//System.out.println(raio);
			Float[] e = {(float) p.distancia,(float) p.hRelevo,(float) los,(float) los+raio,(float) los-raio};
			dados.add(e);
		}

		
		
		GraficoView grafico = new GraficoView("Gráfico", "", dados);
		grafico.pack();
		grafico.setVisible(true);
	}
}
