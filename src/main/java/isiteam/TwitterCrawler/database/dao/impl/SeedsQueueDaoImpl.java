
/**
* @project Twitter
* @author Dayong.Shen
* @package isiteam.TwitterCrawler.database.dao.impl
* @file SeedsQueueDaoImpl.java
* 
* @date 2013-6-8-下午12:20:32
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

import isiteam.TwitterCrawler.database.bean.SeedsQueue;
import isiteam.TwitterCrawler.database.dao.SeedsQueueDao;


/**
 * @project Twitter
 * @author Dayong.Shen
 * @class SeedsQueueDaoImpl
 * 
 * @date 2013-6-8-下午12:20:32
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */
@Repository
public class SeedsQueueDaoImpl implements SeedsQueueDao {
	private static final Logger log = LoggerFactory
			.getLogger(SeedsQueueDaoImpl.class);
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Override
	public List<SeedsQueue> getSeedsQueue(final int count) {
		// TODO Auto-generated method stub
		
		try{
			final String hql="from SeedsQueue order by isFriendsInfo ASC, level ASC";
			List list=this.getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query=session.createQuery(hql);
					query.setFirstResult(0);
					query.setMaxResults(count);
					List list=query.list();
					return list;
				}
			});
			return list;
		
		}catch(Exception e){
			log.error("getSeedsQueue ERROR!"+e.getMessage());
			return null;
		}
	}

	@Override
	public void save(SeedsQueue newSeed) {
		// TODO Auto-generated method stub
		try{
			hibernateTemplate.save(newSeed);
		}catch(Exception e){
			log.error("SeedsQueue save ERROR!"+e.getMessage());			
		}
	}

	@Override
	public void update(SeedsQueue e) {
		// TODO Auto-generated method stub
		try{
			hibernateTemplate.update(e);
		}catch(Exception e1){
			log.error("SeedsQueue update ERROR!"+e1.getMessage());			
		}
	}
	
    public void batchSaveSeedsQueue(final List<SeedsQueue> seedsQueue,final int batchSize) {
        
         this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
        	@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
			       for (int i = 0; i < seedsQueue.size(); i++) {  
	                    try {
							session.save(seedsQueue.get(i));
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
	public boolean getIsExistSeed(SeedsQueue newSeed) {
		// TODO Auto-generated method stub
		final String hql="from SeedsQueue where userId = '"+newSeed.getUserId() +"'";
		List list=this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
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
