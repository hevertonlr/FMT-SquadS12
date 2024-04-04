package com.fmt.app.average.services;

import com.fmt.app.average.repositories.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisciplinaService {
    private final DisciplinaRepository repository;
}
