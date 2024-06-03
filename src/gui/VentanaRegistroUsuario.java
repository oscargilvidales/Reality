package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

class VentanaRegistroUsuario extends JFrame {
	
	private JFrame ventanaPrincipal;
	
	public VentanaRegistroUsuario(JFrame ventanaPrincipal, Image backgroundImage, Image logoImage) {
        this.ventanaPrincipal = ventanaPrincipal;
        // Configuración de la ventana
        setTitle("Registro de Usuario");
        setIconImage(logoImage);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar en la pantalla
        // Crear el panel de contenido con un fondo de pantalla transparente
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout()); // Utilizaremos GridBagLayout para centrar los componentes

        // Crear y agregar los componentes para el registro de usuario aquí
        JPanel formPanel = new JPanel(new GridLayout(0, 4));

        // Aumentar el tamaño de la fuente de los campos de texto y etiquetas
        Font labelFont = new Font("Arial", Font.PLAIN, 18);

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setFont(labelFont);
        nameLabel.setOpaque(false); // Establecer la opacidad en falso
        formPanel.add(nameLabel);
        JTextField nombreField = new JTextField();
        nombreField.setFont(labelFont);
        nombreField.setOpaque(false);
        formPanel.add(nombreField);
        formPanel.setOpaque(false);
        
        JLabel documentoIDLabel = new JLabel("Documento ID:");
        documentoIDLabel.setFont(labelFont);
        documentoIDLabel.setOpaque(false);
        formPanel.add(documentoIDLabel);
        JTextField documentoIDField = new JTextField();
        documentoIDField.setFont(labelFont);
        documentoIDField.setOpaque(false);
        formPanel.add(documentoIDField);

        JLabel apellido1Label = new JLabel("Apellido1:");
        apellido1Label.setFont(labelFont);
        apellido1Label.setOpaque(false);
        formPanel.add(apellido1Label);
        JTextField apellido1Field = new JTextField();
        apellido1Field.setFont(labelFont);
        apellido1Field.setOpaque(false);
        formPanel.add(apellido1Field);
        
        JLabel direccionLabel = new JLabel("Dirección:");
        direccionLabel.setFont(labelFont);
        direccionLabel.setOpaque(false);
        formPanel.add(direccionLabel);
        JTextField direccionField = new JTextField();
        direccionField.setFont(labelFont);
        direccionField.setOpaque(false);
        formPanel.add(direccionField);

        JLabel apellido2Label = new JLabel("Apellido2:");
        apellido2Label.setFont(labelFont);
        apellido2Label.setOpaque(false);
        formPanel.add(apellido2Label);
        JTextField apellido2Field = new JTextField();
        apellido2Field.setFont(labelFont);
        apellido2Field.setOpaque(false);
        formPanel.add(apellido2Field);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setFont(labelFont);
        telefonoLabel.setOpaque(false);
        formPanel.add(telefonoLabel);
        JTextField telefonoField = new JTextField();
        telefonoField.setFont(labelFont);
        telefonoField.setOpaque(false);
        formPanel.add(telefonoField);

        JLabel codigoPostalLabel = new JLabel("Código Postal:");
        codigoPostalLabel.setFont(labelFont);
        codigoPostalLabel.setOpaque(false);
        formPanel.add(codigoPostalLabel);
        JTextField codigoPostalField = new JTextField();
        codigoPostalField.setFont(labelFont);
        codigoPostalField.setOpaque(false);
        formPanel.add(codigoPostalField);

        JLabel provinciaLabel = new JLabel("Provincia:");
        provinciaLabel.setFont(labelFont);
        provinciaLabel.setOpaque(false);
        formPanel.add(provinciaLabel);
        JTextField provinciaField = new JTextField();
        provinciaField.setFont(labelFont);
        provinciaField.setOpaque(false);
        formPanel.add(provinciaField);

        JLabel ciudadLabel = new JLabel("Ciudad:");
        ciudadLabel.setFont(labelFont);
        ciudadLabel.setOpaque(false);
        formPanel.add(ciudadLabel);
        JTextField ciudadField = new JTextField();
        ciudadField.setFont(labelFont);
        ciudadField.setOpaque(false);
        formPanel.add(ciudadField);

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaLabel.setFont(labelFont);
        contraseñaLabel.setOpaque(false);
        formPanel.add(contraseñaLabel);
        JTextField contraseñaField = new JPasswordField();
        contraseñaField.setFont(labelFont);
        contraseñaField.setOpaque(false);
        formPanel.add(contraseñaField);
        
        JLabel confirmaLabel = new JLabel("Confirma contraseña:");
        confirmaLabel.setFont(labelFont);
        confirmaLabel.setOpaque(false);
        formPanel.add(confirmaLabel);
        JTextField confrimaField = new JPasswordField();
        confrimaField.setFont(labelFont);
        confrimaField.setOpaque(false);
        formPanel.add(confrimaField);


        // Botón "Aceptar" para registrar el usuario
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.setFont(new Font("Arial", Font.BOLD, 18));
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si algún campo está vacío
            	// Verificar si algún campo está vacío
                if (nombreField.getText().isEmpty() || apellido1Field.getText().isEmpty() || documentoIDField.getText().isEmpty() ||
                        telefonoField.getText().isEmpty() || direccionField.getText().isEmpty() || codigoPostalField.getText().isEmpty() ||
                        provinciaField.getText().isEmpty() || ciudadField.getText().isEmpty() || contraseñaField.getText().isEmpty() ||
                        confrimaField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!contraseñaField.getText().equals(confrimaField.getText())) {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Todos los campos están completos, puedes proceder con el registro de usuario
                    String documentoID = documentoIDField.getText();
                    File directory = new File("usuarios");
                    if (!directory.exists()) {
                        directory.mkdir(); // Crea el directorio si no existe
                    }
                    File file = new File(directory, documentoID + ".txt");
                    if (file.exists()) {
                        JOptionPane.showMessageDialog(null, "Los datos introducidos corresponden a un usuario existente.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            // Crear el archivo y escribir los datos
                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                            writer.write("Documento ID: " + documentoID);
                            writer.newLine();
                            writer.write("Contraseña: " + contraseñaField.getText());
                            writer.newLine();
                            writer.write("Nombre: " + nombreField.getText());
                            writer.newLine();
                            writer.write("Apellido1: " + apellido1Field.getText());
                            writer.newLine();
                            writer.write("Apellido2: " + apellido2Field.getText());
                            writer.newLine();
                            writer.write("Dirección: " + direccionField.getText());
                            writer.newLine();
                            writer.write("Teléfono: " + telefonoField.getText());
                            writer.newLine();
                            writer.write("Código Postal: " + codigoPostalField.getText());
                            writer.newLine();
                            writer.write("Provincia: " + provinciaField.getText());
                            writer.newLine();
                            writer.write("Ciudad: " + ciudadField.getText());
                            writer.close();
                            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al intentar crear el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        // Agregar los componentes al panel de fondo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(Box.createVerticalGlue(), gbc); // Espacio flexible
        gbc.gridy++;
        backgroundPanel.add(formPanel, gbc);
        gbc.gridy++;
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);
        gbc.gridy++;
        backgroundPanel.add(aceptarButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(Box.createVerticalGlue(), gbc); // Espacio flexible
        
        JButton volverButton = new JButton("Volver");
        volverButton.setFont(new Font("Arial", Font.BOLD, 18));
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Oculta la ventana de registro de usuario
                setVisible(false);
                // Muestra la ventana principal
                ventanaPrincipal.setVisible(true);
            }
        });
        gbc.gridy++;
        backgroundPanel.add(volverButton, gbc);

        setContentPane(backgroundPanel);
        
        
    }
}

