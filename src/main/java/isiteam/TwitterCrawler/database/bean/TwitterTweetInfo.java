package isiteam.TwitterCrawler.database.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TwitterTweetInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "twitter_tweet_info", catalog = "twittercrawler", uniqueConstraints = @UniqueConstraint(columnNames = {
		"username", "tweetid" }))
public class TwitterTweetInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String username;
	private String tweetid;
	private String retweetusername;
	private String tweeturl;
	private String content;
	private String pubtime;
	private String minipubtime;
	private Timestamp inserttime;
	private Integer language;
	private Integer retweetnum;
	private Integer storenum;
	private Timestamp lastcommenttime;
	private String lastcursor;

	// Constructors

	/** default constructor */
	public TwitterTweetInfo() {
	}

	/** full constructor */
	public TwitterTweetInfo(String username, String tweetid,
			String retweetusername, String tweeturl, String content,
			String pubtime, String minipubtime, Timestamp inserttime,
			Integer language, Integer retweetnum, Integer storenum,
			Timestamp lastcommenttime, String lastcursor) {
		this.username = username;
		this.tweetid = tweetid;
		this.retweetusername = retweetusername;
		this.tweeturl = tweeturl;
		this.content = content;
		this.pubtime = pubtime;
		this.minipubtime = minipubtime;
		this.inserttime = inserttime;
		this.language = language;
		this.retweetnum = retweetnum;
		this.storenum = storenum;
		this.lastcommenttime = lastcommenttime;
		this.lastcursor = lastcursor;
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

	@Column(name = "username", length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "tweetid", length = 100)
	public String getTweetid() {
		return this.tweetid;
	}

	public void setTweetid(String tweetid) {
		this.tweetid = tweetid;
	}

	@Column(name = "retweetusername", length = 100)
	public String getRetweetusername() {
		return this.retweetusername;
	}

	public void setRetweetusername(String retweetusername) {
		this.retweetusername = retweetusername;
	}

	@Column(name = "tweeturl", length = 100)
	public String getTweeturl() {
		return this.tweeturl;
	}

	public void setTweeturl(String tweeturl) {
		this.tweeturl = tweeturl;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "pubtime", length = 100)
	public String getPubtime() {
		return this.pubtime;
	}

	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}

	@Column(name = "minipubtime", length = 50)
	public String getMinipubtime() {
		return this.minipubtime;
	}

	public void setMinipubtime(String minipubtime) {
		this.minipubtime = minipubtime;
	}

	@Column(name = "inserttime", length = 19)
	public Timestamp getInserttime() {
		return this.inserttime;
	}

	public void setInserttime(Timestamp inserttime) {
		this.inserttime = inserttime;
	}

	@Column(name = "language")
	public Integer getLanguage() {
		return this.language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	@Column(name = "retweetnum")
	public Integer getRetweetnum() {
		return this.retweetnum;
	}

	public void setRetweetnum(Integer retweetnum) {
		this.retweetnum = retweetnum;
	}

	@Column(name = "storenum")
	public Integer getStorenum() {
		return this.storenum;
	}

	public void setStorenum(Integer storenum) {
		this.storenum = storenum;
	}

	@Column(name = "lastcommenttime", length = 19)
	public Timestamp getLastcommenttime() {
		return this.lastcommenttime;
	}

	public void setLastcommenttime(Timestamp lastcommenttime) {
		this.lastcommenttime = lastcommenttime;
	}

	@Column(name = "lastcursor", length = 50)
	public String getLastcursor() {
		return this.lastcursor;
	}

	public void setLastcursor(String lastcursor) {
		this.lastcursor = lastcursor;
	}

}