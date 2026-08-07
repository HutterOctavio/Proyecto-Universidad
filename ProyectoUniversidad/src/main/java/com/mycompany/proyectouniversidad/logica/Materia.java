package com.mycompany.proyectouniversidad.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Materia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Basic
    private String nombre;
    private String curso;
    private String cuatrimestre;

    @ManyToOne
    private Carrera carrera;

    @ManyToOne
    private Profesor profesor;

    @OneToMany(mappedBy = "materia")
    private List<Cursada> cursadas;

    public Materia() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getCuatrimestre() { return cuatrimestre; }
    public void setCuatrimestre(String cuatrimestre) { this.cuatrimestre = cuatrimestre; }
    public Carrera getCarrera() { return carrera; }
    public void setCarrera(Carrera carrera) { this.carrera = carrera; }
    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }
    public List<Cursada> getCursadas() { return cursadas; }
    public void setCursadas(List<Cursada> cursadas) { this.cursadas = cursadas; }

    @Override
    public String toString() {
        String nombreCarrera = carrera != null ? carrera.getNombre() : "sin carrera";
        return id + " - " + nombre + " (" + nombreCarrera + ")";
    }
}