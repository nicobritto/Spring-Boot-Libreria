package com.egg.biblioteca.controladores;

import com.egg.biblioteca.excepsiones.MIException;
import com.egg.biblioteca.servicios.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")//localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    AutorServicio autorServicio;

    @GetMapping("/registrar")
    public String registro() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")// <form action="/autor/registro" revibe botonde formulario 
    public String registro(@RequestParam String nombre,ModelMap model) {
        try {
            autorServicio.crearAutor(nombre);
            model.put("exito","el autor fue guardado correctamente");
            
        } catch (MIException ex) {
            model.put("error",ex.getMessage());
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_form.html";
        }
        return "editorial_form.html";
    }

}
