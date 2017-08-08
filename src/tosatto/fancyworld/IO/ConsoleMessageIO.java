/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.IO;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 *
 * @author Davide
 */
public class ConsoleMessageIO implements MessageIO{

    @Override
    public void inform(String msg) {
        System.out.println("\n" + msg);
    }

    @Override
    public int ask(String msg, String[] alternatives) {
        StringJoiner alt = new StringJoiner(", ");
       
       for (String entry : alternatives)
       {
           alt.add(entry);
       }
       
        inform(String.format("%s [%s]", msg, alt.toString()));
        
        Scanner in = new Scanner(System.in);
        
        while (true)
        {
            String choice = in.nextLine();
            
            for (int i=0; i < alternatives.length ; i++)
            {
                if (choice.equalsIgnoreCase(alternatives[i]))
                    return i;
            }
            
            inform("Scelta non disponibile");
            inform(String.format("%s [%s]", msg, alt.toString()));
        }
    }

    @Override
    public String ask(String msg) {
        inform(msg);
        
        Scanner in = new Scanner(System.in);
        
        return in.nextLine();
    }

    private void printMenu (String title, String[] entries) 
    {
        System.out.println("\n\n---" + title + "---\n");
        
        for (int i=0; i < entries.length; i++)
        {
            System.out.println(String.format("%2d. %s", i+1, entries[i]));
        }
        
        System.out.print("\nFai la tua scelta: ");
        
    }
    
    @Override
    public int showMenu(String title, String[] entries) {
        
                    
        Scanner in = new Scanner(System.in);
        
        while(true)
        {
            printMenu(title, entries);
            
            try{
                int choice = in.nextInt();

                if (choice <= entries.length)
                    return choice - 1;
                
                inform("Scelta non valida.");
            } catch (Exception e){
                inform("Formato non valido.");
                in = new Scanner(System.in);
            }
            
        }
        
        
    }
    
}
