package com.egg.biblioteca.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Libro {

    @Id
    private String isbn;
    private String titulo;
    private Integer ejeplares;

    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;
    

    public Libro() {
    }

    public Libro(String isbn, String titulo, Integer ejeplares, Date fechaAlta, Autor autor, Editorial editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.ejeplares = ejeplares;
        this.fechaAlta = fechaAlta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getEjeplares() {
        return ejeplares;
    }

    public void setEjeplares(Integer ejeplares) {
        this.ejeplares = ejeplares;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", ejeplares=" + ejeplares + ", fechaAlta=" + fechaAlta + ", autor=" + autor + ", editorial=" + editorial + '}';
    }
    
    
    


    
}
