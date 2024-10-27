package tigo.aplanchados.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import tigo.aplanchados.dtos.RoleDTO;
import tigo.aplanchados.model.Role;
import tigo.aplanchados.model.RolePermission;


@Mapper
public interface RoleMapper {
  RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "rolePermissions", target = "permissionsIds")
    RoleDTO toRoleDTO(Role role);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Role toRole(RoleDTO roleDTO);

    List<RoleDTO> toDTOs(List<Role> roles);

    default List<Long> map(List<RolePermission> rolePermissions) {
        return rolePermissions.stream().map( rp-> rp.getRolePermissionPK().getPermission().getId() ).collect(Collectors.toList());
    }

    
    
    
    
}
