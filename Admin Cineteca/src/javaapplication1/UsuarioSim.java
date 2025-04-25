/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.util.Random;

public class UsuarioSim implements Runnable {
    
    private final String nombre;
    private final Cineteca cineteca;
    private final Random random;
    
    
    public UsuarioSim (String nombre, Cineteca cineteca){
    
        this.nombre = nombre;
        this.cineteca = cineteca;
        this.random = new Random(); 
    }
    

 @Override
 public void run(){



}   
    
}
