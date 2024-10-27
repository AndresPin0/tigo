package tigo.aplanchados.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {
    private Long id;
    private String name;
    private List<Long> permissionsIds;
    
    
    
}
