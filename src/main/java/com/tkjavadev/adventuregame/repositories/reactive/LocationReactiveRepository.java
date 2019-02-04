package com.tkjavadev.adventuregame.repositories.reactive;

import com.tkjavadev.adventuregame.domain.Location;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LocationReactiveRepository extends ReactiveMongoRepository<Location, String> {
    Mono<Location> findByLocId(Long locId);
}
