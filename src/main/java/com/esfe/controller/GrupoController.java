package com.esfe.controller;

import com.esfe.models.Grupo;
import com.esfe.services.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private IGrupoService grupoService;

    @GetMapping
    public String index(Model model, @RequestParam("page")Optional<Integer> page, @RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);

        Page<Grupo> grupos = grupoService.buscarTodosPaginados(pageable);
        model.addAttribute("grupos", grupos);

        int totalPages = grupos.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "grupos/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("grupo", new Grupo());
        return "grupos/create";
    }

    @PostMapping("/save")
    public String save(Grupo grupo, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(grupo);
            attributes.addFlashAttribute("error", "Error al guardar el grupo");
            return "grupos/create";
        }

        grupoService.crearOEditar(grupo);
        attributes.addFlashAttribute("msg", "Grupo guardado con éxito");
        return "redirect:/grupos";
    }

    @GetMapping("/details/{id}")
    public String detail(@PathVariable("id") Integer id, Model model){
        Grupo grupo = grupoService.obtenerPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupos/details";
    }
}
