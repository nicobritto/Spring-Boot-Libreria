package com.egg.biblioteca.servicios;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepsiones.MIException;
import com.egg.biblioteca.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void CrearEditorial(String nombre) throws MIException {
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorialRepositorio.save(editorial);

    }

    public List<Editorial> listarEditoriales() {
        List<Editorial> editoriales = new ArrayList();
        editoriales = editorialRepositorio.findAll();

        return editoriales;

    }

    public void modificarEditorial(String id, String nombre) throws MIException {
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        }

    }

    public void validar(String nombre) throws MIException {

        if (nombre.trim().isEmpty() || nombre == null) {
            throw new MIException("el nombre no puede ser nulo");
        }
    }

    public Editorial getOne(String id) {
        return editorialRepositorio.getOne(id);

    }

    public void eliminarEditorial(String id) throws MIException {
        editorialRepositorio.deleteById(id);

    }

}
