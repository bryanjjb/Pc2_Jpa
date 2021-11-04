/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eje.controller;

import com.example.eje.entity.Autor;
import com.example.eje.entity.Editoriales;
import com.example.eje.entity.Libros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.eje.repository.LibroRepository;


@Controller
public class LibroController {
   @Autowired
    private LibroRepository postRepository;
    @RequestMapping("/")
    public String mensaje(Model model){
        model.addAttribute("mensaje", "Bienvenidos pc2");
        return "index";
    }
    @RequestMapping("/libros")
    public String post(Model model){
        model.addAttribute("libros", postRepository.findAll());
        return "libro";
    }
    @RequestMapping("/form")
    public String create(Model model) {
        return "add";
    }
    @RequestMapping("/add")
    public String guardar(@RequestParam String nombre, @RequestParam  Autor idautor, @RequestParam  Editoriales ideditorial,Model model) {
        Libros libro = new Libros();
        libro.setNombre(nombre);
        libro.setIdautor(idautor);
        libro.setIdeditorial(ideditorial);
        System.out.println("sout:"+libro.getNombre()+"/"+libro.getIdautor()+"/"+libro.getIdeditorial());
        postRepository.save(libro);
        return "redirect:/libros";
    }
    @RequestMapping("/del/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        System.out.println("ID: "+id);
        Libros libro = postRepository.findById(id).orElse(null);
        postRepository.delete(libro);
        return "redirect:/libros";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        model.addAttribute("libro", postRepository.findById(id).orElse(null));
        return "edit";
    }
    @RequestMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String nombre, @RequestParam Autor idautor, @RequestParam Editoriales ideditorial) {
        Libros libro = postRepository.findById(id).orElse(null);
        libro.setNombre(nombre);
        libro.setIdautor(idautor);
        libro.setIdeditorial(ideditorial);
        postRepository.save(libro);
        return "redirect:/libros";
    }
}
