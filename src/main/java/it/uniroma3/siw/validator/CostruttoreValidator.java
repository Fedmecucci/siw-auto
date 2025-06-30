package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import it.uniroma3.siw.model.Costruttore;

import it.uniroma3.siw.service.CostruttoreService;

@Component
public class CostruttoreValidator implements Validator {

	
	@Autowired
	private CostruttoreService costruttoreService;
	
	
	@Override
	public void validate(Object o, Errors errors) {
	    Costruttore costruttore  = (Costruttore)o;
	    if (costruttore.getNome() != null 
	        && costruttoreService.existsByNome(costruttore.getNome())) {
	        errors.reject("costruttore.duplicate");
	    }
	}

	@Override
	public boolean supports(Class<?> aClass) {
	    return Costruttore.class.equals(aClass);
	}
}
