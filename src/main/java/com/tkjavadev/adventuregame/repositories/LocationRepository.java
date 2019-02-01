package com.tkjavadev.adventuregame.repositories;

import com.tkjavadev.adventuregame.domain.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location,String> {

    Optional<Location> findByLocId(Long locId);
}
