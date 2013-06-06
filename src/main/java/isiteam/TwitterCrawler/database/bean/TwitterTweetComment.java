package isiteam.TwitterCrawler.database.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TwitterTweetComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "twitter_tweet_comment", catalog = "twittercrawler")
public class TwitterTweetComment implements java.io.Serializable {

	// Fields

	private Long id;
	private String fromusername;
	private String fromtweeturl;
	private String commentusername;
	private String commenttweetid;
	private String commenttweeturl;
	private String commentcontent;
	private Timestamp commenttime;
	private String commentminitime;
	private Integer language;
	private Timestamp inserttime;

	// Constructors

	/** default constructor */
	public TwitterTweetComment() {
	}

	/** full constructor */
	public TwitterTweetComment(String fromusername, String fromtweeturl,
			String commentusername, String commenttweetid,
			String commenttweeturl, String commentcontent,
			Timestamp commenttime, String commentminitime, Integer language,
			Timestamp inserttime) {
		this.fromusername = fromusername;
		this.fromtweeturl = fromtweeturl;
		this.commentusername = commentusername;
		this.commenttweetid = commenttweetid;
		this.commenttweeturl = commenttweeturl;
		this.commentcontent = commentcontent;
		this.commenttime = commenttime;
		this.commentminitime = commentminitime;
		this.language = language;
		this.inserttime = inserttime;
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

	@Column(name = "fromusername", length = 100)
	public String getFromusername() {
		return this.fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	@Column(name = "fromtweeturl", length = 100)
	public String getFromtweeturl() {
		return this.fromtweeturl;
	}

	public void setFromtweeturl(String fromtweeturl) {
		this.fromtweeturl = fromtweeturl;
	}

	@Column(name = "commentusername", length = 100)
	public String getCommentusername() {
		return this.commentusername;
	}

	public void setCommentusername(String commentusername) {
		this.commentusername = commentusername;
	}

	@Column(name = "commenttweetid", length = 100)
	public String getCommenttweetid() {
		return this.commenttweetid;
	}

	public void setCommenttweetid(String commenttweetid) {
		this.commenttweetid = commenttweetid;
	}

	@Column(name = "commenttweeturl")
	public String getCommenttweeturl() {
		return this.commenttweeturl;
	}

	public void setCommenttweeturl(String commenttweeturl) {
		this.commenttweeturl = commenttweeturl;
	}

	@Column(name = "commentcontent")
	public String getCommentcontent() {
		return this.commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	@Column(name = "commenttime", length = 19)
	public Timestamp getCommenttime() {
		return this.commenttime;
	}

	public void setCommenttime(Timestamp commenttime) {
		this.commenttime = commenttime;
	}

	@Column(name = "commentminitime", length = 50)
	public String getCommentminitime() {
		return this.commentminitime;
	}

	public void setCommentminitime(String commentminitime) {
		this.commentminitime = commentminitime;
	}

	@Column(name = "language")
	public Integer getLanguage() {
		return this.language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	@Column(name = "inserttime", length = 19)
	public Timestamp getInserttime() {
		return this.inserttime;
	}

	public void setInserttime(Timestamp inserttime) {
		this.inserttime = inserttime;
	}

}