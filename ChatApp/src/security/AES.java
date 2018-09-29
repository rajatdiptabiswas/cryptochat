package security;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {

    private static final String ALGO = "AES";
    private byte[] keyValue;

    public AES(String key) {
        this.keyValue = key.getBytes();
    }

    private Key generateKey() throws Exception {
        Key generatedKey = new SecretKeySpec(keyValue, ALGO);

        return generatedKey;
    }

    public String encrpyt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedByteArray = c.doFinal(data.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encryptedByteArray);

        return encryptedValue;
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedByteArray = Base64.getDecoder().decode(encryptedData);
        String decodedValue = new String(c.doFinal(decodedByteArray));

        return decodedValue;
    }
}
