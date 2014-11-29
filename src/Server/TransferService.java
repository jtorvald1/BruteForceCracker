/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Jacob Nørgaard
 */
public class TransferService implements Runnable{
    
    private int numberOfClients;
    private int client;
    private PrintWriter out;
    private ArrayList<String> dictionary;

    public TransferService(int numberOfClients, int client, PrintWriter out, ArrayList<String> dictionary) {
        this.numberOfClients = numberOfClients;
        this.client = client;
        this.out = out;
        this.dictionary = dictionary;
    }
       
    @Override
    public void run()
    {
        double intermediate1 = client/ (float) numberOfClients;
        System.out.println(intermediate1);
        int fromIndex = (int) (intermediate1 * dictionary.size());
        System.out.println("From index: " + fromIndex);
            
        double intermediate2 = (client + 1) / (float) numberOfClients;
        System.out.println("Intermediate: " + intermediate2);
        int toIndex = (int) ((intermediate2) * dictionary.size());
        System.out.println("To index: " + toIndex);
        for(int i = fromIndex; i < toIndex; i++)
        {
            String word = dictionary.get(i);
            System.out.println(i);
            out.println(word);
            out.flush();
        }
    }
}

