package guru.springframework.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {
	
	@Autowired
	private UnitOfMeasureRepository unitOfMeasureRepository;

	@BeforeEach
	public void setup() {
		
	}
	
	@Test
	void testWhenFindByValidDescription_ThenReturnDescription() {
		assertEquals("Teaspoon", unitOfMeasureRepository.findByDescription("Teaspoon").get().getDescription());
	}
	
	@Test
	void testWhenFindByInvalidDescription_ThenOptionalHoldsNoElement() {
		assertFalse(unitOfMeasureRepository.findByDescription("Teaspoonss").isPresent());
	}
}
