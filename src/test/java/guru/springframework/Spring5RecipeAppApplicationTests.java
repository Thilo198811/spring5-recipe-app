package guru.springframework;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Spring5RecipeAppApplicationTests {
	
	@Autowired
	private ApplicationContext ctx;

	@Test
	void contextLoads() {
		assertNotNull(ctx);
	}

}
