/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import interfazGrafica.inicioSesion;

/**
 *
 * @author nicol
 */
public class main {
    
   public static void main(String args[]) {
   
       SistemaOperativo sis= new SistemaOperativo();
       Procesador procesador= new Procesador();
       sis.agregarRecurso(procesador);
       Impresora impresora=new Impresora();
       sis.agregarRecurso(impresora);
       Ram ram= new Ram();
       sis.agregarRecurso(ram);
      Rom rom=new Rom();
      sis.agregarRecurso(rom);
       Usuario usuario= new Usuario("usuario","usuario","1234");
       Usuario invitado= new Usuario("invitado","invitado","");
       Usuario superUsuario= new Usuario("superUsuario","superUsuario","123456789");
       sis.agregarUsuario(usuario);
      sis.agregarUsuario(invitado);
      sis.agregarUsuario(superUsuario);
      
     Proceso imprimir= new Proceso("imprimir",400,1,true,"invitado",sis);
     sis.agregarProceso(imprimir);
     
     Proceso ejecutarLimpiador= new Proceso("ejecutrar limpiador", 150,0, false, "superUsuario",sis);
     sis.agregarProceso(ejecutarLimpiador);
   
    Proceso verProcesos= new Proceso("ver Procesos activos", 100,1, false, "usuario",sis);
    sis.agregarProceso(verProcesos);
    
    inicioSesion vInicio= new inicioSesion(sis);
   vInicio.setVisible(true);
   
  }
}

