package com.example.lab6.Entity;

import javax.persistence.*;

public class eliminame {
    @Entity
    @Table(name = "distribuidoras")
    public static class Distribuidora {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "iddistribuidora", nullable = false)
        private Integer id;

        @Column(name = "nombre", length = 50)
        private String nombre;

        @Column(name = "descripcion", length = 200)
        private String descripcion;

        @Column(name = "fundacion", nullable = false)
        private Integer fundacion;

        @Column(name = "web", length = 200)
        private String web;

        @JoinColumn(name = "idsede")
        private int idsede;

        public int getIdsede() {
            return idsede;
        }

        public void setIdsede(int idsede) {
            this.idsede = idsede;
        }

        public String getWeb() {
            return web;
        }

        public void setWeb(String web) {
            this.web = web;
        }

        public Integer getFundacion() {
            return fundacion;
        }

        public void setFundacion(Integer fundacion) {
            this.fundacion = fundacion;
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

    @Entity
    @Table(name = "editoras")
    public static class Editora {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ideditora", nullable = false)
        private Integer id;

        @Column(name = "nombre", length = 50)
        private String nombre;

        @Column(name = "descripcion", length = 200)
        private String descripcion;

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

    @Entity
    @Table(name = "generos")
    public static class Genero {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idgenero", nullable = false)
        private Integer id;

        @Column(name = "nombre", length = 50)
        private String nombre;

        @Column(name = "descripcion", length = 200)
        private String descripcion;

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

    @Entity
    @Table(name = "juegos")
    public static class Juego {
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

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "idgenero")
        private Genero idgenero;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "idplataforma")
        private Plataforma idplataforma;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ideditora")
        private Editora ideditora;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "iddistribuidora")
        private Distribuidora iddistribuidora;

        public Distribuidora getIddistribuidora() {
            return iddistribuidora;
        }

        public void setIddistribuidora(Distribuidora iddistribuidora) {
            this.iddistribuidora = iddistribuidora;
        }

        public Editora getIdeditora() {
            return ideditora;
        }

        public void setIdeditora(Editora ideditora) {
            this.ideditora = ideditora;
        }

        public Plataforma getIdplataforma() {
            return idplataforma;
        }

        public void setIdplataforma(Plataforma idplataforma) {
            this.idplataforma = idplataforma;
        }

        public Genero getIdgenero() {
            return idgenero;
        }

        public void setIdgenero(Genero idgenero) {
            this.idgenero = idgenero;
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

    @Entity
    @Table(name = "paises")
    public static class Paise {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idpais", nullable = false)
        private Integer id;

        @Column(name = "iso", length = 2)
        private String iso;

        @Column(name = "nombre", length = 80)
        private String nombre;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getIso() {
            return iso;
        }

        public void setIso(String iso) {
            this.iso = iso;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    @Entity
    @Table(name = "plataformas")
    public static class Plataforma {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idplataforma", nullable = false)
        private Integer id;

        @Column(name = "nombre", length = 50)
        private String nombre;

        @Column(name = "descripcion", length = 200)
        private String descripcion;

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
}
