package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
	static Image logoImage;
    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Start Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        // Ruta de las imágenes
        final String backgroundImagePath = "gui/fondo.jpg";
        String logoImagePath = "logo.png";

        // Crear el panel de contenido con un fondo de pantalla
        Image backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File(backgroundImagePath));
            System.out.println("Imagen de fondo cargada correctamente.");
        } catch (IOException e) {
            System.err.println("Error: No se pudo cargar la imagen de fondo.");
            e.printStackTrace();
        }
        
        final BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        // Añadir el logo
        JLabel logoLabel = new JLabel();
        try {
            logoImage = ImageIO.read(new File(logoImagePath));
            // Redimensionar el logo
            int logoWidth = logoImage.getWidth(null);
            int logoHeight = logoImage.getHeight(null);
            int newWidth = logoWidth / 2; // Dividir el ancho original por 2
            int newHeight = logoHeight / 2; // Dividir la altura original por 2
            logoImage = logoImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            frame.setIconImage(logoImage);
            logoLabel.setIcon(new ImageIcon(logoImage));
            System.out.println("Imagen del logo cargada correctamente.");
        } catch (IOException e) {
            System.err.println("Error: No se pudo cargar la imagen del logo.");
            e.printStackTrace();
            logoImage = null;
        }
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundPanel.add(Box.createVerticalGlue());
        backgroundPanel.add(logoLabel);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Hacer el panel transparente
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Cambiar a BoxLayout vertical

        // Crear los botones con tamaño ajustado
        JButton button1 = new JButton("Iniciar Sesión");
        JButton button2 = new JButton("Registrar Usuario"); // Nuevo botón "Registrar Usuario"

        // Crear una copia final de backgroundImage para accederla dentro del ActionListener
        final Image finalBackgroundImage = backgroundImage;

        // Añadir ActionListener al botón "Iniciar Sesión"
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                frame.dispose();
                // Abrir nueva ventana de inicio de sesión
                VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(frame, finalBackgroundImage, logoImage);
                ventanaInicioSesion.setVisible(true);
            }
        });

        // Añadir ActionListener al botón "Registrar Usuario"
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                frame.dispose();
                // Abrir nueva ventana de registro de usuario
                VentanaRegistroUsuario ventanaRegistroUsuario = new VentanaRegistroUsuario(frame, finalBackgroundImage, logoImage);
                ventanaRegistroUsuario.setVisible(true);
            }
        });

        // Ajustar el tamaño de los botones
        Dimension buttonSize = new Dimension(150, 60); // Cambia este valor según sea necesario
        button1.setMaximumSize(buttonSize);
        button2.setMaximumSize(buttonSize);

        // Centrar los botones horizontalmente
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir los botones al panel con espacio entre ellos
        buttonPanel.add(button1);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(button2);

        // Añadir el panel de botones al panel de contenido
        backgroundPanel.add(Box.createVerticalGlue()); // Espacio flexible antes de los botones
        backgroundPanel.add(buttonPanel);
        backgroundPanel.add(Box.createVerticalGlue()); // Espacio flexible después de los botones

        // Añadir el panel de contenido al marco
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}




