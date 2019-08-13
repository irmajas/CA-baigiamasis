package lt.codeacademy.cauzduotis.services;

import lt.codeacademy.cauzduotis.models.Irasas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrasasRep extends JpaRepository<Irasas,Integer> {
}
