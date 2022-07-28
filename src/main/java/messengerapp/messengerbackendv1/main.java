/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package messengerapp.messengerbackendv1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Main class, starts application.
 * Initializes Beans, starts Server, initializes Database according to settings
 * in application.properties file.
 * @author Lüku
 */

@SpringBootApplication
public class main {
    
    public static void main(String[] args) {
        
        ApplicationContext ctx = SpringApplication.run(main.class, args);
        System.out.println("Start up completed.");

    }
    
}