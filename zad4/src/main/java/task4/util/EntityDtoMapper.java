package task4.util;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import task4.entities.AddressEntity;
import task4.entities.ClientEntity;
import task4.entities.SmartphoneEntity;
import task4.rest.dto.AddressDTO;
import task4.rest.dto.ClientDTO;
import task4.rest.dto.SmartphoneDTO;

public class EntityDtoMapper {

    public static ClientDTO mapToDto(ClientEntity clientEntity) {
        ClientDTO clientDTO = new ClientDTO();
//        ClientDTO clientDTO = ClientDTO.builder().build();
//        clientDTO.setId(clientDTO.getId());  //
//        clientDTO.setName(clientDTO.getName()); // zamiast tych 2 wierszy jest jedna poniższa metoda BeanUtils.copyProperties
        BeanUtils.copyProperties(clientEntity, clientDTO); // metoda kopiuje ze źródła (clientEntity) wartosci pól do drugiego argumentu metody, obiektów nie kopiue trzeba wywołać poniższe)
        if(clientEntity.getAddressEntity()!=null) {  // if dlatego że nie każdy klient ma adres i powstaje błąd
            clientDTO.setAddress(EntityDtoMapper.mapToDto(clientEntity.getAddressEntity()));
        }
        return clientDTO;
    }
    public static ClientEntity mapToEntity(ClientDTO clientDTO) {
        //ClientEntity clientEntity = new ClientEntity();
        ClientEntity clientEntity = ClientEntity.builder().build();
        BeanUtils.copyProperties(clientDTO, clientEntity);
        clientEntity.setAddressEntity(EntityDtoMapper.mapToEntity(clientDTO.getAddress()));
        return clientEntity;
    }

    public static AddressDTO mapToDto(AddressEntity addressEntity) {
        //AddressDTO addressDTO = new AddressDTO();
        AddressDTO addressDTO = AddressDTO.builder().build();
        BeanUtils.copyProperties(addressEntity, addressDTO);
        return addressDTO;
    }

    public static AddressEntity mapToEntity(AddressDTO addressDTO) {
       // AddressEntity addressEntity = new AddressEntity();
        AddressEntity addressEntity = AddressEntity.builder().build();
        BeanUtils.copyProperties(addressDTO, addressEntity);
        return addressEntity;
    }

    public static SmartphoneDTO mapToDto(SmartphoneEntity smartphoneEntity) {
        // SmartphoneDTO smartphoneDTO = new SmartphoneDTO();
        SmartphoneDTO smartphoneDTO = SmartphoneDTO.builder().build();
        BeanUtils.copyProperties(smartphoneEntity, smartphoneDTO);
        return smartphoneDTO;
    }

    public static SmartphoneEntity mapToEntity(SmartphoneDTO smartphoneDTO) {
        //SmartphoneEntity smartphoneEntity = new SmartphoneEntity();
        SmartphoneEntity smartphoneEntity = SmartphoneEntity.builder().build();
        BeanUtils.copyProperties(smartphoneDTO, smartphoneEntity);
        return smartphoneEntity;
    }

}
