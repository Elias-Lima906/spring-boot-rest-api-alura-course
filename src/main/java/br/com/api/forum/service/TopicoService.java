package br.com.api.forum.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.api.forum.dto.TopicoDTO;
import br.com.api.forum.modelo.Curso;
import br.com.api.forum.modelo.Topico;

@Service
public class TopicoService {

	public List<TopicoDTO> listTopics() {
		Topico topico = new Topico("Duvida", "Duvida com Spring ", new Curso("Spring", "Programação"));

		TopicoDTO topicoDto = new TopicoDTO(topico);

		return Arrays.asList(topicoDto, topicoDto, topicoDto);
	}

}
