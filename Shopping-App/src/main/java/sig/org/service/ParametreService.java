package sig.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sig.org.entity.Parametre;
import sig.org.repository.ParametreRepository;

@Service
@Transactional
public class ParametreService {
	
	@Autowired
	ParametreRepository parametreRepository;
	
	public List<Parametre> getAll(){
		System.out.println("Get all parametre 1...");
		return parametreRepository.findAll(Sort.by("libelle").ascending());
	}
	
	public Optional<Parametre> findById(Long id){
		return parametreRepository.findById(id);
	}
	
	public long save(Parametre parametre) {
		System.out.println("save all parametre 1...");
		return parametreRepository.save(parametre).getId();
	}
	
	public void update(Long id, Parametre parametre) {
		Optional<Parametre> param = parametreRepository.findById(id);
		if(param.isPresent()) {
			Parametre para = param.get();
			para.setLibelle(parametre.getLibelle());
			parametreRepository.save(para);
		}
	}
	
	public void delete(Long id) {
		Optional<Parametre> param = parametreRepository.findById(id);
		param.ifPresent(parametreRepository::delete);
	}

}
