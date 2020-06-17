package com.hyper.backend.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the appuser database table.
 * 
 */
@Entity
@NamedQuery(name="Appuser.findAll", query="SELECT a FROM Appuser a")
public class Appuser implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	private String email;

	private String gender;

	private BigDecimal latitude;

	private BigDecimal longitude;

	public Appuser(Integer id, Date dateOfBirth, String email, String gender, BigDecimal latitude, BigDecimal longitude,
			String pseudo, String password) {
		super();
		this.id = id;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.gender = gender;
		this.latitude = latitude;
		this.longitude = longitude;
		this.pseudo = pseudo;
		this.password = password;
	}

	private String pseudo;
	
	private String password;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="appuser")
	private List<Post> posts;

	//bi-directional many-to-one association to UserLibrairy
	@OneToMany(mappedBy="appuser")
	private List<UserLibrairy> userLibrairies;

	public Appuser() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setAppuser(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setAppuser(null);

		return post;
	}

	public List<UserLibrairy> getUserLibrairies() {
		return this.userLibrairies;
	}

	public void setUserLibrairies(List<UserLibrairy> userLibrairies) {
		this.userLibrairies = userLibrairies;
	}

	public UserLibrairy addUserLibrairy(UserLibrairy userLibrairy) {
		getUserLibrairies().add(userLibrairy);
		userLibrairy.setAppuser(this);

		return userLibrairy;
	}

	public UserLibrairy removeUserLibrairy(UserLibrairy userLibrairy) {
		getUserLibrairies().remove(userLibrairy);
		userLibrairy.setAppuser(null);

		return userLibrairy;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.pseudo;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean equals(Appuser user) {
		return this.getId().equals(user.getId());
	}
}