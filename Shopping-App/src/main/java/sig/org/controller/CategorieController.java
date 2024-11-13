package sig.org.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sig.org.entity.Categorie;
import sig.org.service.CategorieService;

@RestController
@RequestMapping("/api")
public class CategorieController {
	
	@Autowired
	private CategorieService categorieService;
	
	@GetMapping("/categories/7")
	public int getCode() {
		System.out.println("Get numbers ...");
		int x = categorieService.nbre();
		System.out.println(x);
		if (x == 0) {
			return 0;
		}else {
			return categorieService.max();
		}
	}
	
	@GetMapping("/categories")
	public List<Categorie> getAllList(){
		System.out.println("Get all Categories ...");
			return categorieService.getAll();
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Categorie> post(@PathVariable String id){
		Optional<Categorie> cat = categorieService.findByCode(id);
		return cat.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/categories")
	public long save(@RequestBody Categorie categorie) {
		return categorieService.save(categorie);
	}
	
	@PutMapping("/categories/{code}")
	public void update(@PathVariable String code, @RequestBody Categorie categorie ) {
		Optional<Categorie> cat = categorieService.findByCode(code);
		if(cat.isPresent()) {
			categorieService.update(code, categorie);
		}else {
			categorieService.save(categorie);
		}
		
	}
	
	@DeleteMapping("/categories/{code}")
	public void delete(@PathVariable String code) {
		categorieService.delete(code);
	}

}
