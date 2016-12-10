package com.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PassWord {
	/**
	 * 该函数对用户输入的明文密码采用MD5加密，生成密文存储，核对。
	 * http://www.md5.cz 该网站可用了测评生成的md5密文是否正确。
	 * @param plaintext
	 * @return cyphertext
	 * @throws UnsupportedEncodingException 
	 */
	public static String md5encode(String plaintext) {
		String cyphertext = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(plaintext.getBytes());
			byte[] digets = messageDigest.digest();
			StringBuffer stringBuffer = new StringBuffer();
			for (byte b : digets) {
				stringBuffer.append(String.format("%02x", b&0xff));
			}
			cyphertext = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return cyphertext;
	}
	
	/**
	 * 合法密码应当至少8个字符，至少包含一个数字，至少包含一个小写字母和一个大写字母
	 * 特殊字符至少出现一个，
	 * 不包含空格，制表符
	 * explain:
	 * ^                 # start-of-string
	 * (?=.*[0-9])       # a digit must occur at least once
	 * (?=.*[a-z])       # a lower case letter must occur at least once
	 * (?=.*[A-Z])       # an upper case letter must occur at least once
	 * (?=.*[@#$%^&+=])  # a special character must occur at least once
	 * (?=\\S+$)          # no whitespace allowed in the entire string
	 * .{8,}             # anything, at least eight places though
	 * $                 # end-of-string
	 * @param plaintext
	 * @return 密码格式是否合法
	 */
	public static boolean isLegitimate(String plaintext) {
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$&+,:;=?@#|'<>.^*()%!-])(?=\\S+$).{8,}$";
		return plaintext.matches(pattern);
	}
}
