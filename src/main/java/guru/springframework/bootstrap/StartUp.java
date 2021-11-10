package guru.springframework.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;

@Component
public class StartUp implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(StartUp.class);
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Start up");
		categoryRepository.findByDescription("American").ifPresent((x -> logger.info(x.getDescription()))); 
		
		initRecipes();
	}
	
	private void initRecipes() {
		Recipe r1 = new Recipe();
		r1.setDescription("Muscheln mit Pom Frites");
		
		Recipe r2 = new Recipe();
		r2.setDescription("Currywurst");
		recipeRepository.save(r1);
		recipeRepository.save(r2);
	}

}
