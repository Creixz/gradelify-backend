package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Level;
import com.fs.sistemadenotas.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelService implements ILevelService {

    private final LevelRepository levelRepository;

    @Override
    public List<Level> listarLevel() {
        return levelRepository.findAll();
    }

    @Override
    public Level buscarLevelPorId(Integer idLevel) {
        return levelRepository.findById(idLevel).orElse(null);
    }

    @Override
    public Level guardarLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public void eliminarLevel(Level level) {
        levelRepository.delete(level);
    }
}
