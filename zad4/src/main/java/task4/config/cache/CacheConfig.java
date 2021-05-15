package task4.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
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
    public static final String SMARTPHONE_RESPONSE_CACHE_MANAGER = "smartphoneResponseCacheManager";
    public static final String SMARTPHONE_RESPONSE_CACHE_NAME = "smartphoneResponseCache";

    @Value("${cache.client.ttl}")
    private int ttl;
    @Value("${cache.smartphone.ttl}")
    private int smartphoneTtl;

    @Primary
    @Bean(CLIENT_RESPONSE_CACHE_MANAGER)
    public CacheManager cacheManagerClient() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(ttl, TimeUnit.MINUTES);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(CLIENT_RESPONSE_CACHE_NAME));
        return cacheManager;
    }
    @Bean(SMARTPHONE_RESPONSE_CACHE_MANAGER)
    public CacheManager cacheManagerSmartphone() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(smartphoneTtl, TimeUnit.MINUTES);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(SMARTPHONE_RESPONSE_CACHE_NAME));
        return cacheManager;
    }
}

