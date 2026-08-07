package com.mycompany.proyectouniversidad.logica;

import com.mycompany.proyectouniversidad.logica.Carrera;
import com.mycompany.proyectouniversidad.logica.Cursada;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-03T19:46:55", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile SingularAttribute<Alumno, Date> fechaNac;
    public static volatile ListAttribute<Alumno, Cursada> cursadas;
    public static volatile SingularAttribute<Alumno, String> legajo;
    public static volatile ListAttribute<Alumno, Carrera> carreras;
    public static volatile SingularAttribute<Alumno, String> apellido;
    public static volatile SingularAttribute<Alumno, Integer> id;
    public static volatile SingularAttribute<Alumno, String> sexo;
    public static volatile SingularAttribute<Alumno, String> nombre;

}