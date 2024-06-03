package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// Clase AjustesFrame
class AjustesFrame extends JFrame {
    private JFrame menuFrame;
    private String dni;
    private Image backgroundImage;
    private Image logoImage;

    public AjustesFrame(JFrame menuFrame, Image backgroundImage, String dni, Image logoImage) {
        super("Ajustes");
        this.menuFrame = menuFrame;
        this.dni = dni;
        this.backgroundImage = backgroundImage;
        this.logoImage = logoImage;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setIconImage(logoImage);

        // Panel de contenido con el mismo fondo de pantalla
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout());

        // Crear un GridBagConstraints para centrar y ajustar el tamaño de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Incrementar el tamaño de la fuente de los botones
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Dimension buttonSize = new Dimension(200, 50); // Cambia el tamaño según sea necesario

        JButton verPerfilButton = new JButton("Ver Perfil");
        JButton seguridadButton = new JButton("Seguridad");
        JButton volverButton = new JButton("Volver");

        // Configurar el tamaño y la fuente de los botones
        verPerfilButton.setPreferredSize(buttonSize);
        seguridadButton.setPreferredSize(buttonSize);
        volverButton.setPreferredSize(buttonSize);
        verPerfilButton.setFont(buttonFont);
        seguridadButton.setFont(buttonFont);
        volverButton.setFont(buttonFont);

        // ActionListener para el botón "Ver Perfil"
        verPerfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPerfilUsuario();
            }
        });

        // ActionListener para el botón "Volver"
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                menuFrame.setVisible(true); // Muestra la ventana principal nuevamente
            }
        });

        // Agregar botones al panel de contenido
        gbc.gridy++;
        backgroundPanel.add(verPerfilButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(seguridadButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(volverButton, gbc);

        setContentPane(backgroundPanel);
    }

    private void mostrarPerfilUsuario() {
        String filePath = "usuarios/" + dni + ".txt";
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder userInfo = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    userInfo.append(line).append("\n");
                }
                JOptionPane.showMessageDialog(this, userInfo.toString(), "Perfil de Usuario", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Archivo de usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}