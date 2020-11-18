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
import interfazGrafica.Pantalla;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

public class SistemaOperativo extends Observable {

    public ArrayList<Usuario> listaUsuarios;
    public ArrayList<Recurso> listaRecursos;
    public ArrayList<Proceso> listaProcesos;
    public ArrayList<Programa> listaProgramas;
    public Queue<String> colaImpresion;
    public ArrayList<Programa> ProgramasActivos;
    String data;
    boolean pantallaEnUso;
    Pantalla p;
    Procesador procesador;
    Ram memoria;

    SistemaOperativo(Procesador cpu) {
        listaProgramas = new ArrayList<>();//se carga al comenzar la ejecucion
        listaUsuarios = new ArrayList<>();//se carga al comenzar la ejecucion
        listaRecursos = new ArrayList<>();//se carga al comenzar la ejecucion
        listaProcesos = new ArrayList<>();//se carga al comenzar la ejecucion
        ProgramasActivos = new ArrayList<>();//se carga a medida que se ejecutan los programas y se eliminan de la lista cuando el programa termina
        colaImpresion = new LinkedList();// cola donde encolamos lo que queremos imprimir en el recurso 75
        pantallaEnUso = false;
        p = new Pantalla();
        p.setVisible(true);
        this.procesador = cpu;
 
    }

    //funciones para agregar al sistema distintos objetos
    void agregarPrograma(Programa p) {
        this.listaProgramas.add(p);
    }
    
    void agergarMemoria(Ram r){
        this.memoria=r;
    }

    void agregarRecurso(Recurso r) {
        listaRecursos.add(r);
    }

    void agregarProceso(Proceso p) {
        listaProcesos.add(p);
    }

    void agregarUsuario(Usuario u) {
        listaUsuarios.add(u);
    }

    // fin funciones para agregar objetos al sistema
//VALIDA SI LA CONTRA COINCIDE CON LA REGISTRADA EN EL SO
    //SI ES TRUE , CARGA EL ESCRITORIO
    public boolean usuarioValido(String pasw, String tipoU) {
        boolean retorno = false;
        for (int i = 0; i < this.listaUsuarios.size(); i++) {
            if (this.listaUsuarios.get(i).tipoDeUsuario.equals(tipoU)) {
                retorno = this.listaUsuarios.get(i).pasword.equals(pasw);
            }
        }
        //  if(retorno) mostrarEscritorio(tipoU);
        return retorno;
    }

//DA LOS PROCESOS HABILITADOS PARA EL USUARIO QUE RECIBE
    public ArrayList<Programa> obtenerProgramas(String tipoU) {
        ArrayList<Programa> retorno = new ArrayList();
        if (tipoU.equals("superUsuario")) {
            for (int i = 0; i < this.listaProgramas.size(); i++) {
                retorno.add(this.listaProgramas.get(i));
            }
        } else {
            if (tipoU.equals("usuario")) {
                for (int i = 0; i < this.listaProgramas.size(); i++) {
                    if (!this.listaProgramas.get(i).tipoU.equals("superUsuario")) {
                        retorno.add(this.listaProgramas.get(i));
                    }
                }
            } else {
                for (int i = 0; i < this.listaProgramas.size(); i++) {
                    if (this.listaProgramas.get(i).tipoU.equals("invitado")) {
                        retorno.add(this.listaProgramas.get(i));
                    }
                }
            }
        }
        return retorno;
    }

    public int getCantProgramas(String tipoU) {
        int contador = 0;
        if (tipoU.equals("superUsuario")) {
            for (int i = 0; i < this.listaProgramas.size(); i++) {
                contador++;
            }
        } else {
            if (tipoU.equals("usuario")) {
                for (int i = 0; i < this.listaProgramas.size(); i++) {
                    if (!this.listaProgramas.get(i).tipoU.equals("superUsuario")) {
                        contador++;
                    }
                }
            } else {
                for (int i = 0; i < this.listaProgramas.size(); i++) {
                    if (this.listaProgramas.get(i).tipoU.equals("invitado")) {
                        contador++;
                    }
                }
            }

        }
        return contador;
    }

    public void iniciarVentana(String nombreP) {
        switch (nombreP) {
            case "imprimir":
                ventanaImprimir();
                break;
        }
    }

    /*
    public void iniciarPrograma(String nombreP, String data) {
        boolean seEjecuta = false;
        long TInicio, TFin, tiempo;

        for (int i = 0; i < listaProcesos.size(); i++) {
            if (listaProcesos.get(i).nombreP.equals(nombreP)) {

                if (nombreP.equals("imprimir")) {
                    imprimir(data);
                    ProcesoActivo pro = new ProcesoActivo(listaProcesos.get(i));
                    while (!seEjecuta) {
                        if (procesador.procesosActuales() < procesador.capacidadMaxProcesos) {
                            seEjecuta = true;
                            procesador.procesosActuales++;
                            TInicio = System.currentTimeMillis();
                            pro.start();
                        }
                    }
                }
            }
        }
    }*/
 /*
    public void terminarProceso(Thread proceso) {
        proceso.interrupt();
    }

    public void imprimir(String aImprimir) {
        if (!colaImpresion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Impresion agregada a la cola");
        }

        colaImpresion.add(aImprimir);

    }*/
    public void ventanaImprimir() {
        VentanaImprimir ventana = new VentanaImprimir(this);
        ventana.setVisible(true);

    }

    public boolean solicitarPantalla() {
        if (pantallaEnUso) {
            return false;
        } else {
            return true;
        }
    }

    public void escribir(String texto) {
        p.escribir(texto);
    }

    public void abrirVentanaImprimir() {

    }

    public void hayNuevoPrograma() {

    }

    //esta funcion sirve para demorar los procesos y hacerlos mas reales
    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    Impresora getImpresora() {
        for (int i = 0; i < listaRecursos.size(); i++) {
            if (listaRecursos.get(i).getNombre().equals("Impresora")) {
                return (Impresora) listaRecursos.get(i);
            }
        }
        return null;
    }

    /* public void  imprimiendo(){
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
  }*/
    public Programa getPrograma(String nombreProg) {
        for (int i = 0; i < this.listaProgramas.size(); i++) {
            if (this.listaProgramas.get(i).nombreP.equals(nombreProg)) {
                return this.listaProgramas.get(i);
            }
        }
        return null;
    }

    /*
    public void iniciarPrograma(String nombrePrograma) {
        for (int i = 0; i < this.listaProgramas.size(); i++) {
            if (this.listaProgramas.get(i).nombreP.equals(nombrePrograma)) {
                this.ProgramasActivos.add(this.listaProgramas.get(i));
                ejecutarProgramas();
                //run();

            }
        }
    }*/
    public void ejecutarProceso(Programa prog, Proceso pro) {
        long tiempoI = 0;
        long tiempoF = 0;
        long delta = 0;
        long acumulado = 0;
        double multiplicador = 0.001;
        if (pro.impresora) {
            boolean impreso = false;
            Impresora imp = getImpresora();
            while (!impreso && this.procesador.quantum > acumulado) {
                tiempoI = System.currentTimeMillis();
                if (!imp.enUso()) {
                    imp.usar();
                    System.out.println("ejecutando programa " + prog.nombreP + " proceso " + pro.nombreP);
                    System.out.println("usando impresora, imprimiendo: hola estoy usando la impresora");
                    p.escribir("ejecutando programa " + prog.nombreP + " proceso" + pro.nombreP);
                    esperar(pro.tiempoEjec);
                    imp.dejarDeUsar();
                    impreso = true;
                }
                tiempoF = System.currentTimeMillis();
                delta = (tiempoF - tiempoI);
                acumulado += delta * multiplicador;
            }
        } else {
            System.out.println("ejecutando programa " + prog.nombreP + " proceso " + pro.nombreP);
            p.escribir("ejecutando programa " + prog.nombreP + " proceso" + pro.nombreP);
              esperar(pro.tiempoEjec);
        }
        this.notifyObservers();
        tiempoF = System.currentTimeMillis();
        delta = (tiempoF - tiempoI);
        acumulado += delta * multiplicador;
    }

    public void ejecutarProgramas() {
        int i = 0;
        while (this.ProgramasActivos.size() > 0) {
            ejecutarPrograma(this.ProgramasActivos.get(i), i);
            if (this.ProgramasActivos.size() > i + 1) {
                i++;
            } else {
                i = 0;
            }
        }
    }

    private void ejecutarPrograma(Programa p, int lugarEnMemoria) {
        boolean programaCompletado = false;
        long tiempoF = 0;
        long delta = 0;
        long acumulado = 0;
        double multiplicador = 0.001;
        while (acumulado < this.procesador.quantum && !programaCompletado) {  //while mientras no se pierd el procesador o no haya finalzado el programa
            long tiempoI = System.currentTimeMillis();
            ejecutarProceso(p, p.listaProcesos.get(p.nivelEjecucion)); //ejecutamos una proceso
            if (p.listaProcesos.size() == p.nivelEjecucion + 1) {//if ya termino
                programaCompletado = true;// para sacarlo del while 
                p.nivelEjecucion = 0;
                this.ProgramasActivos.remove(lugarEnMemoria);  //lo sacamos de la lista de programas activos
            } else {
                p.nivelEjecucion++;//sino avanzamos a la siguiente instruccion del programa
            }
            tiempoF = System.currentTimeMillis();
            delta = (tiempoF - tiempoI);
            acumulado += delta * multiplicador;
        }
        if (programaCompletado) {
            this.memoria.enUso-=p.usoMemoria;
            System.out.println(p.nombreP + " finalizado");
        }
    }

    public void agregarProgramaActivo(Programa prog) {
        if (prog == null) {
            ejecutarProgramas();
        } else {
            if(this.memoria.enUso+prog.usoMemoria>this.memoria.capacidad){
                JOptionPane.showMessageDialog(null, "Memoria llena , ejecute los programas para liberarla");
            }else{
            this.memoria.enUso+=prog.usoMemoria;    
            this.ProgramasActivos.add(prog);
            p.escribir(prog.nombreP + " agregado");
            }
        }
    }

}
