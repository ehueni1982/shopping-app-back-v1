package sig.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sig.org.entity.Categorie;
import sig.org.repository.CategorieRepository;

@Service
@Transactional
public class CategorieService {
	
	@Autowired
	CategorieRepository categorieRepository;
	
	public List<Categorie> getAll(){
		System.out.println("Get all Categories 1...");
		return categorieRepository.findAll(org.springframework.data.domain.Sort.by("libelle").ascending());
	}
	
	public int max() {
		return categorieRepository.max();
	}
	
	public int nbre() {
		return categorieRepository.nbre();
	}
	
	public Optional<Categorie> findByCode(String code){
		return categorieRepository.findByCode(code);
	}
	
	public Long save(Categorie categorie) {
		System.out.println("save all Categories 1...");
		Categorie cat = new Categorie();
		cat.setCode(categorie.getCode());
		cat.setLibelle(categorie.getLibelle());
		return categorieRepository.save(cat).getId();
		
	}
	
	public void update(String code, Categorie categorie) {
		Optional<Categorie> categ = categorieRepository.findByCode(code);
		if (categ.isPresent()) {
			Categorie cat = categ.get();
			cat.setLibelle(categorie.getLibelle());
			categorieRepository.save(cat);
		}
		
	}
	
	public List<Categorie> findByLibelle(String libelle){
		return categorieRepository.findAllByLibelleContaining(libelle);
	}
	
	public void delete(String code) {
		Optional<Categorie> cat = categorieRepository.findByCode(code);
		cat.ifPresent(categorieRepository::delete);
	}

}
