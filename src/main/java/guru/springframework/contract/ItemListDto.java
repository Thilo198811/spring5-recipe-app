package guru.springframework.contract;

import java.util.ArrayList;
import java.util.List;

public class ItemListDto {
		private List<ItemDto> list;

		public ItemListDto() {
			super();
			new ArrayList<ItemDto>();
		}

		public List<ItemDto> getList() {
			return list;
		}

		public void setList(List<ItemDto> list) {
			this.list = list;
		}
		
		
		
		
}
