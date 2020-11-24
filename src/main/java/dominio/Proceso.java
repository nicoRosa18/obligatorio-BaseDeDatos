
package dominio;


public class Proceso extends Thread {
    
    
    public String nombreP;
    double tiempoEjec;
    SistemaOperativo sis;
    boolean impresora;
    String estado;
    int lugarEnMemoria;
    
   Proceso(String n, int lugarMemoria, SistemaOperativo s,boolean usaImp){
       this.nombreP=n;
       this.tiempoEjec=lugarMemoria*1.5;
       this.sis=s;
       this.impresora=usaImp;
       this.estado="bloqueado";
       this.lugarEnMemoria=lugarMemoria;
       
       
   }

    //  public void ejecutarP(){
      ////   sis.ejecutarProceso(this);
    //  }
}