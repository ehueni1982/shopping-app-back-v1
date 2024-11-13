package sig.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parametre")
public class Parametre {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String libcourt;
	private String libelle;
	private String adresse;
	private String mf;
	private String email;
	private String rib;
	private String tel;
	private int numc;
	private int numf;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibcourt() {
		return libcourt;
	}
	public void setLibcourt(String libcourt) {
		this.libcourt = libcourt;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getMf() {
		return mf;
	}
	public void setMf(String mf) {
		this.mf = mf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRib() {
		return rib;
	}
	public void setRib(String rib) {
		this.rib = rib;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getNumc() {
		return numc;
	}
	public void setNumc(int numc) {
		this.numc = numc;
	}
	public int getNumf() {
		return numf;
	}
	public void setNumf(int numf) {
		this.numf = numf;
	}
	@Override
	public String toString() {
		return "Parametre [id=" + id + ", libcourt=" + libcourt + ", libelle=" + libelle + ", adresse=" + adresse
				+ ", mf=" + mf + ", email=" + email + ", rib=" + rib + ", tel=" + tel + ", numc=" + numc + ", numf="
				+ numf + "]";
	}
	public Parametre(Long id, String libcourt, String libelle, String adresse, String mf, String email, String rib,
			String tel, int numc, int numf) {
		super();
		this.id = id;
		this.libcourt = libcourt;
		this.libelle = libelle;
		this.adresse = adresse;
		this.mf = mf;
		this.email = email;
		this.rib = rib;
		this.tel = tel;
		this.numc = numc;
		this.numf = numf;
	}
	public Parametre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
