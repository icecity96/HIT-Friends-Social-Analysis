package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.bcel.generic.NEW;
import org.springframework.util.ResourceUtils;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement.Else;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.Static;
import com.util.ldautil.LdaGibbsSampler;
import com.util.ldautil.LdaUtil;
import com.util.ldautil.Vocabulary;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement.Else;


public class StringUtil {
	private static final String ENGLISH_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static double[][] phi;
	private static String[] id2wordMap;
	private static Map<String, Integer> word2idMap;
	private static boolean flag = false;
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
		String timeRegex = "(((?<yyyy>\\d+)年)?((?<mm>\\d+)月)?((?<dd>\\d+)日)?)|(?<today>今天)|(?<yestoday>昨天)|(?<dbd>前天)";
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
			} else if(matcher.group("dbd") != null){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Calendar calendar = Calendar.getInstance();	//系统当前时间
				calendar.add(Calendar.DATE, -2);
				ymdAndHM[0] = dateFormat.format(calendar.getTime());
			}else {
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
		return ymdAndHM[0] + ymdAndHM[1]+"00";
	}
	
	/**
	 * 0 ：＝ 体育类
	 * 1 ：＝ 旅行摄影美食类
	 * 2 ：＝ 军事和时事类
	 * 3 ：＝ 校园和职场类
	 * 4 ：＝ 汽车
	 * 5 ：＝ 财经类
	 * 6 ：＝ 星座时尚语录
	 * 7 ：＝ 娱乐
	 * 8 ：＝ 科技
	 * 9 ：＝ 健康和养生
	 * @param context
	 * @return 兴趣分类
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public static int getTopic(String context) throws FileNotFoundException, IOException, ClassNotFoundException {
		if (flag == false) {
			//load phi
			ObjectInputStream phiObjectInputStream = new ObjectInputStream(StringUtil.class.getClassLoader().getResourceAsStream("data/phi2.data"));
			phi = (double[][])phiObjectInputStream.readObject();
			phiObjectInputStream.close();
			//finish loading phi
			
			//load vocabulary
			ObjectInputStream id2wordInputStream = new ObjectInputStream(StringUtil.class.getClassLoader().getResourceAsStream("data/id2wordMap"));
			id2wordMap = (String[])id2wordInputStream.readObject();
			id2wordInputStream.close();
			ObjectInputStream word2idInputStream = new ObjectInputStream(StringUtil.class.getClassLoader().getResourceAsStream("data/word2idMap"));
			word2idMap = (Map<String, Integer>)word2idInputStream.readObject();
			word2idInputStream.close();
			flag = true;
		}	
		Vocabulary vocabulary = new Vocabulary(word2idMap, id2wordMap);
		//finish construct vocabulary
		
		//原始自内容进行分词处理,提取关键词存入rstring
		String rString = "";
		List<Term> terms = ToAnalysis.parse(context).getTerms();
		for (Term term : terms) {
			//无词性词
			if (term.natrue().equals(term.natrue().NULL)) {
				continue;
			}
			rString += term.getName() + " ";
		}
		
		//预处理结果进一步切割，提取,并形成文档
		String[] words = rString.split(" ");
		List<Integer> wordList = new LinkedList<Integer>();
		for (String word : words) {
			//忽略单字
			if (word.trim().length() < 2) {
				continue;
			}
			Integer id = vocabulary.getId(word);
			if (id != null) {
				wordList.add(id);
			}
		}
		int[] document = new int[wordList.size()];
		int i = 0;
		for (Integer integer : wordList) {
			document[i++] = integer;
		}
		
		//预测兴趣
		double[] tp = LdaGibbsSampler.inference(phi, document);
		return LdaUtil.translate(tp, phi, vocabulary, 10);
	}
}
