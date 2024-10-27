package tigo.aplanchados.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tigo.aplanchados.model.User;
import tigo.aplanchados.dtos.UserDTO;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "role", target = "role.id")
    User toEntity(UserDTO userDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "role.id", target = "role")
    UserDTO toDTO(User user);

    List<UserDTO> toDTOs(List<User> users);
    
}
