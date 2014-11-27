
package Client;

public class UserInfo {
    
    private String name;
    private String password;
    private String encryptedPassword;

    public UserInfo(String name, String password, String encryptedPassword) {
        this.name = name;
        this.password = password;
        this.encryptedPassword = encryptedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "name=" + name + ", password=" + password + ", encryptedPassword=" + encryptedPassword + '}';
    }
}
