package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tigo.aplanchados.model.Person;
import tigo.aplanchados.model.PersonPK;

public interface PersonRepository extends JpaRepository<Person, PersonPK> {
    // Puedes agregar métodos de consulta personalizados aquí si es necesario
}