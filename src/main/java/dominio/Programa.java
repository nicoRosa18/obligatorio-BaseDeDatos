
package dominio;

import java.util.ArrayList;


public class Programa{
    
    String nombreP;
    ArrayList<Proceso> listaProcesos;
    int nivelEjecucion;
    String tipoU;
    int usoMemoria;
    int ubicacion;
    
    public Programa (String nombre, ArrayList<Proceso> lista, String tipU){
        this.nombreP=nombre;
        this.listaProcesos=lista;
        this.nivelEjecucion=0;
        this.tipoU=tipU;
        this.usoMemoria=sumaMemoria(lista);
        ubicacion=-1;
    }
    
    private int sumaMemoria(ArrayList<Proceso> lista){
        int retorno=0;
        for(int i=0;i<lista.size();i++){
            retorno+=lista.get(i).lugarEnMemoria;
        }
        return retorno;
    }
    public String getNombre(){
        return this.nombreP;
    }
    
    
   
}
