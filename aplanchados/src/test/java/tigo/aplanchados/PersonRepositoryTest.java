package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tigo.aplanchados.model.DocumentType;
import tigo.aplanchados.model.Person;
import tigo.aplanchados.model.PersonPK;
import tigo.aplanchados.repositories.DocumentTypeRepository;
import tigo.aplanchados.repositories.PersonRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSaveAndFindById() {
        DocumentType documentType = new DocumentType();
        documentType.setName("test");
        PersonPK personPK = new PersonPK();
        personPK.setDocumentType(documentType);
        personPK.setNumeroDeDocumento("11");

        Person person = new Person();
        person.setPersonPK(personPK);
        personRepository.save(person);
        Optional<Person> foundPerson = personRepository.findById(personPK);


        assertTrue(foundPerson.isPresent());

    }

    @Test
    void testDelete() {
        DocumentType documentType = new DocumentType();
        documentType.setName("test");
        PersonPK personPK = new PersonPK();
        personPK.setDocumentType(documentType);
        personPK.setNumeroDeDocumento("11");
        Person person = new Person();
        person.setPersonPK(personPK);

        personRepository.save(person);


    }
}
