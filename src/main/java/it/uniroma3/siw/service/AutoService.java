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

import it.uniroma3.siw.controller.GlobalController;
import it.uniroma3.siw.model.Auto;

import it.uniroma3.siw.repository.AutoRepository;

@Service
public class AutoService {
	
	@Autowired
	private AutoRepository autoRepository;
	@Autowired
	GlobalController globalController;
	

	public boolean existsByModelloAndAnno(String modello, Integer anno) {
		return  autoRepository.existsByModelloAndAnno(modello, anno);
	}

	public Iterable<Auto> findAll() {
		return autoRepository.findAll();
	}

	@Transactional
	public Auto findById(Long id) {
		return autoRepository.findById(id).get();
	}
	
	@Transactional
	public Auto save(Auto auto) {
		return autoRepository.save(auto);
		
	}
	
	@Transactional
	public void save2(Auto auto ,MultipartFile file) throws IOException {
		auto.setImmagine(Base64.getEncoder().encodeToString(file.getBytes()));
		autoRepository.save(auto);		
	}
	
	 @Transactional
		public void creaAuto( Auto auto, BindingResult bindingResult,
				 MultipartFile imageFile) throws IOException {

			this.save2(auto, imageFile);	
		}
	 
	 @Transactional
		public void modifica( Long id,
				 String nuovoModello,  Integer nuovoAnno,
				 Integer nuovoPrezzo
				) {
			Auto a = this.findById(id);
			a.setModello(nuovoModello);
			a.setAnno(nuovoAnno);
			a.setPrezzo(nuovoPrezzo);
			this.save(a);	
		}
	 
	 @Transactional
		public void delete(Long id) {
			Auto auto = this.findById(id);
			autoRepository.delete(auto);
			
		}

}
