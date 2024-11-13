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

import sig.org.entity.Parametre;
import sig.org.service.ParametreService;

@RestController
@RequestMapping("api")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
public class ParametreController {
	
	@Autowired
	private ParametreService parametreService;
	
	@GetMapping("/parametres")
	public List<Parametre> getAllList(){
		System.out.println("Get all Parametres ...");
		return parametreService.getAll();
	}
	
	@GetMapping("/parametres/{id}")
	public ResponseEntity<Parametre> post(@PathVariable Long id){
		Optional<Parametre> param = parametreService.findById(id);
		return param.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/parametres")
	public long save(@RequestBody Parametre parametre) {
		return parametreService.save(parametre);
	}
	
	@PutMapping("/parametres/{id}")
	public void update(@PathVariable Long id, @RequestBody Parametre parametre) {
		Optional<Parametre> param = parametreService.findById(id);
		if(param.isPresent()) {
			parametreService.update(id, parametre);;
		}
	}
	
	@DeleteMapping("/parametre/{id}")
	public void delete(@PathVariable Long id) {
		parametreService.delete(id);
	}
}
