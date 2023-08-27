package br.com.coruja.domain.repository;

import java.text.MessageFormat;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.coruja.domain.model.Aluno;

public class CustomAlunoRepositoryImpl implements CustomAlunoRepository  {

    private EntityManager entityManager;

    public CustomAlunoRepositoryImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    public Optional<Aluno> findFirstByName(String nome) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Aluno> query = cb.createQuery(Aluno.class);

		Root<Aluno> s = query.from(Aluno.class);
		query.select(s).where(
            cb.like(s.get("nome"), 
            MessageFormat.format("%{0}%", nome)   
        ));
		TypedQuery<Aluno> q = entityManager.createQuery(query);

		return q.setMaxResults(1).getResultStream().findFirst();
    }
        
}
