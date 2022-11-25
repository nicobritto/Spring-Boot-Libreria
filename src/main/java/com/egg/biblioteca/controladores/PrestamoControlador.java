package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Cliente;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.entidades.Prestamo;
import com.egg.biblioteca.excepsiones.MIException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.ClienteServicio;
import com.egg.biblioteca.servicios.LibroServicio;
import com.egg.biblioteca.servicios.PrestamoServicio;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/prestamo")
public class PrestamoControlador {

    @Autowired
    private PrestamoServicio prestamoServicio;
    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {
        List<Libro> libros = libroServicio.listarLibros();
        List<Cliente> clientes = clienteServicio.listaClientes();
        modelo.addAttribute("libroTodos", libros);
        modelo.addAttribute("clientesTodos", clientes);

        return "prestamo_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam("fechaDevolucion")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDevolucion,
            @RequestParam String libroIsbn, @RequestParam String clienteId, ModelMap modelo) {

        try {
            prestamoServicio.crearPrestamo(fechaDevolucion, libroIsbn, clienteId);
            modelo.put("exito", "el prestamo se guardo correctamente");
            return "prestamo_form.html";

        } catch (MIException ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(PrestamoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "prestamo_form.html";
        }

    }

    @GetMapping("/lista")
    public String listarPrestamos(ModelMap model) {
        List<Prestamo> prestamos = prestamoServicio.listaPrestamos();
        model.addAttribute("prestamosTodos", prestamos);
        return "prestamo_list.html";

    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap model) {
        model.put("prestamo", prestamoServicio.getOne(id));

        return "prestamo_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@RequestParam String id,
            @RequestParam("fechaDevolucion")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDevolucion, ModelMap modelo) {
        try {

            prestamoServicio.devolverLibro(id, fechaDevolucion);
            return "redirect:../lista";

        } catch (MIException ex) {
            modelo.addAttribute("error", ex.getMessage());
            Logger.getLogger(PrestamoControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "prestamo_list";
        }

    }

}
