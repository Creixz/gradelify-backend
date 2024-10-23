package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Level;

import java.util.List;

public interface ILevelService {

    public List<Level> listarLevel();

    public Level buscarLevelPorId(Integer idLevel);

    public Level guardarLevel(Level level);

    public void eliminarLevel(Level level);
}
