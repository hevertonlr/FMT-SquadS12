package com.fmt.app.average.services;

import com.fmt.app.average.repositories.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotaService {
    private final NotaRepository repository;
}
