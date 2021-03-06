
/**
* @project Twitter
* @author Dayong.Shen
* @package isiteam.TwitterCrawler.database.dao.impl
* @file TweetInfoDaoImpl.java
* 
* @date 2013-6-8-下午12:21:32
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package isiteam.TwitterCrawler.database.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import isiteam.TwitterCrawler.database.bean.TweetInfo;
import isiteam.TwitterCrawler.database.dao.TweetInfoDao;


/**
 * @project Twitter
 * @author Dayong.Shen
 * @class TweetInfoDaoImpl
 * 
 * @date 2013-6-8-下午12:21:32
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */
@Repository
public class TweetInfoDaoImpl implements TweetInfoDao {
	private static final Logger log = LoggerFactory
			.getLogger(TweetInfoDaoImpl.class);
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Override
	public void batchSaveTweetInfoList(final List<TweetInfo> tweetInfoList,
			final int batchSize) {
		// TODO Auto-generated method stub
		
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
        	@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
			       for (int i = 0; i < tweetInfoList.size(); i++) {  
			    	//   writeHase();
			    	   // 写HABSE
			    	   
			    	   
			    	   
	                    try {
							session.save(tweetInfoList.get(i));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}         
	                    if (i % batchSize == 0) {  
	                        try {
								session.flush();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}  
	                        session.clear();  
	                    }  
	                }			       
			       try {
					session.flush();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
                   session.clear();
			       return null; 
			}
		});
		
	}

	@Override
	public boolean getIsExistTweet(final TweetInfo oneTweet) {
		// TODO Auto-generated method stub
		final String hql="from TweetInfo where tweetId = :tweetid";
		List list=this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setString("tweetid", oneTweet.getTweetId());
				List list=query.list();
				return list;
			}
		});
		
		if(list.size()==0){			
		    return false;
	    }else {
	    	return true;
	    }
	
	}
}
