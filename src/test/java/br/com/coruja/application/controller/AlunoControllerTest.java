package br.com.coruja.application.controller;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.coruja.domain.dto.AlunoDto;
import br.com.coruja.domain.model.Aluno;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunoControllerTest {
    @Autowired
    protected WebTestClient web;

    @BeforeEach
    public void setUp() {
        web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
    }

    @Test
    public void deve_inserir_novo_aluno() {
        AlunoDto aluno = new AlunoDto(new Aluno("Silas", "silasq"));
        web.post().uri("/api/aluno")
            .accept(MediaType.ALL)
            .bodyValue(aluno)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .value(c -> assertDoesNotThrow(() -> Long.parseLong(c)));
    }

}