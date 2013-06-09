
/**
* @project Web
* @author Dayong.Shen
* @package isiteam.TwitterCrawler.mainApp
* @file UserInfoDetailsCrawler.java
* 
* @date 2013-6-9-上午8:26:40
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package isiteam.TwitterCrawler.mainApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import isiteam.TwitterCrawler.database.bean.SeedsQueue;
import isiteam.TwitterCrawler.database.bean.UserFriends;
import isiteam.TwitterCrawler.database.bean.UserInfo;
import isiteam.TwitterCrawler.database.dao.SeedsQueueDao;
import isiteam.TwitterCrawler.database.dao.UserInfoDao;
import isiteam.TwitterCrawler.util.AppContext;
import isiteam.TwitterCrawler.util.CharUtil;
import isiteam.TwitterCrawler.util.Constant;

import javax.annotation.Resource;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import twitter4j.IDs;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.conf.PropertyConfiguration;


/**
 * @project Web
 * @author Dayong.Shen
 * @class UserInfoDetailsCrawler
 * 
 * @date 2013-6-9-上午8:26:40
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */
@Controller
public class UserInfoDetailsCrawler {
	private static final Logger log =  LoggerFactory
			.getLogger(UserInfoDetailsCrawler.class);
	
	
	@Resource
	private	SeedsQueueDao seedsQueueDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	private static Properties props = new Properties();
	
	@SuppressWarnings("deprecation")
	private void startCrawling(String propertyName) {
		// TODO Auto-generated method stub
		
		GetAccessToken(Constant.TwitterKey_PATH,propertyName);
		
        AccessToken token = new AccessToken(
        		props.getProperty("oauth.accessToken"),
        		props.getProperty("oauth.accessTokenSecret"));
        
		PropertyConfiguration conf = new PropertyConfiguration(props);
		Twitter twitter = new TwitterFactory(conf).getInstance(token);
		
		int Count=100;
		int batchSize=100;		
		
		List<SeedsQueue> Queue = null;
		long[] SeedsIdArr;
				
		List<UserInfo> UserInfoList=new ArrayList<UserInfo>();
		
		boolean isChinese;
		
		while(true){
			
				if(Count>100){
					log.info("用户队列不能大于100！");
					System.exit(0);
				}
				
				try {
					Queue = seedsQueueDao.getSeedsQueueByisUserInfo(Count);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					log.error("getSeedsQueueByisUserInfo ERROR!"+e2.getMessage());	
				}
				

				if(Queue==null){//已经取不到数据
					continue;
				}
				log.info("Queue Size: "+Queue.size());
				
				SeedsIdArr=new long[Queue.size()];
				UserInfoList.clear();
				
				for(int j=0;j<Queue.size();j++){
					SeedsIdArr[j]=Long.valueOf(Queue.get(Queue.size()-j-1).getUserId());
				}
								
				
				try {
					
					ResponseList<User> users = twitter.lookupUsers(SeedsIdArr);
					
					
					
					for (int h=0;h<users.size();h++) {
						
						User user = users.get(h);
						
						isChinese=false;
						try {
							
							if(CharUtil.ChinesePercent(user.getStatus().getText())>0.1){
								isChinese=true;
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();							
						}
						
	                    if((isChinese||user.getLang().startsWith("zh-")||CharUtil.ChinesePercent(user.getDescription())>0.1)&&!user.getLang().startsWith("ja")){
	                    	//为中文用户
	                    	UserInfo userInfo=new UserInfo();
	    					
							userInfo.setAllText( String.valueOf(user));//存入String还有问题						
							userInfo.setUserId(String.valueOf(user.getId()));
							userInfo.setScreenName(user.getScreenName());
							userInfo.setName(user.getName());
							userInfo.setLocation(user.getLocation());
							userInfo.setTimeZone(user.getTimeZone());
							userInfo.setLang(user.getLang());
							userInfo.setDescription(user.getDescription());
							userInfo.setCreatedAt(new Timestamp(user.getCreatedAt().getTime()));
							userInfo.setProfileImageUrl(user.getProfileImageURL());
							userInfo.setWebExtendurl(user.getURL());
							userInfo.setIsProtected(user.isProtected()? 1 : 0);
							userInfo.setFollowersCount(user.getFollowersCount());
							userInfo.setFriendsCount(user.getFriendsCount());
							userInfo.setStatusesCount(user.getStatusesCount());
							userInfo.setFavouritesCount(user.getFavouritesCount());
							userInfo.setIsVerified(user.isVerified()? 1 : 0);
							userInfo.setIsGeoEnabled(user.isGeoEnabled()? 1 : 0);
							userInfo.setCrawledNum(Queue.get(h).getIsUserInfo());//指定爬行次数
							
							try {
								userInfo.setCurrentStatuscreatedAt(new Timestamp(user.getStatus().getCreatedAt().getTime()));
								userInfo.setCurrentStatusId(String.valueOf(user.getStatus().getId()));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
							
							userInfo.setInsertTime(new Timestamp(System.currentTimeMillis()));
						
							//加入对列之前，看看数据库有没有
							   if (!userInfoDao.getIsExistUserInfo(userInfo)){
								   UserInfoList.add(userInfo);
							   }
							
							
							
						 }else{//为其他语言用户,则下次不予采集
							 
							 
							 Queue.get(h).setIsDeal(0);//设置禁止采集
							 
							 
						 }//end if
						
						
						
						
						
					}// end for
					
					 try {
						 userInfoDao.batchSaveUserInfoList(UserInfoList,batchSize);
						    } catch (Exception e1) {
							// TODO Auto-generated catch block
						    	log.error("batchSaveUserInfoList ERROR!"+e1.getMessage());	
						    }
					 
				      //更新Seed中的UserInfo字段 					 
					 for(SeedsQueue oneSeed :Queue){
						//更新
						 oneSeed.setIsUserInfo(oneSeed.getIsUserInfo()+1);
						 oneSeed.setInsertTime(new Timestamp(System.currentTimeMillis()));
						 
						  try {
								seedsQueueDao.updateisUserInfo(oneSeed);
							   } catch (Exception e1) {
								// TODO Auto-generated catch block
								log.error("updateisUserInfo ERROR!"+e1.getMessage());	
							   }
						 
					 }
					 
						if(users.getRateLimitStatus().getRemaining()==0){
							log.info("Sleep intervalTime: "+users.getRateLimitStatus().getSecondsUntilReset()/60+" Minutes");
							
							try {
								Thread.sleep(users.getRateLimitStatus().getSecondsUntilReset()*1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								log.error("Sleep Error: " + e1.getMessage());	 
							} 
							
						}
					
					
				} catch (TwitterException te) {
		            log.error("Failed to lookup Users: " + te.getMessage());	 
		            if(te.getStatusCode()==429){
		            	
		            	log.info("Sleep intervalTime: "+te.getRateLimitStatus().getSecondsUntilReset()/60+" Minutes");
						
						try {
							Thread.sleep(te.getRateLimitStatus().getSecondsUntilReset()*1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							log.error("Sleep Error: " + e1.getMessage());	 
						} 
		            }//End If	          
		           }//end Catch

				
			}//  End While
			

			
			
			
		}//end startCrawling
		
		
		


	
	
	/**
	 * @function main
	 * 
	 * @param args
	 * @author Dayong.Shen
	 * @date 2013-6-9-上午8:26:40
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PropertyConfigurator.configureAndWatch(Constant.LOG4J_PATH);
		log.info("正在创建数据库连接和缓冲池...");
	    AppContext.initAppCtx();
		log.info("数据库连接已连接！缓冲池已建立");
	
		UserInfoDetailsCrawler crawler=(UserInfoDetailsCrawler) AppContext.appCtx.getBean("userInfoDetailsCrawler");
		
		if (args.length < 1) {
                // consumer key/secret are not set in twitter4j.properties
                System.out.println(
                        "Usage: java isiteam.TwitterCrawler.mainApp.UserInfoDetailsCrawler [twitter4j-4.propertiesName]");
                System.exit(-1);
           
        } 
		
		crawler.startCrawling(args[0]);

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
