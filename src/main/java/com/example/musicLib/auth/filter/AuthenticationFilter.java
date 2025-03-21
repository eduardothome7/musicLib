package com.example.musicLib.auth.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.musicLib.service.AuthService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter implements Filter {

	@Autowired
	private AuthService authService;

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		System.out.println("====== Token ========");
		System.out.println(httpServletRequest.getHeader("Token"));

		if (httpServletRequest.getHeader("Token") == null) {
			httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autorizado");
			return;
		}

		if (!this.authService.validatesAuthToken(httpServletRequest.getHeader("Token"))) {
			httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
			return;
		}
		
		chain.doFilter(httpServletRequest, httpServletResponse);
	}
}
