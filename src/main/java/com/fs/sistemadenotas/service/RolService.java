package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Rol;
import com.fs.sistemadenotas.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService implements IRolService{

    private final RolRepository rolRepository;

    @Override
    public List<Rol> listarRol() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarRolPorId(Integer idRol) {
        return rolRepository.findByIdRol(idRol).orElse(null);
    }

    @Override
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void eliminarRol(Rol rol) {
        rolRepository.delete(rol);
    }
}
