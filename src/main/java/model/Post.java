package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the post database table.
 * 
 */
@Entity
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="media_url")
	private String mediaUrl;

	@Column(name="publish_time")
	private Timestamp publishTime;

	@Column(name="text_content")
	private String textContent;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="user_id")
	private Appuser appuser;

	//bi-directional many-to-one association to FanartType
	@ManyToOne
	@JoinColumn(name="fanart_id")
	private FanartType fanartType;

	//bi-directional many-to-one association to Media
	@ManyToOne
	private Media media;

	public Post() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMediaUrl() {
		return this.mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public String getTextContent() {
		return this.textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public Appuser getAppuser() {
		return this.appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
	}

	public FanartType getFanartType() {
		return this.fanartType;
	}

	public void setFanartType(FanartType fanartType) {
		this.fanartType = fanartType;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}