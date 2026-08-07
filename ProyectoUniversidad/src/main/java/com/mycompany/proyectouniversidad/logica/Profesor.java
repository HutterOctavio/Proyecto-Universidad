package com.mycompany.proyectouniversidad.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Profesor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Basic
    private String nombre;
    private String apellido;

    @OneToMany(mappedBy = "profesor")
    private List<Materia> materiasDictadas;

    public Profesor() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public List<Materia> getMateriasDictadas() { return materiasDictadas; }
    public void setMateriasDictadas(List<Materia> materiasDictadas) { this.materiasDictadas = materiasDictadas; }

    @Override
    public String toString() {
        return id + " - " + nombre + " " + apellido;
    }
}