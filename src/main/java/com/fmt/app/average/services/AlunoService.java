package com.fmt.app.average.services;

import com.fmt.app.average.repositories.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService{
    private final AlunoRepository repository;

}
