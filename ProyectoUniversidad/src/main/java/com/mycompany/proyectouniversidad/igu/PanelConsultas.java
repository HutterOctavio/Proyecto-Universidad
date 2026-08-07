package com.mycompany.proyectouniversidad.igu;

import com.mycompany.proyectouniversidad.logica.Carrera;
import com.mycompany.proyectouniversidad.logica.Controladora;
import com.mycompany.proyectouniversidad.logica.Materia;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelConsultas extends JPanel implements Refrescable {

    private final Controladora controladora;

    private final JComboBox<Carrera> cmbCarreraAlumnos = new JComboBox<>();
    private final DefaultListModel<Object> modeloAlumnosDeCarrera = new DefaultListModel<>();

    private final JComboBox<Carrera> cmbCarreraMaterias = new JComboBox<>();
    private final DefaultListModel<Object> modeloMateriasDeCarrera = new DefaultListModel<>();

    private final JComboBox<Materia> cmbMateria = new JComboBox<>();
    private final DefaultListModel<Object> modeloCursadasDeMateria = new DefaultListModel<>();

    public PanelConsultas(Controladora controladora) {
        this.controladora = controladora;
        construirUI();
        refrescar();
    }

    private void construirUI() {
        setLayout(new GridLayout(3, 1, 10, 10));

        JPanel p1 = new JPanel(new BorderLayout(5, 5));
        p1.setBorder(BorderFactory.createTitledBorder("Alumnos inscriptos a una carrera"));
        JPanel top1 = new JPanel();
        top1.setLayout(new BoxLayout(top1, BoxLayout.X_AXIS));
        top1.add(new JLabel("Carrera: "));
        top1.add(cmbCarreraAlumnos);
        JButton btn1 = new JButton("Consultar");
        btn1.addActionListener(e -> consultarAlumnosDeCarrera());
        top1.add(btn1);
        p1.add(top1, BorderLayout.NORTH);
        p1.add(new JScrollPane(new JList<>(modeloAlumnosDeCarrera)), BorderLayout.CENTER);

        JPanel p2 = new JPanel(new BorderLayout(5, 5));
        p2.setBorder(BorderFactory.createTitledBorder("Materias de una carrera (cuatrimestre y profesor)"));
        JPanel top2 = new JPanel();
        top2.setLayout(new BoxLayout(top2, BoxLayout.X_AXIS));
        top2.add(new JLabel("Carrera: "));
        top2.add(cmbCarreraMaterias);
        JButton btn2 = new JButton("Consultar");
        btn2.addActionListener(e -> consultarMateriasDeCarrera());
        top2.add(btn2);
        p2.add(top2, BorderLayout.NORTH);
        p2.add(new JScrollPane(new JList<>(modeloMateriasDeCarrera)), BorderLayout.CENTER);

        JPanel p3 = new JPanel(new BorderLayout(5, 5));
        p3.setBorder(BorderFactory.createTitledBorder("Alumnos inscriptos a una materia y su estado"));
        JPanel top3 = new JPanel();
        top3.setLayout(new BoxLayout(top3, BoxLayout.X_AXIS));
        top3.add(new JLabel("Materia: "));
        top3.add(cmbMateria);
        JButton btn3 = new JButton("Consultar");
        btn3.addActionListener(e -> consultarCursadasDeMateria());
        top3.add(btn3);
        p3.add(top3, BorderLayout.NORTH);
        p3.add(new JScrollPane(new JList<>(modeloCursadasDeMateria)), BorderLayout.CENTER);

        add(p1);
        add(p2);
        add(p3);
    }

    private void consultarAlumnosDeCarrera() {
        Carrera carrera = (Carrera) cmbCarreraAlumnos.getSelectedItem();
        modeloAlumnosDeCarrera.clear();
        if (carrera == null) {
            JOptionPane.showMessageDialog(this, "Elegí una carrera.");
            return;
        }
        controladora.listarAlumnosDeCarrera(carrera.getId()).forEach(modeloAlumnosDeCarrera::addElement);
    }

    private void consultarMateriasDeCarrera() {
        Carrera carrera = (Carrera) cmbCarreraMaterias.getSelectedItem();
        modeloMateriasDeCarrera.clear();
        if (carrera == null) {
            JOptionPane.showMessageDialog(this, "Elegí una carrera.");
            return;
        }
        for (Materia m : controladora.listarMateriasDeCarrera(carrera.getId())) {
            String profesor = m.getProfesor() != null ? m.getProfesor().toString() : "sin profesor";
            modeloMateriasDeCarrera.addElement(m + " - cuatrimestre " + m.getCuatrimestre() + " - profesor: " + profesor);
        }
    }

    private void consultarCursadasDeMateria() {
        Materia materia = (Materia) cmbMateria.getSelectedItem();
        modeloCursadasDeMateria.clear();
        if (materia == null) {
            JOptionPane.showMessageDialog(this, "Elegí una materia.");
            return;
        }
        controladora.listarCursadasDeMateria(materia.getId()).forEach(modeloCursadasDeMateria::addElement);
    }

    @Override
    public void refrescar() {
        Carrera selA = (Carrera) cmbCarreraAlumnos.getSelectedItem();
        Carrera selM = (Carrera) cmbCarreraMaterias.getSelectedItem();
        Materia selMat = (Materia) cmbMateria.getSelectedItem();

        Carrera[] carreras = controladora.listarCarreras().toArray(new Carrera[0]);
        cmbCarreraAlumnos.setModel(new DefaultComboBoxModel<>(carreras));
        cmbCarreraMaterias.setModel(new DefaultComboBoxModel<>(carreras));
        cmbMateria.setModel(new DefaultComboBoxModel<>(controladora.listarMaterias().toArray(new Materia[0])));

        if (selA != null) cmbCarreraAlumnos.setSelectedItem(selA);
        if (selM != null) cmbCarreraMaterias.setSelectedItem(selM);
        if (selMat != null) cmbMateria.setSelectedItem(selMat);
    }
}
