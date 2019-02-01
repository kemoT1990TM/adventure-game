package com.tkjavadev.adventuregame.repositories;

import com.tkjavadev.adventuregame.domain.Gate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GateRepository extends MongoRepository<Gate,String> {
}
