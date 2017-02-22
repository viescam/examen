/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Actor.findAll", query= "SELECT a from Actor a ORDER BY a.id")})
@Table( name = "actores" )
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_actor")
    private Integer id;
    
    @Column(nullable=false, length = 50)
    private String nombre;
    
    @Column(nullable=false, length = 100)
    private String apellidos;
    
   @Column(nullable=false, length = 1)
    private String sexo;
    
    @Column(length = 20)
    private String documento;
    
    @OneToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinColumn(name="direccion")
    private Direccion direccion;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "actores")
    private Set<Pelicula> peliculas;

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
   
    

    public Actor() {
    }

    public Actor(String nombre, String apellidos, String sexo, String documento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actor other = (Actor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Actor{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", sexo=" + sexo + ", documento=" + documento + ", direccion=" + direccion + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }    
}
