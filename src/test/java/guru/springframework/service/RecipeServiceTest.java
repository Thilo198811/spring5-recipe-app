package guru.springframework.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.contract.ItemDto;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.proxy.IngredientShopProxyClassic;
import guru.springframework.repository.RecipeRepository;

class RecipeServiceTest {
	
	private RecipeService recipeService;

	@Mock
	private RecipeRepository recipeRepository;
	
	@Mock
	private IngredientShopProxyClassic proxy;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeService(recipeRepository, proxy);
	}
		

	@Test
	void testGetRecipes() {
		List<Recipe> recipeDummyData = new ArrayList<>();
		recipeDummyData.add(new Recipe());
		when(recipeRepository.findAll()).thenReturn(recipeDummyData);
		
		List<Recipe> recipes = recipeService.getRecipes();
		
		assertEquals(1, recipes.size());
		
		// Verify that findAll of repository was called exactly one time
		verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	void testWhenShopReturnsIngredientHigher0_thenServiceReturnAvailabilityTrueOtherwiseFalse() {
		// Given
		List<Recipe> recipeRepoDummyData = new ArrayList<Recipe>();
		Recipe recipe = new Recipe();
		recipe.setDescription("Currywurst");
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient(BigDecimal.ONE, "Wurst" , new UnitOfMeasure()));
		ingredients.add(new Ingredient(BigDecimal.ONE, "Curry" , new UnitOfMeasure()));
		
		recipe.setIngredients(ingredients);
		recipeRepoDummyData.add(recipe);
		
		List<ItemDto> itemDummyData = new ArrayList<>();
		itemDummyData.add(new ItemDto(0L, "Wurst", 3.0, 1));
		itemDummyData.add(new ItemDto(1L, "Curry", 3.0, 0));
		
		when(recipeRepository.findAll()).thenReturn(recipeRepoDummyData);
		when(proxy.getAllItems()).thenReturn(itemDummyData);
		
		// When
		List<Recipe> recipes = recipeService.getRecipes();

		// Then
		assertTrue(recipes.get(0).getIngredients().get(0).isAvailabilityInShop()); // Wurst
		assertFalse(recipes.get(0).getIngredients().get(1).isAvailabilityInShop()); // Curry
	}
	
	@Test
	void testWhenShopIsUnavailable_thenReturnFalseAsDefault() {
		// Given
		List<Recipe> recipeRepoDummyData = new ArrayList<Recipe>();
		Recipe recipe = new Recipe();
		recipe.setDescription("Currywurst");
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient(BigDecimal.ONE, "Wurst" , new UnitOfMeasure()));
		ingredients.add(new Ingredient(BigDecimal.ONE, "Curry" , new UnitOfMeasure()));
		
		recipe.setIngredients(ingredients);
		recipeRepoDummyData.add(recipe);
		
		when(recipeRepository.findAll()).thenReturn(recipeRepoDummyData);
		when(proxy.getAllItems()).thenThrow(RuntimeException.class);
		
		// When
		List<Recipe> recipes = recipeService.getRecipes();

		
		// Then
		assertFalse(recipes.get(0).getIngredients().get(0).isAvailabilityInShop()); // Wurst
		assertFalse(recipes.get(0).getIngredients().get(1).isAvailabilityInShop()); // Curry

	}
	

}
