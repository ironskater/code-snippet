package codesnippet.spring.fundamentals;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import codesnippet.spring.fundamentals.name_service.NameCollections;
import codesnippet.spring.fundamentals.region_service.RegionServiceCollections;

@ComponentScan
public class App
{
    public static void
        main( String[] args )
    {
        try(ConfigurableApplicationContext ctx =
                SpringApplication.run(	App.class,
                                        args))
        {
            RegionServiceCollections regionServiceCollections = ctx.getBean(RegionServiceCollections.class);
            regionServiceCollections.printRegionServiceList();

            NameCollections nameCollections = ctx.getBean(NameCollections.class);
            nameCollections.printNameList();
        }
    }
}
