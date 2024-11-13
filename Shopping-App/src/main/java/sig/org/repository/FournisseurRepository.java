package sig.org.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sig.org.entity.Fournisseur;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

	List<Fournisseur> findAllByLibelleContaining(String libelle);
	
	Optional<Fournisseur> findByCode(int id);
	
	List<Fournisseur> findAllByEmail(String email);
	
}
