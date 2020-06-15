package com.hyper.backend.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_librairy database table.
 * 
 */
@Entity
@Table(name="user_librairy")
@NamedQuery(name="UserLibrairy.findAll", query="SELECT u FROM UserLibrairy u")
public class UserLibrairy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	//bi-directional many-to-one association to Appuser
	@ManyToOne
	@JoinColumn(name="user_id")
	private Appuser appuser;

	//bi-directional many-to-one association to Media
	@ManyToOne
	private Media media;

	public UserLibrairy() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Appuser getAppuser() {
		return this.appuser;
	}

	public void setAppuser(Appuser appuser) {
		this.appuser = appuser;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}