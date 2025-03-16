package com.exercise.registo_jogadores;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.exercise.registo_jogadores.model.GrupoCodinome;
import com.exercise.registo_jogadores.model.Jogador;

@SpringBootTest
@AutoConfigureMockMvc
class RegistoJogadoresIT {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void registarListarJogadorSucesso() throws Exception {
		var jogador = new Jogador("João", "joao@gmai.com", "123456789", "codinome", GrupoCodinome.VINGADORES);

		mockMvc
			.perform(post("/registar-jogador")
				.param("nome", jogador.nome())
				.param("email", jogador.email())
				.param("telefone", jogador.telefone())
				.param("grupoCodinome", jogador.grupoCodinome().name()))
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/listagem-jogadores"));

		mockMvc
			.perform(get("/listagem-jogadores"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("listagem-jogadores"))
			.andExpect(model().attribute("jogadores", hasSize(1)))
			.andExpect(model().attribute("jogadores", contains(allOf(
				hasToString(containsString(jogador.nome())),
				hasToString(containsString(jogador.email())),
				hasToString(containsString(jogador.telefone())),
				hasToString(containsString(jogador.grupoCodinome().name()))
			))));
	}

}
