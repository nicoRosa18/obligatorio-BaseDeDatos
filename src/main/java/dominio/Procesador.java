

package dominio;



public class Procesador  extends Recurso{
    

    int capacidadMaxProcesos;
    int procesosActuales;
    Procesador(){
        nombreRec="procesador";
        capacidadMaxProcesos=4;
        procesosActuales=0;
    }   
    
}
