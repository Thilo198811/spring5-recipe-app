package guru.springframework.contract;

public class IngredientDto {

	private String description;
	private boolean availableInShop;
	
	public IngredientDto(String description, boolean availableInShop) {
		super();
		this.description = description;
		this.availableInShop = availableInShop;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAvailableInShop() {
		return availableInShop;
	}
	public void setAvailableInShop(boolean availableInShop) {
		this.availableInShop = availableInShop;
	}
	
	
	
}
