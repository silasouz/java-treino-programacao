package br.com.coruja.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coruja.application.exceptions.ResourceNotFoundException;
import br.com.coruja.domain.dto.AlunoDto;
import br.com.coruja.domain.model.Aluno;
import br.com.coruja.domain.repository.AlunoRepository;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    private AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository) {
        super();
        this.alunoRepository = alunoRepository;
    }

    @GetMapping("/nome/{nome}")
    public AlunoDto find(@PathVariable String nome){
        return this.alunoRepository.findFirstByName(nome)
            .map(a -> new AlunoDto(a))
            .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/{id}")
    public AlunoDto find(@PathVariable Long id){
        return this.alunoRepository.findById(id)
            .map(a -> new AlunoDto(a))
            .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping()
    public List<AlunoDto> list() {
        return this.alunoRepository.findAll().stream()
            .map(a -> new AlunoDto(a))
            .collect(Collectors.toList());
    }

    @PostMapping
    public Long save(@RequestBody AlunoDto aluno){
        Aluno alunoInsert = aluno.toAlunoInsert();
        alunoInsert = this.alunoRepository.save(alunoInsert);
        return alunoInsert.getId();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody AlunoDto aluno) {
        Aluno alunoUpdate = this.alunoRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);
        alunoUpdate.setNome(aluno.getNome());
        alunoUpdate.setEmail(aluno.getEmail());
        this.alunoRepository.save(alunoUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            this.alunoRepository.deleteById(id);
        } catch (DataRetrievalFailureException e) {
            throw new ResourceNotFoundException();
        }
    }
}
