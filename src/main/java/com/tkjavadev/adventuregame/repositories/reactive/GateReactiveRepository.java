package com.tkjavadev.adventuregame.repositories.reactive;

import com.tkjavadev.adventuregame.domain.Gate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GateReactiveRepository extends ReactiveMongoRepository<Gate,String> {

    Flux<Gate> findByLocId(Long locId);
}
