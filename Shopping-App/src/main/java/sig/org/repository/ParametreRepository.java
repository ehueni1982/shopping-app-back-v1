package sig.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sig.org.entity.Parametre;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {

}
