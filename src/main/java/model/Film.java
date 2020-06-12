package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Integer duration;

	//bi-directional one-to-one association to Media
	@OneToOne
	@JoinColumn(name="id")
	private Media media;

	public Film() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}