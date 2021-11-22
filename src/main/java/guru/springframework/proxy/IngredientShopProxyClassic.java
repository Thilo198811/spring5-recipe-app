package guru.springframework.proxy;


import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.contract.ItemDto;
import guru.springframework.contract.ItemListDto;

@Component
public class IngredientShopProxyClassic {

	public List<ItemDto> getAllItems() {
		RestTemplate template = new RestTemplate();
		return template.getForEntity("http://localhost:8081/items", ItemListDto.class).getBody().getList();
	}
	
}
