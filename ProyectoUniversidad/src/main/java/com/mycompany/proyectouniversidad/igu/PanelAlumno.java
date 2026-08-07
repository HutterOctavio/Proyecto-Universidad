package com.mycompany.proyectouniversidad.igu;

import com.mycompany.proyectouniversidad.logica.Alumno;
import com.mycompany.proyectouniversidad.logica.Controladora;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelAlumno extends JPanel implements Refrescable {

    private final Controladora controladora;
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtLegajo = new JTextField();
    private final JComboBox<String> cmbSexo = new JComboBox<>(new String[]{"Masculino", "Femenino"});
    private final JTextField txtFechaNac = new JTextField("dd/MM/aaaa");
    private final DefaultListModel<Object> modeloLista = new DefaultListModel<>();
    private final JList<Object> lista = new JList<>(modeloLista);
    private Alumno seleccionado;

    public PanelAlumno(Controladora controladora) {
        this.controladora = controladora;
        construirUI();
        refrescar();
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Apellido:"));
        form.add(txtApellido);
        form.add(new JLabel("Legajo:"));
        form.add(txtLegajo);
        form.add(new JLabel("Sexo:"));
        form.add(cmbSexo);
        form.add(new JLabel("Fecha de nacimiento:"));
        form.add(txtFechaNac);

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
        if (!(valor instanceof Alumno alumno)) {
            seleccionado = null;
            return;
        }
        seleccionado = alumno;
        txtNombre.setText(alumno.getNombre());
        txtApellido.setText(alumno.getApellido());
        txtLegajo.setText(alumno.getLegajo());
        cmbSexo.setSelectedItem(alumno.getSexo());
        if (alumno.getFechaNac() != null) {
            txtFechaNac.setText(new SimpleDateFormat("dd/MM/yyyy").format(alumno.getFechaNac()));
        }
    }

    private void limpiar() {
        seleccionado = null;
        lista.clearSelection();
        txtNombre.setText("");
        txtApellido.setText("");
        txtLegajo.setText("");
        cmbSexo.setSelectedIndex(0);
        txtFechaNac.setText("dd/MM/aaaa");
    }

    private Date leerFecha() throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(txtFechaNac.getText().trim());
    }

    private boolean datosValidos() {
        if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()
                || txtLegajo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completá nombre, apellido y legajo.");
            return false;
        }
        return true;
    }

    private void guardar() {
        if (!datosValidos()) return;
        try {
            controladora.guardarAlumno(txtNombre.getText().trim(), txtApellido.getText().trim(),
                    txtLegajo.getText().trim(), (String) cmbSexo.getSelectedItem(), leerFecha());
            limpiar();
            refrescar();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Fecha inválida. Usá el formato dd/MM/aaaa.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    private void actualizar() {
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná un alumno de la lista.");
            return;
        }
        if (!datosValidos()) return;
        try {
            controladora.actualizarAlumno(seleccionado, txtNombre.getText().trim(),
                    txtApellido.getText().trim(), txtLegajo.getText().trim(),
                    (String) cmbSexo.getSelectedItem(), leerFecha());
            limpiar();
            refrescar();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Fecha inválida. Usá el formato dd/MM/aaaa.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminar() {
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná un alumno de la lista.");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Eliminar a " + seleccionado.getNombre() + " " + seleccionado.getApellido() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) return;
        try {
            controladora.eliminarAlumno(seleccionado);
            limpiar();
            refrescar();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    @Override
    public void refrescar() {
        modeloLista.clear();
        controladora.listarAlumnos().forEach(modeloLista::addElement);
    }
}
