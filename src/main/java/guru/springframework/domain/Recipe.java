package guru.springframework.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String urls;
	private String directions;

	// mappedBy refers to the foreign key attribute of the ingredient entity 
	// in order to establish a bidirectional relation
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", fetch = FetchType.EAGER)
	private List<Ingredient> ingredients = new ArrayList<>();
	
	@Lob
	private byte[] image;
	
	// All operation on Recipe should be propagated to notes
	// E.g. Deleting recipe will cause that notes is deleted as well
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;
	
	@ManyToMany
	@JoinTable( name = "recipe_category",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories = new ArrayList<>();
	
	
	public Recipe() {
		super();
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

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		notes.setRecipe(this);
		this.notes = notes;
	}	
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addIngredient(BigDecimal amount, String description, UnitOfMeasure uom) {
		Ingredient ingredient = new Ingredient(amount, description, uom);
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
	}
	
	public void addCategory(Category category) {
		this.categories.add(category);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		return Objects.equals(id, other.id);
	}	
	
	

}
