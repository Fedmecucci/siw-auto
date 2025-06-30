package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Auto;
import it.uniroma3.siw.model.Costruttore;

import it.uniroma3.siw.repository.CostruttoreRepository;

@Service
public class CostruttoreService {

	@Autowired
	private CostruttoreRepository costruttoreRepository;

	public boolean existsByNome(String nome) {
		return  costruttoreRepository.existsByNome(nome);
	}

	public Iterable<Costruttore> findAll() {
		return costruttoreRepository.findAll();
	}

	@Transactional
	public Costruttore findById(Long id) {
		return costruttoreRepository.findById(id).get();
	}


	@Transactional
	public Costruttore save(Costruttore costruttore) {
		return costruttoreRepository.save(costruttore);

	}

	@Transactional
	public void save2(Costruttore costruttore ,MultipartFile file) throws IOException {
		costruttore.setImmagine(Base64.getEncoder().encodeToString(file.getBytes()));
		costruttoreRepository.save(costruttore);		
	}

	@Transactional
	public void creaCostruttore( Costruttore costruttore, BindingResult bindingResult,
			MultipartFile imageFile) throws IOException {

		this.save2(costruttore, imageFile);	
	}


	@Transactional
	public void modifica( Long id,
			String nuovoNome,  Integer nuovoAnnoFondazione
			) {
		Costruttore c = this.findById(id);
		c.setNome(nuovoNome);;
		c.setAnnoFondazione(nuovoAnnoFondazione);
		this.save(c);	
	}

	@Transactional
	public void delete(Long id) {
		Costruttore costruttore = this.findById(id);
		costruttoreRepository.delete(costruttore);

	}
}
