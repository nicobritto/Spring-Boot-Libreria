package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepsiones.MIException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MIException {
        Autor autor = new Autor();
        validar(nombre);
        autor.setNombre(nombre);
        autorRepositorio.save(autor);

    }

    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList();
        autores = autorRepositorio.findAll();

        return autores;

    }

    @Transactional
    public void eliminarAutor(String id) throws MIException {
       // Autor autor = autorRepositorio.findById(id).get();
        autorRepositorio.deleteById(id);
    }

    @Transactional
    public void modificarAutor(String nombre, String id) throws MIException {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        }

    }

    public Autor getOne(String id) {
        return autorRepositorio.getOne(id);
    }

    private void validar(String nombre) throws MIException {

        if (nombre.trim().isEmpty() || nombre == null) {
            throw new MIException("el titulo no pede ser nulo");
        }
    }

}
