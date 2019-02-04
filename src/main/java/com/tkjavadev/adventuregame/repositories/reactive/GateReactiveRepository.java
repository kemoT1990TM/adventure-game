package com.tkjavadev.adventuregame.repositories.reactive;

import com.tkjavadev.adventuregame.domain.Gate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GateReactiveRepository extends ReactiveMongoRepository<Gate,String> {
}
