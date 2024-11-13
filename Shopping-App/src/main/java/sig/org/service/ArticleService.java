package sig.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sig.org.Dto.ListArticle;
import sig.org.entity.Article;
import sig.org.repository.ArticleRepository;

@Service
@Transactional
public class ArticleService {
	
	@Autowired
	ArticleRepository articlerepository;
	
	public List<ListArticle> getAll(){
		System.out.println("Get all Articles .....");
		return articlerepository.listArticle();	}
	
	
	public Optional<Article> findByCode(String code){
		return articlerepository.findByCode(code);
		
	}
	
	public Long save(Article article) {
		return articlerepository.save(article).getId();
	}
	
	public void update(String code, Article article) {
		Optional<Article> articles = articlerepository.findByCode(code);
		if (articles.isPresent()) {
			Article art = articles.get();
			art.setLibelle(article.getLibelle());
			art.setCcateg(article.getCcateg());
			articlerepository.save(art);
		}
	}
	
	
	public List<Article> findByLibelle(String libelle){
		return articlerepository.findAllByLibelleContaining(libelle);
	}
	
	public void delete(String code) {
		Optional<Article> art = articlerepository.findByCode(code);
		art.ifPresent(articlerepository::delete);
	}
	
	public List<Article> findByCcateg(String code){
		return articlerepository.findAllByCcateg(code);
	}
	
	public List<Article> findByCscateg(String code){
		return articlerepository.findAllByCscateg(code);
	}
	
	public int nbre(String code) {
		return articlerepository.nbre(code);
	}
	
	public int max(String code) {
		return articlerepository.max(code);
	}
	
	public Optional<Article> findById(Long id){
		return articlerepository.findAllById(id);
	}
	
	public List<ListArticle> listArticleFour(int code){
		return articlerepository.listArticleFour(code);
	}
}
