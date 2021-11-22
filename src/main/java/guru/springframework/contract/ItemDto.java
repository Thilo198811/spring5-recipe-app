package guru.springframework.contract;

public class ItemDto {
	private Long id;
	private String description;
	private double price;
	private long availability;
	
	public ItemDto() {
		
	}

	public ItemDto(Long id, String description, double price, long availability) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.availability = availability;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getAvailability() {
		return availability;
	}
	public void setAvailability(long availability) {
		this.availability = availability;
	}
	
	
}
