package task4.rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task4.rest.dto.SmartphoneDTO;
import task4.services.SmartphoneService;


import javax.validation.Valid;
import java.util.List;

@RestController
public class SmartphoneController {

    private final SmartphoneService smartphoneService;

    public SmartphoneController(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    @Operation(description = "Get all smartphones")
    @GetMapping("/smartphones")
    public List<SmartphoneDTO> getSmartphones(@RequestParam(name = "model", required = false) String model,
                                              @RequestParam(name = "brand", required = false) String brand,
                                              @RequestParam(name = "minPrice", required = false) Integer minPrice,
                                              @RequestParam(name = "maxPrice", required = false) Integer maxPrice) {
        return smartphoneService.getSmartphones(model, brand, minPrice, maxPrice);
    }

    @Operation(description = "Add new smartphone")
    @PostMapping("/smartphone")
    public SmartphoneDTO addSmartphone(@Valid @RequestBody SmartphoneDTO smartphoneDTO) {
        return smartphoneService.addSmartphone(smartphoneDTO);
    }

    @Operation(description = "Edit smartphone") // + adnotacja @Valid
    @PutMapping("/smartphone/{id}")
    public SmartphoneDTO editSmartphone(@PathVariable(name = "id") Long id,
                                        @Valid @RequestBody SmartphoneDTO smartphoneDTO) {
        return smartphoneService.editSmartphone(id, smartphoneDTO);
    }
    @Operation(description = "Delete smartphone")
    @DeleteMapping("/smartphone/{id}")
    public ResponseEntity deleteSmartphone(@PathVariable(name = "id") Long id) {
        smartphoneService.deleteSmartphone(id);
        return ResponseEntity.ok().build();
    }

}
