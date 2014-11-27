package Client;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    
    private Scanner passwordFile;
    private Scanner inputServer;
    private final EncrypterEncoder encrypterAndEncoder;
    private final ArrayList<UserInfo> users;

    public Client() throws NoSuchAlgorithmException {
        users = new ArrayList<>();
        encrypterAndEncoder = new EncrypterEncoder();
    }
    
    private void startClient() throws IOException
    {
        Socket socket = new Socket("localHost", 8888);
        inputServer = new Scanner(socket.getInputStream());
        passwordFile = new Scanner(new File("passwords.txt"));
        readUserInfo();
        receiveAndCheck();
        
        socket.close();
    }
        
    private void receiveAndCheck()
    {
        String word;
        
        while(inputServer.hasNext())
        {
            word = inputServer.nextLine();
            checkWordWithVariations(word);
        }
    }
    
    private void checkWordWithVariations(String word) 
    {          
        checkSingleWord(word);
        checkSingleWord(word.toUpperCase());
        checkSingleWord(StringUtilities.capitalize(word));
        checkSingleWord(new StringBuilder(word).reverse().toString());

        /*for (int i = 0; i < 100; i++)
        {
            String possiblePasswordEndDigit = word + i;
            checkSingleWord(possiblePasswordEndDigit);
        }

        for (int i = 0; i < 100; i++)
        {
            String possiblePasswordStartDigit = i + word;
            checkSingleWord(possiblePasswordStartDigit);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 100; j++) {
                String possiblePasswordStartEndDigit = i + word + j;
                checkSingleWord(possiblePasswordStartEndDigit);
            }
        }*/
    }
    
    private void checkSingleWord(String word)
    {
        for(UserInfo user: users)
        {
            String encryptedWord = encrypterAndEncoder.EncryptEncode(word);
                    
            if(encryptedWord.equals(user.getEncryptedPassword()))
                System.out.println(user.getName() + "'s account is cracked. Password: " + user.getPassword());
        }
    }
    
    public static void main(String args[]) throws IOException, NoSuchAlgorithmException
    {
        new Client().startClient();
    }

    private void readUserInfo() {
        
        while(passwordFile.hasNext())
        {
            String name = passwordFile.next();
            String password = passwordFile.next();
            String encryptedPw = passwordFile.next();
            users.add(new UserInfo(name, password, encryptedPw));
        }
    }
}