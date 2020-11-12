/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author nicol
 */
public class Proceso extends Thread {
    
    public String nombreP;
    public int ramUsada;
    public int procesadorUsado;
    boolean usaImpresora;
    String tipoU;
    SistemaOperativo sis;
    
    
   Proceso(String n, int r, int p, boolean imp, String tipoUs,SistemaOperativo sistema){
       nombreP=n;
       ramUsada=r;
       procesadorUsado=p;
       usaImpresora=imp;
       tipoU=tipoUs;
       sis=sistema;
   }

   
   @Override
   public void run() {
       switch(this.nombreP){
           case "imprimir":
              imprimiendo();
       }
}
   
   
      Impresora getImpresora(){
        for(int i=0;i<sis.listaRecursos.size();i++){
            if(sis.listaRecursos.get(i).getNombre().equals("Impresora")){
                return (Impresora) sis.listaRecursos.get(i);
            }
        }
        return null;
    }
    
   
    
    
  private void  imprimiendo(){
      Impresora impresora= getImpresora();
      while(!sis.colaImpresion.isEmpty()){
          if(!impresora.enUso()){
          String aImprimir=sis.colaImpresion.element();
          sis.colaImpresion.remove(); 
          //zona de mutua exclusion
          impresora.usar();
          System.out.println(aImprimir);
          esperar(5);
          impresora.dejarDeUsar();
          //termina zona de mutua exclusion
          }      
  }
  }
   
    private static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
         } catch (Exception e) {
            System.out.println(e);
         }
    }

   //public void verRecursos(){
   //   
  //}
   
  // public void imprimirArchivo(){
    //   
   //}
   
}
