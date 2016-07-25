package ipn;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashLib {
	
	public static String sha1Hash(String password) {
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			return new BigInteger(1, crypt.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new UnsupportedOperationException("SHA-1 algorithm not found.");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException("UTF-8 encoding not supported.");
		}
	}
	
	
}
