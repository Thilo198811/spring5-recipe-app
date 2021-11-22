package guru.springframework.proxy;

import java.util.List;

//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import guru.springframework.contract.ItemDto;


//@FeignClient(name="ingredient-shop", url="localhost:8081")
public interface IngredientShopProxyFeign {
	@GetMapping("/items")
	
	public List<ItemDto> getAllItems();
	
	@GetMapping("/items/{id}")
	public ItemDto getItemById(@PathVariable Long id);
}
