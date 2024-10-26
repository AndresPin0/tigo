package tigo.aplanchados.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tigo.aplanchados.dtos.IncomeDTO;
import tigo.aplanchados.model.Income;
import java.util.List;

@Mapper
public interface IncomeMapper {
    IncomeMapper INSTANCE = Mappers.getMapper(IncomeMapper.class);

    @Mapping(source = "person.personPK.documentNumber", target = "personDocumentNumber")
    @Mapping(source = "paymentMethod.code", target = "paymentMethodCode")
    @Mapping(source = "paymentType.code", target = "paymentTypeCode")
    @Mapping(source = "incomeConcept.id", target = "incomeConceptCode")
    @Mapping(source = "user.id", target = "userId")
    IncomeDTO toDTO(Income income);

    @Mapping(source = "personDocumentNumber", target = "person.personPK.documentNumber")
    @Mapping(source = "paymentMethodCode", target = "paymentMethod.code")
    @Mapping(source = "paymentTypeCode", target = "paymentType.code")
    @Mapping(source = "incomeConceptCode", target = "incomeConcept.id")
    @Mapping(source = "userId", target = "user.id")
    Income toEntity(IncomeDTO incomeDTO);

    List<IncomeDTO> toDTOs(List<Income> incomes); 
}
