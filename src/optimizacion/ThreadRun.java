
package optimizacion;

import java.util.HashMap;

/**
 * Desarrollado por
 * Jose Arias Carazo, B40569
 * Eduardo Biazzetti Sibaja, B40999
 * Javier Fernández Aguilar, B32540
 * 2018
 */
public class ThreadRun implements Runnable{
    
    int id;
    HashMap<String,Double>[] resultados;
    HashMap<String,Double> hmap;
    
    /**
     * Clase ThreadRun
     * 
     * Corre la simulacion. Tiene un array de HashMaps donde se almacenan los resultados
     * que devuelve la simulacion. Abre la simulacion en el directorio devuelto por 
     * System.getProperty("user.dir")
     * 
     * @param id: El identificador del thread para uso del join
     * @param resultados: Un array donde se almacenan los resultados devueltos por la simulacion
     * @param hmap: Contiene todos los parametros relevantes para la simulacion del objeto Solucion
     */
    
    public ThreadRun(int id, HashMap<String,Double>[] resultados, HashMap<String,Double> hmap){
        this.id=id;
        this.resultados=resultados;
        this.hmap=hmap;
    }
    
    @Override
    public void run(){
        
         System.out.println("Corriendo el thread id:"+id);
         System.out.println("Buscando el archivo zika.nlogo en el directorio "+System.getProperty("user.dir"));
         String path =System.getProperty("user.dir")+"\\zika.nlogo";
         NetLogoSimMod simulacion = new NetLogoSimMod(path,id);
         simulacion.updateParams(hmap);
            try{
                resultados[id]=simulacion.runSimulation();
                }
            catch(Exception e)
                {
                e.printStackTrace();
                }
    }
}
