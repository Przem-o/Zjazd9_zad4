package task4.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task4.rest.dto.SmartphoneDTO;
import task4.services.SmartphoneService;


import java.util.List;

@RestController
public class SmartphoneController {

    private final SmartphoneService smartphoneService;

    public SmartphoneController(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    @GetMapping("/smartphones")
    public List<SmartphoneDTO> getSmartphones(@RequestParam(name = "model", required = false) String model,
                                              @RequestParam(name = "brand", required = false) String brand,
                                              @RequestParam(name = "minPrice", required = false) Integer minPrice,
                                              @RequestParam(name = "maxPrice", required = false) Integer maxPrice) {
        return smartphoneService.getSmartphones(model, brand, minPrice, maxPrice);
    }

    @PostMapping("/smartphone")
    public SmartphoneDTO addSmartphone(@RequestBody SmartphoneDTO smartphoneDTO) {
        return smartphoneService.addSmartphone(smartphoneDTO);
    }

    @PutMapping("/smartphone/{id}")
    public SmartphoneDTO editSmartphone(@PathVariable(name = "id") Long id,
                                        @RequestBody SmartphoneDTO smartphoneDTO) {
        return smartphoneService.editSmartphone(id, smartphoneDTO);
    }

    @DeleteMapping("/smartphone/{id}")
    public ResponseEntity deleteSmartphone(@PathVariable(name = "id") Long id) {
        smartphoneService.deleteSmartphone(id);
        return ResponseEntity.ok().build();
    }

}
