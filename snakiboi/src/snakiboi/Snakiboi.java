/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakiboi;
import javax.swing.*;
/**
 *
 * @author rajdw
 */
public class Snakiboi extends JFrame{
    Snakiboi(){
        super("Snakiboi");
        add(new board());
        pack();
        
        //setLocation(600,250);
        //setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Snakiboi();
    }
    
}
