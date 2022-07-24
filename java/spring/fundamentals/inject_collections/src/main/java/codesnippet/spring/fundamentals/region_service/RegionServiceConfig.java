package codesnippet.spring.fundamentals.region_service;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegionServiceConfig
{
    @Bean
    public List<IRegionService> regionServiceList() {
        return Arrays.asList(new GBRegionService(), new USRegionService());
    }

    @Bean
    public RegionServiceCollections regionServiceCollections(List<IRegionService> regionServiceList) {
        return new RegionServiceCollections(regionServiceList);
    }
}
