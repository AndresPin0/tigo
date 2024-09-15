package tigo.aplanchados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tigo.aplanchados.model.*;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    
}
