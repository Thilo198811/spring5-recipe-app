package guru.springframework.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.contract.IngredientDto;
import guru.springframework.contract.RecipeDto;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import io.micrometer.core.annotation.Counted;

@RestController
public class RecipeRestController {
	
	private final RecipeService recipeService;

	public RecipeRestController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@GetMapping("rest/recipes")
	@Counted("request.all-recipes")
	public List<RecipeDto> getRecipes() {
		return recipeService.getRecipes().stream()
				.map(r -> new RecipeDto(r.getId(), r.getDescription(), getIngredientDtos(r)))
				.collect(Collectors.toList());
	}
	
	private List<IngredientDto> getIngredientDtos(Recipe r) {
		return r.getIngredients().stream().map(i -> mapIngredientEntityToDto(i)).collect(Collectors.toList());
	}
	
	private IngredientDto mapIngredientEntityToDto(Ingredient ingredient) {
		return new IngredientDto(ingredient.getDescription(), ingredient.isAvailabilityInShop());
	}
	
	@GetMapping("rest/recipes/{id}")
	@Counted("request.get-recipe-by-id")
	public RecipeDto getRecipeById(@PathVariable Long id) {
		return recipeService.getRecipeById(id)
				.map(r -> new RecipeDto(r.getId(), r.getDescription(), getIngredientDtos(r)))
				.orElseThrow(IllegalArgumentException::new);
	}
	
	@DeleteMapping("rest/recipes/{id}")
	@Counted("request.delete-recipe-by-id")
	public void deleteRecipeById(@PathVariable Long id) {
		recipeService.deleteRecipeById(id);
	}
}
