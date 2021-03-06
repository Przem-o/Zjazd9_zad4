package task4.util;

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
        BeanUtils.copyProperties(clientEntity, clientDTO);
        clientDTO.setAddress(EntityDtoMapper.mapToDto(clientEntity.getAddressEntity()));
        return clientDTO;
    }

    public static ClientEntity mapToEntity(ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(clientDTO, clientEntity);
        clientEntity.setAddressEntity(EntityDtoMapper.mapToEntity(clientDTO.getAddress()));
        return clientEntity;
    }

    public static AddressDTO mapToDto(AddressEntity addressEntity) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(addressEntity, addressDTO);
        return addressDTO;
    }

    public static AddressEntity mapToEntity(AddressDTO addressDTO) {
        AddressEntity addressEntity = new AddressEntity();
        BeanUtils.copyProperties(addressDTO, addressEntity);
        return addressEntity;
    }

    public static SmartphoneDTO mapToDto(SmartphoneEntity smartphoneEntity) {
        SmartphoneDTO smartphoneDTO = new SmartphoneDTO();
        BeanUtils.copyProperties(smartphoneEntity, smartphoneDTO);
        return smartphoneDTO;
    }

    public static SmartphoneEntity mapToEntity(SmartphoneDTO smartphoneDTO) {
        SmartphoneEntity smartphoneEntity = new SmartphoneEntity();
        BeanUtils.copyProperties(smartphoneDTO, smartphoneEntity);
        return smartphoneEntity;
    }

}
