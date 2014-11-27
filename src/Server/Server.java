package Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Server {
    
    private PrintWriter out;
    private final BufferedReader bufferedReader;
    private final int PORT = 8888;
    private final int numberOfClients = 2;

    public Server() throws FileNotFoundException {
        FileReader fileReader = new FileReader("dictionary.txt");
        bufferedReader = new BufferedReader(fileReader);
    }

    private void startServer()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for clients to connect...");
                        
            PrintWriter[] writers = new PrintWriter[numberOfClients];
            
            int i = 0;
            while(i < numberOfClients)
            {
                Socket socket = serverSocket.accept();
                writers[i] = new PrintWriter(socket.getOutputStream());
                System.out.println("Client " + (i + 1) + " connected");               
                i++;
            }
            System.out.println("Executing crackers");
            transferWords(writers);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "OH NOOOO!");
        }                
    }
    
    private void transferWords(PrintWriter[] writers) throws IOException
    {
        int client = 0;
        
        while(true)
        {
            String word = bufferedReader.readLine();
            out = writers[client];
            out.println(word);
            out.flush();
            
            if(word == null) break;
            if(client < numberOfClients - 1)
            {
                client++;                
            }         
            else 
            {
                client = 0;
            }  
        }
    }
    
    public static void main(String[] args) throws IOException
    {       
        new Server().startServer();
    }
}