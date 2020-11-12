/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import interfazGrafica.VentanaImprimir;
import java.io.*;
import java.util.*;
import interfazGrafica.Escritorio;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
        
        
public class SistemaOperativo extends Observable {
    
     public ArrayList<Usuario> listaUsuarios;
     public ArrayList<Recurso> listaRecursos;
     public ArrayList<Proceso> listaProcesos;
      public  Queue<String> colaImpresion;
      String data;
      
     
     
     


   SistemaOperativo(){
    listaUsuarios= new ArrayList<>();
    listaRecursos=new ArrayList<>();
    listaProcesos=new ArrayList<>();   
    colaImpresion= new LinkedList();
}
   
   
  void agregarRecurso(Recurso r){
    listaRecursos.add(r);
 }
  
  void agregarProceso(Proceso p){
    listaProcesos.add(p);
}
  
 
  void agregarUsuario(Usuario u){
      listaUsuarios.add(u);
  }
//VALIDA SI LA CONTRA COINCIDE CON LA REGISTRADA EN EL SO
  //SI ES TRUE , CARGA EL ESCRITORIO
  public boolean usuarioValido(String pasw, String tipoU){
      boolean retorno =false;
   for(int i=0;i<this.listaUsuarios.size();i++){
      if(this.listaUsuarios.get(i).tipoDeUsuario.equals(tipoU)){
          retorno=this.listaUsuarios.get(i).pasword.equals(pasw);
      }
  }
 //  if(retorno) mostrarEscritorio(tipoU);
   return retorno;
}
  //CARGA LOS PROCESOS SEGUN QUE USUARIO INICIO SESION
/*  private void mostrarEscritorio(String tipoU){
      escritorio e= new escritorio();
     ArrayList pro= obtenerProcesos(tipoU);
     e.cargarEscritorio(pro);
     e.setVisible(true);
     
  }*/
//DA LOS PROCESOS HABILITADOS PARA EL USUARIO QUE RECIBE
public ArrayList<Proceso> obtenerProcesos(String tipoU) {
    ArrayList<Proceso> retorno = new ArrayList();
    if (tipoU.equals("superUsuario")) {
        for (int i = 0; i < this.listaProcesos.size(); i++) {
            retorno.add(this.listaProcesos.get(i));
        }
    } else {
        if (tipoU.equals("usuario")) {
            for (int i = 0; i < this.listaProcesos.size(); i++) {
                if (!this.listaProcesos.get(i).tipoU.equals("superUsuario")) {
                    retorno.add(this.listaProcesos.get(i));
                }
            }
        } else {
            for (int i = 0; i < this.listaProcesos.size(); i++) {
                if (this.listaProcesos.get(i).tipoU.equals("invitado")) {
                    retorno.add(this.listaProcesos.get(i));
                }
            }
        }
    }
    return retorno;
}

   /* public void iniciarInvitado() {
       mostrarEscritorio("invitado");
       
    }*/
    
    public int getCantProcesos(String tipoU){
        int contador=0;
        if (tipoU.equals("superUsuario")) {
        for (int i = 0; i < this.listaProcesos.size(); i++) {
           contador++;
        }
    } else {
        if (tipoU.equals("usuario")) {
            for (int i = 0; i < this.listaProcesos.size(); i++) {
                if (!this.listaProcesos.get(i).tipoU.equals("superUsuario")) {
                   contador++;
                }
            }
        } else {
            for (int i = 0; i < this.listaProcesos.size(); i++) {
                if (this.listaProcesos.get(i).tipoU.equals("invitado")) {
                  contador++;
                }
            }
        }
        
       
    }
        return contador;
    }
    public void iniciarVentana(String nombreP){
      switch(nombreP){
          case "imprimir":
              ventanaImprimir();
              break;
     }  
    }
    
    public void iniciarProceso(String nombreP, String data){
      
     for(int i=0;i<listaProcesos.size();i++){
         if(listaProcesos.get(i).nombreP.equals(nombreP)){
            
             if(nombreP.equals("imprimir")){     
                 imprimir(data);
             }
             listaProcesos.get(i).start();
         }
     }
    }
    
     public void imprimir(String aImprimir){
            colaImpresion.add(aImprimir);
          
    }
     
    public void ventanaImprimir(){
        VentanaImprimir ventana=  new VentanaImprimir(this);
        ventana.setVisible(true);
        
    }
 
  
  public void abrirVentanaImprimir(){
      
  }
  
  //esta funcion sirve para demorar los procesos y hacerlos mas reales
  public static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
         } catch (Exception e) {
            System.out.println(e);
         }
    } 
  
     
      Impresora getImpresora(){
        for(int i=0;i<listaRecursos.size();i++){
            if(listaRecursos.get(i).getNombre().equals("Impresora")){
                return (Impresora) listaRecursos.get(i);
            }
        }
        return null;
    }
    
   
    
    
  public void  imprimiendo(){
      Impresora impresora= getImpresora();
      while(!colaImpresion.isEmpty()){
          if(!impresora.enUso()){
          String aImprimir=colaImpresion.element();
          colaImpresion.remove(); 
          //zona de mutua exclusion
          impresora.usar();
          System.out.println(aImprimir);
          esperar(5);
          impresora.dejarDeUsar();
          //termina zona de mutua exclusion
          }      
  }
  }
   




}
