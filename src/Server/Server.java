package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Server {
    
    private final Scanner in;
    private final int PORT = 8888;
    private final int numberOfClients = 5;

    public Server() throws FileNotFoundException {
        in = new Scanner(new File("dictionary.txt"));
    }

    private void connectClients()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for clients to connect...");

            ArrayList<String> dictionary = readDictionary();
            System.out.println("Total number: " + dictionary.size());
            
            int i = 0;
            while(i < numberOfClients)
            {
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                
                TransferService transferService = new TransferService(numberOfClients, i, out, dictionary);
                
                Thread t = new Thread(transferService);
                t.start();
                
                System.out.println("Client " + (i + 1) + " connected");               
                i++;
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "OH NOOOO!");
        }                
    }
    
    private ArrayList<String> readDictionary() throws IOException
    {
        ArrayList<String> dictionary = new ArrayList<>();
        
        while(in.hasNext())
        {
            dictionary.add(in.nextLine());
        }
        return dictionary;
    }
    
    public static void main(String[] args) throws IOException
    {       
        new Server().connectClients();
    }
}