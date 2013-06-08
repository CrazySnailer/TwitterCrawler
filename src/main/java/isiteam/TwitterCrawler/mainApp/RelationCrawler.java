
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

import java.util.List;

import isiteam.TwitterCrawler.database.bean.SeedsQueue;
import isiteam.TwitterCrawler.database.dao.SeedsQueueDao;
import isiteam.TwitterCrawler.database.dao.UserFriendsDao;
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
public class RelationCrawler {
	private static final Logger log =  LoggerFactory
			.getLogger(RelationCrawler.class);

	@Resource
	private	SeedsQueueDao seedsQueueDao;
	
	@Resource
	private	UserFriendsDao userFriendsDao;
		
	public void startCrawling(){
		
		int Count=1;
		
		//while(true){
			
			List<SeedsQueue> Queue=seedsQueueDao.getSeedsQueue(Count);
			
			log.info(Queue.get(0).getUserId());
			
			
		//}
		
		
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
	
		RelationCrawler crawler=(RelationCrawler) AppContext.appCtx.getBean("relationCrawler");
		
		crawler.startCrawling();

	}

}
