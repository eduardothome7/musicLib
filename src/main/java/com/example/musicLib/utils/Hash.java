package com.example.musicLib.utils;

import org.mindrot.jbcrypt.BCrypt;

import com.example.musicLib.model.User;

public class Hash {	

	public static String encode(String input) {
		return BCrypt.hashpw(input, BCrypt.gensalt());
	}

	public static boolean matches(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}

	public static String generateHashToken(User user) {
		return BCrypt.hashpw(user.getEmail() +  " - " + user.getId().toString(), BCrypt.gensalt());
	}
}
