
/**
* @project Twitter
* @author Dayong.Shen
* @package isiteam.TwitterCrawler.database.dao.impl
* @file UserFriendsDaoImpl.java
* 
* @date 2013-6-8-下午12:22:07
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package isiteam.TwitterCrawler.database.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import isiteam.TwitterCrawler.database.dao.UserFriendsDao;


/**
 * @project Twitter
 * @author Dayong.Shen
 * @class UserFriendsDaoImpl
 * 
 * @date 2013-6-8-下午12:22:07
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */
@Repository
public class UserFriendsDaoImpl implements UserFriendsDao {
	private static final Logger log = LoggerFactory
			.getLogger(UserFriendsDaoImpl.class);
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
}
