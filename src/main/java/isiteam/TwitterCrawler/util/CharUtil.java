
/**
* @project Web
* @author Dayong.Shen
* @package isiteam.TwitterCrawler.util
* @file CharUtil.java
* 
* @date 2013-6-9-下午1:44:35
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package isiteam.TwitterCrawler.util;

import java.util.regex.Pattern;


/**
 * @project Web
 * @author Dayong.Shen
 * @class CharUtil 字符通用工具类
 * 
 * @date 2013-6-9-下午1:44:35
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */

public class CharUtil {
	
	
	public static float ChinesePercent(String str){
		
		if(str==null){
			return 0;
		}
		
//		System.out.println(str+"  "+str.length());
		
		char[] charArray=str.toCharArray();
		float percent=0;
		for(int i=0;i<charArray.length;i++){
			char c = charArray[i];
			if (isChinese(c)) {
				percent++;
			}			
		}
		
		return percent/charArray.length;
	}
	
	// 根据Unicode编码完美的判断中文汉字和符号
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	// 完整的判断中文汉字和符号
	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	// 只能判断部分CJK字符（CJK统一汉字）
	public static boolean isChineseByREG(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
		return pattern.matcher(str.trim()).find();
	}

	// 只能判断部分CJK字符（CJK统一汉字）
	public static boolean isChineseByName(String str) {
		if (str == null) {
			return false;
		}
		// 大小写不同：\\p 表示包含，\\P 表示不包含
		// \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
		String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(str.trim()).find();
	}

	/**
	 * @function main
	 * 
	 * @param args
	 * @author Dayong.Shen
	 * @date 2013-6-9-下午1:44:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String[] strArr = new String[] { "www.micmiu.com",
				"!@#$%^&*()_+{}[]|\"'?/:;<>,.", "！￥……（）——：；“”‘’《》，。？、", "不要啊",
				"やめて", "韩佳人", "한가인" };
		for (String str : strArr) {
			System.out.println("===========> 测试字符串：" + str);
			System.out.println("正则判断：" + isChineseByREG(str) + " -- "
					+ isChineseByName(str));
			System.out.println("Unicode判断结果 ：" + isChinese(str));
			System.out.println("详细判断列表：");
			char[] ch = str.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				char c = ch[i];
				System.out.println(c + " --> " + (isChinese(c) ? "是" : "否"));
			}
		} */
		
		
		String str="Don't fucking piss me off. 96's ✿ Instagram - queenieong";
		
		System.out.println(ChinesePercent(str));
		
	}//end main

}





