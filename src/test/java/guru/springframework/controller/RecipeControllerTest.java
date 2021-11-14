package guru.springframework.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.domain.Recipe;
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
		recipeController = new RecipeController(recipeService);
	}
	
	@Test
	public void testWhenGetRecipes_thenStringRecipeIsReturnedAndServiceWasCalledOneTime() {
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(new Recipe());
		recipes.add(new Recipe());
		recipes.add(new Recipe());
						
		assertEquals("recipe", recipeController.getRecipes(model));
		
		
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), anyList());
	}
	
	// With argument catcher
	@Test
	public void testWhenGetRecipes_thenTheRecipesReturnedByServiceAreAddedToModel() {
		// Given , configure mocks
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(new Recipe());
		recipes.add(new Recipe());
		recipes.add(new Recipe());
		
		when(recipeService.getRecipes()).thenReturn(recipes);
		
		// Create captor of type list
		// purpose of a captor is to capture the passed argument to a mock 
		ArgumentCaptor<List<Recipe>> captor = ArgumentCaptor.forClass(List.class);
		
		
		// When, Then
		assertEquals("recipe", recipeController.getRecipes(model));
		
		
		verify(recipeService, times(1)).getRecipes();
		// Captor will hold the argument, addAttribute was called with
		verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
		
		// Compare if the recipes returned by the service ( mock) are added to the model
		assertEquals(recipes, captor.getValue());
		assertEquals(3, captor.getValue().size());
		
	}
	
	@Test
	public void testMockMvc() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		mockMvc.perform(get("/"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("recipe"));
	}
	

}
