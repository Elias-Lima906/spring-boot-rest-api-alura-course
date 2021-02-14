package br.com.api.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.api.forum.modelo.Usuario;
import br.com.api.forum.repository.UsuarioRepository;

public class JwtRequestFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;

	public JwtRequestFilter() {

	}

	public JwtRequestFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = this.getToken(request);

		boolean isValid = tokenService.isTokenValid(token);

		if(isValid) {
			autenticarUsuario(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String token) {
		
		Long idUsuario = this.tokenService.getIdUsuario(token);
		
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}
