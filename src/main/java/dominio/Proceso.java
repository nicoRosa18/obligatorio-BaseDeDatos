
package dominio;


public class Proceso extends Thread {
    
    
    public String nombreP;
    int tiempoEjec;
    SistemaOperativo sis;
    boolean impresora;
    
   Proceso(String n, int time, SistemaOperativo s,boolean usaImp){
       this.nombreP=n;
       this.tiempoEjec=time;
       this.sis=s;
       this.impresora=usaImp;
       
   }

    //  public void ejecutarP(){
      ////   sis.ejecutarProceso(this);
    //  }
}