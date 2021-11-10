package guru.springframework.repository;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

}
