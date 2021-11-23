package guru.springframework.proxy;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.contract.ItemDto;

@Component
public class IngredientShopProxyClassic {

	private IngredientProxyConfiguration configuration;
	private Logger logger = LoggerFactory.getLogger(IngredientProxyConfiguration.class);
	
	public IngredientShopProxyClassic(IngredientProxyConfiguration configuration) {
		this.configuration = configuration;
		logger.info("Proxy loaded with endpoint: {}", configuration.getEndpoint());
	}
	
	public List<ItemDto> getAllItems() {
		RestTemplate template = new RestTemplate();
		ResponseEntity<ItemDto[]> response = template.getForEntity(String.format("%s/items", configuration.getEndpoint()), ItemDto[].class);
		return Arrays.asList(response.getBody());
	}
	
	public Optional<ItemDto> getItemByDescription(String description) {
		RestTemplate template = new RestTemplate();
		final String endpoint = String.format("%s/items/item?description=%s",configuration.getEndpoint(),description);
		logger.info("Request to {}", endpoint);
		ResponseEntity<ItemDto> response = template.getForEntity(endpoint, ItemDto.class);
		return Optional.ofNullable(response.getBody());
	}
	
}
