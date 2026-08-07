package com.mycompany.proyectouniversidad.igu;

import com.mycompany.proyectouniversidad.logica.Controladora;
import com.mycompany.proyectouniversidad.logica.Cursada;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelCursada extends JPanel implements Refrescable {

    private final Controladora controladora;
    private final DefaultListModel<Object> modeloLista = new DefaultListModel<>();
    private final JList<Object> listaCursadas = new JList<>(modeloLista);
    private final JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"Regular", "Libre", "Promocionado"});

    public PanelCursada(Controladora controladora) {
        this.controladora = controladora;
        construirUI();
        refrescar();
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));
        add(new JLabel("Cursadas (seleccioná una para operar):"), BorderLayout.NORTH);
        add(new JScrollPane(listaCursadas), BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAsistencia = new JButton("Registrar asistencia (+1)");
        btnAsistencia.addActionListener(e -> registrarAsistencia());
        acciones.add(btnAsistencia);

        acciones.add(new JLabel("Situación final:"));
        acciones.add(cmbEstado);
        JButton btnEstado = new JButton("Guardar situación final");
        btnEstado.addActionListener(e -> cargarSituacion());
        acciones.add(btnEstado);

        JButton btnEliminar = new JButton("Eliminar cursada");
        btnEliminar.addActionListener(e -> eliminar());
        acciones.add(btnEliminar);

        add(acciones, BorderLayout.SOUTH);
    }

    private void eliminar() {
        Cursada c = getSeleccionada();
        if (c == null) return;
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Eliminar esta cursada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) return;
        controladora.eliminarCursada(c);
        refrescar();
    }

    private Cursada getSeleccionada() {
        Cursada c = (Cursada) listaCursadas.getSelectedValue();
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná una cursada de la lista.");
        }
        return c;
    }

    private void registrarAsistencia() {
        Cursada c = getSeleccionada();
        if (c == null) return;
        controladora.registrarAsistencia(c);
        refrescar();
    }

    private void cargarSituacion() {
        Cursada c = getSeleccionada();
        if (c == null) return;
        String estado = (String) cmbEstado.getSelectedItem();
        controladora.cargarSituacionFinal(c, estado);
        refrescar();
    }

    @Override
    public void refrescar() {
        modeloLista.clear();
        controladora.listarCursadas().forEach(modeloLista::addElement);
    }
}
