package br.com.api.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.forum.dto.LoginDTO;
import br.com.api.forum.dto.TokenDTO;
import br.com.api.forum.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDTO login) {

		UsernamePasswordAuthenticationToken authData = new UsernamePasswordAuthenticationToken(login.getEmail(),
				login.getSenha());

		try {
			Authentication authentication = authManager.authenticate(authData);

			String token = tokenService.gerarToken(authentication);

			System.out.println(token);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
