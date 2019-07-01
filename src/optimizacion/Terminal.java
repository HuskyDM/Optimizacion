
package optimizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Desarrollado por
 * Jose Arias Carazo, B40569
 * Eduardo Biazzetti Sibaja, B40999
 * Javier Fernández Aguilar, B32540
 * 2018
 */
public class Terminal {
    
    /**
     * Metodo Naming
     * Recibe el nombre del archivo donde
     * se almacenaran los resultados
     * @return filename: El nombre del archivo a generar
     */
    
    public String naming(){
        String filename;
        System.out.println("Introduzca el nombre del archivo");
        System.out.println("----No añada extension----");
        Scanner scan = new Scanner(System.in);
        filename=scan.nextLine();
        return filename;
    }
    
    /**
     * Metodo crossover
     * Recibe el valor numerico double crossover rate.
     * @return  cross: El valor de crossover rate que usara 
     * la simulacion
     */
    
    public double crossover(){
        double cross;
        System.out.println("Introduzca el crossover rate");
        System.out.println("Usa la funcion floor((crossover-rate*population-size)/5/2)");
        Scanner scan = new Scanner(System.in);
        cross=scan.nextDouble();
        return cross;
    }
    
    /**
     * Metodo setUp
     * Prepara cinco objetos solucion, cada uno con los mismos parametros
     * para ser ejecutados de manera concurrente. Luego llama a la clase 
     * ThreadCall con el nombre del archivo, el crossover rate y una lista de los
     * objetos Solucion para realizar la simulación.
     */
    
    public void setUp(){
        List<Solucion> iniciales = new ArrayList<Solucion>();
       Solucion sol1 = new Solucion(0, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       Solucion sol2 = new Solucion(1, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       Solucion sol3 = new Solucion(2, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       Solucion sol4 = new Solucion(3, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       Solucion sol5 = new Solucion(4, 2.0, 0.0, 1.5, 15.0, 100.0, 0.595, 2.50, 0.12);
       
       iniciales.add(sol1);
       iniciales.add(sol2);
       iniciales.add(sol3);
       iniciales.add(sol4);
       iniciales.add(sol5);
             
        String name = naming();
        double cross = crossover();
    
        ThreadCall tru = new ThreadCall(name, cross,iniciales);
        tru.runProject();
    }
    
}
