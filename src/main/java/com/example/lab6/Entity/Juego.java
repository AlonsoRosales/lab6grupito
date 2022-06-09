package com.example.lab6.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "juegos")
public class Juego implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuego", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 448)
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "image", length = 400)
    private String image;

    @ManyToOne
    @JoinColumn(name = "idgenero")
    private Genero idgenero;

    @ManyToOne
    @JoinColumn(name = "idplataforma")
    private Plataforma idplataforma;

    @ManyToOne
    @JoinColumn(name = "ideditora")
    private Editora ideditora;

    @ManyToOne
    @JoinColumn(name = "iddistribuidora")
    private Distribuidora iddistribuidora;

    public Genero getIdgenero() {
        return idgenero;
    }

    public void setIdgenero(Genero idgenero) {
        this.idgenero = idgenero;
    }

    public Plataforma getIdplataforma() {
        return idplataforma;
    }

    public void setIdplataforma(Plataforma idplataforma) {
        this.idplataforma = idplataforma;
    }

    public Editora getIdeditora() {
        return ideditora;
    }

    public void setIdeditora(Editora ideditora) {
        this.ideditora = ideditora;
    }

    public Distribuidora getIddistribuidora() {
        return iddistribuidora;
    }

    public void setIddistribuidora(Distribuidora iddistribuidora) {
        this.iddistribuidora = iddistribuidora;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}