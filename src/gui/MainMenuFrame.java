package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MenuFrame extends JFrame {
    private JFrame ventanaPrincipal;
    private String nombreCompleto;

    public MenuFrame(JFrame ventanaPrincipal, Image backgroundImage, String dni, String nombreCompleto, Image logoImage2) {
        super("Main Menu");
        this.ventanaPrincipal = ventanaPrincipal;
        this.nombreCompleto = nombreCompleto;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); 
        setIconImage(logoImage2);

        // Panel de contenido con el mismo fondo de pantalla
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout());

        // Crear un GridBagConstraints para centrar y ajustar el tamaño de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Agregar la imagen encima de los botones
        ImageIcon logoIcon = new ImageIcon(logoImage2);
        Image scaledLogoImage = logoIcon.getImage().getScaledInstance(logoImage2.getWidth(null) / 2, logoImage2.getHeight(null) / 2, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogoImage));
        backgroundPanel.add(logoLabel, gbc);

        // Incrementar el tamaño de la fuente de los botones
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        // Botones del menú
        JButton cuentasButton = new JButton("Cuentas");
        JButton operacionesButton = new JButton("Operaciones"); // Nuevo botón "Operaciones"
        JButton ajustesButton = new JButton("Ajustes");
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");

        // Configurar el tamaño y la fuente de los botones
        Dimension buttonSize = new Dimension(200, 50); // Cambia el tamaño según sea necesario
        cuentasButton.setPreferredSize(buttonSize);
        operacionesButton.setPreferredSize(buttonSize); // Establecer el mismo tamaño que otros botones
        ajustesButton.setPreferredSize(buttonSize);
        cerrarSesionButton.setPreferredSize(buttonSize);
        cuentasButton.setFont(buttonFont);
        operacionesButton.setFont(buttonFont); // Aplicar la misma fuente que otros botones
        ajustesButton.setFont(buttonFont);
        cerrarSesionButton.setFont(buttonFont);

        // ActionListener para el botón "Cerrar Sesión"
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                ventanaPrincipal.setVisible(true); // Muestra la ventana principal nuevamente
            }
        });

        // ActionListener para el botón "Cuentas"
        cuentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                dispose();
                // Abrir la ventana de cuentas
                CuentasFrame cuentasFrame = new CuentasFrame(MenuFrame.this, backgroundImage, logoImage2, dni);
                cuentasFrame.setVisible(true);
            }
        });

        // ActionListener para el botón "Operaciones"
        operacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Coloca aquí la lógica para las operaciones
                dispose();
                // Abrir la ventana de cuentas
                Operaciones operaciones = new Operaciones(MenuFrame.this, backgroundImage, logoImage2);
                operaciones.setVisible(true);
            }
        });
        
     // ActionListener para el botón "Ajustes"
        ajustesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AjustesFrame ajustesFrame = new AjustesFrame(MenuFrame.this, backgroundImage,dni , logoImage2);
                ajustesFrame.setVisible(true);
            }
        });

        // Agregar botones al panel de contenido
        gbc.gridy++;
        backgroundPanel.add(cuentasButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(operacionesButton, gbc); // Agregar el botón "Operaciones"
        gbc.gridy++;
        backgroundPanel.add(ajustesButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(cerrarSesionButton, gbc);

        // Agregar el nombre completo del usuario debajo de los botones
        gbc.gridy++;
        JLabel nombreLabel = new JLabel("Bienvenido " + nombreCompleto);
        nombreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        backgroundPanel.add(nombreLabel, gbc);

        setContentPane(backgroundPanel);
    }
}
