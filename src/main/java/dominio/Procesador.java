

package dominio;



public class Procesador  extends Recurso{
    

    int capacidadMaxProcesos;
    int procesosActuales;
    int quantum;
    Procesador(){
        nombreRec="procesador";
        capacidadMaxProcesos=2;
        procesosActuales=0;
        quantum=4;
    }  
    
    
    public int procesosActuales(){
        return this.procesosActuales;
    }
    
    
}
