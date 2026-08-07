package com.mycompany.proyectouniversidad.logica;

import com.mycompany.proyectouniversidad.logica.Alumno;
import com.mycompany.proyectouniversidad.logica.Materia;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-03T19:46:55", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cursada.class)
public class Cursada_ { 

    public static volatile SingularAttribute<Cursada, String> estadoFinal;
    public static volatile SingularAttribute<Cursada, Alumno> alumno;
    public static volatile SingularAttribute<Cursada, Materia> materia;
    public static volatile SingularAttribute<Cursada, Integer> id;
    public static volatile SingularAttribute<Cursada, Integer> asistencias;

}