package com.util;
/**
 * LanguageAnalyseUtil类基于哈尔滨工业大学LTP云平台，该平台可实现中文分词，词性标注，命名实体识别
 * 句法分析，语意角色标注等操作。类中实现利用文本发掘兴趣的方法基于石伟杰等的微博用户兴趣发现研究，并加以改进
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 对定义的参数有任何疑问，请访问 http://www.ltp-cloud.com/document/#api_rest_error
 * 获取更多信息
 * @author ice_city
 *
 */
public class LanguageAnalyseUtil {
	private static final String API_KEY = "i8r2P9Q28NjXWEnr0PVZgJptXl1PuzaCUYbfYDKr";	//获取权限的key,勿动
	public static enum Pattern{
		WS("ws"),POS("pos"),NER("ner"),DP("dp"),SDP("sdp"),SRL("srl"),ALL("all");
		private String value;
		public String getValue() {
			return value;
		}
		Pattern(String value) {
	        this.value = value;
	    }
	}
	public static enum Format{
		XML("xml"),JSON("json"),CONLL("conll"),PLAIN("plain");
		private String value;
		public String getValue() {
			return value;
		}
		Format(String value) {
			this.value = value;
		}
	}
	
	//趋向动词表,see http://xh.5156edu.com/page/z7668m4418j19061.html for detail
	private final static Set<String> tendencyVerbs = new HashSet<String>(){{
		add("来");add("上");add("下");add("下");add("进");add("出");add("回");
		add("开");add("过");add("起");add("上来");add("下来");add("进来");add("出来");
		add("回来");add("开来");add("过来");add("起来");add("去");add("上去");
		add("下去");add("进去");add("出去");add("回去");add("开去");add("过去");
	}};
	
	/**
	 * 对输入文本进行分析，具体参数请参考http://www.ltp-cloud.com/document 。
	 * @param text
	 * @param pattern
	 * @param format
	 * @return
	 * @throws IOException 
	 */
	public static String annlyseText(String text,Pattern pattern,Format format) throws IOException {
		String result = "";
		String url = "http://api.ltp-cloud.com/analysis?api_key="+API_KEY+
				"&pattern="+pattern.getValue()+
				"&format="+format.getValue()+
				"&text="+URLEncoder.encode(text, "utf-8");
		URL getUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		//200 代表成功，详情参考java URLConnection
		if (connection.getResponseCode() == 200) {
			InputStream inputStream = new BufferedInputStream(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = bufferedReader.readLine())!=null) {
				result += line+"\n";
			}
		} else {
			System.err.println(connection.getResponseCode());
			result = "error";
		}
		connection.disconnect();
		return result;
	}
	
	/**
	 * 将可能对正确提取核心谓语造成干扰的趋向动词排除。
	 * @param text
	 * @return	修正后的句子
	 * @throws IOException
	 */
	public static String eliminateTendencyVerbs(String text) throws IOException {
		String result = "";
		String temp = annlyseText(text, Pattern.WS, Format.PLAIN);
		String[] words = temp.split(" ");
		for (String string : words) {
			if (tendencyVerbs.contains(string)) {
				continue;
			}
			result += string;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		String text;
		Scanner scanner = new Scanner(System.in);
		text=scanner.nextLine();
		text = eliminateTendencyVerbs(text);
		String result = annlyseText(text, Pattern.DP, Format.PLAIN);
		System.out.println(result);
	}
}
