
/**
* @project Twitter
* @author Dayong.Shen
* @package isiteam.TwitterCrawler.database.dao.impl
* @file TwitterUserInfoDaoImpl.java
* 
* @date 2013-6-6-下午11:12:18
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package isiteam.TwitterCrawler.database.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import isiteam.TwitterCrawler.database.bean.TwitterUserInfo;
import isiteam.TwitterCrawler.database.dao.TwitterUserInfoDao;


/**
 * @project Twitter
 * @author Dayong.Shen
 * @class TwitterUserInfoDaoImpl
 * 
 * @date 2013-6-6-下午11:12:18
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */
@Repository
public class TwitterUserInfoDaoImpl implements TwitterUserInfoDao {
	private static final Logger log = LoggerFactory
			.getLogger(TwitterUserInfoDaoImpl.class);
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Override
	public void save(TwitterUserInfo user) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(user);
	}
}
