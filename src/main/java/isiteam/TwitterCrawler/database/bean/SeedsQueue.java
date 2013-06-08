package isiteam.TwitterCrawler.database.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SeedsQueue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "seeds_queue", catalog = "twittercrawler", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))
public class SeedsQueue implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private Integer isUserInfo;
	private Integer isFriendsInfo;
	private Integer isTweetsInfo;
	private Integer level;
	private Timestamp insertTime;

	// Constructors

	/** default constructor */
	public SeedsQueue() {
	}

	/** full constructor */
	public SeedsQueue(String userId, Integer isUserInfo, Integer isFriendsInfo,
			Integer isTweetsInfo, Integer level, Timestamp insertTime) {
		this.userId = userId;
		this.isUserInfo = isUserInfo;
		this.isFriendsInfo = isFriendsInfo;
		this.isTweetsInfo = isTweetsInfo;
		this.level = level;
		this.insertTime = insertTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userId", unique = true, length = 20)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "isUserInfo")
	public Integer getIsUserInfo() {
		return this.isUserInfo;
	}

	public void setIsUserInfo(Integer isUserInfo) {
		this.isUserInfo = isUserInfo;
	}

	@Column(name = "isFriendsInfo")
	public Integer getIsFriendsInfo() {
		return this.isFriendsInfo;
	}

	public void setIsFriendsInfo(Integer isFriendsInfo) {
		this.isFriendsInfo = isFriendsInfo;
	}

	@Column(name = "isTweetsInfo")
	public Integer getIsTweetsInfo() {
		return this.isTweetsInfo;
	}

	public void setIsTweetsInfo(Integer isTweetsInfo) {
		this.isTweetsInfo = isTweetsInfo;
	}

	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "insertTime", length = 19)
	public Timestamp getInsertTime() {
		return this.insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

}