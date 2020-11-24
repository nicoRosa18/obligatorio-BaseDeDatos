/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import dominio.Programa;
import dominio.SistemaOperativo;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JButton;

public abstract class Escritorio extends javax.swing.JFrame implements Observer {

    private JButton[][] botones;
    private javax.swing.JPanel panelMatriz;
    private SistemaOperativo modelo;
    String user;

    public Escritorio(SistemaOperativo unSistema, String usuario) {
        modelo = unSistema;
          modelo.addObserver(this);
//        modelo.addObserver(this);
        user = usuario;
        initComponents();
        cargarEscritorio();
       
        //  panelMatriz = new javax.swing.JPanel();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMatriz = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelMatrizLayout = new javax.swing.GroupLayout(panelMatriz);
        panelMatriz.setLayout(panelMatrizLayout);
        panelMatrizLayout.setHorizontalGroup(
            panelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );
        panelMatrizLayout.setVerticalGroup(
            panelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(panelMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(panelMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarEscritorio() {

        int cantidadProcesos = modelo.getCantProgramas(user);

        panelMatriz.removeAll();
        // crear botones y agregarlos al panel
        panelMatriz.setLayout(new GridLayout(cantidadProcesos+1, 1));
        botones = new JButton[cantidadProcesos+3][1];
        for (int i = 0; i < cantidadProcesos; i++) {
            JButton jButton = new JButton();
            jButton.addActionListener(new ListenerBoton(i));
            panelMatriz.add(jButton);
            botones[i][0] = jButton;
            jButton.setText(modelo.obtenerProgramas(user).get(i).getNombre());
        }
        JButton jButton = new JButton();
            jButton.addActionListener(new ListenerBoton(cantidadProcesos));
            panelMatriz.add(jButton);
            botones[cantidadProcesos][0] = jButton;
            jButton.setText("ejecutar Programas agregados");
        //  panelMatriz.setVisible(true);
    }
    

    public class ListenerBoton implements ActionListener {

        private int x = -1;

        public ListenerBoton(int i) {
            // en el constructor se almacena la fila y columna que se presionó
            x = i;

        }
        public int getX(){
            return this.x;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // cuando se presiona un botón, se ejecutará este método
            clickBoton(x);
        }
       

        public void clickBoton(int fila) {
            Programa prog = modelo.getPrograma(botones[fila][0].getText());//me traigo el programa identigicado por nombre
         modelo.agregarProgramaActivo(prog);//lo agrego a la lista de programas que quiero ejecutar
         
        }

        
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelMatriz;
    // End of variables declaration//GEN-END:variables

}
      public void update(Observable o, Object arg) {
            this.cargarEscritorio();
        }
}
