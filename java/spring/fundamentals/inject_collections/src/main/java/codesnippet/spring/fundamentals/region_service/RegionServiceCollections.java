package codesnippet.spring.fundamentals.region_service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import codesnippet.java_utility.Slf4jLogger;

public class RegionServiceCollections
{
    private final static Slf4jLogger LOGGER =
        new Slf4jLogger(MethodHandles.lookup().lookupClass());

    private List<IRegionService> regionServiceList;

    public RegionServiceCollections(List<IRegionService> regionServiceList) {
        this.regionServiceList = regionServiceList;
    }

    public void printRegionServiceList() {
        this.regionServiceList.forEach(
            e -> LOGGER.info("ISO country code: " + e.getISOCountryCode())
        );
    }
}
