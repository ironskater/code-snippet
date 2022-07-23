package codesnippet.spring.fundamentals.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class BeanFactoryDynamicAutowireService
{
    private static final String SERVICE_NAME_SUFFIX = "regionService";
    private final BeanFactory beanFactory;

    public BeanFactoryDynamicAutowireService(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public boolean isServerActive(String isoCountryCode, int serverId) {
        IRegionService regionService = this.beanFactory.getBean(this.getRegionServiceBeanName(isoCountryCode),
                                                                IRegionService.class);

        return regionService.isServerActive(serverId);
    }

    private String getRegionServiceBeanName(String isoCountryCode) {
        return isoCountryCode + SERVICE_NAME_SUFFIX;
    }
}
