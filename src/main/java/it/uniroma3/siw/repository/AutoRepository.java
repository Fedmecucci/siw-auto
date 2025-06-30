package it.uniroma3.siw.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Auto;




@Repository
public interface AutoRepository extends CrudRepository<Auto,Long>{
	
	public boolean existsByModelloAndAnno(String modello,Integer anno);
	
	 public List<Auto> findAll();

}
