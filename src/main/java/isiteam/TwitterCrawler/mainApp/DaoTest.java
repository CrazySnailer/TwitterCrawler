
/**
* @project Twitter
* @author Dayong.Shen
* @package daoTest
* @file DaoTest.java
* 
* @date 2013-6-6-下午11:45:19
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package isiteam.TwitterCrawler.mainApp;


import isiteam.TwitterCrawler.database.bean.TwitterUserInfo;
import isiteam.TwitterCrawler.database.dao.TwitterFansFriendDao;
import isiteam.TwitterCrawler.database.dao.TwitterTweetCommentDao;
import isiteam.TwitterCrawler.database.dao.TwitterTweetInfoDao;
import isiteam.TwitterCrawler.database.dao.TwitterUserInfoDao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 * @project Twitter
 * @author Dayong.Shen
 * @class DaoTest
 * 
 * @date 2013-6-6-下午11:45:19
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DaoTest {
	/*@Resource
	private TwitterFansFriendDao twitterFansFriendDao;
	
	@Resource
	private TwitterTweetCommentDao twitterTweetCommentDao;
	
	@Resource
	private TwitterTweetInfoDao twitterTweetInfoDao; */
	
	@Resource
	private TwitterUserInfoDao twitterUserInfoDao;

	@Test
	public void save(){
		
		TwitterUserInfo user=new TwitterUserInfo();
		user.setUsername("XoXOO");
	
		
		twitterUserInfoDao.save(user);
		
	}
	
	

}
