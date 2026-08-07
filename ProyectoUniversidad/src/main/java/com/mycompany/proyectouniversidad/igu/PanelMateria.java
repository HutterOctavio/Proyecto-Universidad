package com.mycompany.proyectouniversidad.igu;

import com.mycompany.proyectouniversidad.logica.Carrera;
import com.mycompany.proyectouniversidad.logica.Controladora;
import com.mycompany.proyectouniversidad.logica.Materia;
import com.mycompany.proyectouniversidad.logica.Profesor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelMateria extends JPanel implements Refrescable {

    private final Controladora controladora;
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtCurso = new JTextField();
    private final JTextField txtCuatrimestre = new JTextField();
    private final JComboBox<Carrera> cmbCarrera = new JComboBox<>();
    private final JComboBox<Profesor> cmbProfesor = new JComboBox<>();
    private final DefaultListModel<Object> modeloLista = new DefaultListModel<>();
    private final JList<Object> lista = new JList<>(modeloLista);
    private Materia seleccionada;

    public PanelMateria(Controladora controladora) {
        this.controladora = controladora;
        construirUI();
        refrescar();
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Curso / año:"));
        form.add(txtCurso);
        form.add(new JLabel("Cuatrimestre:"));
        form.add(txtCuatrimestre);
        form.add(new JLabel("Carrera:"));
        form.add(cmbCarrera);
        form.add(new JLabel("Profesor:"));
        form.add(cmbProfesor);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnGuardar = new JButton("Guardar nueva");
        btnGuardar.addActionListener(e -> guardar());
        JButton btnActualizar = new JButton("Actualizar seleccionada");
        btnActualizar.addActionListener(e -> actualizar());
        JButton btnEliminar = new JButton("Eliminar seleccionada");
        btnEliminar.addActionListener(e -> eliminar());
        JButton btnLimpiar = new JButton("Limpiar / nueva");
        btnLimpiar.addActionListener(e -> limpiar());
        botones.add(btnGuardar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(form, BorderLayout.CENTER);
        norte.add(botones, BorderLayout.SOUTH);

        lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarSeleccion();
        });

        add(norte, BorderLayout.NORTH);
        add(new JScrollPane(lista), BorderLayout.CENTER);
    }

    private void cargarSeleccion() {
        Object valor = lista.getSelectedValue();
        if (!(valor instanceof Materia materia)) {
            seleccionada = null;
            return;
        }
        seleccionada = materia;
        txtNombre.setText(materia.getNombre());
        txtCurso.setText(materia.getCurso());
        txtCuatrimestre.setText(materia.getCuatrimestre());
        cmbCarrera.setSelectedItem(materia.getCarrera());
        cmbProfesor.setSelectedItem(materia.getProfesor());
    }

    private void limpiar() {
        seleccionada = null;
        lista.clearSelection();
        txtNombre.setText("");
        txtCurso.setText("");
        txtCuatrimestre.setText("");
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();
        Carrera carrera = (Carrera) cmbCarrera.getSelectedItem();
        Profesor profesor = (Profesor) cmbProfesor.getSelectedItem();
        if (nombre.isEmpty() || carrera == null || profesor == null) {
            JOptionPane.showMessageDialog(this,
                    "Completá el nombre y elegí una carrera y un profesor.");
            return;
        }
        controladora.guardarMateria(nombre, txtCurso.getText().trim(), txtCuatrimestre.getText().trim(),
                carrera, profesor);
        limpiar();
        refrescar();
    }

    private void actualizar() {
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná una materia de la lista.");
            return;
        }
        String nombre = txtNombre.getText().trim();
        Carrera carrera = (Carrera) cmbCarrera.getSelectedItem();
        Profesor profesor = (Profesor) cmbProfesor.getSelectedItem();
        if (nombre.isEmpty() || carrera == null || profesor == null) {
            JOptionPane.showMessageDialog(this,
                    "Completá el nombre y elegí una carrera y un profesor.");
            return;
        }
        controladora.actualizarMateria(seleccionada, nombre, txtCurso.getText().trim(),
                txtCuatrimestre.getText().trim(), carrera, profesor);
        limpiar();
        refrescar();
    }

    private void eliminar() {
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná una materia de la lista.");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Eliminar la materia " + seleccionada.getNombre() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) return;
        try {
            controladora.eliminarMateria(seleccionada);
            limpiar();
            refrescar();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    @Override
    public void refrescar() {
        Carrera carreraSel = (Carrera) cmbCarrera.getSelectedItem();
        Profesor profesorSel = (Profesor) cmbProfesor.getSelectedItem();

        cmbCarrera.setModel(new DefaultComboBoxModel<>(controladora.listarCarreras().toArray(new Carrera[0])));
        cmbProfesor.setModel(new DefaultComboBoxModel<>(controladora.listarProfesores().toArray(new Profesor[0])));

        if (carreraSel != null) cmbCarrera.setSelectedItem(carreraSel);
        if (profesorSel != null) cmbProfesor.setSelectedItem(profesorSel);

        modeloLista.clear();
        controladora.listarMaterias().forEach(modeloLista::addElement);
    }
}
