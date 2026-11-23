package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.domain.Location;
import reactor.core.publisher.Flux;

public interface GateService {

    Flux<Gate> getGatesByLocId(Long locId);
}
