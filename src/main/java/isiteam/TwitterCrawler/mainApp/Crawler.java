
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

import isiteam.TwitterCrawler.database.bean.TwitterUserInfo;
import isiteam.TwitterCrawler.database.dao.TwitterFansFriendDao;
import isiteam.TwitterCrawler.database.dao.TwitterTweetCommentDao;
import isiteam.TwitterCrawler.database.dao.TwitterTweetInfoDao;
import isiteam.TwitterCrawler.database.dao.TwitterUserInfoDao;
import isiteam.TwitterCrawler.util.AppContext;
import isiteam.TwitterCrawler.util.Constant;



import javax.annotation.Resource;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;




/**
 * @project Twitter
 * @author Dayong.Shen
 * @class Crawler
 * 
 * @date 2013-6-6-下午11:28:58
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */
@Service
public class Crawler {
	private static final Logger log =  LoggerFactory
			.getLogger(Crawler.class);

	@Resource
	private TwitterFansFriendDao twitterFansFriendDao;
	
	@Resource
	private TwitterTweetCommentDao twitterTweetCommentDao;
	
	@Resource
	private TwitterTweetInfoDao twitterTweetInfoDao;
	
	@Resource
	private TwitterUserInfoDao twitterUserInfoDao;

    public void save(){
		
    	TwitterUserInfo user=new TwitterUserInfo();
		user.setUsername("PPPPPPPOXXOOOP");
	
		
		twitterUserInfoDao.save(user);
		
	}
	
	/**
	 * @function main
	 * 
	 * @param args
	 * @author Dayong.Shen
	 * @date 2013-6-6-下午11:28:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    PropertyConfigurator.configureAndWatch(Constant.LOG4J_PATH);
		log.info("正在创建数据库连接和缓冲池...");
	    AppContext.initAppCtx();
		log.info("数据库连接已连接！缓冲池已建立");
	
		Crawler crawler=(Crawler) AppContext.appCtx.getBean("crawler");
		crawler.save();

	}

}
