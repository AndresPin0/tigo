package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tigo.aplanchados.model.Person;
import tigo.aplanchados.model.PersonPK;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonPK> {

    Optional<Person> findByPersonPK_DocumentNumber(String documentNumber);
}
