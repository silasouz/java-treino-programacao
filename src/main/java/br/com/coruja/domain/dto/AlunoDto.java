package br.com.coruja.domain.dto;

import br.com.coruja.domain.model.Aluno;

public class AlunoDto {
    
    public AlunoDto() {
        super();
    }

    public AlunoDto(Aluno aluno) {
        setId(aluno.getId());
        setNome(aluno.getNome());
        setEmail(aluno.getEmail());
    }

    private Long id;

    private String nome;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Aluno toAluno(){
        return new Aluno(id, nome, email);
    }

    public Aluno toAlunoInsert() {
        return new Aluno(nome, email);
    }
}
