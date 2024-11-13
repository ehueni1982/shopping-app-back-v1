package sig.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sig.org.entity.Panier;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {
	
	List<Panier> findAllByNom(String nom);

}
