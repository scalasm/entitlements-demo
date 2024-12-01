package me.marioscalas.edemo.product.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.marioscalas.edemo.product.Program;
import me.marioscalas.edemo.product.ProgramRepository;
import me.marioscalas.edemo.product.ProgramService;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    @Override
    public Program createProgram(String programCode, String programName) {
        return programRepository.save(
            Program.builder().code(programCode).name(programName).build()
        );
    }
}
