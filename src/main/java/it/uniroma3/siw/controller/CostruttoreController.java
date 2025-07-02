package it.uniroma3.siw.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import it.uniroma3.siw.model.Costruttore;
import it.uniroma3.siw.service.AutoService;
import it.uniroma3.siw.service.CostruttoreService;
import it.uniroma3.siw.validator.CostruttoreValidator;
import jakarta.validation.Valid;

@Controller
public class CostruttoreController {

	
	@Autowired
	private CostruttoreService costruttoreService;
	
	@Autowired
	private AutoService autoService;
	@Autowired
	private CostruttoreValidator costruttoreValidator;
	
	
	
	@GetMapping("/paginaCostruttori")
	public String paginaCostruttori(Model model) {
		model.addAttribute("costruttori", this.costruttoreService.findAll());
		
		return "/paginaCostruttori.html";
	}
	
	@GetMapping("/costruttore{id}")
	public String getCostruttore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("costruttore", this.costruttoreService.findById(id));
		
		return "costruttore.html";
	}
	
	@GetMapping(value="/admin/indexCostruttore")
	public String indexCostruttore() {
		return "admin/indexCostruttore.html";
	}
	
	@GetMapping(value="/admin/gestisciCostruttori")
	public String gestisciCostruttori(Model model) {
		model.addAttribute("costruttori", this.costruttoreService.findAll());
		return "admin/gestisciCostruttori.html";
	}
	
	 @GetMapping("/admin/formCreaCostruttore")
		public String formCreaCostruttore(Model model) {
			model.addAttribute("costruttore", new Costruttore());
			return "admin/formCreaCostruttore.html";
		}
	 
	 @PostMapping("/admin/formCreaCostruttore")
	 public String creaCostruttore(@Valid @ModelAttribute("costruttore") Costruttore costruttore, 
          BindingResult bindingResult,
          @RequestParam("imageFile") MultipartFile imageFile, 
          Model model) throws IOException {
   this.costruttoreValidator.validate(costruttore, bindingResult);
   if (!bindingResult.hasErrors()) {
    this.costruttoreService.creaCostruttore(costruttore, bindingResult, imageFile);
    return "/admin/indexAdmin";
      } else {
      return "admin/formCreaCostruttore";
       }
}
	 

	 @GetMapping(value="/admin/formModificaCostruttore/{id}")
	public String formModificaCostruttore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("costruttore", this.costruttoreService.findById(id));
			return "admin/formModificaCostruttore.html";
	}
	 

	 @PostMapping("/admin/formModificaCostruttore/{id}")
	    public String modificacostruttore(@RequestParam("id") Long id,
				@RequestParam("nuovoNome") String nuovoNome,
				@RequestParam("nuovoAnnoFondazione") Integer nuovoAnnoFondazione,
				
			
			 Model model) {
	        this.costruttoreService.modifica(id,nuovoNome,nuovoAnnoFondazione);
	        return "/admin/indexAdmin";
	    }
	 
	 @GetMapping(value = "/admin/cancellaCostruttore/{id}")
		public String cancellaCostruttore(@PathVariable("id") Long id, Model model) {
			this.costruttoreService.delete(id);
			return "admin/indexAdmin.html";
		}
	 

	
}
