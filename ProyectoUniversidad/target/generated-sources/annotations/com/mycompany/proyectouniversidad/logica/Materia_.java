package com.mycompany.proyectouniversidad.logica;

import com.mycompany.proyectouniversidad.logica.Carrera;
import com.mycompany.proyectouniversidad.logica.Cursada;
import com.mycompany.proyectouniversidad.logica.Profesor;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-03T19:46:55", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Materia.class)
public class Materia_ { 

    public static volatile SingularAttribute<Materia, String> cuatrimestre;
    public static volatile ListAttribute<Materia, Cursada> cursadas;
    public static volatile SingularAttribute<Materia, String> curso;
    public static volatile SingularAttribute<Materia, Profesor> profesor;
    public static volatile SingularAttribute<Materia, Integer> id;
    public static volatile SingularAttribute<Materia, Carrera> carrera;
    public static volatile SingularAttribute<Materia, String> nombre;

}