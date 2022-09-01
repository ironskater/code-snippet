package codesnippet.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import codesnippet.spring.aop.service.TrafficService;

@SpringBootApplication
public class App implements CommandLineRunner
{
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private TrafficService trafficService;

    public static void main(String[] args) {
        LOGGER.info("LOGGER: ============= start spring application.........");
        System.out.println("SYSOUT: ============= start spring application.........");
        SpringApplication.run(App.class);
        LOGGER.info("============= end spring application.........");
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("============= Start call TrafficService");

        boolean tripWire = true;
        String result = this.trafficService.getTraffic(tripWire);

        LOGGER.info("the traffic result is: " + result);
    }
}
