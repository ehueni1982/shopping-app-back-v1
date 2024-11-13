package sig.org.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sig.org.entity.User;
import sig.org.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAll(){
		System.out.println("Get all Users 1...");
		return userRepository.findAll(Sort.by("username").ascending());
	}
	
	public List<User> getAllEmail(String email){
		System.out.println("Get all Users 1...");
		return userRepository.findAllByEmail(email);
	}
	
	public Optional<User> findById(Long id){
		return userRepository.findById(id);
	}
	
	public long save(User user) {
		System.out.println("save all Users 1...");
		user.setActive(true);
		return userRepository.save(user).getId();
	}
	
	public void update(Long id, User user) {
		Optional<User> users = userRepository.findById(id);
			if(users.isPresent()) {
				User usr = users.get();
				usr.setUsername(user.getUsername());
				usr.setEmail(user.getEmail());
				usr.setPassword(user.getPassword());
				usr.setRole(user.getRole());
				usr.setActive(user.isActive());
				userRepository.save(usr);
			}
	}
	
	public Optional<User> login(String name){
		return userRepository.findAllByUsername(name);
	}
	
	public void delete(Long id) {
		Optional<User> user = userRepository.findById(id);
		user.ifPresent(userRepository::delete);
	}

}
