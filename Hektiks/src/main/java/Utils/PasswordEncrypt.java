package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
* Classe per l'ecryptazione della password
*/
public class PasswordEncrypt {

    //Implementazione stardard dell'algoritmo SHA1
    public static String sha1(String password) throws NoSuchAlgorithmException {

        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();

        for (byte b : result)
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }
}
