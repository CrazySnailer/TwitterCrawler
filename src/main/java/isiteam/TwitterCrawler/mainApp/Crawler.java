
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
import isiteam.TwitterCrawler.util.OSUtil;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;



/**
 * @project Twitter
 * @author Dayong.Shen
 * @class Crawler
 * 
 * @date 2013-6-6-下午11:28:58
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */
//@ApplicationContext(locations = "classpath:applicationContext.xml")
public class Crawler {
	
	/*@Resource
	public  ApplicationContext appCtx;



	// init internal application context
	public  void initAppCtx() {
		if(OSUtil.isWindowsOS()){
			appCtx = new FileSystemXmlApplicationContext(new String[] {
					Constant.applicationContext_PATH});
		}else{
			appCtx = new FileSystemXmlApplicationContext("/"+Constant.applicationContext_PATH);
		}
		
	}
	
	@Resource
    private ApplicationContext context;


    
	
	@Resource
	private TwitterFansFriendDao twitterFansFriendDao;
	
	@Resource
	private TwitterTweetCommentDao twitterTweetCommentDao;
	
	@Resource
	private TwitterTweetInfoDao twitterTweetInfoDao;
	
	@Resource*/
	private static TwitterUserInfoDao twitterUserInfoDao;

    public void save(){
		
    	TwitterUserInfo user=new TwitterUserInfo();
		user.setUsername("OOXXOO");
	
		
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
		//log.info("正在创建数据库连接和缓冲池...");
	    //AppContext.initAppCtx();
		//log.info("数据库连接已连接！缓冲池已建立");
		
	    ApplicationContext appCtx = new FileSystemXmlApplicationContext(new String[] {
				Constant.applicationContext_PATH});
	
		Crawler crawler=new Crawler();
		twitterUserInfoDao=(TwitterUserInfoDao) appCtx.getBean("twitterUserInfoDao");
		crawler.save();

	}

}
