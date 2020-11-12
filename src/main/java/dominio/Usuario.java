/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Usuario {
    
    String tipoDeUsuario;
    String nombreUsuario;
    String pasword;
            
    
   Usuario(String tipo, String nombre, String contrasena){
      tipoDeUsuario=tipo;
      nombreUsuario=nombre;
      pasword=contrasena;
   }
   
   String getTipoUser(){
       return this.nombreUsuario;
   }
}
