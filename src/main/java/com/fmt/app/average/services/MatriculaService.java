package com.fmt.app.average.services;

import com.fmt.app.average.repositories.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatriculaService {
    private final MatriculaRepository repository;
}
