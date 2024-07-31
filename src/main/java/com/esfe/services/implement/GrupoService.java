package com.esfe.services.implement;

import com.esfe.models.Grupo;
import com.esfe.repositories.IGrupoRepository;
import com.esfe.services.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService implements IGrupoService {
    @Autowired
    private IGrupoRepository grupoRepository;

    @Override
    public Page<Grupo> buscarTodosPaginados(Pageable pageable) {
        return grupoRepository.findAll(pageable);
    }

    @Override
    public List<Grupo> obtenerTodos() {
        return grupoRepository.findAll();
    }

    @Override
    public Optional<Grupo> obtenerPorId(Integer id) {
        return grupoRepository.findById(id);
    }

    @Override
    public Grupo crearOEditar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Override
    public void eliminar(Integer id) {
        grupoRepository.deleteById(id);
    }
}
