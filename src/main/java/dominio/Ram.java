
package dominio;


public class Ram extends Recurso{
    
    
  public  int capacidad;
  public  int enUso;
    
    Ram(){
        nombreRec= "Ram";
         capacidad=1000;
         enUso=0;
    }
}
