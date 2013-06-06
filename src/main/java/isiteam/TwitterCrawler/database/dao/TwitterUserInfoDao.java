
/**
* @project Twitter
* @author Dayong.Shen
* @package isiteam.TwitterCrawler.database.dao
* @file TwitterUserInfoDao.java
* 
* @date 2013-6-6-下午11:11:53
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package isiteam.TwitterCrawler.database.dao;

import isiteam.TwitterCrawler.database.bean.TwitterUserInfo;


/**
 * @project Twitter
 * @author Dayong.Shen
 * @class TwitterUserInfoDao
 * 
 * @date 2013-6-6-下午11:11:53
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */

public interface TwitterUserInfoDao {

	void save(TwitterUserInfo user);

}
