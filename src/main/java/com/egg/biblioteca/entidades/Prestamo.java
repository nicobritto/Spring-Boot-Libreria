package com.egg.biblioteca.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(generator = "uuid")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrestamo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;

    private Boolean alta;

    @OneToOne
    private Libro libro;

    @ManyToOne
    Cliente cliente;

    public Prestamo() {
    }

    public Prestamo(Date fechaPrestamo, Date fechaDevolucion, Boolean alta, Libro libro, Cliente cliente) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.alta = alta;
        this.libro = libro;
        this.cliente = cliente;
    }

    public Prestamo(String id, Date fechaDevolucion, Boolean alta, Libro libro, Cliente cliente) {
        this.id = id;
        this.fechaDevolucion = fechaDevolucion;
        this.alta = alta;
        this.libro = libro;
        this.cliente = cliente;
    }

 
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    



}
