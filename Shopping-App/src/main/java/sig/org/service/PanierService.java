package sig.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sig.org.entity.Compteur;
import sig.org.entity.Lpanier;
import sig.org.entity.Panier;
import sig.org.repository.CompteurRepository;
import sig.org.repository.LpanierRepository;
import sig.org.repository.PanierRepository;

@Service
@Transactional
public class PanierService {
	
	@Autowired
	PanierRepository panierRepository;
	@Autowired
	LpanierRepository lpanierRepository;
	@Autowired
	CompteurRepository compteurRepository;
	
	public List<Panier> getAll(){
		System.out.println("Get all Paniers 1...");
		return panierRepository.findAll(Sort.by("numero").ascending());
		
	}
	
	public Optional<Panier> findById(Long id){
		return panierRepository.findById(id);
	}
	
	public long save(Panier panier) {
		System.out.println("Save Lpanier ...");
		List<Lpanier> lpaniers = panier.getLpaniers();
		for(Lpanier lp : lpaniers) {
			lp.setNumero(panier.getNumero());
			lpanierRepository.save(lp);
		}
	 Optional<Compteur> compteur = compteurRepository.findByAnnee(panier.getAnnee());
	 	if(compteur.isPresent()){
	 		Compteur compt = compteur.get();
	 		compt.setNumpanier(compt.getNumpanier()+1);
	 		compt = compteurRepository.save(compt);
	 	}
	 	return panierRepository.save(panier).getId();
	}
	
	
	public List<Panier> findByNom(String nom){
		return panierRepository.findAllByNom(nom);
	}
	
	public void delete(Long id) {
		Optional<Panier> panier = panierRepository.findById(id);
		panier.ifPresent(panierRepository::delete);
		
	}

}
