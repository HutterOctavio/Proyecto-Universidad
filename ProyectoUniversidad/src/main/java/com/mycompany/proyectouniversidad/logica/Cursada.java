package com.mycompany.proyectouniversidad.logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cursada implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Materia materia;

    @Basic
    private String estadoFinal; // Regular, Libre, Promocionado, Cursando
    private int asistencias;

    public Cursada() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Alumno getAlumno() { return alumno; }
    public void setAlumno(Alumno alumno) { this.alumno = alumno; }
    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }
    public String getEstadoFinal() { return estadoFinal; }
    public void setEstadoFinal(String estadoFinal) { this.estadoFinal = estadoFinal; }
    public int getAsistencias() { return asistencias; }
    public void setAsistencias(int asistencias) { this.asistencias = asistencias; }

    @Override
    public String toString() {
        String nombreAlumno = alumno != null ? (alumno.getNombre() + " " + alumno.getApellido()) : "?";
        String nombreMateria = materia != null ? materia.getNombre() : "?";
        return id + " - " + nombreAlumno + " en " + nombreMateria
                + " [" + estadoFinal + ", asistencias=" + asistencias + "]";
    }
}