
package optimizacion;

import java.util.HashMap;

/**
 * Desarrollado por
 * Jose Arias Carazo, B40569
 * Eduardo Biazzetti Sibaja, B40999
 * Javier Fernández Aguilar, B32540
 * 2018
 */

/**
 * Clase Solucion
 * 
 * Una solucion es un individuo en la poblacion. Un posible padre para
 * nuevos parametros. Cada objeto solucion tiene un id, cada uno de los 8 parametros
 * relevantes para la simulacion de NetLogo, cada uno de los resultados a travez
 * de los 5 ciclos de 24 ticks de la simulacion, su aptitud o fitness y el total
 * de celulas. Se usan Hash Maps para la facilidad de imprimir los datos y enviarlos
 * a la simulacion.
 */

public class Solucion {
    
    int id;
    HashMap<String,Double> hmap;      
    
    public Solucion(int id, double cell, double infected, double viral, double infection, double mNeptune, double probDeath, double probCond, double marker){
        this.hmap=new HashMap<String,Double>();
        this.id=id;
        
        this.hmap.put("id",(double)id);
        this.hmap.put("cell-density", cell);
        this.hmap.put("initial-infected-cell-percentage", infected);
        this.hmap.put("viral-reach", viral);
        this.hmap.put("infection-rate", infection);
        this.hmap.put("mNeptune-effectiveness", mNeptune);
        this.hmap.put("initial-probability-of-death", probDeath);
        this.hmap.put("initial-probability-of-chromatin-condensation", probCond);
        this.hmap.put("marker-detection-threashold", marker);
        
        this.hmap.put("alive1",0.0);
        this.hmap.put("dead1",0.0);
        this.hmap.put("infected1",0.0);
        
        this.hmap.put("alive2",0.0);
        this.hmap.put("dead2",0.0);
        this.hmap.put("infected2",0.0);
        
        this.hmap.put("alive3",0.0);
        this.hmap.put("dead3",0.0);
        this.hmap.put("infected3",0.0);
        
        this.hmap.put("alive4",0.0);
        this.hmap.put("dead4",0.0);
        this.hmap.put("infected4",0.0);
        
        this.hmap.put("alive5",0.0);
        this.hmap.put("dead5",0.0);
        this.hmap.put("infected5",0.0);
    
        this.hmap.put("Fitness", 0.0);
        this.hmap.put("Cell-Total",0.0);
    }
    
    /**
     * Metodo getValue
     * Retorna el valor de la llave (key) indicada en el argumento.
     * @param key: la llave al valor que se quiere obtener
     * @return: El valor de la llave indicada
     */
    
    public double getValue(String key){
        return this.hmap.get(key);
    }
    
    /**
     * Metodo getAllMap
     * Retorna todo el HashMap del objeto Solucion que llame
     * a este metodo.
     * @return el HashMap completo de este objeto Solucion
     */
    public HashMap<String, Double> getAllMap(){
        return this.hmap;
    }
    
    /**
     * Metodo getParameters
     * Devuelve un HashMap con todos los parametros relevantes para
     * la simulacion
     * @return un HashMap<String, Double> con todos los parametros de este 
     * objeto Solucion
     */
    
    public HashMap<String,Double> getParameters(){
        HashMap<String,Double> parameters = new HashMap<String,Double>();
        
        parameters.put("cell-density", this.hmap.get("cell-density"));
        parameters.put("initial-infected-cell-percentage", this.hmap.get("initial-infected-cell-percentage"));
        parameters.put("viral-reach",  this.hmap.get("viral-reach"));
        parameters.put("infection-rate",  this.hmap.get("infection-rate"));
        parameters.put("mNeptune-effectiveness",  this.hmap.get("mNeptune-effectiveness"));
        parameters.put("initial-probability-of-death",  this.hmap.get("initial-probability-of-death"));
        parameters.put("initial-probability-of-chromatin-condensation",  this.hmap.get("initial-probability-of-chromatin-condensation"));
        parameters.put("marker-detection-threashold",  this.hmap.get("marker-detection-threashold"));
        
        return parameters;
    }
    
    /**
     * Metodo setValue
     * Cambia el valor de la llave indicada en el argumento
     * @param key: El parametro a cambiar
     * @param value: El valor a asignarle a la llave
     */
    
    public void setValue(String key, double value){
        this.hmap.replace(key, value);
    }
    
    /**
     * Metodo getLastResults
     * Devuelve en un hash map los resultados de la ejecucion del ciclo
     * 5 de la simulacion
     * @return un HashMap con los ultimos resultados de este objeto Solucion
     */
    
    public HashMap<String,Double> getLastResults(){
        HashMap<String,Double> results = new HashMap<String,Double>();
        results.put("alive5",this.hmap.get("alive5"));
        results.put("dead5",this.hmap.get("dead5"));
        results.put("infected5",this.hmap.get("infected5"));
        return results;
    }
    
    /**
     * Metodo setCellTotal
     * Obtiene los ultimos resultados, el ciclo 5 de la simulacion, los suma
     * y los guarda con la llave Cell-Total
     */
    
    public void setCellTotal(){
        this.hmap.replace("Cell-Total",this.hmap.get("alive5")+this.hmap.get("dead5")+this.hmap.get("infected5"));
    }
    
    /**
     * Metodo getCellTotal
     * @return devuelve un double con el total de celulas del ultimo ciclo de
     * la simulacion.
     */
    
    public double getCellTotal(){
        double total = this.hmap.get("Cell-Total");
        return total;
    }
    
    /**
     * Metodo setFitness
     * Utiliza la formula de error cuadrado minimo para establecer la aptitud
     * o fitness del objeto Solucion que llame a este metodo
     */
    
    public void setFitness(){
    
        double fitness=0;
        
        double labAlive = 20.7832829381;
        double labDead = 68.1157972467;
        double labInfected = 11.1009198152;
        
        double total = this.hmap.get("Cell-Total");
        double promAlive = (this.hmap.get("alive5")*100)/total;
        double promDead = (this.hmap.get("dead5")*100)/total;
        double promInfected =  (this.hmap.get("infected5")*100)/total;
        
       // double sum = promAlive+promDead+promInfected;
        
        double downAlive=promAlive-labAlive;
        double downDead=promDead-labDead;
        double downInfected=promInfected-labInfected;
        
        double squareAlive=Math.pow(downAlive, 2);
        double squareDead=Math.pow(downDead, 2);
        double squareInfected=Math.pow(downInfected, 2);
        
        
        double sumOfAll = squareAlive+squareDead+squareInfected;        
        double newValue = sumOfAll/3;        
        fitness = Math.sqrt(newValue);
        this.hmap.replace("Fitness", fitness);
    }
    
    /**
     * Metodo getFitness
     * @return devuelve la aptitud de este objeto Solucion
     */
    
    public double getFitness(){  
        return this.hmap.get("Fitness");
    }
    /**
     * Metodo getId
     * @return devuelve el id del objeto Solucion que llama a este metodo
     */
    
    public int getId(){
        return this.id;
    }
    
}
