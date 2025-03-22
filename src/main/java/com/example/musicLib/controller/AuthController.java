package com.example.musicLib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.musicLib.model.User;
import com.example.musicLib.service.AuthService;
import com.example.musicLib.service.UserService;
import com.example.musicLib.utils.Hash;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@PostMapping("/sign_in")
	public ResponseEntity<?> login(HttpServletRequest request, @RequestBody User user){
		try {
			User currentUser = userService.getByEmail(user.getEmail());

			if (currentUser == null) {
				return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
				   	.body("Usuário não encontrado");				
			}

			if (Hash.matches(user.getPassword(), currentUser.getPassword())) {
				this.authService.setSessionToCurrentUser(request, currentUser);
				return ResponseEntity.ok(currentUser);
			}

			return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Usuário/Senha incorretos");

		} catch (Exception e) {
			return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(e.getMessage());
		}
	}

	@PostMapping("/sign_up")
	public ResponseEntity<?> add(@RequestBody User user) {
		try {

			User userExists = userService.getByEmail(user.getEmail());

			if (userExists != null) {
				return ResponseEntity
						.status(HttpStatus.CONFLICT)
						.body("Erro: Usuário já cadastrado!");
			}

			User saved = userService.save(user);

			return ResponseEntity.ok(saved);

		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

	@DeleteMapping("/log_out")
	public void logout(HttpServletRequest request) {
		this.authService.destroySessionByAuthToken(request.getHeader("Token"));
	}

}
