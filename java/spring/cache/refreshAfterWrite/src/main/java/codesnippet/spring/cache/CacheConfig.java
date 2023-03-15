package codesnippet.spring.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import codesnippet.spring.cache.model.StudentDto;

@Configuration
public class CacheConfig {

    private Logger logger = LogManager.getLogger();

    @Bean
    public CacheManager caffeineCacheManager() {

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        for (CacheBean cacheBean : getCacheBeanConfigs()) {

            Cache<Object, Object> caffeineCache =
                Caffeine.newBuilder()
                    .recordStats()
                    .refreshAfterWrite(cacheBean.getRefreshAfterWrite(), TimeUnit.SECONDS)
                    .maximumSize(cacheBean.getMaximumSize())
                    .build(key -> {

                        Thread.sleep(5000);

                        logger.info("data refreshed for id[{}]", key);

                        return new StudentDto((Long)key, "firstName" + key, "lastName" + key);
                    });

            cacheManager.registerCustomCache(cacheBean.getKey(), caffeineCache);
        }

        return cacheManager;
    }

    private List<CacheBean> getCacheBeanConfigs(){

        List<CacheBean> list = new ArrayList<>();

        CacheBean userCache = new CacheBean();
        userCache.setKey("userCache");
        userCache.setExpireAfterWrite(5);
        userCache.setRefreshAfterWrite(5);
        userCache.setMaximumSize(10000);

        list.add(userCache);

        return list;
    }

    class CacheBean {
        private String key;
        private long expireAfterWrite;
        private long refreshAfterWrite;
        private long maximumSize;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public long getExpireAfterWrite() {
            return expireAfterWrite;
        }

        public void setExpireAfterWrite(long expireAfterWrite) {
            this.expireAfterWrite = expireAfterWrite;
        }

        public long getRefreshAfterWrite() {
            return refreshAfterWrite;
        }

        public void setRefreshAfterWrite(long refreshAfterWrite) {
            this.refreshAfterWrite = refreshAfterWrite;
        }

        public long getMaximumSize() {
            return maximumSize;
        }

        public void setMaximumSize(long maximumSize) {
            this.maximumSize = maximumSize;
        }
    }
}
