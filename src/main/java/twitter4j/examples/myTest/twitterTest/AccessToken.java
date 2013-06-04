
/**
* @project Web
* @author Dayong.Shen
* @package twitter4j.examples.myTest.twitterTest
* @file AccessToken.java
* 
* @date 2013-6-4-下午6:26:28
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package twitter4j.examples.myTest.twitterTest;


import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.isiteam.crawler.TwitterCrawler.util.Constant;



/**
 * @project Web
 * @author Dayong.Shen
 * @class AccessToken
 * 
 * @date 2013-6-4-下午6:26:28
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */

public class AccessToken {
	private static Log log = LogFactory.getLog(AccessToken.class);
	
	public void GetAccessToken(String filePath){
		
		final String extend_name = ".properties";
		
		File dir = new File(filePath);
		if(!dir.isDirectory()){
			log.info("不是有效的文件夹目录!");
			return;
		}
		// 所有的文件和目录名
	    String[] children=null;
	    
	    // 可以指定返回文件列表的过滤条件,返回那些以extend_name开头的文件名
	    FilenameFilter filter = new FilenameFilter() {
	      public boolean accept(File dir, String name) {
	        return name.endsWith(extend_name);
	      }
	    };
	    children = dir.list(filter);
	    
	    for (int i = 0; i < children.length; i++) {
	      // 文件名
	      System.out.println(children[i]);
	    }
		
		
	}
	

	/**
	 * @function main
	 * 
	 * @param args
	 * @author Dayong.Shen
	 * @date 2013-6-4-下午6:26:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configureAndWatch(Constant.LOG4J_PATH);
		
		AccessToken accessToken=new AccessToken();
		
		accessToken.GetAccessToken(Constant.TwitterKey_PATH);
	}

}
