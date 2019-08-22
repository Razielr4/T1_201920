package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import model.data_structures.LinkedList;
/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private LinkedList<String[]> archivo1;
	private LinkedList<String[]> archivo2;
	private int[] totalViajesMes;
	
	
	/**
	 * Constructor del modelo del mingundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		totalViajesMes = new int[6];
		for(int i=0; i<6;i++){
			totalViajesMes[i] = 0;
		}
	}
	
	public int[] darTotalViajesMes(){
		return totalViajesMes;
	}
	
public void carga() throws IOException{
		
		
		CSVReader reader1 = null;
		CSVReader reader2 = null;
		archivo1 = new LinkedList<>();
		archivo2 = new LinkedList<>();
		
		try {
			
			String [] nextLine1;
			reader1 = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-MonthlyAggregate.csv"));
			nextLine1 = reader1.readNext();
			archivo1 = new LinkedList<String[]>(nextLine1);		
			while ((nextLine1 = reader1.readNext()) != null) {
				archivo1.add(nextLine1);
				
				if(nextLine1[2].equals("01")){totalViajesMes[3] ++;}
				else if(nextLine1[2].equals("02")){totalViajesMes[4] ++;}
				else if(nextLine1[2].equals("03")){totalViajesMes[5] ++;}
				else { System.out.println("Falle :(");}
				
		     }
			
			String [] nextLine2;
			reader2 = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-MonthlyAggregate.csv"));
			nextLine2 = reader2.readNext();
			archivo2 = new LinkedList<String[]>(nextLine2);
			while ((nextLine2 = reader2.readNext()) != null) {
				archivo2.add(nextLine2);
				
				if(nextLine2[2].equals("04")){totalViajesMes[3] ++;}
				else if(nextLine2[2].equals("05")){totalViajesMes[4] ++;}
				else if(nextLine2[2].equals("06")){totalViajesMes[5] ++;}
				else { System.out.println("Falle :( 2");}
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}if (reader2 != null) {
				try {
					reader2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
	}


	public LinkedList<String[]>  consulta(String mes, String identificador){
		LinkedList<String[]> respuesta = null;
		boolean primer = false;	
		
		if(mes.equals("01") || mes.equals("02") || mes.equals("03") )
		{		
			for(String[] leer: archivo1){
				if(leer[0].equals(identificador) && leer[2].equals(mes)){
					if(!primer){
						respuesta = new LinkedList<String[]>(leer);
						primer = true;
					}else{
						respuesta.add(leer);
					}
				}
			}		
		}else{ 
			for(String[] leer: archivo2){
				if(leer[0].equals(identificador) && leer[2].equals(mes)){
					if(!primer){
						respuesta = new LinkedList<String[]>(leer);
						primer = true;
					}else{respuesta.add(leer);}
					
				}
			}
		
		}
		return respuesta;
	}
}
