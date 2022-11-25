package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Cliente;
import com.egg.biblioteca.excepsiones.MIException;
import com.egg.biblioteca.repositorios.ClienteRepositorio;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    public void crearCliente(Integer dni, String nombre, String apellido, String telefono) throws MIException {
        validar(dni, nombre, apellido, telefono);
        Boolean alta = true;
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono, alta);
        clienteRepositorio.save(cliente);

    }

    public void actualizar(Integer dni, String nombre, String apellido, String telefono) throws MIException {
        validar(dni, nombre, apellido, telefono);
        String otrodni = String.valueOf(dni);
        Optional<Cliente> respuesta = clienteRepositorio.findById(otrodni);
        Cliente cliente = new Cliente();
        if (respuesta.isPresent()) {
            cliente = respuesta.get();
            cliente.setDocumento(dni);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            clienteRepositorio.save(cliente);
        }

    }

    public List<Cliente> listaClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteRepositorio.findAll();
        return clientes;
    }

    public void validar(Integer dni, String nombre, String apellido, String telefono) throws MIException {
        if (dni == null || dni < 0) {
            throw new MIException("dni no puede ser nulo");
        }
        if (nombre.trim().isEmpty()) {
            throw new MIException("nombre noi puede ser nulo");
        }
        if (apellido.trim().isEmpty()) {
            throw new MIException("apellido noi puede ser nulo");
        }
        if (nombre.trim().isEmpty()) {
            throw new MIException("telefono noi puede ser nulo");
        }

    }

    public Cliente buscarCkiente(String id) throws MIException {
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            return cliente;
        } else {
            throw new MIException("no se encontro el cliente");
        }

    }

}
