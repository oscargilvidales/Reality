package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Operaciones extends JFrame {
    private JFrame mainWindow;

    public Operaciones(JFrame mainWindow, Image backgroundImage, Image logoImage) {
        super("Operaciones");
        this.mainWindow = mainWindow;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setIconImage(logoImage);
        
        /*
        // Escalar el logo
        ImageIcon bizumIcon = new ImageIcon("bizum.png"); // Cargar el icono de Bizum
        Image scaledBizumImage = bizumIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Escalar la imagen
        ImageIcon scaledBizumIcon = new ImageIcon(scaledBizumImage); // Crear un ImageIcon con la imagen escalada
		*/
        // Panel de contenido con el mismo fondo de pantalla
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout());

        // Crear un GridBagConstraints para centrar y ajustar el tamaño de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Botones de operaciones
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Dimension buttonSize = new Dimension(200, 50); // Cambia el tamaño según sea necesario

        JButton transferenciaButton = new JButton("Transferencia");
        transferenciaButton.setPreferredSize(buttonSize);
        transferenciaButton.setFont(buttonFont);
        /*
        JButton bizumButton = new JButton("Bizum", scaledBizumIcon); // Crear un JButton con texto y logo para Bizum
        bizumButton.setPreferredSize(buttonSize);
        bizumButton.setFont(buttonFont);
         */
        JButton cambioDivisa = new JButton("Cambio Divisa");
        cambioDivisa.setPreferredSize(buttonSize);
        cambioDivisa.setFont(buttonFont);
        
        JButton historialButton = new JButton("Historial");
        historialButton.setPreferredSize(buttonSize);
        historialButton.setFont(buttonFont);

        // ActionListener para el botón "Volver"
        JButton volverButton = new JButton("Volver");
        volverButton.setPreferredSize(buttonSize);
        volverButton.setFont(buttonFont);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                mainWindow.setVisible(true); // Muestra la ventana principal nuevamente
            }
        });

        // Agregar botones al panel de contenido
        gbc.gridy++;
        backgroundPanel.add(transferenciaButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(cambioDivisa, gbc);
        gbc.gridy++;
        backgroundPanel.add(historialButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(volverButton, gbc);

        setContentPane(backgroundPanel);
    }
}
