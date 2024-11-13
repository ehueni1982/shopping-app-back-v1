package sig.org.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sig.org.Dto.ListCategorie;
import sig.org.entity.Scategorie;

@Repository
public interface ScategorieRepository extends JpaRepository<Scategorie, Long> {

	Optional<Scategorie> findByCode(String code);
	
	List<Scategorie> findAllByLibelleContaining(String libelle);
	
	List<Scategorie> findAllByCcateg(String code);
	
	@Query(value = "SELECT count(*) FROM Scategorie WHERE ccateg = :code")
	public int nbre(@Param("code") String code);
	
	@Query(value = "SELECT max(code) FROM Scategorie WHERE ccateg = :code")
	public int max(@Param("code") String code);
	
	@Query(value = "SELECT new sig.org.Dto.ListCategorie(a.code,a.libelle,b.libelle,b.code) FROM Scategorie a join Categorie b"
			+ "on a.ccateg = b.code")
	public List<ListCategorie> listScateg();
	
	
}
