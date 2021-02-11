package br.com.api.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.forum.modelo.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long>{

}
