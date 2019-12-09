package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import view.GraficoView;

public class InicialModel {
	private String endereco;
	private Double potencia, aConector, aCabo;
	private Double hTorre1, gAntena1, hTorre2, gAntena2, sensibilidade;
	private int frequencia;
	
	private float percentualobstruido =0;
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
			this.percentualobstruido = 0;
			return true;			
		} catch(Exception e) {
			throw FileException;		
		}			
	}
	
	public Double calcHipotenusa() {

		Double hip = Math.hypot(dados.get(dados.size()-1).distancia, (((dados.get(0).hRelevo + hTorre1) - (dados.get(dados.size()-1).hRelevo + hTorre2))/1000));	
		return hip;
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
		this.percentualobstruido = 0;
		Double atenLivre = 32.5 +(20 * Math.log10(calcHipotenusa()))+ (20* Math.log10(frequencia));
		return atenLivre;
	}
	
	public Double gObstrucao() {
				float colisao = 0;
				int flag = 0;
				Float[] e = {(float) 0.0,(float) 0.0,(float) 0.0,(float) 0.0,(float) 0.0};
				for(PosicaoModel p: this.dados) {
					float raio = valorDaZona(p.distancia);
					float los = lineOfSight(p.distancia);

					if(((los-raio) - p.hRelevo) < colisao) {
						flag = 1;
						colisao = ((los-raio) - p.hRelevo);
						e[0] = p.distancia; 
						e[1] = p.hRelevo;
						e[2] = los;						
						e[4] = raio;

					}
				} //roda todo o grafico e encontra o pior ponto
				
				if(flag == 0) {
					
					return 0.0;
				}
				
				double h = (double) ((e[2]) - e[1]);
				
				double d1 = (double) e[0];
				this.percentualobstruido = Math.abs(((e[1] - (e[2]-e[4]) )/(2*e[4])));
				//System.out.println(this.percentualobstruido);
				//System.out.println("distancia: " + e[0] + " hrelevo: " + e[1]);
				double d2 = (double)(dados.get(dados.size()-1).distancia - e[0]);
				e[0] = (float) 0; 
				e[1] = (float) 0;
				e[2] = (float) 0;						
				e[4] = (float) 0;
				double lamb = (double) comprimentoOnda();	
				double a = ((2*(d1 + d2))/(lamb*(d1*d2)));
				double v =  (h*Math.sqrt(a));
				
				if(v < -1) {
					return 0.0;
				} else if(v <  0.0) {
					Double aObstr = (20*Math.log10(0.5-(0.62*v))-30);
					return aObstr;
					
				} else if(v < 1) {
					Double aObstr = (20*Math.log10(0.5*(Math.exp(-0.95*v)))-30);
					return aObstr;					
					
				} else if(v < 2.4) {
					Double aObstr = (20*Math.log10(0.4-(Math.sqrt( 0.1184 - (Math.pow((0.38-(0.1*v)),2) ))))-30);
					return aObstr;
					
				} else {
					Double aObstr = (20*Math.log10((0.225/v))-30);
					return aObstr;					
					
				}
		
		
	}
	
	public Double calcPrx() {
		
		obtemRelevo();
		
		return calcPeirp()+gAntena1+gAntena2-calcaCaboTotal2()-atenuacaoLivre()+gObstrucao();
	}
	
	public Double calcMargem() {
		
		return calcPrx()-sensibilidade;
	}
	
	public void obtemRelevo() {
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
		    	System.out.print("Erro No Arquivo");
		    }
		    StringTokenizer tk = new StringTokenizer(contentBuilder.toString());
		    try {
		    	while(tk.hasMoreTokens()) {
		    		PosicaoModel pm = new PosicaoModel();	
		    		Float distancia = Float.valueOf(tk.nextToken(";"));
		    		pm.distancia = distancia;
		    		Float altura = Float.valueOf(tk.nextToken(";")); 
		    		pm.hRelevo = altura;
		    		dados.add(pm);
		    }
		    	} catch(Exception e1) {
		    }
		    
}
	
	public float getPercentual() {
		//gObstrucao();
		return this.percentualobstruido;
	}
	
	public float comprimentoOnda() {
		return (299792458/this.frequencia); 
	}
	
	public float lineOfSight(Float distancia) {
		
		float hRelevo1 = dados.get(0).hRelevo;
		float hTorre1 = this.hTorre1.floatValue();
		float h1 = hRelevo1 + hTorre1;
		
		float hRelevo2 = dados.get(dados.size()-1).hRelevo;
		float hTorre2 = this.hTorre2.floatValue();
		float h2 = hRelevo2 + hTorre2;
		
		float percurso = dados.get(dados.size()-1).distancia; //distancia horizontal
		float desnivel = ((h2-h1)/percurso);
		
		return ((desnivel*distancia)+h1);
	}
		
	public float valorDaZona(Float distancia) {
		float distanciafinal = dados.get(dados.size()-1).distancia; //ultima posição normalmente o tamanho do plano
		float d1 = distancia;
		float d2 = distanciafinal - d1;
		float raio1 = ((d1*d2)/(this.frequencia*distanciafinal));
		float raio = 547 * (float) Math.sqrt(raio1);	
		//System.out.println(raio);
		//raio = (float) (raio*(((dados.get(0).hRelevo + hTorre1) - (dados.get(dados.size()-1).hRelevo + hTorre2))/(1000*calcHipotenusa()))); // raio ajustado a declinio
		
		raio = (float) (raio * (distanciafinal/(calcHipotenusa())));
		//System.out.println(raio);
		return (Math.abs(raio));
	}

	public void geraGrafico() {
		ArrayList<Float[]> dados = new ArrayList<Float[]>();
		
		for(PosicaoModel p: this.dados) {
			float raio = valorDaZona(p.distancia);
			float los = lineOfSight(p.distancia);
			Float[] e = {(float) p.distancia,(float) p.hRelevo,(float) los,(float) los+raio,(float) los-raio};
			dados.add(e);
		}

		
		
		GraficoView grafico = new GraficoView("Gráfico", "", dados);
		grafico.pack();
		grafico.setVisible(true);
	}
}
