package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文字列ハッシュ化クラス
 * 文字列をSHA-512でハッシュ化するクラス
 * @author Akihiro Sasaki
 */
public class Digest {
	public static final String SHA512 = "SHA-512";
	private MessageDigest messageDigest;

	public Digest(String algorithm) {
		super();

		if (algorithm == null) {
			throw new NullPointerException("no Algorithm");
		}
		try {
			this.messageDigest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String hex(String message) throws UnsupportedEncodingException {
		if (message == null)
			throw new NullPointerException("no message");
		if (this.messageDigest == null)
			return "";
		StringBuilder builder = new StringBuilder();
		messageDigest.reset();
		messageDigest.update(message.getBytes("UTF-8"));
		byte[] digest = messageDigest.digest();
		for (int i = 0; i < digest.length; i++) {
			int d = digest[i] & 0xff;
			String hex = Integer.toHexString(d);
			if (hex.length() == 1) {
				builder.append("0");
			}
			builder.append(hex);
		}
		return builder.toString();
	}
}
