package br.com.api.forum.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AutenticacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldAuthenticateIfLoginDataIsRight() throws Exception {
		
		URI uri = new URI("/auth");
		String jsonLogin = "{ \"email\": \"aluno@email.com\", \"senha\": \"123456\" }";
	
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(jsonLogin)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
		}
	
	@Test
	public void shouldNotAuthenticateIfLoginDataIsWorng() throws Exception {
		
		URI uri = new URI("/auth");
		String jsonLogin = "{ \"email\": \"professor@email.com\", \"senha\": \"7776687\" }";
	
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(jsonLogin)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(403));
		}

}
