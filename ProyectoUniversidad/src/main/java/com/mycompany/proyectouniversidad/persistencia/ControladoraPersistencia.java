package com.mycompany.proyectouniversidad.persistencia;

import com.mycompany.proyectouniversidad.logica.Alumno;
import com.mycompany.proyectouniversidad.logica.Carrera;
import com.mycompany.proyectouniversidad.logica.Cursada;
import com.mycompany.proyectouniversidad.logica.Materia;
import com.mycompany.proyectouniversidad.logica.Profesor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ControladoraPersistencia {

    private static final String PERSISTENCE_UNIT_NAME = "ProyectoUniversidadPU";
    private final EntityManagerFactory emf;

    public ControladoraPersistencia() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }


    public void guardarAlumno(Alumno alumno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Alumno> listarAlumnos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno buscarAlumnoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizarAlumno(Alumno alumno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(alumno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminarAlumno(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Alumno alumno = em.find(Alumno.class, id);
            if (alumno != null) {
                em.remove(alumno);
            }
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("No se pudo eliminar: el alumno tiene inscripciones/cursadas asociadas.", ex);
        } finally {
            em.close();
        }
    }


    public void guardarCarrera(Carrera carrera) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(carrera);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Carrera> listarCarreras() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Carrera c", Carrera.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Carrera buscarCarreraPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    public void actualizarCarrera(Carrera carrera) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(carrera);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminarCarrera(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Carrera carrera = em.find(Carrera.class, id);
            if (carrera != null) {
                em.remove(carrera);
            }
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("No se pudo eliminar: la carrera tiene materias o alumnos asociados.", ex);
        } finally {
            em.close();
        }
    }


    public void guardarProfesor(Profesor profesor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(profesor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Profesor> listarProfesores() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Profesor p", Profesor.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizarProfesor(Profesor profesor) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(profesor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminarProfesor(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Profesor profesor = em.find(Profesor.class, id);
            if (profesor != null) {
                em.remove(profesor);
            }
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("No se pudo eliminar: el profesor tiene materias asignadas.", ex);
        } finally {
            em.close();
        }
    }


    public void guardarMateria(Materia materia) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(materia);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Materia> listarMaterias() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Materia m", Materia.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizarMateria(Materia materia) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(materia);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminarMateria(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Materia materia = em.find(Materia.class, id);
            if (materia != null) {
                em.remove(materia);
            }
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("No se pudo eliminar: la materia tiene cursadas asociadas.", ex);
        } finally {
            em.close();
        }
    }

    public List<Materia> listarMateriasPorCarrera(int idCarrera) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT m FROM Materia m WHERE m.carrera.id = :idCarrera", Materia.class)
                    .setParameter("idCarrera", idCarrera)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void inscribirAlumnoEnCarrera(int idAlumno, int idCarrera) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Alumno alumno = em.find(Alumno.class, idAlumno);
            Carrera carrera = em.find(Carrera.class, idCarrera);
            carrera.getAlumnosInscriptos().add(alumno);
            em.merge(carrera);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Alumno> listarAlumnosDeCarrera(int idCarrera) {
        EntityManager em = emf.createEntityManager();
        try {
            Carrera carrera = em.find(Carrera.class, idCarrera);
            return carrera.getAlumnosInscriptos();
        } finally {
            em.close();
        }
    }

    public void inscribirAlumnoEnMateria(int idAlumno, int idMateria) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Alumno alumno = em.find(Alumno.class, idAlumno);
            Materia materia = em.find(Materia.class, idMateria);

            Cursada cursada = new Cursada();
            cursada.setAlumno(alumno);
            cursada.setMateria(materia);
            cursada.setEstadoFinal("Cursando");
            cursada.setAsistencias(0);

            em.persist(cursada);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void actualizarEstadoCursada(int idCursada, String nuevoEstado) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Cursada cursada = em.find(Cursada.class, idCursada);
            cursada.setEstadoFinal(nuevoEstado);
            em.merge(cursada);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void registrarAsistencia(int idCursada) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Cursada cursada = em.find(Cursada.class, idCursada);
            cursada.setAsistencias(cursada.getAsistencias() + 1);
            em.merge(cursada);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Cursada> listarCursadas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cursada c", Cursada.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Cursada> listarCursadasPorMateria(int idMateria) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Cursada c WHERE c.materia.id = :idMateria", Cursada.class)
                    .setParameter("idMateria", idMateria)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarCursada(int idCursada) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Cursada cursada = em.find(Cursada.class, idCursada);
            if (cursada != null) {
                em.remove(cursada);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void cerrar() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
