package codesnippet.spring.fundamentals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// integrates Spring's TestContext framework with JUnit5
@ExtendWith(SpringExtension.class)
// enables support for @ConfigurationProperties beans (in this case, the ServerConfig bean)
@EnableConfigurationProperties(codesnippet.spring.fundamentals.ServerConfig.class)
// specifies a testing file that overrides the default application.properties file
@TestPropertySource("classpath:server-config-test.properties")
public class AppTest
{
	@Autowired
	private ServerConfig serverConfig;

	@Test
	public void configTest()
	{
		assertEquals("192.168.0.1", serverConfig.getAddress().getIp());

		Map<String, String> pathByResourceType = new HashMap<>();
		pathByResourceType.put("imgs", "/root/imgs");
		assertEquals(	pathByResourceType,
						serverConfig.getResourcesPath());
	}
}