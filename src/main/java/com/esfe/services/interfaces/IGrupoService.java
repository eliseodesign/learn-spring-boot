package com.esfe.services.interfaces;

import com.esfe.models.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface IGrupoService {
    Page<Grupo> buscarTodosPaginados(Pageable pageable);

    List<Grupo> obtenerTodos();

    Optional<Grupo> obtenerPorId(Integer id);

    Grupo crearOEditar(Grupo grupo);

    void eliminar(Integer id);
}
