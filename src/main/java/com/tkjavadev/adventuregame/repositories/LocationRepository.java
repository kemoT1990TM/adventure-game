package com.tkjavadev.adventuregame.repositories;

import com.tkjavadev.adventuregame.domain.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location,String> {
}
