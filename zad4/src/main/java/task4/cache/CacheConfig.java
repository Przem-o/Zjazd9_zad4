package task4.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String CLIENT_RESPONSE_CACHE_MANAGER = "clientResponseCacheManager";//client + Respons...
    public static final String CLIENT_RESPONSE_CACHE_NAME = "clientResponseCache"; //client + Respons...

    @Primary
    @Bean(CLIENT_RESPONSE_CACHE_MANAGER)
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(CLIENT_RESPONSE_CACHE_NAME));
        return cacheManager;
    }
}

