package guru.springframework.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import guru.springframework.service.RecipeService;

public class RecipeControllerTest {
	
	private RecipeController recipeController;
	
	@Mock
	private RecipeService recipeService;
	
	@Mock
	private Model model;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testWhenGetRecipes_thenStringRecipeIsReturnedAndServiceWasCalledOneTime() {
		recipeController = new RecipeController(recipeService);
		
		assertEquals("recipe", recipeController.getRecipes(model));
		
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), anyList());
	}

}
