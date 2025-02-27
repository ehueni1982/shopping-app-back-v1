package sig.org.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sig.org.entity.Compteur;
import sig.org.service.CompteurService;

@RestController
@RequestMapping("api")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
public class CompteurController {
	
	@Autowired
	private CompteurService compteurService;
	
	@GetMapping("/compteurs/{annee}")
	public ResponseEntity<Compteur> nbre(@PathVariable int annee){
		System.out.println("Get Numbers ...");
		
		int x = compteurService.nbre(annee);
		
		if(x == 0) {
			compteurService.create(annee);
		}Optional<Compteur> cpt = compteurService.findByAnnee(annee);
		return cpt.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

}
