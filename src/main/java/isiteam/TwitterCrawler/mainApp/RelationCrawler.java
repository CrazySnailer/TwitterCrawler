
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


import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import isiteam.TwitterCrawler.database.bean.SeedsQueue;
import isiteam.TwitterCrawler.database.bean.UserFriends;
import isiteam.TwitterCrawler.database.dao.SeedsQueueDao;
import isiteam.TwitterCrawler.database.dao.UserFriendsDao;
import isiteam.TwitterCrawler.util.AppContext;
import isiteam.TwitterCrawler.util.Constant;



import javax.annotation.Resource;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.PropertyConfiguration;




/**
 * @project Twitter
 * @author Dayong.Shen
 * @class Crawler
 * 
 * @date 2013-6-6-下午11:28:58
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */
@Controller
public class RelationCrawler {
	private static final Logger log =  LoggerFactory
			.getLogger(RelationCrawler.class);

	@Resource
	private	SeedsQueueDao seedsQueueDao;
	
	@Resource
	private	UserFriendsDao userFriendsDao;
	
	private static Properties props = new Properties();
	
	
	public void startCrawling(String propertyName){
		
		GetAccessToken(Constant.TwitterKey_PATH,propertyName);
			
        AccessToken token = new AccessToken(
        		props.getProperty("oauth.accessToken"),
        		props.getProperty("oauth.accessTokenSecret"));
        
        log.info("props's accessToken is: "+props.getProperty("oauth.accessToken"));
        
		PropertyConfiguration conf = new PropertyConfiguration(props);
		Twitter twitter = new TwitterFactory(conf).getInstance(token);
		
		
		int Count=15;
		long cursor = -1;
		int batchSize=100;
		IDs ids;
		
		List<SeedsQueue> SeedsQueueList=new ArrayList<SeedsQueue>();
		List<UserFriends> UserFriendsList=new ArrayList<UserFriends>();
		List<SeedsQueue> Queue = null;
		
		while(true){
			
			if(Count>15){
				log.info("用户队列不能大于15！");
				System.exit(0);
			}
			
			try {
				Queue = seedsQueueDao.getSeedsQueueByisFriendsInfo(Count);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				log.error("getSeedsQueueByisFriendsInfo ERROR!"+e2.getMessage());	
			}
						
			
			if(Queue==null){//已经取不到数据
				continue;
			}
			log.info("Queue Size: "+Queue.size());

			
			for(SeedsQueue e:Queue){
				
				log.info("采集用户: "+e.getUserId());
				
				try {
					ids = twitter.getFriendsIDs(Long.valueOf(e.getUserId()), cursor);
					
					//清除列表，以进一步添加数据
					SeedsQueueList.clear();
					UserFriendsList.clear();
				   
					   for (long id : ids.getIDs()) {
		                
						   //插入好友关系
						   UserFriends newFriend=new UserFriends();
						   
						   newFriend.setUserId(e.getUserId());
						   newFriend.setFriendsId(String.valueOf(id));
						   newFriend.setCrawledNum(e.getIsFriendsInfo()+1);
						   newFriend.setInsertTime(new Timestamp(System.currentTimeMillis()));
						   
						   if (!userFriendsDao.getIsExistFriends(newFriend)){
							   UserFriendsList.add(newFriend);
						   }
						   
						   
						   //增加种子节点
						   SeedsQueue newSeed=new SeedsQueue();
						   
						   newSeed.setUserId(String.valueOf(id));
						   newSeed.setIsFriendsInfo(0);
						   newSeed.setIsTweetsInfo(0);
						   newSeed.setIsUserInfo(0);
						   newSeed.setIsDeal(1);
						   newSeed.setLevel(e.getLevel()+1);
						   newSeed.setInsertTime(new Timestamp(System.currentTimeMillis()));
						   
						   if (!seedsQueueDao.getIsExistSeed(newSeed)){
							    SeedsQueueList.add(newSeed);
						   }
						   
		               }//end for
					   
					   try {
							userFriendsDao.batchSaveUserFriends(UserFriendsList,batchSize);
						    } catch (Exception e1) {
							// TODO Auto-generated catch block
						    	log.error("batchSaveUserFriends ERROR!"+e1.getMessage());	
						    }
					   
					   try {
							seedsQueueDao.batchSaveSeedsQueue(SeedsQueueList,batchSize);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							log.error("batchSaveSeedsQueue ERROR!"+e1.getMessage());		
						}
					   
					 
					   
					     //更新
					      e.setIsFriendsInfo(e.getIsFriendsInfo()+1);
					      e.setInsertTime(new Timestamp(System.currentTimeMillis()));
					   try {
						seedsQueueDao.updateIsFriendsInfo(e);
					   } catch (Exception e1) {
						// TODO Auto-generated catch block
						log.error("SeedsQueue update ERROR!"+e1.getMessage());	
					   }
					   
					   
						if(ids.getRateLimitStatus().getRemaining()==0){
							log.info("Sleep intervalTime: "+ids.getRateLimitStatus().getSecondsUntilReset()/60+" Minutes");
							
							try {
								Thread.sleep(ids.getRateLimitStatus().getSecondsUntilReset()*1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								log.error("Sleep Error: " + e1.getMessage());	 
							} 
							
						}
						   
				   
				   } catch (TwitterException te) {
		            log.error("Failed to get friends' ids: " + te.getMessage());	 
		            if(te.getStatusCode()==429){
		            	
		            	log.info("Sleep intervalTime: "+te.getRateLimitStatus().getSecondsUntilReset()/60+" Minutes");
						
						try {
							Thread.sleep(te.getRateLimitStatus().getSecondsUntilReset()*1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							log.error("Sleep Error: " + e1.getMessage());	 
						} 
		            }//End If
		           }//End Catch
				
			}
			
		
			

			
		}
		
		
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
		
		if (args.length < 1) {
                // consumer key/secret are not set in twitter4j.properties
                System.out.println(
                        "Usage: java isiteam.TwitterCrawler.mainApp.RelationCrawler [twitter4j-4.propertiesName]");
                System.exit(-1);
           
        } 
		
		crawler.startCrawling(args[0]);

	}// end main
	
public void GetAccessTokenList(String filePath){
		
		
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
	    	log.info(children[i]);
	      
	      	File file = new File(filePath+"\\"+children[i]);
	        Properties prop = new Properties();
	        InputStream is = null;
	        OutputStream os = null;
	     
	        try {
	        	
	            if (file.exists()) {
	                is = new FileInputStream(file);
	                prop.load(is);
	            }
	          
	            if (null == prop.getProperty("oauth.consumerKey")
	                        && null == prop.getProperty("oauth.consumerSecret")
	                        &&null==prop.getProperty("HTTP_PROXY_HOST")
	                        &&null==prop.getProperty("HTTP_PROXY_PORT")) {
	                    // consumer key/secret are not set in twitter4j.properties
	                	log.info("Invalid Twitter Properties");
	                    System.exit(-1);
	                }	            
	           
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	            System.exit(-1);
	        } finally {
	            if (is != null) {
	                try {
	                    is.close();
	                } catch (IOException ignore) {
	                }
	            }
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException ignore) {
	                }
	            }
	        }
	      
	    }//end for
		
		
	}
	
	
	
   public void GetAccessToken(String filePath,String propertyName){		
		

		File file = new File(filePath,propertyName);
		if(!file.exists()){
			log.info("文件不存在！");
			return;
		}
		
	//        Properties prop = new Properties();
	        InputStream is = null;
	        OutputStream os = null;
	     
	        try {
	        	
	            if (file.exists()) {
	                is = new FileInputStream(file);
	                props.load(is);
	            }
	          
	            if (null == props.getProperty("oauth.consumerKey")
	                        && null == props.getProperty("oauth.consumerSecret")
	                        &&null==props.getProperty("http.proxyHost")
	                        &&null==props.getProperty("http.proxyPort")
	                        &&null==props.getProperty("oauth.accessToken")
	                        &&null==props.getProperty("oauth.accessTokenSecret")) {
	                    // consumer key/secret are not set in twitter4j.properties
	                	log.info("Invalid Twitter Properties");
	                    System.exit(-1);
	                }	            
	           
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	            System.exit(-1);
	        } finally {
	            if (is != null) {
	                try {
	                    is.close();
	                } catch (IOException ignore) {
	                }
	            }
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException ignore) {
	                }
	            }
	        }
	      
	
		
		
	}

}
