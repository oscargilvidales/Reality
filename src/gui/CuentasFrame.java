package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

@SuppressWarnings("serial")
class CuentasFrame extends JFrame {
    private JFrame mainMenuFrame;
    private String dni;

    public CuentasFrame(JFrame mainMenuFrame, Image backgroundImage, Image logoImage, String dni) {
        super("Cuentas");
        this.mainMenuFrame = mainMenuFrame;
        this.dni = dni;
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
        gbc.insets = new Insets(10, 10, 10, 10); // Añadir margen entre los botones

        // Leer el archivo y generar los botones de cuenta
        List<JButton> cuentaButtons = generateCuentaButtons(dni);
        for (JButton cuentaButton : cuentaButtons) {
            gbc.weighty = 0.1; // Espacio vertical distribuido equitativamente entre botones de cuenta
            backgroundPanel.add(cuentaButton, gbc);
            gbc.gridy++;
        }

        // Agregar los botones a la ventana de cuentas
        JButton abrirCuentaButton = new JButton("Abrir Cuenta");
        JButton borrarCuentaButton = new JButton("Borrar Cuenta");
        JButton volverButton = new JButton("Volver");

        // Configurar el tamaño y la fuente de los botones
        Dimension buttonSize = new Dimension(200, 50);
        abrirCuentaButton.setPreferredSize(buttonSize);
        borrarCuentaButton.setPreferredSize(buttonSize);
        volverButton.setPreferredSize(buttonSize);

        // ActionListener para el botón "Abrir Cuenta"
        abrirCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                NewAccountFrame newAccountFrame = new NewAccountFrame(CuentasFrame.this, backgroundImage, logoImage, dni);
                newAccountFrame.setVisible(true);
            }
        });

        // ActionListener para el botón "Borrar Cuenta"
        borrarCuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cuentaABorrar = JOptionPane.showInputDialog(CuentasFrame.this, "Ingrese el nombre de la cuenta a borrar:");
                if (cuentaABorrar != null && !cuentaABorrar.trim().isEmpty()) {
                    boolean cuentaBorrada = borrarCuenta(dni, cuentaABorrar.trim());
                    if (cuentaBorrada) {
                        JOptionPane.showMessageDialog(CuentasFrame.this, "Operación realizada. Un agente se pondrá en contacto para reorganizar sus cuentas.");
                        dispose();
                        CuentasFrame cuentasFrame = new CuentasFrame(mainMenuFrame, backgroundImage, logoImage, dni);
                        cuentasFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(CuentasFrame.this, "La cuenta no existe.");
                    }
                }
            }
        });

        // ActionListener para el botón "Volver"
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                mainMenuFrame.setVisible(true); // Muestra la ventana principal nuevamente
            }
        });

        // Agregar los botones al panel de contenido
        gbc.weighty = 0.0; // Restablecer weighty para los botones de control
        gbc.gridy++;
        backgroundPanel.add(abrirCuentaButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(borrarCuentaButton, gbc);
        gbc.gridy++;
        backgroundPanel.add(volverButton, gbc);

        setContentPane(backgroundPanel);
    }

    private List<JButton> generateCuentaButtons(String dni) {
        List<JButton> buttons = new ArrayList<>();
        String filePath = "cuentas/" + dni + ".txt";
        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            System.err.println("Usuario " + dni + " no tiene cuentas");
            return buttons;
        }
        System.err.println("Usuario " + dni + " tiene cuentas");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String cuentaNombre = null;
            double saldo = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\"") && line.endsWith("\" : {")) {
                    if (cuentaNombre != null) {
                        addButton(buttons, cuentaNombre, saldo);
                    }
                    cuentaNombre = line.substring(1, line.indexOf("\" : {"));
                } else if (line.startsWith("saldo : ")) {
                    saldo = Double.parseDouble(line.substring(8, line.indexOf('€')));
                }
            }

            if (cuentaNombre != null) {
                addButton(buttons, cuentaNombre, saldo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return buttons;
    }

    private void addButton(List<JButton> buttons, String cuentaNombre, double saldo) {
        String buttonText = String.format("%s - Saldo: %.2f€", cuentaNombre, saldo);
        JButton cuentaButton = new JButton(buttonText);
        cuentaButton.setPreferredSize(new Dimension(600, 50));
        buttons.add(cuentaButton);
    }

    private boolean borrarCuenta(String dni, String cuentaNombre) {
        String filePath = "cuentas/" + dni + ".txt";
        File file = new File(filePath);
        boolean cuentaEncontrada = false;

        if (!file.exists() || file.length() == 0) {
            return false;
        }

        try {
            List<String> lines = new ArrayList<>(Files.readAllLines(file.toPath()));
            List<String> updatedLines = new ArrayList<>();
            String cuentaStart = "\"" + cuentaNombre + "\" : {";
            boolean deleting = false;

            for (String line : lines) {
                if (line.trim().equals(cuentaStart)) {
                    deleting = true;
                    cuentaEncontrada = true;
                }
                if (!deleting) {
                    updatedLines.add(line);
                }
                if (deleting && line.trim().equals("}")) {
                    deleting = false;
                }
            }

            if (cuentaEncontrada) {
                Files.write(file.toPath(), updatedLines);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return cuentaEncontrada;
    }
}
