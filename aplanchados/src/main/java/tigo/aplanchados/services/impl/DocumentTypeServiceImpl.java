package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tigo.aplanchados.model.DocumentType;
import tigo.aplanchados.repositories.DocumentTypeRepository;

import java.util.Optional;

@Service
public class DocumentTypeServiceImpl {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    /**
     * Obtiene el tipo de documento por defecto.
     * Puedes modificar esta lógica para seleccionar un tipo de documento específico como predeterminado.
     */
    public DocumentType getDefaultDocumentType() {
        return documentTypeRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No default document type found"));
    }

    /**
     * Encuentra un tipo de documento por su código.
     */
    public Optional<DocumentType> findByCode(String code) {
        return documentTypeRepository.findById(Long.valueOf(code));
    }
}
