package tigo.aplanchados.mappers;

import java.util.List;
import tigo.aplanchados.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import tigo.aplanchados.dtos.PermissionDTO;

@Mapper
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);
    @Mapping(source="name", target="name")
    @Mapping(source="id", target="id")
    Permission toEntity(PermissionDTO permissionDTO);

    @Mapping(source="name", target="name")
    @Mapping(source="id", target="id")
    PermissionDTO toDTO(Permission permission);

    List<PermissionDTO> toDTOs(List<Permission> permissions);

    
}
