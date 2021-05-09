package task4.rest.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class SmartphoneDTO {
    @Schema(description = "id of existing smartphone", example = "1")
    private Long id;
    @Schema(description = "smartphone brand", example = "Nokia",required = true)
    @NotBlank(message="smartphone brand  can't be blank")
    @NotNull(message="smartphone brand can't be null")
    private String name;
    @Schema(description = "smartphone model", example = "3310",required = true)
    @NotBlank(message="smartphone model can't be blank")
    @NotNull(message="smartphone model can't be null")
    private String model;
    @Schema(description = "smartphone price", example = "10.99",required = true)
    @NotBlank(message="smartphone price can't be blank")
    @Min(value = 0, message = "min 0")
    private Double price;

}
