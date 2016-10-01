package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static final String ENGLISH_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * 根据首字母或者汉字来绘制头像
	 * @param nickname
	 * @return
	 */
	public static String getCharString(String nickname) {
		if (nickname == null) {
			return String.valueOf(ENGLISH_CHARS.charAt((int)(Math.random()*26)));
		}
		
		char[] chars = nickname.toCharArray();
		char c = chars[0];
		if (Character.isLetter(c)) {	
			//字母
			return String.valueOf(Character.toUpperCase(c));
		} else {
			//不是字母
			return String.valueOf(c);
		}
	}
	/**
	 * 利用正则判断是否符合email格式
	 * @param email
	 * @return 是否email格式
	 */
	public static boolean isEmail(String email){
		if (email==null || "".equals(email)) {
			return false;
		}
		Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
