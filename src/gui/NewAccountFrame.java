package gui;

import java.awt.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class NewAccountFrame extends JFrame {

	private CuentasFrame menuCuentas;

    public NewAccountFrame(CuentasFrame menuCuentas, Image backgroundImage, Image logoImage, String dni) {
        super("New Account");
        this.menuCuentas = menuCuentas;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setIconImage(logoImage);
        setLocationRelativeTo(null);

        // Panel de contenido con el mismo fondo de pantalla
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout());

        // Crear un GridBagConstraints para centrar y ajustar el tamaño de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Campo de texto para el nombre de la cuenta
        JLabel nameLabel = new JLabel("Nombre de la cuenta:");
        JTextField nameField = new JTextField(20);
        gbc.gridy++;
        backgroundPanel.add(nameLabel, gbc);
        gbc.gridy++;
        backgroundPanel.add(nameField, gbc);

        // Botones para la ventana de nueva cuenta
        JButton cancelarButton = new JButton("Cancelar");
        JButton solicitarButton = new JButton("Solicitar");

        // Configurar el tamaño y la fuente de los botones
        Dimension buttonSize = new Dimension(200, 50);
        cancelarButton.setPreferredSize(buttonSize);
        solicitarButton.setPreferredSize(buttonSize);

        // ActionListener para el botón "Cancelar"
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                menuCuentas.setVisible(true); // Muestra la ventana principal nuevamente
            }
        });
        
        solicitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(nameField.getText().length() <= 5)
            	{
                    JOptionPane.showMessageDialog(null, "Nombre de cuenta demasiado corto (minimo 6 caracteres)", "Error", JOptionPane.ERROR_MESSAGE);
            	}else
            	{
                    JOptionPane.showMessageDialog(null, "Se ha enviado la solicitud a tu sucursal.\n En cuanto tu cuenta este lista recibiras una notificación", "Exito", JOptionPane.NO_OPTION);
                    dispose(); // Cierra la ventana actual
                    menuCuentas.setVisible(true);
            	}
            }
        });

        // Agregar botones al panel de contenido
        gbc.gridy++;
        backgroundPanel.add(solicitarButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(cancelarButton, gbc);

        setContentPane(backgroundPanel);
    }
}