package sig.org.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sig.org.entity.Compteur;
import sig.org.repository.CompteurRepository;

@Service
@Transactional
public class CompteurService {
	
	@Autowired
	CompteurRepository compteurRepository;
	
	public int nbre(int annee) {
		return compteurRepository.nbre(annee);
	}
	
	public void create(int annee) {
		Compteur cpt = new Compteur();
		cpt.setAnnee(annee);
		cpt.setNumpanier(1);
		compteurRepository.save(cpt);
	}
	
	public Optional<Compteur> findByAnnee(int anne){
		return compteurRepository.findByAnnee(anne);
	}

}
