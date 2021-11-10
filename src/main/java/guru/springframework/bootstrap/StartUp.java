package guru.springframework.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.repository.CategoryRepository;

@Component
public class StartUp implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(StartUp.class);
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Start up");
		categoryRepository.findByDescription("American").ifPresent((x -> logger.info(x.getDescription()))); 
	}

}
