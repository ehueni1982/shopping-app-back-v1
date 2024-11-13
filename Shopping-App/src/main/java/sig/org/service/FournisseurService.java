package sig.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sig.org.entity.Fournisseur;
import sig.org.entity.Parametre;
import sig.org.entity.User;
import sig.org.repository.FournisseurRepository;
import sig.org.repository.ParametreRepository;
import sig.org.repository.UserRepository;

@Service
@Transactional
public class FournisseurService {
	
	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	ParametreRepository parametreRepository;
	@Autowired
	UserRepository userRepository;
	
	public List<Fournisseur>GetAll(){
		System.out.println("Get all Fournisseur 1...");
		return fournisseurRepository.findAll(Sort.by("libelle").ascending());
		
	}
	
	public Optional<Fournisseur> findByCode(int id){
		return fournisseurRepository.findByCode(id);
	}
	
	public long save(Fournisseur four) {
		System.out.println("save all Fournisseur 1...");
		long id = 1;
		Optional<Parametre> paramInfo = parametreRepository.findById(id);
			if(paramInfo.isPresent()) {
				Parametre parametre = paramInfo.get();
					parametre.setNumf(parametre.getNumf()+1);
					parametre = parametreRepository.save(parametre);
			}
			User user = new User();
			user.setUsername(four.getEmail());
			user.setNom(four.getLibelle());
			user.setCode(four.getCode());
			user.setEmail(four.getEmail());
			user.setPassword(four.getPwd());
			user.setRole("FOUR");
			user.setActive(true);
			userRepository.save(user);
		return fournisseurRepository.save(four).getId();
			
		
	}
	
	public void update(int code, Fournisseur fournisseur) {
		Optional<Fournisseur> four = fournisseurRepository.findByCode(code);
			if(four.isPresent()) {
				Fournisseur fournis = four.get();
				fournis.setLibelle(fournisseur.getLibelle());
				fournis.setAdresse(fournisseur.getAdresse());
				fournisseurRepository.save(fournis);
			}
	
	}
	public List<Fournisseur> findByEmail(String email){
				return fournisseurRepository.findAllByEmail(email);
	}
	
	public List<Fournisseur> findByLibelle(String libelle){
		return fournisseurRepository.findAllByLibelleContaining(libelle);
	}
	
	public void delete(int code) {
		Optional<Fournisseur> fournis = fournisseurRepository.findByCode(code);
		fournis.ifPresent(fournisseurRepository::delete);
	}

}
