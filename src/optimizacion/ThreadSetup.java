
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
public class ThreadSetup {
    
    private HashMap<String,Double>[] results;
    private List<Solucion> soluciones;
    
    /**
     * Clase ThreadSetup
     * 
     * Prepara la simulacion. Recibe una lista de objetos Solucion y prepara un thread con
     * cada uno de ellos. Luego se corren con el metodo ThreadRun. Luego se hace join de 
     * cada uno. Luego de que cada Thread termina se asignan los resultados que devolvio la
     * simulacion a cada objeto Solucion.
     * 
     * @param solList una lista de objetos Solucion que se van a correr en la simulacion
     *                 de manera concurrente
     */
    
    ThreadSetup(List<Solucion> solList){
    
    this.soluciones=new ArrayList<Solucion>();    
    results = new HashMap[5];
    Thread t1 = new Thread(new ThreadRun(0, results, solList.get(0).getParameters()));
    Thread t2 = new Thread(new ThreadRun(1, results, solList.get(1).getParameters()));
    Thread t3 = new Thread(new ThreadRun(2, results, solList.get(2).getParameters()));
    Thread t4 = new Thread(new ThreadRun(3, results, solList.get(3).getParameters()));
    Thread t5 = new Thread(new ThreadRun(4, results, solList.get(4).getParameters()));
    
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    
    try{
    t1.join();
    t2.join();
    t3.join();
    t4.join();
    t5.join();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
    for(int i=0;i<results.length;++i){
            
            solList.get(i).setValue("alive1",results[i].get("alive1"));
            solList.get(i).setValue("dead1",results[i].get("dead1"));
            solList.get(i).setValue("infected1",results[i].get("infected1"));
           
            solList.get(i).setValue("alive2",results[i].get("alive2"));
            solList.get(i).setValue("dead2",results[i].get("dead2"));
            solList.get(i).setValue("infected2",results[i].get("infected2"));
            
            
            solList.get(i).setValue("alive3",results[i].get("alive3"));
            solList.get(i).setValue("dead3",results[i].get("dead3"));
            solList.get(i).setValue("infected3",results[i].get("infected3"));
           
            solList.get(i).setValue("alive4",results[i].get("alive4"));
            solList.get(i).setValue("dead4",results[i].get("dead4"));
            solList.get(i).setValue("infected4",results[i].get("infected4"));
           
            solList.get(i).setValue("alive5",results[i].get("alive5"));
            solList.get(i).setValue("dead5",results[i].get("dead5"));
            solList.get(i).setValue("infected5",results[i].get("infected5"));
           
            solList.get(i).setCellTotal();
            solList.get(i).setFitness();
            
            soluciones.add(solList.get(i));
    }
        
    
    }        
   
    
    public List<Solucion>getResults(){
        return this.soluciones;
        }
}
