package com.mycompany.proyectouniversidad.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Carrera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Basic
    private String nombre;
    private int duracionAnios;
    private String coordinador;
    private double precioInscripcion;
    private double precioCuota;

    @OneToMany(mappedBy = "carrera")
    private List<Materia> materias;

    @ManyToMany
    private List<Alumno> alumnosInscriptos;

    public Carrera() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getDuracionAnios() { return duracionAnios; }
    public void setDuracionAnios(int duracionAnios) { this.duracionAnios = duracionAnios; }
    public String getCoordinador() { return coordinador; }
    public void setCoordinador(String coordinador) { this.coordinador = coordinador; }
    public double getPrecioInscripcion() { return precioInscripcion; }
    public void setPrecioInscripcion(double precioInscripcion) { this.precioInscripcion = precioInscripcion; }
    public double getPrecioCuota() { return precioCuota; }
    public void setPrecioCuota(double precioCuota) { this.precioCuota = precioCuota; }
    public List<Materia> getMaterias() { return materias; }
    public void setMaterias(List<Materia> materias) { this.materias = materias; }
    public List<Alumno> getAlumnosInscriptos() { return alumnosInscriptos; }
    public void setAlumnosInscriptos(List<Alumno> alumnosInscriptos) { this.alumnosInscriptos = alumnosInscriptos; }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}