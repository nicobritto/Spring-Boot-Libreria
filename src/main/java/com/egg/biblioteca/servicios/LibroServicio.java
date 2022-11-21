package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepsiones.MIException;
import com.egg.biblioteca.repositorios.AutorRepositorio;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import com.egg.biblioteca.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearLibro(String isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MIException {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        Autor autor = autorRepositorio.findById(idAutor).get();

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjeplares(ejemplares);
        libro.setFechaAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList();
        libros = libroRepositorio.findAll();

        return libros;

    }

    public void modificarLibro(String isbn, String titulo, String idAutorm, String idEditorial, Integer cantEjemplares) throws MIException {

        validar(isbn, titulo, cantEjemplares, idAutorm, idEditorial);
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutorm);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }

        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjeplares(cantEjemplares);

            libroRepositorio.save(libro);
        }

    }

    private void validar(String isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MIException {
        if (isbn == null) {
            throw new MIException("el isbn no puede ser nulo");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new MIException("el titutlo no puede ser nulo o esta vacio");
        }
        if (ejemplares == null) {
            throw new MIException("el isbn no puede ser nulo");
        }
        if (idAutor == null || idAutor.trim().isEmpty()) {
            throw new MIException("el idAutor no puede ser nulo o esta vacio");
        }
        if (idEditorial == null || idEditorial.trim().isEmpty()) {
            throw new MIException("el idEditorial no puede ser nulo o esta vacio");
        }
    }

}
