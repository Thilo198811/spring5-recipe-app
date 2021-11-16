package guru.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import guru.springframework.service.RecipeService;

@Controller
public class RecipeController {
	
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	

	@GetMapping({"/","/recipes"})
	public String getRecipes(Model model) {
		model.addAttribute("recipes",recipeService.getRecipes());
		return "recipe";
	}
	
	@GetMapping("/recipes/{id}")
	public String getRecipeById(@PathVariable Long id, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeById(id).orElseThrow(IllegalArgumentException::new));
		return "recipeview";
	}

}
