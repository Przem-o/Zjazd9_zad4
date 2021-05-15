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
        Optional<ClientDTO> client = clientCache.getClientResponse(id);// sprawdzanie czy jest w cache
        if(client.isPresent()){
            return Optional.of(client.get());//jesli jest to uzywam tych danych z cacha
        }
        try {
            Thread.sleep(5000);
        }catch (InterruptedException interruptedException){}
        ClientEntity clientEntity = clientRepository.findById(id).get();// jesli jest w cache to wyciagam go z bazy danych
        ClientDTO clientDTO = EntityDtoMapper.mapToDto(clientEntity);
        clientCache.saveResponseInCache(clientDTO);//  i zapisuje zeby przy nastepnym zapytaniu o te dane, mozna juz było pracowac z danymi z kesza
        return Optional.of(clientDTO);
    }
//    public Optional<ClientDTO> getClientById(Long id) {
//        Optional<ClientEntity> byId = clientRepository.findById(id);
//        ClientEntity clientEntity = byId.get();
//        ClientDTO clientDTO = EntityDtoMapper.mapToDto(clientEntity);
//        return Optional.ofNullable(clientDTO);
//
//    }

    public List<ClientDTO> findClients(String name) {
        return findClientsByName(name).stream() // mapowanie zamiana clientEntity z metody findClientsByName na DTO żeby użyć w controlerze
                .map(EntityDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private List<ClientEntity> findClientsByName(String name) { // metoda pomocnicza do w/w
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
        ClientEntity saveClient = clientRepository.save(clientEntity); // zapisanie w/w clienta z adresem
        ClientDTO clientDTO1 = EntityDtoMapper.mapToDto(saveClient);// zamiana mapowanie klienta (sava) na DTO
        clientCache.saveResponseInCache(clientDTO1); // zapisanie clienta do cacha, w cache zapisujemy to co trafiło do bazy danych
        return EntityDtoMapper.mapToDto(saveClient);
    }

    public void deleteClient(Long id) { // usuwamy klienta z encji tutaj jest domyślnie clientEntity
        clientRepository.deleteById(id);
        clientCache.deleteClientResponseFromCache(id);// usuwanie z cache
    }

}
