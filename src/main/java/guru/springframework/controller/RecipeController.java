package guru.springframework.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.contract.RecipeDto;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;

@RestController
public class RecipeController {
	
	private final RecipeRepository recipeRepository;

	public RecipeController(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@RequestMapping("/recipes")
	public List<RecipeDto> getRecipes() {
		final List<RecipeDto> list = new ArrayList<>();
		recipeRepository.findAll().forEach(x -> list.add(new RecipeDto(x.getId(), x.getDescription())));
		return list;
		
	}
	
}
