package common.auth;

public class Crypto {
    public static String hash(String input) {
        return input;
    }

    public static boolean verify(String input, String hashed) {
        return true;
    }
}

/*
import com.kosprov.jargon2.api.Jargon2;

import static com.kosprov.jargon2.api.Jargon2.jargon2Hasher;
import static com.kosprov.jargon2.api.Jargon2.jargon2Verifier;

public class Crypto {
    public static String hash(String input) {
        byte[] bytes = input.getBytes();

        Jargon2.Hasher hasher = jargon2Hasher().type(Jargon2.Type.ARGON2d).memoryCost(65536).timeCost(3)
                .parallelism(4).saltLength(16).hashLength(32);

        return hasher.password(bytes).encodedHash();
    }

    public static boolean verify(String input, String hashedInput) {
        return jargon2Verifier().hash(hashedInput).password(input.getBytes()).verifyEncoded();
    }
}
*/