package com.hyper.backend.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tvshow database table.
 * 
 */
@Entity
@NamedQuery(name="Tvshow.findAll", query="SELECT t FROM Tvshow t")
public class Tvshow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="season_number")
	private Integer seasonNumber;

	//bi-directional one-to-one association to Media
	@OneToOne
	@JoinColumn(name="id")
	private Media media;

	public Tvshow() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSeasonNumber() {
		return this.seasonNumber;
	}

	public void setSeasonNumber(Integer seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}