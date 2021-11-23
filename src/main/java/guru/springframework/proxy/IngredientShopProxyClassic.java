package guru.springframework.proxy;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.contract.ItemDto;

@Component
public class IngredientShopProxyClassic {


	public List<ItemDto> getAllItems() {
		RestTemplate template = new RestTemplate();
		ResponseEntity<ItemDto[]> response = template.getForEntity("http://localhost:8081/items", ItemDto[].class);
		return Arrays.asList(response.getBody());
	}
	
	public Optional<ItemDto> getItemByDescription(String description) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<ItemDto> response = template.getForEntity(String.format("http://localhost:8081/items/item?description=%s", description), ItemDto.class);
		return Optional.ofNullable(response.getBody());
	}
	
}
