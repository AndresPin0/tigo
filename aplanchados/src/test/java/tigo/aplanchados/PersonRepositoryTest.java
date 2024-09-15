package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import tigo.aplanchados.model.DocumentType;
import tigo.aplanchados.model.Person;
import tigo.aplanchados.model.PersonPK;
import tigo.aplanchados.repositories.DocumentTypeRepository;
import tigo.aplanchados.repositories.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PersonRepositoryTest {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSaveAndFindById() {
        DocumentType documentType = new DocumentType();
        documentType.setName("test");
        documentTypeRepository.save(documentType);
        PersonPK personPK = new PersonPK();
        personPK.setDocumentType(documentType);
        personPK.setDocumentNumber("11");
        Person person = new Person();
        person.setPersonPK(personPK);
        person.setName("test");
        personRepository.save(person);
        if (personRepository.findById(person.getPersonPK()).isEmpty()) {
            fail();
        }
        assertEquals(person.getPersonPK().getDocumentNumber(), personRepository.findById(person.getPersonPK()).get().getPersonPK().getDocumentNumber());
        
    }

    @Test
    void testDelete() {
        DocumentType documentType = new DocumentType();
        documentType.setName("test");
        documentTypeRepository.save(documentType);
        PersonPK personPK = new PersonPK();
        personPK.setDocumentType(documentType);
        personPK.setDocumentNumber("11");
        Person person = new Person();
        person.setName("test");
        person.setPersonPK(personPK);
        personRepository.save(person);
        if (personRepository.findById(person.getPersonPK()).isEmpty()) {
            fail();
        }
        assertEquals(person.getPersonPK().getDocumentNumber(), personRepository.findById(person.getPersonPK()).get().getPersonPK().getDocumentNumber());
        personRepository.deleteById(person.getPersonPK());
        assertFalse(personRepository.findById(person.getPersonPK()).isPresent());


    }
}
