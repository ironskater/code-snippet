package codesnippet.spring.fundamentals.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service(value = "customMapFromListDynamicAutowireService")
public class CustomMapFromListDynamicAutowireService
{
    private final Map<String, IRegionService> serviceByCountryCode;

    /**
     * Besides standard single-field autowiring, Spring gives us an ability to collect all beans
     */
    public CustomMapFromListDynamicAutowireService(List<IRegionService> regionService) {
        this.serviceByCountryCode =
            regionService.stream().collect(Collectors.toMap(IRegionService::getISOCountryCode,
                                                            Function.identity()));
    }

    public boolean isServerActive(String isoCountryCode, int serverId) {
        IRegionService regionService = serviceByCountryCode.get(isoCountryCode);
        return regionService.isServerActive(serverId);
    }
}
