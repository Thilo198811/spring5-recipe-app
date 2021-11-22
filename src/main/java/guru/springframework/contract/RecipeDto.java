package guru.springframework.contract;

import java.util.List;

public class RecipeDto {
	private Long id;
	private String description;
	private List<IngredientDto> ingredients;
	
	public RecipeDto(Long id, String description,List<IngredientDto> ingredients) {
		super();
		this.id = id;
		this.description = description;
		this.ingredients = ingredients;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<IngredientDto> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDto> ingredients) {
		this.ingredients = ingredients;
	}
	
}
