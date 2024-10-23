package com.fs.sistemadenotas.service;

import com.fs.sistemadenotas.model.Rol;

import java.util.List;

public interface IRolService {

    public List<Rol> listarRol();

    public Rol buscarRolPorId(Integer idRol);

    public Rol guardarRol(Rol rol);

    public void eliminarRol(Rol rol);
}
