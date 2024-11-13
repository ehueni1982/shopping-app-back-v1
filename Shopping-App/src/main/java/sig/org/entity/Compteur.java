package sig.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "compteur")
public class Compteur {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int annee;
	private int numpanier;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getNumpanier() {
		return numpanier;
	}
	public void setNumpanier(int numpanier) {
		this.numpanier = numpanier;
	}
	@Override
	public String toString() {
		return "Compteur [id=" + id + ", annee=" + annee + ", numpanier=" + numpanier + "]";
	}
	public Compteur(Long id, int annee, int numpanier) {
		super();
		this.id = id;
		this.annee = annee;
		this.numpanier = numpanier;
	}
	public Compteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
