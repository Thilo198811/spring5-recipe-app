package guru.springframework.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;

class RecipeRestControllerTest {
	
	private RecipeRestController recipeRestController;
	@Mock
	private RecipeService recipeService;
	

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		recipeRestController = new RecipeRestController(recipeService);
	}
	
	@Test
	void testRecipeControllerReturns200() throws Exception {
		MockMvc mockmvc = MockMvcBuilders.standaloneSetup(recipeRestController).build();
		mockmvc.perform(get("/rest/recipes")).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	void testOneRecipeControllerReturns200() throws Exception {
		//Given
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		when(recipeService.getRecipeById(1L)).thenReturn(Optional.ofNullable(recipe));
		MockMvc mockmvc = MockMvcBuilders.standaloneSetup(recipeRestController).build();
		
		// When / Then
		mockmvc.perform(get("/rest/recipes/1")).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	void testOneRecipeControllerReturns2001() throws Exception {
		//Given
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		when(recipeService.getRecipeById(1L)).thenReturn(Optional.ofNullable(recipe));
		MockMvc mockmvc = MockMvcBuilders.standaloneSetup(recipeRestController).build();
		
		// When / Then
		mockmvc.perform(get("/rest/recipes/1")).andExpect(content().json("{\n"
				+ "  \"id\": 1}"));
	}

}

