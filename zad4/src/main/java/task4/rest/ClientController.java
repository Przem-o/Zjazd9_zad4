package task4.rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task4.config.cache.SecurityConfig;
import task4.rest.dto.ClientDTO;
import task4.services.ClientService;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static task4.config.cache.SecurityConfig.ADMIN_ROLE;
import static task4.config.cache.SecurityConfig.SALES_ROLE;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PreAuthorize("hasRole('" + ADMIN_ROLE +"')")
    @Operation(description = "Get all clients")
    @GetMapping("/clients")
    public List<ClientDTO> getClients(@RequestParam(name = "name", required = false) String name) {
        return clientService.findClients(name);
    }
    @PreAuthorize("hasRole('" + ADMIN_ROLE +"') || hasRole('" + SALES_ROLE +"')")
    @Operation(description = "Add new client") // + adnotacja @Operation i @Valid ze swaggera
    @PostMapping("/client")
    public ClientDTO addClient(@Valid @RequestBody ClientDTO clientDTO) {
        return clientService.addClient(clientDTO);
    }

    @PreAuthorize("hasRole('" + ADMIN_ROLE +"') || hasRole('" + SALES_ROLE +"')")
    @Operation(description = "Delete client by id")
    @DeleteMapping("/client/{id}")
    public ResponseEntity deleteClient(@PathVariable(name = "id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasRole('" + ADMIN_ROLE +"')")
    @Operation(description = "find client by id")
    @GetMapping("/client/{id}")
    public ResponseEntity getClientById(@PathVariable(name = "id") Long id) {
        Optional<ClientDTO> getClientById = clientService.getClientById(id);
        if (getClientById.isPresent()) {
            return ResponseEntity.ok(getClientById.get());

        }
        return ResponseEntity.noContent().build();// http no content 204

    }// dorobic edytowanie klientow
}
