package task4.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import task4.entities.AddressEntity;
import task4.entities.ClientEntity;
import task4.repositories.ClientRepository;
import task4.rest.dto.ClientDTO;
import task4.util.EntityDtoMapper;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> findClients(String name) {
        return findClientsByName(name).stream()
                .map(EntityDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private List<ClientEntity> findClientsByName(String name) {
        if (StringUtils.isBlank(name)) {
            return clientRepository.findAll();
        } else {
            return clientRepository.findByName(name);
        }
    }

    public ClientDTO addClient(ClientDTO clientDTO) {
        ClientEntity clientEntity = EntityDtoMapper.mapToEntity(clientDTO);
        AddressEntity addressEntity = EntityDtoMapper.mapToEntity(clientDTO.getAddress());
        clientEntity.setAddressEntity(addressEntity);
        addressEntity.setClientEntity(clientEntity);
        ClientEntity save = clientRepository.save(clientEntity);
        return EntityDtoMapper.mapToDto(save);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
