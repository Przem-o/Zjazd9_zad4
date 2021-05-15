package task4.repositories;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import task4.config.cache.CacheConfig;
import task4.rest.dto.ClientDTO;

import java.util.Optional;
@Repository
public class ClientCache {

    // służy do zapisywania danych do cache oraz  pod jaki kluczem ma to byc zapisane #clientDTO.id gdzie id to nazwa pola z obiektu

    @CachePut(key = "#clientDTO.id", cacheManager = CacheConfig.CLIENT_RESPONSE_CACHE_MANAGER, cacheNames = CacheConfig.CLIENT_RESPONSE_CACHE_NAME) // manager i name z pola klasy configCache
    public ClientDTO saveResponseInCache(ClientDTO clientDTO) {
        return clientDTO;
    }
    // służy do wyciągania z danych z cache po id wczesniej ustalonym wyzej przy dodawaniu

    @Cacheable(key = "#id", cacheManager = CacheConfig.CLIENT_RESPONSE_CACHE_MANAGER, cacheNames = CacheConfig.CLIENT_RESPONSE_CACHE_NAME)
    public Optional<ClientDTO> getClientResponse(Long id) {
        return Optional.empty();
    }

    //usuwanie danych z cache z podanego klucza

    @CacheEvict(key = "#id", cacheManager = CacheConfig.CLIENT_RESPONSE_CACHE_MANAGER, cacheNames = CacheConfig.CLIENT_RESPONSE_CACHE_NAME)
    public void deleteClientResponseFromCache(Long id) {
    }
}
