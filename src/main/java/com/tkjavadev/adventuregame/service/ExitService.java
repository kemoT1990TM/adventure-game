package com.tkjavadev.adventuregame.service;

import com.tkjavadev.adventuregame.domain.Exit;

public interface ExitService {

    Exit getExit(Long id);

    void saveExit(Exit exit);
}
