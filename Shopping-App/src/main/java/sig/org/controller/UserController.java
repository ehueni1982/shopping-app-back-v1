package sig.org.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletContext;
import sig.org.entity.User;
import sig.org.service.UserService;

@RestController
@RequestMapping("api")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	ServletContext context;
	
	@GetMapping("/users")
	public List<User> getAllList(){
		System.out.println("Get all Users ...");
		return userService.getAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> post(@PathVariable Long id){
		Optional<User> user = userService.findById(id);
		return user.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/users/verif/{email}")
	public List<User> listUser(@PathVariable String email){
		System.out.println("Get all Users ...");
		return userService.getAllEmail(email);
	}
	
	@PostMapping("/users")
	public long createUser (@RequestParam("file") MultipartFile file,
			@RequestParam("user") String user) throws JsonParseException,JsonMappingException,Exception{
		System.out.println("Save Article ...");
		User users = new ObjectMapper().readValue(user, User.class);
		boolean isExist = new File(context.getRealPath("/Imagess/")).exists();
		
		if (!isExist) {
			
			new File (context.getRealPath("/Imagess/")).mkdir();
			System.out.println("mk dir Imagess ...");
		}
		System.out.println("Save Article 2...");
		String filename = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		File serverFile = new File (context.getRealPath("/Imagess/"+File.separator+newFileName));
		try {
			System.out.println("Image");
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Save Article 3 ...");
		users.setFileName(newFileName);
		return userService.save(users);
	}
	
	@PostMapping("/userss")
	public long save(@RequestBody User user) {
		System.out.println("Save all Users ...");
		return userService.save(user);
	}
	
	@PutMapping("/users/{id}")
	public void update(@PathVariable Long id, @RequestBody User user) {
		Optional<User> users = userService.findById(id);
		if(users.isPresent()) {
			userService.update(id, user);
		}else {
			userService.save(user);
		}
	}
	
	@PutMapping("/users/{id}")
	public void update(@PathVariable long id, @RequestBody User user) {
		Optional<User> users = userService.findById(id);
		if(users.isPresent()) {
			userService.update(id, user);
		}else {
			userService.save(user);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
	
	
	@GetMapping(path="/Imgusers/{id}")
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		User user = userService.findById(id).get();
		return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/")+user.getFileName()));
	}

}
