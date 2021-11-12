package guru.springframework.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;

public class RecipeServiceTest {
	
	private RecipeService recipeService;

	@Mock
	private RecipeRepository recipeRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeService(recipeRepository);
	}
		

	@Test
	public void testGetRecipes() {
		List<Recipe> recipeDummyData = new ArrayList<>();
		recipeDummyData.add(new Recipe());
		when(recipeRepository.findAll()).thenReturn(recipeDummyData);
		
		List<Recipe> recipes = recipeService.getRecipes();
		
		assertEquals(1, recipes.size());
		
		// Verify that findAll of repository was called exactly one time
		verify(recipeRepository, times(1)).findAll();
	}

}
