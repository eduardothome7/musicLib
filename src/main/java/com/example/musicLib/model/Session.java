package com.example.musicLib.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Session {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	private String token;
	private Date createdAt;
	private Date endedAt;
	private String device;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the created_at
	 */
	public Date getCreated_at() {
		return createdAt;
	}
	/**
	 * @param created_at the created_at to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the ended_at
	 */
	public Date getEndedAt() {
		return endedAt;
	}
	/**
	 * @param ended_at the ended_at to set
	 */
	public void setEndedAt(Date endedAt) {
		this.endedAt = endedAt;
	}
	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}
	/**
	 * @param device the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}
}