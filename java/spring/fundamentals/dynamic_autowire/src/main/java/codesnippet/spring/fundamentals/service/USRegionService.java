package codesnippet.spring.fundamentals.service;

import org.springframework.stereotype.Service;

@Service(value = "USRegionService")
public class USRegionService implements IRegionService
{
    @Override
    public boolean isServerActive(int serverId) {
        return true;
    }

    @Override
    public String getISOCountryCode() {
        return "US";
    }
}
