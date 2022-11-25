package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepsiones.MIException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.EditorialServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")//localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    EditorialServicio editorialServicio;

    @GetMapping("/registrar")
    public String registro() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")// <form action="/autor/registro" revibe botonde formulario 
    public String registro(@RequestParam String nombre, ModelMap model) {

        try {
            editorialServicio.CrearEditorial(nombre);
            model.put("exito", "el autor se guardo correctamente");
        } catch (MIException ex) {
            model.put("error", ex.getMessage());
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_form.html";
        }

        return "editorial_form.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap model) {
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        model.addAttribute("editorialTodas", editoriales);

        return "editorial_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("editorial", editorialServicio.getOne(id));

        return "editorial_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo) {

        try {
            editorialServicio.modificarEditorial(id, nombre);
            return "redirect:../lista";
        } catch (MIException ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "editorial_modificar.html";
        }

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap model) {

        try {
            editorialServicio.eliminarEditorial(id);
            return "redirect:../lista";
        } catch (MIException ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            model.put("error", ex.getMessage());
            return "redirect:../lista";
        }

    }

}
