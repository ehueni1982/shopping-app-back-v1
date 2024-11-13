package sig.org.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sig.org.entity.Panier;
import sig.org.service.PanierService;

@RestController
@RequestMapping("api")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
public class PanierController {
	
	@Autowired
	private PanierService panierService;
	
	@GetMapping("/paniers")
	public List<Panier> getAllList(){
		System.out.println("Get all paniers ...");
		return panierService.getAll();
	}
	
	@GetMapping("/paniers/{id}")
	public ResponseEntity<Panier> post(@PathVariable Long id){
		Optional<Panier> panier = panierService.findById(id);
		return panier.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/paniers")
	public long save(@RequestBody Panier panier) {
		System.out.println("Save Panier ...");
		return panierService.save(panier);
	}
	
	@PutMapping("/paniers/{id}")
	public void update(@PathVariable Long id, @RequestBody Panier panier) {
		
	}
}
