package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tigo.aplanchados.model.*;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    DocumentType findByid(Long id);
    
}
