package guru.springframework.bootstrap;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;

@Component
public class StartUp implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(StartUp.class);
	
	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	public StartUp(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Start up");
		
		initRecipes();
	}
	
	private void initRecipes() {
		UnitOfMeasure kg = unitOfMeasureRepository.findByDescription("kg").get();
		UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
		Category fastfood = categoryRepository.findByDescription("Fast Food").get();
		Category belgium = categoryRepository.findByDescription("Belgium").get();
		Recipe r1 = new Recipe();
		r1.setDescription("Muscheln mit Pom Frites");
		r1.setDifficulty(Difficulty.MODERATE);
		r1.addCategory(fastfood);
		r1.addCategory(belgium);
		r1.addIngredient(BigDecimal.ONE, "Muscheln", kg);
		r1.addIngredient(BigDecimal.ONE, "Pommes", kg);
		r1.addIngredient(BigDecimal.ONE, "Mayo", teaspoon);
		r1.setNotes(new Notes("Lorem Ipsum"));
		recipeRepository.save(r1);
		
//		Recipe r2 = new Recipe();
//		r2.setDescripxtion("Currywurst");
//		recipeRepository.save(r1);
//		recipeRepository.save(r2);
	}

}
