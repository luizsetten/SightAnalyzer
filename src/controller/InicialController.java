package controller;

import java.io.File;

public class InicialController {
	private String endereco;
	private Double potencia, aConector, aCabo;
	private Double hTorre1, gAntena1, hTorre2, gAntena2, sensibilidade;
	private int frequencia;
	
	private  final Exception FileException = null;

	public boolean setDados(String endereco, Double potencia, Double aConector, int frequencia, Double aCabo, Double hTorre1, Double gAntena1, Double hTorre2, Double gAntena2, Double sensibilidade) throws java.lang.Exception{
		try {
			File arq = new File(endereco);
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
	
	public Double atenuacaoLivre() {
		
		
		return 6.5;
	}
	
	public Double atenuacaoObstrucao() {
		
		return 10.2;
	}
	
	public Double calcPrx() {
		return calcPeirp()-calcaCaboTotal2()-atenuacaoLivre()-atenuacaoObstrucao();
	}
	
	public Double calcMargem() {
		
		return calcPrx()-sensibilidade;
	}
}
