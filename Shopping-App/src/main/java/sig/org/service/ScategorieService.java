package sig.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sig.org.Dto.ListCategorie;
import sig.org.entity.Scategorie;
import sig.org.repository.ScategorieRepository;

@Service
@Transactional
public class ScategorieService {
	@Autowired
	ScategorieRepository scategorieRepository;
	
	public List<ListCategorie> getAll(){
		
		System.out.println("Get all sous categories 1...");
		return scategorieRepository.listScateg();	
	}
	
	public Optional<Scategorie> findByCode(String code){
		return scategorieRepository.findByCode(code);
	}
	
	public long save(Scategorie scategorie) {
		System.out.println("save all categories 1...");
		Scategorie scat = new Scategorie();
		scat.setCode(scategorie.getCode());
		scat.setLibelle(scategorie.getLibelle());
		scat.setCcateg(scategorie.getCcateg());
		scat.setRang(1);
		return scategorieRepository.save(scat).getId();	
	}
	
	public void update(String code, Scategorie scategorie) {
		Optional<Scategorie> scateg = scategorieRepository.findByCode(code);
		if(scateg.isPresent()) {
			Scategorie scat = scateg.get();
			scat.setLibelle(scategorie.getLibelle());
			scat.setCcateg(scategorie.getCcateg());
			scategorieRepository.save(scat);
		}
	}
	
	public List<Scategorie> findByLibelle(String libelle){
		return scategorieRepository.findAllByLibelleContaining(libelle);
	}
	
	public void delete(String code) {
		Optional<Scategorie> scat = scategorieRepository.findByCode(code);
		scat.ifPresent(scategorieRepository::delete);
	}
	
	public List<Scategorie> findByCcateg(String code){
		return scategorieRepository.findAllByCcateg(code);
	}
	
	public int nbre(String code) {
		return scategorieRepository.nbre(code);
	}
	
	public int max(String code) {
		return scategorieRepository.max(code);
	}

}
