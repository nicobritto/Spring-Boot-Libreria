package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Cliente;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.entidades.Prestamo;
import com.egg.biblioteca.excepsiones.MIException;
import com.egg.biblioteca.repositorios.PrestamoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoServicio {

    @Autowired
    PrestamoRepositorio pretamoRepositorio;
    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    LibroServicio libroServicio;

    public void crearPrestamo(Date fechaDevolucion, String idLibro, String idCliente) throws MIException {
        Libro libro = libroServicio.buscarLibro(idLibro);
        Cliente cliente = clienteServicio.buscarCkiente(idCliente);
        validar(idLibro, idCliente);
        if (libro.getEjeplares() > 0) {
            libro.setEjeplares(libro.getEjeplares() - 1);
            Prestamo prestamo = new Prestamo(new Date(), fechaDevolucion, true, libro, cliente);
            pretamoRepositorio.save(prestamo);
        } else {
            throw new MIException("no quedan ejemplares de ese libro");
        }
    }

    public void validar(String idLibro, String idClient) throws MIException {
        if (idClient == null || idClient.trim().isEmpty()) {
            throw new MIException("idClient nulo");
        }
        if (idLibro == null || idLibro.trim().isEmpty()) {
            throw new MIException("idLibro nulo");
        }
    }

    public List<Prestamo> listaPrestamos() {
        return pretamoRepositorio.findAll();

    }

    public Prestamo buscarPrestamo(String idPrestamo) throws MIException {
        Prestamo prestamo = pretamoRepositorio.findById(idPrestamo).get();
        if (prestamo.getAlta()) {
            return prestamo;
        } else {
            throw new MIException("prestamo ");
        }
    }

//    La entidad préstamo modela los datos de un préstamo de un libro. Esta entidad registra 
//la fecha en la que se efectuó el préstamo y la fecha en la que fue devuelto el libro, al 
//devolver el libro este préstamo queda dado de baja. Esta entidad también registra el 
//libro que se llevaron en dicho préstamo y quien fue el cliente al cual se lo prestaron. 
    public void devolverLibro(String idPrestamo, Date fechaDevolucion) throws MIException {
        Prestamo prestamo = buscarPrestamo(idPrestamo);
        Libro libro = libroServicio.buscarLibro(prestamo.getLibro().getIsbn());
        Cliente cliente = clienteServicio.buscarCkiente(prestamo.getCliente().getId());
        libro.setEjeplares(libro.getEjeplares() + 1);
        prestamo.setAlta(Boolean.FALSE);
        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setCliente(cliente);
        prestamo.setLibro(libro);
        pretamoRepositorio.save(prestamo);

    }

    public Prestamo getOne(String id) {
        return pretamoRepositorio.getOne(id);
    }

}
