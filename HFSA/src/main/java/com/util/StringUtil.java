package com.util;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.bcel.generic.NEW;


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
	/**
	 * 
	 * @param rawTimeString
	 * @return 处理完的正确格式串 yyyymmddhhmm
	 */
	public static String parseTianYatime(String rawTimeString) {
		String[] ymdAndHM = rawTimeString.split("[ |\t]");
		//change 13:21 to 1321
		ymdAndHM[1] = ymdAndHM[1].replaceAll(":", "");
		String timeRegex = "(((?<yyyy>\\d+)年)?((?<mm>\\d+)月)?((?<dd>\\d+)日)?)|(?<today>今天)|(?<yestoday>昨天)";
		Pattern pattern = Pattern.compile(timeRegex);
		Matcher matcher = pattern.matcher(ymdAndHM[0]);
		if (matcher.matches()) {
			//今天动态
			if (matcher.group("today") != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				ymdAndHM[0] = dateFormat.format(new Date());
			} else if (matcher.group("yestoday") != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Calendar calendar = Calendar.getInstance();	//系统当前时间
				calendar.add(Calendar.DATE, -1);
				ymdAndHM[0] = dateFormat.format(calendar.getTime());
			} else {
				if (matcher.group("yyyy") == null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
					ymdAndHM[0] = dateFormat.format(new Date());
				} else {
					ymdAndHM[0] = matcher.group("yyyy");
				}
				ymdAndHM[0] += String.format("%02d", Integer.parseInt(matcher.group("mm")))
						+ String.format("%02d", Integer.parseInt(matcher.group("dd")));
			}
		}
		return ymdAndHM[0] + ymdAndHM[1];
	}
}
