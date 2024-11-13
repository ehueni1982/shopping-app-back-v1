package sig.org.entity;

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
@Table(name = "lpanier")
public class Lpanier {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int numero;
	private String code;
	private String libelle;
	private int pv;
	private double qte;
	private int tva;
	private double montht;
	private double monttva;
	private double montttc;
	@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private Panier panier;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public double getQte() {
		return qte;
	}
	public void setQte(double qte) {
		this.qte = qte;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public double getMontht() {
		return montht;
	}
	public void setMontht(double montht) {
		this.montht = montht;
	}
	public double getMonttva() {
		return monttva;
	}
	public void setMonttva(double monttva) {
		this.monttva = monttva;
	}
	public double getMontttc() {
		return montttc;
	}
	public void setMontttc(double montttc) {
		this.montttc = montttc;
	}
	public Panier getPanier() {
		return panier;
	}
	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	@Override
	public String toString() {
		return "Lpanier [id=" + id + ", numero=" + numero + ", code=" + code + ", libelle=" + libelle + ", pv=" + pv
				+ ", qte=" + qte + ", tva=" + tva + ", montht=" + montht + ", monttva=" + monttva + ", montttc="
				+ montttc + ", panier=" + panier + "]";
	}
	public Lpanier(Long id, int numero, String code, String libelle, int pv, double qte, int tva, double montht,
			double monttva, double montttc, Panier panier) {
		super();
		this.id = id;
		this.numero = numero;
		this.code = code;
		this.libelle = libelle;
		this.pv = pv;
		this.qte = qte;
		this.tva = tva;
		this.montht = montht;
		this.monttva = monttva;
		this.montttc = montttc;
		this.panier = panier;
	}
	public Lpanier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
