package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Auto;
import it.uniroma3.siw.service.AutoService;



@Component
public class AutoValidator implements Validator{


	@Autowired
	private AutoService autoService;
	
	
	@Override
	public void validate(Object o, Errors errors) {
	    Auto auto  = (Auto)o;
	    if (auto.getModello() != null && auto.getAnno() != null
	        && autoService.existsByModelloAndAnno(auto.getModello(), auto.getAnno())) {
	        errors.reject("autore.duplicate");
	    }
	}

	@Override
	public boolean supports(Class<?> aClass) {
	    return Auto.class.equals(aClass);
	}
}
