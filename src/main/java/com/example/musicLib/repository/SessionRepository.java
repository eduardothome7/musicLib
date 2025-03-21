package com.example.musicLib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.musicLib.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
	Session findByToken(String token);
}
