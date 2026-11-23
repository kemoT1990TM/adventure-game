package com.tkjavadev.adventuregame.services;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.repositories.reactive.GateReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GateServiceImpl implements GateService{

    // == fields ==
    private final GateReactiveRepository gateReactiveRepository;

    // == constructors ==
    public GateServiceImpl(GateReactiveRepository gateReactiveRepository) {
        this.gateReactiveRepository = gateReactiveRepository;
    }

    @Override
    public Flux<Gate> getGatesByLocId(Long locId) {
        return gateReactiveRepository.findByLocId(locId);
    }
}
