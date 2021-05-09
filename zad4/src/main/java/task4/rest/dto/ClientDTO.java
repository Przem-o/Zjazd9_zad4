package task4.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClientDTO {

    @Schema(description = "id of existing client", example = "1")
    private Long id;
    @Schema(description = "client name", example = "Jaro",required = true)
    @NotBlank(message="name can't be blank")
    @NotNull(message="name can't be null")
    private String name;
    @Schema(description = "client address",required = true)
    private AddressDTO address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

}
