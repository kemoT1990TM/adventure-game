package com.tkjavadev.adventuregame.service;

import com.tkjavadev.adventuregame.domain.Exit;
import com.tkjavadev.adventuregame.repository.ExitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExitServiceImpl implements ExitService {
    private final ExitRepository exitRepository;

    public ExitServiceImpl(ExitRepository exitRepository) {
        this.exitRepository = exitRepository;
    }

    @Override
    public Exit getExit(Long id) {
        Optional<Exit> exitOptional = exitRepository.findById(id);
        if (!exitOptional.isPresent()) {
//            throw new NotFoundException("Location Not Found. For ID value: "+id.toString());
            System.out.println("Exit Not Found. For ID value: "+id.toString());
        }
        return exitOptional.get();
    }

    @Override
    public void saveExit(Exit exit) {
        exitRepository.save(exit);
    }

}
