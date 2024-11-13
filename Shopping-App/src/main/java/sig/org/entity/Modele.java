package sig.org.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class Modele {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String code;
	private String description;

}
