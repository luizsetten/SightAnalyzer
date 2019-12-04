package controller;

import java.io.File;

public class InicialController {

	
	private static final Exception Exception = null;

	public static boolean setDados(String endereco, Float potencia, Float aConector, int numConector, Float aCabo, Float hTorre1, Float gAntena1, Float hTorre2, Float gTorre2) throws java.lang.Exception{
		try {
			File arq = new File(endereco);
			
			return true;
			
		} catch(Exception e) {
			throw Exception;
			
		}
		
		
	}
}
