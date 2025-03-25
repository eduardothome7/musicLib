package com.example.musicLib.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musicLib.model.Session;
import com.example.musicLib.model.User;
import com.example.musicLib.repository.SessionRepository;
import com.example.musicLib.repository.UserRepository;
import com.example.musicLib.utils.Hash;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService implements SharedService<Session> {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Session> getAll(){
		return sessionRepository.findAll();
	}

	public void setSessionToCurrentUser(HttpServletRequest request, User currentUser) {
		try {
			List<Session> userSessions = currentUser.getSessions();
			Session currentSession = new Session();

			if (userSessions == null) {
				userSessions = new ArrayList<Session>();
			}

			currentSession.setUser(currentUser);
			String userSessionToken = Hash.generateHashToken(currentUser);

			currentSession.setToken(userSessionToken);
            currentSession.setCreatedAt(new Date());

			sessionRepository.save(currentSession);

			userSessions.add(currentSession);
			currentUser.setSessions(userSessions);

			System.out.println(userSessions);

			System.out.println("Save User");
			userRepository.save(currentUser);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Boolean validatesAuthToken(String authToken) {
		Session session = this.sessionRepository.findByToken(authToken);

		if (session == null) {
			return false;
		}

		if (session.getEndedAt() != null) {
			return false;
		}

		return true;
	}

	public Boolean destroySessionByAuthToken(String authToken) {
		Session session = this.sessionRepository.findByToken(authToken);

		if (session == null) {
			return false;
		}

		session.setEndedAt(new Date());
		sessionRepository.save(session);

		return true;
	}

	@Override
	public Optional<Session> getById(Long id) {
		return sessionRepository.findById(id);
	}

	@Override
	public Session save(Session session) {
		return sessionRepository.save(session);
	}

	@Override
	public void delete(Long id) {
		sessionRepository.deleteById(id);
	}
}
