package com.tkjavadev.adventuregame.service;

import com.tkjavadev.adventuregame.domain.Gate;
import com.tkjavadev.adventuregame.repository.ExitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GateServiceImpl implements GateService {

    // == fields ==
    private final ExitRepository exitRepository;

    // == constructors ==
    public GateServiceImpl(ExitRepository exitRepository) {
        this.exitRepository = exitRepository;
    }

    // == methods ==
    @Override
    public Gate getGate(Long id) {
        Optional<Gate> exitOptional = exitRepository.findById(id);
        if (!exitOptional.isPresent()) {
//            throw new NotFoundException("Location Not Found. For ID value: "+id.toString());
            System.out.println("Gate Not Found. For ID value: " + id.toString());
        }
        return exitOptional.get();
    }

    @Override
    public void saveGate(Gate gate) {
        exitRepository.save(gate);
    }

}
