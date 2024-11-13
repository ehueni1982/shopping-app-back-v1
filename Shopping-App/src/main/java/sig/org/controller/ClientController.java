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

import sig.org.entity.Client;
import sig.org.service.ClientService;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/clients")
	public List<Client> getAllList(){
		System.out.println("Get all Clients ...");
			return clientService.getAll();
	}
	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> post(@PathVariable int id){
		Optional<Client> fournisseur = clientService.findByCode(id);
		return fournisseur.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/clients")
	public long save(@RequestBody Client client) {
		return clientService.save(client);
	}
	
	@PutMapping("/clients/{code}")
	public void update(@PathVariable int code, @RequestBody Client client) {
		Optional<Client> fournisseur = clientService.findByCode(code);
		if(fournisseur.isPresent()) {
			clientService.update(code, client);
		}
	}
	
	
	
	@DeleteMapping("/clients/{code}")
	public void delete(int code) {
		
		clientService.delete(code);
	}

}
