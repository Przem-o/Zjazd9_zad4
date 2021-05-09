package task4.services;

import org.springframework.stereotype.Service;
import task4.entities.SmartphoneEntity;
import task4.repositories.SmartphoneRepository;
import task4.rest.dto.SmartphoneDTO;
import task4.util.EntityDtoMapper;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;

    public SmartphoneService(SmartphoneRepository smartphoneRepository) {
        this.smartphoneRepository = smartphoneRepository;
    }

    public List<SmartphoneDTO> getSmartphones(String model, String brand, Integer minPrice, Integer maxPrice) {
        return smartphoneRepository.findAll().stream()
                .filter(smartphoneEntity -> model == null || smartphoneEntity.getModel().equals(model))
                .filter(smartphoneEntity -> brand == null || smartphoneEntity.getName().equals(brand))
                .filter(smartphoneEntity -> minPrice == null || smartphoneEntity.getPrice() >= minPrice)
                .filter(smartphoneEntity -> maxPrice == null || smartphoneEntity.getPrice() <= maxPrice)
                .map(EntityDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public SmartphoneDTO addSmartphone(SmartphoneDTO smartphoneDTO) {
        SmartphoneEntity smartphoneEntity = EntityDtoMapper.mapToEntity(smartphoneDTO);
        SmartphoneEntity save = smartphoneRepository.save(smartphoneEntity);
        return EntityDtoMapper.mapToDto(save);
    }

    public SmartphoneDTO editSmartphone(Long id, SmartphoneDTO smartphoneDTO) {
        Optional<SmartphoneEntity> smartphoneEntity = smartphoneRepository.findById(id);
        if(smartphoneEntity.isPresent()) {
            SmartphoneEntity smartphone = smartphoneEntity.get();
            smartphone.setName(smartphoneDTO.getName());
            smartphone.setModel(smartphoneDTO.getModel());
            smartphone.setPrice(smartphoneDTO.getPrice());
            SmartphoneEntity save = smartphoneRepository.save(smartphone);
            return EntityDtoMapper.mapToDto(save);
        } else {
            SmartphoneEntity smartphoneEntity1 = EntityDtoMapper.mapToEntity(smartphoneDTO);
            SmartphoneEntity save = smartphoneRepository.save(smartphoneEntity1);
            return EntityDtoMapper.mapToDto(save);
        }
    }

    public boolean deleteSmartphone(Long id) {
        smartphoneRepository.deleteById(id);
        return true;
    }

}
