package codesnippet.spring.fundamentals.region_service;

public interface IRegionService
{
    public boolean isServerActive(int serverId);
    public String getISOCountryCode();
}
