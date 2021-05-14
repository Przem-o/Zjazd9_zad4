package task4.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ClientDTO {

    @Schema(description = "id of existing client", example = "1") // @Schema adnotacje od swaggera
    private Long id;
    @Schema(description = "client name", example = "Jaro",required = true)
    @NotBlank(message="name can't be blank")
    @NotNull(message="name can't be null")
    private String name;
    @Schema(description = "client address",required = true)
    private AddressDTO address;

}
