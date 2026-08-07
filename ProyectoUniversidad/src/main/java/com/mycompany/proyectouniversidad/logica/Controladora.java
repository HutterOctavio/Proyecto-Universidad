package com.mycompany.proyectouniversidad.logica;

import com.mycompany.proyectouniversidad.persistencia.ControladoraPersistencia;
import java.util.Date;
import java.util.List;

public class Controladora {

    private final ControladoraPersistencia controlPersis = new ControladoraPersistencia();


    public void guardarAlumno(String nombre, String apellido, String legajo, String sexo, Date fechaNac) {
        Alumno alu = new Alumno();
        alu.setNombre(nombre);
        alu.setApellido(apellido);
        alu.setLegajo(legajo);
        alu.setSexo(sexo);
        alu.setFechaNac(fechaNac);
        controlPersis.guardarAlumno(alu);
    }

    public List<Alumno> listarAlumnos() {
        return controlPersis.listarAlumnos();
    }

    public void actualizarAlumno(Alumno alumno, String nombre, String apellido, String legajo, String sexo, Date fechaNac) {
        alumno.setNombre(nombre);
        alumno.setApellido(apellido);
        alumno.setLegajo(legajo);
        alumno.setSexo(sexo);
        alumno.setFechaNac(fechaNac);
        controlPersis.actualizarAlumno(alumno);
    }

    public void eliminarAlumno(Alumno alumno) {
        controlPersis.eliminarAlumno(alumno.getId());
    }

    public void guardarCarrera(String nombre, int duracionAnios, String coordinador,
            double precioInscripcion, double precioCuota) {
        Carrera carrera = new Carrera();
        carrera.setNombre(nombre);
        carrera.setDuracionAnios(duracionAnios);
        carrera.setCoordinador(coordinador);
        carrera.setPrecioInscripcion(precioInscripcion);
        carrera.setPrecioCuota(precioCuota);
        controlPersis.guardarCarrera(carrera);
    }

    public List<Carrera> listarCarreras() {
        return controlPersis.listarCarreras();
    }

    public void actualizarCarrera(Carrera carrera, String nombre, int duracionAnios, String coordinador,
            double precioInscripcion, double precioCuota) {
        carrera.setNombre(nombre);
        carrera.setDuracionAnios(duracionAnios);
        carrera.setCoordinador(coordinador);
        carrera.setPrecioInscripcion(precioInscripcion);
        carrera.setPrecioCuota(precioCuota);
        controlPersis.actualizarCarrera(carrera);
    }

    public void eliminarCarrera(Carrera carrera) {
        controlPersis.eliminarCarrera(carrera.getId());
    }


    public void guardarProfesor(String nombre, String apellido) {
        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setApellido(apellido);
        controlPersis.guardarProfesor(profesor);
    }

    public List<Profesor> listarProfesores() {
        return controlPersis.listarProfesores();
    }

    public void actualizarProfesor(Profesor profesor, String nombre, String apellido) {
        profesor.setNombre(nombre);
        profesor.setApellido(apellido);
        controlPersis.actualizarProfesor(profesor);
    }

    public void eliminarProfesor(Profesor profesor) {
        controlPersis.eliminarProfesor(profesor.getId());
    }


    public void guardarMateria(String nombre, String curso, String cuatrimestre,
            Carrera carrera, Profesor profesor) {
        Materia materia = new Materia();
        materia.setNombre(nombre);
        materia.setCurso(curso);
        materia.setCuatrimestre(cuatrimestre);
        materia.setCarrera(carrera);
        materia.setProfesor(profesor);
        controlPersis.guardarMateria(materia);
    }

    public List<Materia> listarMaterias() {
        return controlPersis.listarMaterias();
    }

    public void actualizarMateria(Materia materia, String nombre, String curso, String cuatrimestre,
            Carrera carrera, Profesor profesor) {
        materia.setNombre(nombre);
        materia.setCurso(curso);
        materia.setCuatrimestre(cuatrimestre);
        materia.setCarrera(carrera);
        materia.setProfesor(profesor);
        controlPersis.actualizarMateria(materia);
    }

    public void eliminarMateria(Materia materia) {
        controlPersis.eliminarMateria(materia.getId());
    }

    public List<Materia> listarMateriasDeCarrera(int idCarrera) {
        return controlPersis.listarMateriasPorCarrera(idCarrera);
    }


    public void inscribirAlumnoEnCarrera(Alumno alumno, Carrera carrera) {
        controlPersis.inscribirAlumnoEnCarrera(alumno.getId(), carrera.getId());
    }

    public List<Alumno> listarAlumnosDeCarrera(int idCarrera) {
        return controlPersis.listarAlumnosDeCarrera(idCarrera);
    }

    public void inscribirAlumnoEnMateria(Alumno alumno, Materia materia) {
        controlPersis.inscribirAlumnoEnMateria(alumno.getId(), materia.getId());
    }

    public List<Cursada> listarCursadas() {
        return controlPersis.listarCursadas();
    }

    public List<Cursada> listarCursadasDeMateria(int idMateria) {
        return controlPersis.listarCursadasPorMateria(idMateria);
    }


    public void cargarSituacionFinal(Cursada cursada, String estado) {
        controlPersis.actualizarEstadoCursada(cursada.getId(), estado);
    }

    public void registrarAsistencia(Cursada cursada) {
        controlPersis.registrarAsistencia(cursada.getId());
    }

    public void eliminarCursada(Cursada cursada) {
        controlPersis.eliminarCursada(cursada.getId());
    }
}
