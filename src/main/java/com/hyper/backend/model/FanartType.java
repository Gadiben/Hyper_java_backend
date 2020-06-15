package com.hyper.backend.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fanart_type database table.
 * 
 */
@Entity
@Table(name="fanart_type")
@NamedQuery(name="FanartType.findAll", query="SELECT f FROM FanartType f")
public class FanartType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="fanartType")
	private List<Post> posts;

	public FanartType() {
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

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setFanartType(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setFanartType(null);

		return post;
	}

}