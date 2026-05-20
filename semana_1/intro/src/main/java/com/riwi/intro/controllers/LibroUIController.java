package com.riwi.intro.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.riwi.intro.models.Libros;
import com.riwi.intro.service.LibroService;

@Controller
@RequestMapping("/admin/libros")
class LibroUIController {

    private final LibroService libroService;

    public LibroUIController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public String ListarlibrosUI(Model model) {
        List<Libros> libros = libroService.getAll();
        model.addAttribute("libros", libros);
        model.addAttribute("tituloPantalla", "Catalogo de libros - dashboard");

        return "index";

    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion( Model model){
        model.addAttribute("libro", new Libros());
        model.addAttribute("tituloPantalla", "registrar nuevo libro");
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute("libro") Libros libros, Model model){
        int anioActual = LocalDate.now().getYear();

        if (libros.getAnioPublicacion() > anioActual) {
            model.addAttribute("errorAnio", "El año de publicacion no puede ser mayor al año actual (" + anioActual + ").");
            model.addAttribute("tituloPantalla", "Registrar Nuevo Libro (Correccion)");
            return "formulario";
        }

        libroService.saveLibro(libros);
        return "redirect:/admin/libros";
    }


}
