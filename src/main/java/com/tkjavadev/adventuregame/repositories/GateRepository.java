package com.tkjavadev.adventuregame.repositories;

import com.tkjavadev.adventuregame.domain.Gate;
import org.springframework.data.repository.CrudRepository;

public interface GateRepository extends CrudRepository<Gate,String> {
}
