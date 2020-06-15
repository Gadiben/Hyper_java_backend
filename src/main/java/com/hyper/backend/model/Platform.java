package com.hyper.backend.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the platforms database table.
 * 
 */
@Entity
@Table(name="platforms")
@NamedQuery(name="Platform.findAll", query="SELECT p FROM Platform p")
public class Platform implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-one association to VideoGame
	@OneToMany(mappedBy="platform")
	private List<VideoGame> videoGames;

	public Platform() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VideoGame> getVideoGames() {
		return this.videoGames;
	}

	public void setVideoGames(List<VideoGame> videoGames) {
		this.videoGames = videoGames;
	}

	public VideoGame addVideoGame(VideoGame videoGame) {
		getVideoGames().add(videoGame);
		videoGame.setPlatform(this);

		return videoGame;
	}

	public VideoGame removeVideoGame(VideoGame videoGame) {
		getVideoGames().remove(videoGame);
		videoGame.setPlatform(null);

		return videoGame;
	}

}