package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MapCreationGUI extends JFrame {

    private JTextField sizeField;
    private JTextField densityField;
    private JComboBox<String> typeComboBox;

    public MapCreationGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Map Creation");

        sizeField = new JTextField(5);
        densityField = new JTextField(5);
        String[] types = {"direcional", "bidirecional"};
        typeComboBox = new JComboBox<>(types);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(new JLabel("Map Size:"));
        panel.add(sizeField);
        panel.add(new JLabel("Type:"));
        panel.add(typeComboBox);
        panel.add(new JLabel("Edge Density:"));
        panel.add(densityField);

        JButton createMapButton = new JButton("Create Map");
        createMapButton.addActionListener(e -> createMap());
        panel.add(createMapButton);

        add(panel);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void createMap() {
        try {
            int size = Integer.parseInt(sizeField.getText());
            String type = (String) typeComboBox.getSelectedItem();
            float density = Float.parseFloat(densityField.getText());

            // Restante da lógica para criar o mapa (semelhante ao código original)

            System.out.println("Mapa criado!");
            dispose(); // Fechar a janela após a criação do mapa
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para o tamanho e densidade.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MapCreationGUI().setVisible(true));
    }
}
