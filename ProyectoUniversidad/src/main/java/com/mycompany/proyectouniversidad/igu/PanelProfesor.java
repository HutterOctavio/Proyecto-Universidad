package com.mycompany.proyectouniversidad.igu;

import com.mycompany.proyectouniversidad.logica.Controladora;
import com.mycompany.proyectouniversidad.logica.Profesor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelProfesor extends JPanel implements Refrescable {

    private final Controladora controladora;
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final DefaultListModel<Object> modeloLista = new DefaultListModel<>();
    private final JList<Object> lista = new JList<>(modeloLista);
    private Profesor seleccionado;

    public PanelProfesor(Controladora controladora) {
        this.controladora = controladora;
        construirUI();
        refrescar();
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Apellido:"));
        form.add(txtApellido);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnGuardar = new JButton("Guardar nuevo");
        btnGuardar.addActionListener(e -> guardar());
        JButton btnActualizar = new JButton("Actualizar seleccionado");
        btnActualizar.addActionListener(e -> actualizar());
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        btnEliminar.addActionListener(e -> eliminar());
        JButton btnLimpiar = new JButton("Limpiar / nuevo");
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
        if (!(valor instanceof Profesor profesor)) {
            seleccionado = null;
            return;
        }
        seleccionado = profesor;
        txtNombre.setText(profesor.getNombre());
        txtApellido.setText(profesor.getApellido());
    }

    private void limpiar() {
        seleccionado = null;
        lista.clearSelection();
        txtNombre.setText("");
        txtApellido.setText("");
    }

    private boolean datosValidos() {
        if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completá nombre y apellido.");
            return false;
        }
        return true;
    }

    private void guardar() {
        if (!datosValidos()) return;
        controladora.guardarProfesor(txtNombre.getText().trim(), txtApellido.getText().trim());
        limpiar();
        refrescar();
    }

    private void actualizar() {
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná un profesor de la lista.");
            return;
        }
        if (!datosValidos()) return;
        controladora.actualizarProfesor(seleccionado, txtNombre.getText().trim(), txtApellido.getText().trim());
        limpiar();
        refrescar();
    }

    private void eliminar() {
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná un profesor de la lista.");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Eliminar a " + seleccionado.getNombre() + " " + seleccionado.getApellido() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) return;
        try {
            controladora.eliminarProfesor(seleccionado);
            limpiar();
            refrescar();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    @Override
    public void refrescar() {
        modeloLista.clear();
        controladora.listarProfesores().forEach(modeloLista::addElement);
    }
}
