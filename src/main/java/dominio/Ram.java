
package dominio;


public class Ram extends Recurso{
    
    
  public  int[] arr;
  int capacidad;
  public  int enUso;
    
    Ram(){
        nombreRec= "Ram";
         capacidad=30;
         arr= new int [30];
         enUso=0;
    }
}
