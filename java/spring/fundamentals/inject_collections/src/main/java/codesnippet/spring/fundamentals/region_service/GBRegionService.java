package codesnippet.spring.fundamentals.region_service;

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
