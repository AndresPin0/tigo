package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import tigo.aplanchados.model.DocumentType;
import tigo.aplanchados.repositories.DocumentTypeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Con @DirtiesContext: El contexto se limpia antes de cada método de prueba para que no haya interferencia entre las pruebas.
//check
@SpringBootTest
//@Sql({"/sql/schema.sql", "/sql/data.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DocumentTypeRepositoryTest {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Test
    void testSaveAndFindById() {
        DocumentType documentType = new DocumentType();
        documentType.setName("Foreign ID");
        DocumentType savedDocumentType = documentTypeRepository.save(documentType);
        Optional<DocumentType> foundDocumentType = documentTypeRepository.findById(savedDocumentType.getId());
        assertTrue(foundDocumentType.isPresent());
        assertEquals("Foreign ID", foundDocumentType.get().getName());
    }

    @Test
    void testDelete() {
        DocumentType documentType = new DocumentType();
        documentType.setName("Foreign ID");
        DocumentType savedDocumentType = documentTypeRepository.save(documentType);
        documentTypeRepository.deleteById(savedDocumentType.getId());
        Optional<DocumentType> foundDocumentType = documentTypeRepository.findById(savedDocumentType.getId());
        assertTrue(foundDocumentType.isEmpty());
    }
}
