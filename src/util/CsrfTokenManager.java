package util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * トークン発行クラス
 * トークンを発行するクラス
 * @author Akihiro Sasaki
 */
public class CsrfTokenManager {
	private int TOKEN_LENGTH = 16;

	public String getCsrfToken() {
		byte token[] = new byte[TOKEN_LENGTH];
		StringBuffer buf = new StringBuffer();
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			random.nextBytes(token);
			for (int i = 0; i < token.length; i++) {
				buf.append(String.format("%02x", token[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
}
