package br.com.api.forum.repository;

import org.junit.Assert;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.forum.modelo.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class CursoRepositoryTest {
	
	@Autowired
	CursoRepository repo;

	@Test
	public void shouldLoadCourseByName() {
		String nomeCurso =  "Spring Boot";
		
		Curso curso = repo.findByNome(nomeCurso);
		
		Assert.assertNotNull(curso);
		Assert.assertEquals(nomeCurso, curso.getNome());
	}
	
	@Test
	public void shouldNotLoadCourseByNameIfNameIsWrong() {
		String nomeCurso =  "Spring Boot Test";
		
		Curso curso = repo.findByNome(nomeCurso);
		
		Assert.assertNull(curso);
	}
	
	

}
