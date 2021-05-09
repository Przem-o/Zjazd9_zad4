package task4.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import task4.entities.AddressEntity;
import task4.entities.ClientEntity;
import task4.repositories.ClientCache;
import task4.repositories.ClientRepository;
import task4.rest.dto.ClientDTO;
import task4.util.EntityDtoMapper;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientCache clientCache;

//    public ClientService(ClientRepository clientRepository) {
//        this.clientRepository = clientRepository;
//    }

    public Optional<ClientDTO> getClientById(Long id) {
        Optional<ClientDTO> client = clientCache.getClientResponse(id);
        if(client.isPresent()){
            return Optional.of(client.get());
        }
        try {
            Thread.sleep(5000);
        }catch (InterruptedException interruptedException){}
        ClientEntity clientEntity = clientRepository.findById(id).get();
        ClientDTO clientDTO = EntityDtoMapper.mapToDto(clientEntity);
        clientCache.saveResponseInCache(clientDTO);
        return Optional.of(clientDTO);
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
        ClientDTO clientDTO1 = EntityDtoMapper.mapToDto(save);
        clientCache.saveResponseInCache(clientDTO1);
        return EntityDtoMapper.mapToDto(save);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
