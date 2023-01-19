import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * We need to hash 3 bytes 10 times because the FlagChecker.checkFlag() only uses the 3
 * bytes of the key. In other words, we need to produce 128 bites per byte.
 * The encryption used in FlagChecker.java is AES which is a symmetric-key encryption algorithm.
 * We need thus to decrypt, using the inversion of the encryption algorithm.
 * The final flag was converted to hex form so we need to convert it back to bytes.
 */
public class Main {

    static String temp = "0eef68c5ef95b67428c178f045e6fc8389b36a67bbbd800148f7c285f938a24e696ee2925e12ecf7c11f35a345a2a142639fe87ab2dd7530b29db87ca71ffda2af558131d7da615b6966fb0360d5823b79c26608772580bf14558e6b7500183ed7dfd41dbb5686ea92111667fd1eff9cec8dc29f0cfe01e092607da9f7c2602f5463a361ce5c83922cb6c3f5b872dcc088eb85df80503c92232bf03feed304d669ddd5ed1992a26674ecf2513ab25c20f95a5db49fdf6167fda3465a74e0418b2ea99eb2673d4c7e1ff7c4921c4e2d7b";


    private static byte[] decrypt(byte[] in, byte[] key) throws Exception {
        Key aesKey = new SecretKeySpec(key, "AES");
        Cipher decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, aesKey);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(in);
        CipherInputStream cipherInputStream = new CipherInputStream(inputStream, decryptCipher);
        byte[] decrypted = new byte[in.length];
        int decryptedBytes = cipherInputStream.read(decrypted);
        cipherInputStream.close();
        return Arrays.copyOfRange(decrypted, 0, decryptedBytes);
    }

    public static byte[] hash(byte[] in) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(in);
        return md.digest();
    }

    public static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }

    private static void decode(byte[] key) throws Exception {
        byte[] currPt = hexStringToByteArray(temp);
        byte[] currKey = key;
        for (int i = 0; i < 10; i++) {
            currKey = hash(currKey);
            currPt = decrypt(currPt, currKey);
        }
        String res = new String(currPt);
        if (res.matches("\\A\\p{ASCII}*\\z")) {
            System.out.println(res);
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = -128; i < 128; i++) {
            for (int j = -128; j < 128; j++) {
                for (int k = -128; k < 128; k++) {
                    byte[] currKey = new byte[]{(byte) k, (byte) j, (byte) i};
                    decode(currKey);
                }
            }
        }

    }
}