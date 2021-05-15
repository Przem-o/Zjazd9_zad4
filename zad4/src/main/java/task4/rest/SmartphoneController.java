package task4.rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task4.config.cache.SecurityConfig;
import task4.rest.dto.SmartphoneDTO;
import task4.services.SmartphoneService;


import javax.validation.Valid;
import java.util.List;

import static task4.config.cache.SecurityConfig.*;

@RestController
public class SmartphoneController {

    private final SmartphoneService smartphoneService;

    public SmartphoneController(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    @PreAuthorize("hasRole('" + ADMIN_ROLE +"') || hasRole('" + SALES_ROLE +"')")
    @Operation(description = "Get all smartphones")
    @GetMapping("/smartphones")
    public List<SmartphoneDTO> getSmartphones(@RequestParam(name = "model", required = false) String model,
                                              @RequestParam(name = "brand", required = false) String brand,
                                              @RequestParam(name = "minPrice", required = false) Integer minPrice,
                                              @RequestParam(name = "maxPrice", required = false) Integer maxPrice) {
        return smartphoneService.getSmartphones(model, brand, minPrice, maxPrice);
    }
    @PreAuthorize("hasRole('" + ADMIN_ROLE +"') || hasRole('" + MANAGER_ROLE +"')")
    @Operation(description = "Add new smartphone")
    @PostMapping("/smartphone")
    public SmartphoneDTO addSmartphone(@Valid @RequestBody SmartphoneDTO smartphoneDTO) {
        return smartphoneService.addSmartphone(smartphoneDTO);
    }
    @PreAuthorize("hasRole('" + ADMIN_ROLE +"') || hasRole('" + MANAGER_ROLE +"')")
    @Operation(description = "Edit smartphone") // + adnotacja @Valid
    @PutMapping("/smartphone/{id}")
    public SmartphoneDTO editSmartphone(@PathVariable(name = "id") Long id,
                                        @Valid @RequestBody SmartphoneDTO smartphoneDTO) {
        return smartphoneService.editSmartphone(id, smartphoneDTO);
    }
    @PreAuthorize("hasRole('" + ADMIN_ROLE +"') || hasRole('" + MANAGER_ROLE +"')")
    @Operation(description = "Delete smartphone")
    @DeleteMapping("/smartphone/{id}")
    public ResponseEntity deleteSmartphone(@PathVariable(name = "id") Long id) {
        smartphoneService.deleteSmartphone(id);
        return ResponseEntity.ok().build();
    }

}
