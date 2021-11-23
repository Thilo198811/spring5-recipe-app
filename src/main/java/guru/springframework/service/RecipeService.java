package guru.springframework.service;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import guru.springframework.contract.ItemDto;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.proxy.IngredientShopProxyClassic;
import guru.springframework.repository.RecipeRepository;

@Service
public class RecipeService {

	private final RecipeRepository recipeRepository;
	private final IngredientShopProxyClassic proxy;
	private final Logger logger = LoggerFactory.getLogger(RecipeService.class);

	public RecipeService(RecipeRepository recipeRepository, IngredientShopProxyClassic proxy) {
		super();
		this.recipeRepository = recipeRepository;
		this.proxy = proxy;
	}
	
	public List<Recipe> getRecipes() {
				
		final List<Recipe> list = new ArrayList<>();
		recipeRepository.findAll().forEach(recipe -> list.add(attachAvailabilityToIngredients(recipe)));
		return list;
	}
	
	public Optional<Recipe> getRecipeById(Long id) {
		return recipeRepository.findById(id);
	}
	
	private Recipe attachAvailabilityToIngredients(Recipe recipe) {
		final Recipe recipeWithAvailability = recipe;
		recipeWithAvailability.getIngredients().forEach(ingredient -> attachIngredientAvailability(ingredient));
		return recipeWithAvailability;
	}
	
	private Ingredient attachIngredientAvailability(Ingredient ingredient) {
		final Ingredient ingredientWithAvailability = ingredient;
		try {
			proxy.getItemByDescription(ingredient.getDescription()).ifPresent(item -> ingredient.setAvailabilityInShop(isAvailable(item)));
		} catch (Exception e) {
			logger.warn("Shop not available - set default value");
			ingredientWithAvailability.setAvailabilityInShop(false);
		}
		return ingredientWithAvailability;
	}
	
	private boolean isAvailable(ItemDto item) {
		return item.getAvailability() > 0;
	}
	
}
