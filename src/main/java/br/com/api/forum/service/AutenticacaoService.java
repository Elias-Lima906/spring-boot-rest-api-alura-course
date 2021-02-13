package br.com.api.forum.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.api.forum.modelo.Usuario;
import br.com.api.forum.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	private static final String USUARIO_INVALIDO = "Usuário inválido!";
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> user = usuarioRepository.findByEmail(username);
		
		if(user.isEmpty()) {
			throw new UsernameNotFoundException(USUARIO_INVALIDO);
		}
		return user.get();
	}

	
}
