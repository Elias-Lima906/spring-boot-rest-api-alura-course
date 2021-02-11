package br.com.api.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.forum.dto.TopicoDTO;
import br.com.api.forum.service.TopicoService;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	TopicoService topicoService;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<TopicoDTO> listTopics(){
		return topicoService.listTopics();
	}
}
