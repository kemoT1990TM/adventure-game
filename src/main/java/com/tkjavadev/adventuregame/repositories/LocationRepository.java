package com.tkjavadev.adventuregame.repositories;

import com.tkjavadev.adventuregame.domain.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location,String> {

    Optional<Location> findByLocId(Long locId);
}
