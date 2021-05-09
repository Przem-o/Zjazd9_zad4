package task4.rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task4.rest.dto.ClientDTO;
import task4.services.ClientService;


import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(description = "Get all clients")
    @GetMapping("/clients")
    public List<ClientDTO> getClients(@RequestParam(name = "name", required = false) String name) {
        return clientService.findClients(name);
    }
    @Operation(description = "Add new client") // + adnotacja @Valid
    @PostMapping("/client")
    public ClientDTO addClient(@Valid @RequestBody ClientDTO clientDTO) {
        return clientService.addClient(clientDTO);
    }

    @Operation(description = "Delete client by id")
    @DeleteMapping("/client/{id}")
    public ResponseEntity deleteClient(@PathVariable(name = "id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
