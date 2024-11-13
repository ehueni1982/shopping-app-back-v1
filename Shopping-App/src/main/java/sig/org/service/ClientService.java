package sig.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sig.org.entity.Client;
import sig.org.entity.Parametre;
import sig.org.entity.User;
import sig.org.repository.ClientRepository;
import sig.org.repository.ParametreRepository;
import sig.org.repository.UserRepository;

@Service
@Transactional
public class ClientService {
	
	@Autowired
	ClientRepository repoClientRepository;
	@Autowired
	ParametreRepository repoParametreRepository;
	@Autowired
	UserRepository repoUserRepository;
	
	public List<Client> getAll(){
		System.out.println("Get all Clients 1...");
		return repoClientRepository.findAll(Sort.by("libelle").ascending());
	}
	
	public Optional<Client> findByCode(int id){
		return repoClientRepository.findByCode(id);
	}
	
	public Long save(Client client) {
		long id = 1;
		Optional<Parametre> paramInfo = repoParametreRepository.findById(id);
			if (paramInfo.isPresent()) {
				Parametre param = paramInfo.get();
					param.setNumc(param.getNumc()+1);
					param = repoParametreRepository.save(param);
			}
			User user = new User();
				user.setUsername(client.getEmail());
				user.setEmail(client.getEmail());
				user.setPassword(client.getPwd());
				user.setRole("CLIENT");
				user.setActive(true);
				repoUserRepository.save(user);
				return repoClientRepository.save(client).getId();
	}
	public void update(int code, Client client) {
		Optional<Client> clt = repoClientRepository.findByCode(code);
		if (clt.isPresent()) {
			Client cl = clt.get();
			cl.setLibelle(client.getLibelle());
			cl.setAdresse(cl.getAdresse());
			repoClientRepository.save(cl);
		}
	}
	
	public List<Client> findByLibelle(String libelle){
		return repoClientRepository.findAllByLibelleContaining(libelle);
	}
	
	public List<Client> findByEmail(String email){
		return repoClientRepository.findAllByEmail(email);
	}
	
	public void delete(int code) {
		Optional<Client> cat = repoClientRepository.findByCode(code);
		cat.ifPresent(repoClientRepository::delete);
	}

}
