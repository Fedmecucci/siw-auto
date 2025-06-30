package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import it.uniroma3.siw.model.Costruttore;

@Repository
public interface CostruttoreRepository extends CrudRepository<Costruttore,Long>{
	
	public boolean existsByNome(String nome);

	public List<Costruttore> findAll();
}
