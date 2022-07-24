package codesnippet.spring.fundamentals.region_service;

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
