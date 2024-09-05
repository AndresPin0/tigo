package tigo.aplanchados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tigo.aplanchados.model.DocumentType;
import tigo.aplanchados.repositories.DocumentTypeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//check
@SpringBootTest
class DocumentTypeRepositoryTest {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Test
    void testSaveAndFindById() {
        DocumentType documentType = new DocumentType();
        documentType.setName("passport");

        DocumentType savedDocumentType = documentTypeRepository.save(documentType);

        Optional<DocumentType> foundDocumentType = documentTypeRepository.findById(savedDocumentType.getId());

        assertTrue(foundDocumentType.isPresent());
        assertEquals("passport", foundDocumentType.get().getName());
    }

    @Test
    void testDelete() {
        DocumentType documentType = new DocumentType();
        documentType.setName("passport");


        DocumentType savedDocumentType = documentTypeRepository.save(documentType);
        documentTypeRepository.deleteById(savedDocumentType.getId());

        Optional<DocumentType> foundDocumentType = documentTypeRepository.findById(savedDocumentType.getId());

        assertTrue(foundDocumentType.isEmpty());
    }
}
