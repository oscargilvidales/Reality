package gui;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class VentanaInicioSesion extends JFrame {
    /**
	 * 
	 */
	
	private JFrame ventanaPrincipal;
	
	private static final long serialVersionUID = 1L;

	public VentanaInicioSesion(JFrame ventanaPrincipal, Image backgroundImage, Image logoImage) {
        super("Inicio de Sesión");
        this.ventanaPrincipal = ventanaPrincipal;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setIconImage(logoImage);
        setLocationRelativeTo(null);
        // Panel de contenido con el mismo fondo de pantalla
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        // Etiqueta de título
        JLabel titleLabel = new JLabel("Inicio de Sesión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Cambia la fuente y el tamaño del título
        backgroundPanel.add(titleLabel, gbc);

        // Panel para las casillas de texto
        JPanel textFieldPanel = new JPanel(new GridBagLayout());
        textFieldPanel.setOpaque(false); // Hacer el panel transparente

        // Casilla de texto para DNI
        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Cambia la fuente y el tamaño del texto del DNI
        JTextField dniField = new JTextField(20); // 10 columnas de ancho para el campo de DNI
        dniField.setFont(new Font("Arial", Font.PLAIN, 14)); // Cambia la fuente y el tamaño del campo de texto del DNI
        dniField.setOpaque(false); // Hacer el campo de texto transparente
        dniField.setBackground(new Color(0, 0, 0, 0)); // Hacer el fondo del campo de texto transparente
        gbc.gridy = 0;
        textFieldPanel.add(dniLabel, gbc);
        gbc.gridy = 1;
        textFieldPanel.add(dniField, gbc);

        // Casilla de texto para contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Cambia la fuente y el tamaño del texto de Contraseña
        JPasswordField passwordField = new JPasswordField(20); // 10 columnas de ancho para el campo de contraseña
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14)); // Cambia la fuente y el tamaño del campo de texto de Contraseña
        passwordField.setOpaque(false); // Hacer el campo de texto transparente
        passwordField.setBackground(new Color(0, 0, 0, 0)); // Hacer el fondo del campo de texto transparente
        gbc.gridy = 2;
        textFieldPanel.add(passwordLabel, gbc);
        gbc.gridy = 3;
        textFieldPanel.add(passwordField, gbc);

        // Botón de Aceptar
        JButton acceptButton = new JButton("Aceptar");
        acceptButton.setFont(new Font("Arial", Font.BOLD, 18)); // Cambia la fuente y el tamaño del botón
        gbc.gridy = 4;
        textFieldPanel.add(acceptButton, gbc);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si algún campo está vacío
                if (passwordField.getText().isEmpty() || dniField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Todos los campos están completos, puedes proceder con el registro de usuario
                    // Aquí puedes realizar las acciones necesarias, como guardar los datos en una base de datos, etc.
                    // Por ahora, mostraremos un mensaje indicando que el usuario ha sido registrado exitosamente.
                    JOptionPane.showMessageDialog(null, "Comprobando Datos", "Comprobando", JOptionPane.INFORMATION_MESSAGE);
                    String dni = dniField.getText();
                    String password = new String(passwordField.getPassword());

                    // Comprobar si existe un archivo con el nombre de DNI proporcionado en la carpeta "usuarios"
                    File file = new File("usuarios/" + dni + ".txt");
                    if (file.exists()) {
                        try {
                            // Leer el contenido del archivo
                            BufferedReader reader = new BufferedReader(new FileReader(file));
                            String storedPassword = reader.readLine().split(": ")[1];
                            storedPassword = reader.readLine().split(": ")[1];
                            System.out.println("Contraseña esperada " + storedPassword);

                            // Comparar la contraseña proporcionada con la contraseña almacenada en el archivo
                            if (password.equals(storedPassword)) {
                                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                String nombre = new String();
                                nombre += reader.readLine().split(": ")[1] + " ";
                                nombre += reader.readLine().split(": ")[1] + " ";
                                nombre += reader.readLine().split(": ")[1];
                                reader.close();
                                MenuFrame menuPrincipal = new MenuFrame(ventanaPrincipal, backgroundImage, dni, nombre, logoImage);
                                menuPrincipal.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "Credenciales no válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                                reader.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        gbc.gridy = 1;
        backgroundPanel.add(textFieldPanel, gbc);

        // Añadir el panel de contenido al marco
        
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

        // Agrega el botón "Volver" al panel de fondo
        gbc.gridy++;
        backgroundPanel.add(volverButton, gbc);
        
        setContentPane(backgroundPanel);
    }
}
