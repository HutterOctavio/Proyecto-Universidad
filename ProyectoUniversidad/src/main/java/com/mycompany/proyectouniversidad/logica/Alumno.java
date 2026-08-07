package com.mycompany.proyectouniversidad.logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    @Basic
    private String nombre;
    private String apellido;
    private String legajo;
    private String sexo;
    
    @Temporal(TemporalType.DATE)
    private Date fechaNac;

    @ManyToMany(mappedBy = "alumnosInscriptos")
    private List<Carrera> carreras;

    @OneToMany(mappedBy = "alumno")
    private List<Cursada> cursadas;

    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellido, String legajo, Date fechaNac) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.fechaNac = fechaNac;
    }

    // Generar Getters y Setters desde tu IDE
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getLegajo() { return legajo; }
    public void setLegajo(String legajo) { this.legajo = legajo; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public Date getFechaNac() { return fechaNac; }
    public void setFechaNac(Date fechaNac) { this.fechaNac = fechaNac; }
    public List<Carrera> getCarreras() { return carreras; }
    public void setCarreras(List<Carrera> carreras) { this.carreras = carreras; }
    public List<Cursada> getCursadas() { return cursadas; }
    public void setCursadas(List<Cursada> cursadas) { this.cursadas = cursadas; }

    @Override
    public String toString() {
        return id + " - " + nombre + " " + apellido + " (legajo " + legajo + ")";
    }
}