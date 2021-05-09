package task4.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task4.rest.dto.SmartphoneDTO;
import task4.services.OrderService;


import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{clientId}")
    public List<SmartphoneDTO> getOrderedSmartphones(@PathVariable(name = "clientId") Long clientId) {
        return orderService.findOrderedSmartphones(clientId);
    }

    @PostMapping("/orders/{clientId}/{smartphoneId}")
    public List<SmartphoneDTO> addSmartphoneToOrderList(@PathVariable(name = "clientId") Long clientId,
                                                   @PathVariable(name = "smartphoneId") Long smartphoneId){
        return orderService.addSmartphoneToOrderList(clientId, smartphoneId);
    }

    @DeleteMapping("/orders/{clientId}/{smartphoneId}")
    public ResponseEntity deleteSmartphoneFromOrderList(@PathVariable(name = "clientId") Long clientId,
                                                        @PathVariable(name = "smartphoneId") Long smartphoneId){
        orderService.deleteSmartphoneFromOrderList(clientId, smartphoneId);
        return ResponseEntity.ok().build();
    }
}
