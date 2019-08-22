package controller;

import java.io.IOException;
import java.util.Scanner;

import model.data_structures.LinkedList;
import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
				try {
					modelo.carga();
				} catch (IOException e) {
					e.printStackTrace();
				}
					System.out.println("--------- \n Total de viajes leídos para cada mes del primer semestre del año:");
					System.out.println("Para Enero: "+modelo.darTotalViajesMes()[0]);
					System.out.println("Para Febrero: "+modelo.darTotalViajesMes()[1]);
					System.out.println("Para Marzo: "+modelo.darTotalViajesMes()[2]);
					System.out.println("Para Abril: "+modelo.darTotalViajesMes()[3]);
					System.out.println("Para Mayo: "+modelo.darTotalViajesMes()[4]);
					System.out.println("Para Junio: "+modelo.darTotalViajesMes()[5]);
					
					int mesInt = 0;
					while(mesInt <1 && mesInt>6){
						System.out.println("--------- \n Seleccione un mes: ");
						System.out.println("1. Enero");
						System.out.println("2. Febrero");
						System.out.println("3. Marzo");
						System.out.println("4. Abril");
						System.out.println("5. Mayo");
						System.out.println("6. Junio");
						System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return");
						
						mesInt = lector.nextInt();
						
						if(mesInt <1 && mesInt>6){
							System.out.println("--------- \n Opcion Invalida !! \n---------");
						}
						
					}
					String mes = "0"+ mesInt;
					
				    
				    System.out.println("\n Porfavor ingresa el el identificador numérico de una zona de origen de los viajes.");
				    System.out.println("\n Dar el numero, luego oprimir tecla Return");
				    
				    String identificador = lector.next();
				         
					do{
						System.out.println("1. Realizar Consulta de los viajes reportados en el mes dado saliendo desde la zona de origen dada.");
						System.out.println("2. Estadisticas");
						System.out.println("6. Exit");
						System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return:");
						
						option = lector.nextInt();
						
						if(option == 1){
							System.out.println("se mostrara para cada viaje: su zona origen, su zona destino, su tiempo promedio y su desviación estándar.\n ");
							LinkedList<String[]> consulta = modelo.consulta(mes, identificador);
							if(consulta == null){
								System.out.println("no hay viajes desde la zona origen");
							}else{
							for(String[] leer: modelo.consulta(mes, identificador)){
								System.out.println(" - "+leer[0]+", "+ leer[1]+", "+leer[3]+", "+ leer[4]);
							}
							}
						}else if(option == 2 ){
							int temp = 0;
							for(int meses: modelo.darTotalViajesMes()){
								temp += meses;
							}
							System.out.println("- El total de viajes reportados en el semestre es: "+ temp);
							double temp2 = modelo.darTotalViajesMes()[mesInt -1]/temp;
							System.out.println("- El total de viajes reportados para el mes de consulta es: "+ modelo.darTotalViajesMes()[mesInt -1]
									+ ".\n y su respectivo porcentaje con respecto al total de viajes del semestre es: "+ temp2 +"%");
							LinkedList<String[]> consulta = modelo.consulta(mes, identificador);
							double temp3 = 0;
							int temp4 = 0;
							if(consulta != null){
								temp4 = consulta.size();
								temp3 = temp4/temp;
							}
							System.out.println("- El total de viajes reportados para el mes de consulta saliendo desde la zona de origen de consulta es: "+temp4
													+"y su respectivo porcentaje con respecto al total de viajes del mes de consulta: "+ temp3+"%");
						}else if(option == 6){
							break;
						}else{
							System.out.println("--------- \n Opcion Invalida !! \n---------");
						}
						
					}while(!(option == 6));
				    break;
					
				case 6: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
