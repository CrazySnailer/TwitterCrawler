
/**
* @project Twitter
* @author Dayong.Shen
* @package isiteam.TwitterCrawler.mainApp
* @file Crawler.java
* 
* @date 2013-6-6-下午11:28:58
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package isiteam.TwitterCrawler.mainApp;

import isiteam.TwitterCrawler.database.dao.TwitterFansFriendDao;
import isiteam.TwitterCrawler.database.dao.TwitterTweetCommentDao;
import isiteam.TwitterCrawler.database.dao.TwitterTweetInfoDao;
import isiteam.TwitterCrawler.database.dao.TwitterUserInfoDao;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * @project Twitter
 * @author Dayong.Shen
 * @class Crawler
 * 
 * @date 2013-6-6-下午11:28:58
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */

public class Crawler {
	
	@Resource
	private TwitterFansFriendDao twitterFansFriendDao;
	
	@Resource
	private TwitterTweetCommentDao twitterTweetCommentDao;
	
	@Resource
	private TwitterTweetInfoDao twitterTweetInfoDao;
	
	@Resource
	private TwitterUserInfoDao twitterUserInfoDao;

	/**
	 * @function main
	 * 
	 * @param args
	 * @author Dayong.Shen
	 * @date 2013-6-6-下午11:28:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("config/applicationContext.xml");

	}

}
