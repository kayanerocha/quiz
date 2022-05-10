/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author kayane
 */
public class TesteCronometro {
    
    public static void main(String[] args){
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Timer timer = null;

        if (timer == null)   
        {      
            timer = new Timer();  

            TimerTask tarefa = new TimerTask() {     

                public void run()   
                {      
                    try {      
                        System.out.println("Hora: " + format.format(new Date().getTime()));      
                    }catch (Exception e) {      
                        e.printStackTrace();      
                    }      
                }   
            };      

            timer.scheduleAtFixedRate(tarefa, 0, 1000);      
        }    
    }
    
}
