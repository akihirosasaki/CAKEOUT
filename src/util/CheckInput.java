package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 入力チェッククラス
 * ユーザーが入力した情報の正規表現、DBとの整合性チェックをするクラス
 * @author Akihiro Sasak
 */
public class CheckInput {
	/**
	 * @param mailAdd
	 * @return mailFormatCheck
	 */
	public String CheckMail(String mailAdd) {
		String mailFormatCheck = "true";
		String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
		if (!mailAdd.matches(mailFormat)) {
			mailFormatCheck = "false";
		}
		return mailFormatCheck;
	}

	/**
	 * @param password
	 * @return isPassCheck
	 */
	public String CheckPass(String password) {
		String isPassCheck = "true";
		Pattern passPettern = Pattern.compile("/^(?=.*?[a-z])(?=.*?\\d)[a-z\\d]{8,100}$/i");
		Matcher passMatch = passPettern.matcher(password);
		Boolean result = passMatch.find();
		if (result) {
			isPassCheck = "false";
		}
		return isPassCheck;
	}

	/**
	 * @param userName
	 * @return userLengthCheck
	 */
	public String CheckUserLength(String userName) {
		String userLengthCheck = "true";
		if (userName.length() > 16) {
			userLengthCheck = "false";
		}
		return userLengthCheck;
	}

	/**
	 * @param password
	 * @return passLengthCheck
	 */
	public String CheckPassLength(String password) {
		String passLengthCheck = "true";
		if (password.length() > 16) {
			passLengthCheck = "false";
		}
		return passLengthCheck;
	}

	/**
	 * @param input
	 * @return isExceptionString
	 */
	public String CheckException(String input) {
		String exceptPattern = "<|>|\"|\'|&";
		Pattern p = Pattern.compile(exceptPattern);
		Matcher m = p.matcher(input);
		String isExceptionString = "true";

		if (m.find() || "".equals(input)) {
			isExceptionString = "false";
		}
		return isExceptionString;
	}
}
