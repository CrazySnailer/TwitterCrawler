
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
	}
}
