package guru.springframework.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.contract.RecipeDto;
import guru.springframework.service.RecipeService;

@RestController
public class RecipeRestController {
	
	private final RecipeService recipeService;

	public RecipeRestController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@GetMapping("rest/recipes")
	public List<RecipeDto> getRecipes() {
		List<RecipeDto> list = recipeService.getRecipes().stream()
				.map(r -> new RecipeDto(r.getId(), r.getDescription()))
				.collect(Collectors.toList());
		return list;		
	}
}
