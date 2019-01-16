package com.tkjavadev.adventuregame.service;

import com.tkjavadev.adventuregame.domain.Gate;

public interface GateService {

    Gate getGate(Long id);

    void saveGate(Gate gate);
}
