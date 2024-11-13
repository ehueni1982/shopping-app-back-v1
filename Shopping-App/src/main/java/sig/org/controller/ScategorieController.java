package sig.org.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletResponse;
import sig.org.Dto.ListCategorie;
import sig.org.entity.Scategorie;
import sig.org.entity.ScategorieExcel;
import sig.org.service.ScategorieService;

@RestController
@RequestMapping("api")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin("*")
public class ScategorieController {
	
	@Autowired
	private ScategorieService scategorieService;
	
	@GetMapping("/scategories/7/{code}")
	public int getCode(@PathVariable String code) {
		System.out.println("Get Numbers ...");
		int x = scategorieService.nbre(code);
		System.out.println(x);
		if(x == 0) {
			return 0;
		}else {
			return scategorieService.max(code);
		}
	}
	
	@GetMapping("/scategories/{code}")
	public ResponseEntity<Scategorie> post(@PathVariable String code){
		Optional<Scategorie> scat = scategorieService.findByCode(code);
		return scat.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/scategories")
	public long save(@RequestBody Scategorie scategorie) {
		return scategorieService.save(scategorie);
	}
	
	@DeleteMapping("/scategories/{code}")
	public void delete(@PathVariable String code) {
		scategorieService.delete(code);
	}
	
	@GetMapping("/scategories/5/{code}")
	public ResponseEntity<List<Scategorie>> listCateg(@PathVariable String code){
		
		List<Scategorie> scategories = scategorieService.findByCcateg(code);
		return new ResponseEntity<List<Scategorie>>(scategories, HttpStatus.OK);
	}
	
	@GetMapping("/scategories/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException{
		System.out.println("Export to Excel ...");
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=categories_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<ListCategorie> listCategories = scategorieService.getAll();
		ScategorieExcel excel = new ScategorieExcel(listCategories);
		excel.export(response);
	}

}
