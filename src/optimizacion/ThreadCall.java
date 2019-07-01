
package optimizacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Desarrollado por
 * Jose Arias Carazo, B40569
 * Eduardo Biazzetti Sibaja, B40999
 * Javier Fernández Aguilar, B32540
 * 2018
 */
public class ThreadCall {
    
    List<Solucion> iniciales;
    String filename;
    double crossover;
    
    /**
     * Constructor ThreadCall
     * @param filename: El nombre de archivo donde se almacenan los resultados
     * @param crossover: El valor de crossover rate que el algoritmo de optimizacion va a usar
     * @param iniciales: Una lista de los primeros objetos Solucion que se van a ejecutar en la
     *                    simulacion
     */
    
        public ThreadCall(String filename, double crossover,List<Solucion> iniciales){
            
            this.iniciales=iniciales;
            this.filename = filename;
            this.crossover = crossover;
        }
    
        /**
         * Metodo runProject
         * El ciclo principal del programa. Ejecuta la simulacion con la lista
         * de soluciones dadas por la clase Terminal y guarda los resultados en el
         * archivo de texto indicado. Luego ejecuta el algoritmo de optimizacion y
         * ejecuta un nuevo ciclo de simulaciones con los nuevos parametros. Esto
         * se realiza de manera indefinida.
         */
        
    public void runProject() {
       int gen=1;     
        Printer printer = new Printer();
        ThreadSetup mid = new ThreadSetup(iniciales);
        List<Solucion>results = mid.getResults();
        
        while(true){                   
              
        printer.printResults(filename,results,gen);   
        gen++;
        GeneticSR genetic = new GeneticSR(crossover,results,0.01);
        List<Solucion>nuevaGen = genetic.createPopulationSR();
        System.out.println("Tamaño de la nueva gen: "+nuevaGen.size());
        ThreadSetup newMid = new ThreadSetup(nuevaGen);
        results=newMid.getResults();
        printer.printResults("newPop", nuevaGen, gen);
        }
    }
    
}