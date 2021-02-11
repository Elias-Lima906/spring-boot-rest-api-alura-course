package br.com.api.forum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.forum.dto.AtualizaTopicoDTO;
import br.com.api.forum.dto.DetalhesTopicoDTO;
import br.com.api.forum.dto.MensagemDTO;
import br.com.api.forum.dto.TopicoDTO;
import br.com.api.forum.dto.TopicoFormDTO;
import br.com.api.forum.exceptions.GlobalException;
import br.com.api.forum.service.TopicoService;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	TopicoService topicoService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TopicoDTO> listTopics() {
		return topicoService.listTopics();
	}

	@GetMapping(path = "/{nomeCurso}/nomeCurso", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TopicoDTO> findByCourseName(@PathVariable String nomeCurso) {
		return topicoService.findByCourseName(nomeCurso);
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public DetalhesTopicoDTO findById(@PathVariable Long id) throws GlobalException {
		return topicoService.findById(id);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TopicoDTO> saveTopic(@RequestBody @Valid TopicoFormDTO topicoForm,
			UriComponentsBuilder uriBuilder) {
		return topicoService.saveTopic(topicoForm, uriBuilder);

	}

	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TopicoDTO> updateTopic(@PathVariable Long id,
			@RequestBody @Valid AtualizaTopicoDTO atualizaTopicoDTO) throws GlobalException {
		return topicoService.updateTopic(id, atualizaTopicoDTO);
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MensagemDTO> updateTopic(@PathVariable Long id) throws GlobalException {
		return topicoService.deleteTopic(id);
	}

}
