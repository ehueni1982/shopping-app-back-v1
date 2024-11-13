package sig.org.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lvente")
public class Lvente {
	
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
	private Vente vente;
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
	public Vente getVente() {
		return vente;
	}
	public void setVente(Vente vente) {
		this.vente = vente;
	}
	@Override
	public String toString() {
		return "Lvente [id=" + id + ", numero=" + numero + ", code=" + code + ", libelle=" + libelle + ", pv=" + pv
				+ ", qte=" + qte + ", tva=" + tva + ", montht=" + montht + ", monttva=" + monttva + ", montttc="
				+ montttc + ", vente=" + vente + "]";
	}
	public Lvente(Long id, int numero, String code, String libelle, int pv, double qte, int tva, double montht,
			double monttva, double montttc, Vente vente) {
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
		this.vente = vente;
	}
	public Lvente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
