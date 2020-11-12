
package dominio;


public class Impresora extends Recurso {
    
    boolean enUso;
    
    Impresora(){
        
        nombreRec="Impresora";
        enUso=false;     
    }
    
    public boolean enUso(){
        return this.enUso;
    }
    
    public void usar(){
        this.enUso=true;
    }
    
    public void dejarDeUsar(){
        this.enUso=false;
    }
}
