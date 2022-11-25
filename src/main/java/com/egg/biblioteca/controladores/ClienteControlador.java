package com.egg.biblioteca.controladores;

import com.egg.biblioteca.entidades.Cliente;
import com.egg.biblioteca.excepsiones.MIException;
import com.egg.biblioteca.servicios.ClienteServicio;
import java.util.List;
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
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/registrar")
    public String registro() {
        return "cliente_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, Integer documento, String apellido, String telefono,
            ModelMap modelo) {
        try {
            clienteServicio.crearCliente(documento, nombre, apellido, telefono);
            return "index.html";
        } catch (MIException ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "cliente_form.html";
        }
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Cliente> clientes = clienteServicio.listaClientes();
        modelo.addAttribute("clientesTodos", clientes);

        return "cliente_list.html";

    }

}
