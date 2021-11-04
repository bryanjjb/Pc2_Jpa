
package com.example.Pc2.controller;

import com.example.Pc2.entity.Autor;
import com.example.Pc2.entity.Libro;
import com.example.Pc2.entity.Editorial;
import com.example.Pc2.repository.AutorRepository;
import com.example.Pc2.repository.EditorialRepository;
import com.example.Pc2.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LibroController {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private EditorialRepository editorialRepository;
    @Autowired
    private AutorRepository autorRepository;
    @RequestMapping("/")
    public String mensaje(Model model){
        model.addAttribute("mensaje", "Bienvenidos");
        return "index";
    }
    @RequestMapping("/librs")
    public String libs(Model model) {
        model.addAttribute("librs", libroRepository.findAll());
        return "librs";
    }
    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("eds", editorialRepository.findAll());
        model.addAttribute("auts",autorRepository.findAll());
        return "add";
    }
    @RequestMapping("/add")
    public String Guardar(@RequestParam String nombre,@RequestParam int idaut,@RequestParam int ided,Model model) {
        Libro lib = new Libro();
        Autor aut = autorRepository.findById(idaut).get();
        Editorial ed = editorialRepository.findById(ided).get();
        lib.setNombre(nombre);
        lib.setAutor(aut);
        lib.setEditorial(ed);
        libroRepository.save(lib);
        return "redirect:/librs";
    }
    @RequestMapping("/del/{id}")
    public String delete(@PathVariable(value="id") int id) {
        Libro lib = libroRepository.findById(id).orElse(null);
        libroRepository.delete(lib);
        return "redirect:/librs";
    }
     @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(value="id") int id, Model model) {
        model.addAttribute("lib", libroRepository.findById(id).orElse(null));
        model.addAttribute("eds", editorialRepository.findAll());
        model.addAttribute("auts",autorRepository.findAll());
        return "edit";
    }
    @RequestMapping("/update")
    public String update(@RequestParam int id,@RequestParam String nombre,
                        @RequestParam int idautor,@RequestParam int ideditorial) {
        Libro lib= libroRepository.findById(id).orElse(null);
        Autor aut = autorRepository.findById(idautor).get();
        Editorial ed = editorialRepository.findById(ideditorial).get();
        lib.setNombre(nombre);
        lib.setAutor(aut);
        lib.setEditorial(ed);
        libroRepository.save(lib);
        return "redirect:/librs";
    }
}
