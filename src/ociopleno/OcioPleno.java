/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ociopleno;

/**
 *
 * @author jcfalcon
 */
public class OcioPleno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //llamar un jframe desde el main

       Login vLogin = new Login();
       
       vLogin.setLocationRelativeTo(null); // ventana centrada
       vLogin.setResizable(false);// No se puede cambiar el tamaño
       vLogin.setDefaultCloseOperation(0);//Evita que se cierre la ventana con X
       vLogin.setVisible(true);//visualiza la Ventana
   
       
        
    }
    
}