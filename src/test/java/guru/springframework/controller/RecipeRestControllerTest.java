package guru.springframework.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.service.RecipeService;

public class RecipeRestControllerTest {
	
	private RecipeRestController recipeRestController;
	@Mock
	private RecipeService recipeService;
	

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		recipeRestController = new RecipeRestController(recipeService);
	}
	
	@Test
	public void testRecipeControllerReturns200() throws Exception {
		MockMvc mockmvc = MockMvcBuilders.standaloneSetup(recipeRestController).build();
		mockmvc.perform(get("/rest/recipes")).andExpect(status().is2xxSuccessful());
	}
	

}
