package br.com.coruja.domain.repository;

import java.util.Optional;

import br.com.coruja.domain.model.Aluno;

public interface CustomAlunoRepository {

    public Optional<Aluno> findFirstByName(String nome);
        
}
