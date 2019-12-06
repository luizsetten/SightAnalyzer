package view;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;


public class GraficoView extends JFrame {


	 
	
	public GraficoView(String tituloJanela, String tituloGrafico, ArrayList<Float[]> dados) {
		super(tituloJanela);
	      JFreeChart lineChart = ChartFactory.createLineChart(
	    	         tituloGrafico,
	    	         "Distância [km]","Altura [m]",
	    	         createDataset(dados),
	    	         PlotOrientation.VERTICAL,
	    	         true,true,false);
	    	         
	    	      ChartPanel chartPanel = new ChartPanel( lineChart );
	    	      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	    	      setContentPane( chartPanel );
	    	   }

	    	   private DefaultCategoryDataset createDataset(ArrayList<Float[]> dados ) {
	    	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	    	      
	    	      for(Float[] insere: dados) {
	    	    	  String dist = String.valueOf(insere[0]);
	    	    	  dataset.addValue( insere[1] , "Relevo" ,  dist);
	    	    	  //System.out.print(insere[1]);
	    	    	  dataset.addValue( insere[2]  , "Line Of Sight" , dist );
	    	    	  dataset.addValue( insere[3] , "Zona de Fresnel(superior)" , dist );
	    	    	  dataset.addValue( insere[4] , "Zona de Fresnel(inferior)" , dist );
	    	      }
	    	      
	    	      
	    	      return dataset;
	    	   }		
	}
