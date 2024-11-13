package sig.org.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
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

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import sig.org.Dto.ListArticle;
import sig.org.entity.Article;
import sig.org.entity.ArticleExcel;
import sig.org.service.ArticleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ArticleController {
	
	@Autowired
	private ArticleService artService;
	@Autowired
	ServletContext context;
	
	@GetMapping("/articles/7/{code}")
	public int getCode(@PathVariable String code) {
		System.out.println("Get Numbers...");
		int x = artService.nbre(code);
		System.out.println(x);
		if (x == 0)
		{
			return 0;
		}else {
			return artService.max(code);
		}
		
	}
	
	@GetMapping("/articles")
	public List<ListArticle> list(){
		System.out.println("Get all Articles...");
		      return artService.getAll();
		
	}
	
	@GetMapping("/articles/f/{code}")
	public List<ListArticle> listArtfour(@PathVariable int code){
		System.out.println("Get All Articles...");
		return artService.listArticleFour(code);
		
	}
	@GetMapping("/articles/{id}")
	public ResponseEntity<Article> post(@PathVariable String id){
		Optional<Article> article = artService.findByCode(id);
		return article.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/articles")
	public Long createArticle (@RequestParam("file") MultipartFile file,
			@RequestParam("article") String article) throws JsonParseException, JsonMappingException,Exception
	{
		System.out.println("Save Article......");
		Article art = new ObjectMapper().readValue(article, Article.class);
		boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
		if (!isExit)
		{
			new File (context.getRealPath("/Imagess")).mkdir();
			System.out.println("mk dir Imagessss...");
		}
		System.out.println("Save Article 2........");
		String filename = file.getOriginalFilename();
		String newFilename = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		File serverFile = new File (context.getRealPath("/Imagess/"+File.separator+newFilename));
		try
		{
			System.out.println("Image");
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Save Article 3.........");
		art.setFileName(newFilename);
		return artService.save(art);
	}
	
	@PutMapping("/articles/{code}")
	public void update(@PathVariable String code, @RequestBody Article article) {
		Optional<Article> art = artService.findByCode(code);
		if (art.isPresent()) {
			artService.update(code, article);
		}
	}
	
	@DeleteMapping("/articles/{code}")
	public void delete(@PathVariable String code) {
		artService.delete(code);
	}
	
	@GetMapping(path="/Imparticles/{id}")
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		Article art = artService.findById(id).get();
		return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/")+art.getFileName()));
		
	}
	
	@GetMapping("/articles/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		System.out.println("Export to Excel ...");
		response.setContentType("application/octe-stream");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=articles_" + currentDateTime + ".xlxs";
		response.setHeader(headerKey, headerValue);
		List<ListArticle> listArticles = artService.getAll();
		ArticleExcel excel = new ArticleExcel(listArticles);
		excel.export(response);
		
	}
	
	
}


