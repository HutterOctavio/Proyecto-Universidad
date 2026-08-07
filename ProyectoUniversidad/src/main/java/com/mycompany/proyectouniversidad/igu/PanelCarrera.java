package com.mycompany.proyectouniversidad.igu;

import com.mycompany.proyectouniversidad.logica.Carrera;
import com.mycompany.proyectouniversidad.logica.Controladora;
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

public class PanelCarrera extends JPanel implements Refrescable {

    private final Controladora controladora;
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtDuracion = new JTextField();
    private final JTextField txtCoordinador = new JTextField();
    private final JTextField txtPrecioInscripcion = new JTextField();
    private final JTextField txtPrecioCuota = new JTextField();
    private final DefaultListModel<Object> modeloLista = new DefaultListModel<>();
    private final JList<Object> lista = new JList<>(modeloLista);
    private Carrera seleccionada;

    public PanelCarrera(Controladora controladora) {
        this.controladora = controladora;
        construirUI();
        refrescar();
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Duración (años):"));
        form.add(txtDuracion);
        form.add(new JLabel("Coordinador:"));
        form.add(txtCoordinador);
        form.add(new JLabel("Precio inscripción:"));
        form.add(txtPrecioInscripcion);
        form.add(new JLabel("Precio cuota:"));
        form.add(txtPrecioCuota);

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
        if (!(valor instanceof Carrera carrera)) {
            seleccionada = null;
            return;
        }
        seleccionada = carrera;
        txtNombre.setText(carrera.getNombre());
        txtDuracion.setText(String.valueOf(carrera.getDuracionAnios()));
        txtCoordinador.setText(carrera.getCoordinador());
        txtPrecioInscripcion.setText(String.valueOf(carrera.getPrecioInscripcion()));
        txtPrecioCuota.setText(String.valueOf(carrera.getPrecioCuota()));
    }

    private void limpiar() {
        seleccionada = null;
        lista.clearSelection();
        txtNombre.setText("");
        txtDuracion.setText("");
        txtCoordinador.setText("");
        txtPrecioInscripcion.setText("");
        txtPrecioCuota.setText("");
    }

    private void guardar() {
        try {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completá el nombre de la carrera.");
                return;
            }
            controladora.guardarCarrera(nombre, Integer.parseInt(txtDuracion.getText().trim()),
                    txtCoordinador.getText().trim(), Double.parseDouble(txtPrecioInscripcion.getText().trim()),
                    Double.parseDouble(txtPrecioCuota.getText().trim()));
            limpiar();
            refrescar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duración y precios deben ser números.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    private void actualizar() {
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná una carrera de la lista.");
            return;
        }
        try {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completá el nombre de la carrera.");
                return;
            }
            controladora.actualizarCarrera(seleccionada, nombre, Integer.parseInt(txtDuracion.getText().trim()),
                    txtCoordinador.getText().trim(), Double.parseDouble(txtPrecioInscripcion.getText().trim()),
                    Double.parseDouble(txtPrecioCuota.getText().trim()));
            limpiar();
            refrescar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duración y precios deben ser números.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminar() {
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná una carrera de la lista.");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Eliminar la carrera " + seleccionada.getNombre() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) return;
        try {
            controladora.eliminarCarrera(seleccionada);
            limpiar();
            refrescar();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    @Override
    public void refrescar() {
        modeloLista.clear();
        controladora.listarCarreras().forEach(modeloLista::addElement);
    }
}
