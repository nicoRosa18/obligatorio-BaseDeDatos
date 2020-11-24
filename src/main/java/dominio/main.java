/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import interfazGrafica.inicioSesion;
import java.util.ArrayList;

/**
 *
 * @author nicol
 */
public class main {
    
   public static void main(String args[]) {
       Procesador procesador= new Procesador();   
       SistemaOperativo sis= new SistemaOperativo(procesador);     
       sis.agregarRecurso(procesador);
       Impresora impresora=new Impresora();
       sis.agregarRecurso(impresora);
       Ram ram= new Ram();
       sis.agergarMemoria(ram);
       sis.agregarRecurso(ram);
      Rom rom=new Rom();
      sis.agregarRecurso(rom);
       Usuario usuario= new Usuario("usuario","usuario","1234");
       Usuario invitado= new Usuario("invitado","invitado","");
       Usuario superUsuario= new Usuario("superUsuario","superUsuario","123456789");
       sis.agregarUsuario(usuario);
      sis.agregarUsuario(invitado);
      sis.agregarUsuario(superUsuario);
      
      //creo los procesos, que son una instruccion a realizar
      Proceso a= new Proceso ("A",4,sis,false);
      Proceso b= new Proceso("B",3,sis,true);
      Proceso c= new Proceso("C", 2,sis,false);
      Proceso d= new Proceso("D",3,sis,false);
      Proceso e= new Proceso("E",5,sis,true);
      //los agrego al sistema, algo asi como un registro de que hace cada instruccion
      sis.agregarProceso(a);
      sis.agregarProceso(b);
      sis.agregarProceso(c);
      //creo la lista de procesos y los agrego al arraylist
      ArrayList<Proceso> listaP= new ArrayList<>();
      listaP.add(a);
      listaP.add(b);
      listaP.add(c);
     Programa p1= new Programa("Programa 1",listaP, "invitado");
     //programa 2:
     ArrayList<Proceso> listaP2=new ArrayList<>();
     listaP2.add(a);
     listaP2.add(b);
     listaP2.add(e);
     ArrayList<Proceso> listaVacia=new ArrayList<>();
     Programa p2= new Programa ("Programa 2",listaP2,"usuario");
     Programa crearP= new Programa("Crear Programa",listaVacia , "usuario");
      Programa crearProc= new Programa("Crear Proceso", listaVacia, "superUsuario");
     sis.agregarPrograma(crearP);
      sis.agregarPrograma(crearProc);
     //agrego los programas al sistema
     sis.agregarPrograma(p1);
     sis.agregarPrograma(p2);
     
     
     
      
      //creo el arrayList con los procesos
      //creo el programa
     //Proceso imprimir= new Proceso("imprimir",400,1,true,"invitado",sis);
     //sis.agregarProceso(imprimir);
     
    // Proceso ejecutarLimpiador= new Proceso("ejecutrar limpiador", 150,0, false, "superUsuario",sis);
    // sis.agregarProceso(ejecutarLimpiador);
   
    //Proceso verProcesos= new Proceso("ver Procesos activos", 100,1, false, "usuario",sis);
    //sis.agregarProceso(verProcesos);
    inicioSesion vInicio= new inicioSesion(sis);
    
//      Runnable interfaz= new inicioSesion(sis);
     vInicio.setVisible(true);
    // new Thread(interfaz).start();
    // new Thread (sis).start();
     
   
  }
}

