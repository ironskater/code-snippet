package codesnippet.spring.fundamentals.service;

import org.springframework.stereotype.Service;

@Service(value = "GBRegionService")
public class GBRegionService implements IRegionService
{
    @Override
    public boolean isServerActive(int serverId) {
        return false;
    }

    @Override
    public String getISOCountryCode() {
        return "GB";
    }
}
