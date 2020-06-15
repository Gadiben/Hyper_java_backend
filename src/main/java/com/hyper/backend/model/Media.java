package com.hyper.backend.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the media database table.
 * 
 */
@Entity
@NamedQuery(name="Media.findAll", query="SELECT m FROM Media m")
public class Media implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String description;

	private String image;

	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	private Date releaseDate;

	private String title;

	//bi-directional one-to-one association to Film
	@OneToOne(mappedBy="media")
	private Film film;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="media")
	private List<Post> posts;

	//bi-directional one-to-one association to Tvshow
	@OneToOne(mappedBy="media")
	private Tvshow tvshow;

	//bi-directional many-to-one association to UserLibrairy
	@OneToMany(mappedBy="media")
	private List<UserLibrairy> userLibrairies;

	//bi-directional one-to-one association to VideoGame
	@OneToOne(mappedBy="media")
	private VideoGame videoGame;

	public Media() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setMedia(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setMedia(null);

		return post;
	}

	public Tvshow getTvshow() {
		return this.tvshow;
	}

	public void setTvshow(Tvshow tvshow) {
		this.tvshow = tvshow;
	}

	public List<UserLibrairy> getUserLibrairies() {
		return this.userLibrairies;
	}

	public void setUserLibrairies(List<UserLibrairy> userLibrairies) {
		this.userLibrairies = userLibrairies;
	}

	public UserLibrairy addUserLibrairy(UserLibrairy userLibrairy) {
		getUserLibrairies().add(userLibrairy);
		userLibrairy.setMedia(this);

		return userLibrairy;
	}

	public UserLibrairy removeUserLibrairy(UserLibrairy userLibrairy) {
		getUserLibrairies().remove(userLibrairy);
		userLibrairy.setMedia(null);

		return userLibrairy;
	}

	public VideoGame getVideoGame() {
		return this.videoGame;
	}

	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}

}