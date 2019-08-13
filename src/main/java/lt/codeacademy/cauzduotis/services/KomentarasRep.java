package lt.codeacademy.cauzduotis.services;

import lt.codeacademy.cauzduotis.models.Komentaras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KomentarasRep extends JpaRepository<Komentaras, Integer> {
    @Query(value = "select *  from KOMENTARAS   where (irasas =?1)", nativeQuery = true)
      List<Komentaras> findByIrasasId(int id);
}
