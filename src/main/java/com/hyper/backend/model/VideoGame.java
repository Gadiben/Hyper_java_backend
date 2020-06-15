package com.hyper.backend.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the video_game database table.
 * 
 */
@Entity
@Table(name="video_game")
@NamedQuery(name="VideoGame.findAll", query="SELECT v FROM VideoGame v")
public class VideoGame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	//bi-directional one-to-one association to Media
	@OneToOne
	@JoinColumn(name="id")
	private Media media;

	//bi-directional many-to-one association to Platform
	@ManyToOne
	private Platform platform;

	public VideoGame() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Platform getPlatform() {
		return this.platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

}