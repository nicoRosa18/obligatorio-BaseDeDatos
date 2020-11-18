
package dominio;

import java.util.ArrayList;


public class Programa{
    
    String nombreP;
    ArrayList<Proceso> listaProcesos;
    int nivelEjecucion;
    String tipoU;
    int usoMemoria;
    
    public Programa (String nombre, ArrayList<Proceso> lista, String tipU){
        this.nombreP=nombre;
        this.listaProcesos=lista;
        this.nivelEjecucion=0;
        this.tipoU=tipU;
        this.usoMemoria=lista.size()*100;
    }
    
    public String getNombre(){
        return this.nombreP;
    }
    
    
   
}
