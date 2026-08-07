package com.mycompany.proyectouniversidad.logica;

import com.mycompany.proyectouniversidad.logica.Alumno;
import com.mycompany.proyectouniversidad.logica.Materia;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-03T19:46:55", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Carrera.class)
public class Carrera_ { 

    public static volatile SingularAttribute<Carrera, Double> precioCuota;
    public static volatile SingularAttribute<Carrera, String> coordinador;
    public static volatile SingularAttribute<Carrera, Integer> duracionAnios;
    public static volatile SingularAttribute<Carrera, Double> precioInscripcion;
    public static volatile ListAttribute<Carrera, Alumno> alumnosInscriptos;
    public static volatile SingularAttribute<Carrera, Integer> id;
    public static volatile SingularAttribute<Carrera, String> nombre;
    public static volatile ListAttribute<Carrera, Materia> materias;

}