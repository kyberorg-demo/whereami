package ee.yals.whoami;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath*:test-app.xml"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class WhoamiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
