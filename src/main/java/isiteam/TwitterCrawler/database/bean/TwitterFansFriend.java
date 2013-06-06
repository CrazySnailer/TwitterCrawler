package isiteam.TwitterCrawler.database.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TwitterFansFriend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "twitter_fans_friend", catalog = "twittercrawler", uniqueConstraints = @UniqueConstraint(columnNames = {
		"username", "fansorfriendname", "fansorfriend" }))
public class TwitterFansFriend implements java.io.Serializable {

	// Fields

	private Long id;
	private String username;
	private String fansorfriendname;
	private String nickname;
	private Integer followernum;
	private Integer friendsnum;
	private Integer tweetnum;
	private String regtime;
	private String location;
	private Integer tweetdone;
	private Integer fansdone;
	private Integer levels;
	private Timestamp lastcollecttime;
	private Integer fansorfriend;

	// Constructors

	/** default constructor */
	public TwitterFansFriend() {
	}

	/** full constructor */
	public TwitterFansFriend(String username, String fansorfriendname,
			String nickname, Integer followernum, Integer friendsnum,
			Integer tweetnum, String regtime, String location,
			Integer tweetdone, Integer fansdone, Integer levels,
			Timestamp lastcollecttime, Integer fansorfriend) {
		this.username = username;
		this.fansorfriendname = fansorfriendname;
		this.nickname = nickname;
		this.followernum = followernum;
		this.friendsnum = friendsnum;
		this.tweetnum = tweetnum;
		this.regtime = regtime;
		this.location = location;
		this.tweetdone = tweetdone;
		this.fansdone = fansdone;
		this.levels = levels;
		this.lastcollecttime = lastcollecttime;
		this.fansorfriend = fansorfriend;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "fansorfriendname")
	public String getFansorfriendname() {
		return this.fansorfriendname;
	}

	public void setFansorfriendname(String fansorfriendname) {
		this.fansorfriendname = fansorfriendname;
	}

	@Column(name = "nickname")
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "followernum")
	public Integer getFollowernum() {
		return this.followernum;
	}

	public void setFollowernum(Integer followernum) {
		this.followernum = followernum;
	}

	@Column(name = "friendsnum")
	public Integer getFriendsnum() {
		return this.friendsnum;
	}

	public void setFriendsnum(Integer friendsnum) {
		this.friendsnum = friendsnum;
	}

	@Column(name = "tweetnum")
	public Integer getTweetnum() {
		return this.tweetnum;
	}

	public void setTweetnum(Integer tweetnum) {
		this.tweetnum = tweetnum;
	}

	@Column(name = "regtime", length = 100)
	public String getRegtime() {
		return this.regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	@Column(name = "location", length = 100)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "tweetdone")
	public Integer getTweetdone() {
		return this.tweetdone;
	}

	public void setTweetdone(Integer tweetdone) {
		this.tweetdone = tweetdone;
	}

	@Column(name = "fansdone")
	public Integer getFansdone() {
		return this.fansdone;
	}

	public void setFansdone(Integer fansdone) {
		this.fansdone = fansdone;
	}

	@Column(name = "levels")
	public Integer getLevels() {
		return this.levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	@Column(name = "lastcollecttime", length = 19)
	public Timestamp getLastcollecttime() {
		return this.lastcollecttime;
	}

	public void setLastcollecttime(Timestamp lastcollecttime) {
		this.lastcollecttime = lastcollecttime;
	}

	@Column(name = "fansorfriend")
	public Integer getFansorfriend() {
		return this.fansorfriend;
	}

	public void setFansorfriend(Integer fansorfriend) {
		this.fansorfriend = fansorfriend;
	}

}