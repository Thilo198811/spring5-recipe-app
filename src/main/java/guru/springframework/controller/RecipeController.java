package guru.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.service.RecipeService;

@Controller
public class RecipeController {
	
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	

	@RequestMapping({"/","/recipes"})
	public String getRecipes(Model model) {
		model.addAttribute("recipes",recipeService.getRecipes());
		return "recipe";
	}
	

}
