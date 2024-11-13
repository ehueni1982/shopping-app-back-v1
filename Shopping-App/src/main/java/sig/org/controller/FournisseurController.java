package sig.org.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sig.org.entity.Fournisseur;
import sig.org.service.FournisseurService;

@RestController
@RequestMapping("api")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
public class FournisseurController {
	
	@Autowired
	private FournisseurService fournisseurService;
	
	@GetMapping("/fournisseurs")
	public List<Fournisseur> getAllList(){
		System.out.println("Get all Fournisseurs ...");
		return fournisseurService.GetAll();
	}
	
	@GetMapping("/fournisseurs/{id}")
	public ResponseEntity<Fournisseur> post(@PathVariable int id){
		Optional<Fournisseur> fournis = fournisseurService.findByCode(id);
		return fournis.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/fournisseurs")
	public long save(@RequestBody Fournisseur fournisseur) {
		return fournisseurService.save(fournisseur);
	}
	
	@PutMapping("/fournisseurs/{code}")
	public void update(@RequestBody int code, Fournisseur fournisseur) {
		Optional<Fournisseur> fournis = fournisseurService.findByCode(code);
		if(fournis.isPresent()) {
			fournisseurService.update(code, fournisseur);
		}
	}
	
	@DeleteMapping("/fournisseurs/{code}")
	public void delete(@PathVariable int code) {
		fournisseurService.delete(code);
	}

}
