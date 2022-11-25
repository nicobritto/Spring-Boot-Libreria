package com.egg.biblioteca.repositorios;

import com.egg.biblioteca.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String> {

    @Query("select p from Cliente p where p.documento LIKE :dni")
    List<Cliente> findAll(@Param("dni") Integer dni);
}
