package guru.springframework.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;
	
	public Category() {
		super();
	}
	
	public Category(String description) {
		super();
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Set<Recipe> getRecipes() {
		return recipes;
	}
	
	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	public void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
