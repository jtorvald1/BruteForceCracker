
package Client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

public class EncrypterEncoder {
    
    private final MessageDigest messageDigest;   

    public EncrypterEncoder() throws NoSuchAlgorithmException
    {
        messageDigest = MessageDigest.getInstance("SHA");        
    }
    
    public String EncryptEncode(String password)
    {
        byte[] digest = messageDigest.digest(password.getBytes());
        String encode64 = Base64.encodeBase64String(digest);
        
        return encode64;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        System.out.println(new EncrypterEncoder().EncryptEncode("BOAT"));
        System.out.println(new EncrypterEncoder().EncryptEncode("Flower"));
        System.out.println(new EncrypterEncoder().EncryptEncode("1giraffe2"));
        System.out.println(new EncrypterEncoder().EncryptEncode("largo12"));
        System.out.println(new EncrypterEncoder().EncryptEncode("secret"));
        System.out.println(new EncrypterEncoder().EncryptEncode("word1"));
    }
}