package com.mycompany.proyectouniversidad.igu;

import com.mycompany.proyectouniversidad.logica.Alumno;
import com.mycompany.proyectouniversidad.logica.Carrera;
import com.mycompany.proyectouniversidad.logica.Controladora;
import com.mycompany.proyectouniversidad.logica.Materia;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class PanelInscripciones extends JPanel implements Refrescable {

    private final Controladora controladora;

    private final JComboBox<Alumno> cmbAlumnoCarrera = new JComboBox<>();
    private final JComboBox<Carrera> cmbCarrera = new JComboBox<>();

    private final JComboBox<Alumno> cmbAlumnoMateria = new JComboBox<>();
    private final JComboBox<Materia> cmbMateria = new JComboBox<>();

    private final DefaultListModel<Object> modeloCursadas = new DefaultListModel<>();

    public PanelInscripciones(Controladora controladora) {
        this.controladora = controladora;
        construirUI();
        refrescar();
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel arriba = new JPanel();
        arriba.setLayout(new BoxLayout(arriba, BoxLayout.Y_AXIS));

        JPanel panelCarrera = new JPanel(new GridLayout(3, 2, 5, 5));
        panelCarrera.setBorder(BorderFactory.createTitledBorder("Inscribir alumno a una carrera"));
        panelCarrera.add(new JLabel("Alumno:"));
        panelCarrera.add(cmbAlumnoCarrera);
        panelCarrera.add(new JLabel("Carrera:"));
        panelCarrera.add(cmbCarrera);
        JButton btnInscribirCarrera = new JButton("Inscribir a carrera");
        btnInscribirCarrera.addActionListener(e -> inscribirACarrera());
        panelCarrera.add(new JLabel());
        panelCarrera.add(btnInscribirCarrera);

        JPanel panelMateria = new JPanel(new GridLayout(3, 2, 5, 5));
        panelMateria.setBorder(BorderFactory.createTitledBorder("Inscribir alumno al cursado de una materia"));
        panelMateria.add(new JLabel("Alumno:"));
        panelMateria.add(cmbAlumnoMateria);
        panelMateria.add(new JLabel("Materia:"));
        panelMateria.add(cmbMateria);
        JButton btnInscribirMateria = new JButton("Inscribir a materia");
        btnInscribirMateria.addActionListener(e -> inscribirAMateria());
        panelMateria.add(new JLabel());
        panelMateria.add(btnInscribirMateria);

        arriba.add(panelCarrera);
        arriba.add(panelMateria);

        add(arriba, BorderLayout.NORTH);
        add(new JScrollPane(new JList<>(modeloCursadas)), BorderLayout.CENTER);
    }

    private void inscribirACarrera() {
        Alumno alumno = (Alumno) cmbAlumnoCarrera.getSelectedItem();
        Carrera carrera = (Carrera) cmbCarrera.getSelectedItem();
        if (alumno == null || carrera == null) {
            JOptionPane.showMessageDialog(this, "Elegí un alumno y una carrera.");
            return;
        }
        controladora.inscribirAlumnoEnCarrera(alumno, carrera);
        JOptionPane.showMessageDialog(this, "Alumno inscripto en la carrera.");
    }

    private void inscribirAMateria() {
        Alumno alumno = (Alumno) cmbAlumnoMateria.getSelectedItem();
        Materia materia = (Materia) cmbMateria.getSelectedItem();
        if (alumno == null || materia == null) {
            JOptionPane.showMessageDialog(this, "Elegí un alumno y una materia.");
            return;
        }
        controladora.inscribirAlumnoEnMateria(alumno, materia);
        refrescar();
    }

    @Override
    public void refrescar() {
        cmbAlumnoCarrera.setModel(new DefaultComboBoxModel<>(controladora.listarAlumnos().toArray(new Alumno[0])));
        cmbAlumnoMateria.setModel(new DefaultComboBoxModel<>(controladora.listarAlumnos().toArray(new Alumno[0])));
        cmbCarrera.setModel(new DefaultComboBoxModel<>(controladora.listarCarreras().toArray(new Carrera[0])));
        cmbMateria.setModel(new DefaultComboBoxModel<>(controladora.listarMaterias().toArray(new Materia[0])));

        modeloCursadas.clear();
        controladora.listarCursadas().forEach(modeloCursadas::addElement);
    }
}
