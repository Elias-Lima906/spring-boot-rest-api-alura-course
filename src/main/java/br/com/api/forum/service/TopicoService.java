package br.com.api.forum.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.forum.dto.AtualizaTopicoDTO;
import br.com.api.forum.dto.DetalhesTopicoDTO;
import br.com.api.forum.dto.MensagemDTO;
import br.com.api.forum.dto.TopicoDTO;
import br.com.api.forum.dto.TopicoFormDTO;
import br.com.api.forum.exceptions.GlobalException;
import br.com.api.forum.modelo.Curso;
import br.com.api.forum.modelo.Topico;
import br.com.api.forum.repository.CursoRepository;
import br.com.api.forum.repository.TopicoRepository;

@Service
public class TopicoService {

	private static final String O_TÓPICO_COM_O_ID = "O tópico com o id ";

	private static final String NÃO_EXISTE = " não existe!";

	@Autowired
	TopicoRepository topicoRepository;

	@Autowired
	CursoRepository cursoRepository;

	public List<TopicoDTO> listTopics() {
		List<Topico> topicos = topicoRepository.findAll();

		return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
	}

	public List<TopicoDTO> findByCourseName(String nomeCurso) {

		List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);

		return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
	}

	public ResponseEntity<TopicoDTO> saveTopic(TopicoFormDTO topicoForm, UriComponentsBuilder uriBuilder) {

		Curso curso = cursoRepository.findByNome(topicoForm.getNomeCurso());
		Topico topico = new Topico(topicoForm.getTitulo(), topicoForm.getMensagem(), curso);

		topicoRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	public DetalhesTopicoDTO findById(Long id) throws GlobalException {

		if (!topicoRepository.existsById(id)) {
			throw new GlobalException(O_TÓPICO_COM_O_ID + id + NÃO_EXISTE);
		}

		Topico topico = topicoRepository.getOne(id);

		return new DetalhesTopicoDTO(topico);
	}

	public ResponseEntity<TopicoDTO> updateTopic(Long id, AtualizaTopicoDTO atualizaTopicoDTO) throws GlobalException {

		if (!topicoRepository.existsById(id)) {
			throw new GlobalException(O_TÓPICO_COM_O_ID + id + NÃO_EXISTE);
		}

		Topico topico = topicoRepository.getOne(id);

		BeanUtils.copyProperties(atualizaTopicoDTO, topico);

		topicoRepository.save(topico);

		return ResponseEntity.ok(new TopicoDTO(topico));

	}

	public ResponseEntity<MensagemDTO> deleteTopic(Long id) throws GlobalException {
		
		if (!topicoRepository.existsById(id)) {
			throw new GlobalException(O_TÓPICO_COM_O_ID + id + NÃO_EXISTE);
		}
		
		topicoRepository.deleteById(id);
	
		return ResponseEntity.ok(new MensagemDTO("Tópico removido com sucesso!"));
	}

}
