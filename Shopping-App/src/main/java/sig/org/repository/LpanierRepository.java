package sig.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sig.org.entity.Lpanier;

@Repository
public interface LpanierRepository extends JpaRepository<Lpanier, Long> {
	

}
