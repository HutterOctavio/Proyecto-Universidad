package com.mycompany.proyectouniversidad.igu;

import com.mycompany.proyectouniversidad.logica.Controladora;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        super("Sistema de Universidad");
        Controladora controladora = new Controladora();

        PanelAlumno panelAlumno = new PanelAlumno(controladora);
        PanelCarrera panelCarrera = new PanelCarrera(controladora);
        PanelProfesor panelProfesor = new PanelProfesor(controladora);
        PanelMateria panelMateria = new PanelMateria(controladora);
        PanelInscripciones panelInscripciones = new PanelInscripciones(controladora);
        PanelCursada panelCursada = new PanelCursada(controladora);
        PanelConsultas panelConsultas = new PanelConsultas(controladora);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Alumnos", panelAlumno);
        tabs.addTab("Carreras", panelCarrera);
        tabs.addTab("Profesores", panelProfesor);
        tabs.addTab("Materias", panelMateria);
        tabs.addTab("Inscripciones", panelInscripciones);
        tabs.addTab("Cursadas", panelCursada);
        tabs.addTab("Consultas", panelConsultas);

        tabs.addChangeListener(e -> {
            Object seleccionado = tabs.getSelectedComponent();
            if (seleccionado instanceof Refrescable refrescable) {
                refrescable.refrescar();
            }
        });

        setContentPane(tabs);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
