package codesnippet.spring.aop.service;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TrafficService
{
    private static final Logger LOGGER = LogManager.getLogger();

    public String getTraffic() {
        // simulate delay
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException inEx) {
            LOGGER.warn(inEx);
        }

        return "Expect heavy traffic this morning";
    }

    public String getTraffic(boolean tripWire) {
        if(tripWire) {
            throw new RuntimeException("Major Accident! Highway is closed!!!!!!!!!!!");
        }
        return this.getTraffic();
    }
}
