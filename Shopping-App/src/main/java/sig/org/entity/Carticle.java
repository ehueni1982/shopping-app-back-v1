package sig.org.entity;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="article")
public class Carticle {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String code;
	private String libelle;
	@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private Article article;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Article getArcArticle() {
		return article;
	}
	public void setArcArticle(Article arcArticle) {
		this.article = article;
	}
	@Override
	public String toString() {
		return "Carticle [id=" + id + ", code=" + code + ", libelle=" + libelle + ", article=" + article + "]";
	}
	public Carticle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Carticle(Long id, String code, String libelle, Article article) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.article = article;
	}
	
	

}
