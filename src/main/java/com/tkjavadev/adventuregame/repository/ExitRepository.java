package com.tkjavadev.adventuregame.repository;

import com.tkjavadev.adventuregame.domain.Gate;
import org.springframework.data.repository.CrudRepository;

public interface ExitRepository extends CrudRepository<Gate,Long> {
}
