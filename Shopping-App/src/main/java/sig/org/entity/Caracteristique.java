package sig.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="caracteristique")
public class Caracteristique {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String code;
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Caracteristique(Long id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}
	public Caracteristique() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Caracteristique [id=" + id + ", code=" + code + ", description=" + description + "]";
	}
	
	

}
