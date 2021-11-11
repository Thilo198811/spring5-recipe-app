package guru.springframework.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;

@Service
public class RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeService(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}
	
	public List<Recipe> getRecipes() {
		final List<Recipe> list = new ArrayList<>();
		recipeRepository.findAll().forEach(r -> list.add(r));
		return list;
	}
	
}
