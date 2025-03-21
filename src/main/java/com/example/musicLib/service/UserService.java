package com.example.musicLib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musicLib.model.User;
import com.example.musicLib.repository.UserRepository;
import com.example.musicLib.utils.Hash;

@Service
public class UserService implements SharedService<User> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAll(){
		return userRepository.findAll();
	}

	public User getByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public Optional<User> getById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		user.setPassword(Hash.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
