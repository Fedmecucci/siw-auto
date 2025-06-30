package it.uniroma3.siw.controller;

import java.io.IOException;

import java.util.List;

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

import it.uniroma3.siw.model.Auto;

import it.uniroma3.siw.service.AutoService;
import it.uniroma3.siw.service.CostruttoreService;
import it.uniroma3.siw.validator.AutoValidator;
import jakarta.validation.Valid;

@Controller
public class AutoController {
	
	@Autowired
	private AutoService autoService;
	@Autowired
	private CostruttoreService costruttoreService;
	@Autowired
	private AutoValidator autoValidator;
	
	@GetMapping("/paginaMacchine")
    public String paginaMacchine(Model model) {
	  model.addAttribute("macchine", this.autoService.findAll());
	
	 return "paginaMacchine.html";
    }

	@GetMapping("/auto{id}")
	public String getAuto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("auto", this.autoService.findById(id));
	
		return "auto.html";
	}
	
	@GetMapping(value="/admin/indexAuto")
	public String indexAuto() {
		return "admin/indexAuto.html";
	}
	
	 @GetMapping("/admin/formCreaAuto")
		public String formCreaAuto(Model model) {
			model.addAttribute("auto", new Auto());
			model.addAttribute("listaCostruttori",costruttoreService.findAll());
			return "admin/formCreaAuto.html";
		}
	 
	 @PostMapping("/admin/formCreaAuto")
	 public String creaAuto(@Valid @ModelAttribute("auto") Auto auto, 
          BindingResult bindingResult,
          @RequestParam("imageFile") MultipartFile imageFile, 
          Model model) throws IOException {
   this.autoValidator.validate(auto, bindingResult);
   if (!bindingResult.hasErrors()) {
    this.autoService.creaAuto(auto, bindingResult, imageFile);
    return "/admin/indexAdmin";
      } else {
      return "admin/formCreaAuto";
       }
}
	 
	 

       @GetMapping(value="/admin/formModificaAuto/{id}")
		public String formModificaAuto(@PathVariable("id") Long id, Model model) {
			model.addAttribute("auto", this.autoService.findById(id));
		return "admin/formModificaAuto.html";
		}
	 
	 @GetMapping(value="/admin/gestisciMacchine")
		public String gestisciMacchine(Model model) {
			model.addAttribute("macchine", this.autoService.findAll());
			return "admin/gestisciMacchine.html";
		}

	 @PostMapping("/admin/formModificaAuto/{id}")
	    public String modificaauto(@RequestParam("id") Long id,
				@RequestParam("nuovoModello") String nuovoModello,
				@RequestParam("nuovoAnno") Integer nuovoAnno,
				@RequestParam("nuovoPrezzo") Integer nuovoPrezzo,
				
			
			 Model model) {
	        this.autoService.modifica(id,nuovoModello,nuovoAnno,nuovoPrezzo);
	        return "/admin/indexAdmin";
	    }
	 
	 @GetMapping(value = "/admin/cancellaAuto/{id}")
		public String cancellaAuto(@PathVariable("id") Long id, Model model) {
			this.autoService.delete(id);
			return "admin/indexAdmin.html";
		}
	 
}
