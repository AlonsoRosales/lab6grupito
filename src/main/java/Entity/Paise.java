package Entity;

import javax.persistence.*;

@Entity
@Table(name = "paises")
public class Paise {
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