package sig.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "scategorie")
public class Scategorie {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String code;
	private String libelle;
	private String ccateg;
	private int rang;
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
	public String getCcateg() {
		return ccateg;
	}
	public void setCcateg(String ccateg) {
		this.ccateg = ccateg;
	}
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
	@Override
	public String toString() {
		return "Scategorie [id=" + id + ", code=" + code + ", libelle=" + libelle + ", ccateg=" + ccateg + ", rang="
				+ rang + "]";
	}
	public Scategorie(Long id, String code, String libelle, String ccateg, int rang) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.ccateg = ccateg;
		this.rang = rang;
	}
	public Scategorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
