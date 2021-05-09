package task4.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task4.rest.dto.ClientDTO;
import task4.services.ClientService;


import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<ClientDTO> getClients(@RequestParam(name = "name", required = false) String name) {
        return clientService.findClients(name);
    }

    @PostMapping("/client")
    public ClientDTO addClient(@RequestBody ClientDTO clientDTO) {
        return clientService.addClient(clientDTO);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity deleteClient(@PathVariable(name = "id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
