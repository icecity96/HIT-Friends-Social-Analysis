package com.util;

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
}
