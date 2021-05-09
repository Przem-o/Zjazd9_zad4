package task4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task4.entities.SmartphoneEntity;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartphoneEntity, Long> {
}
