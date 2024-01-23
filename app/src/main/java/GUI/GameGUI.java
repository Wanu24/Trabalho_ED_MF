package GUI;

import trabalho_ed_mf.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {

    private JTextField player1NameField;
    private JTextField player2NameField;
    private JComboBox<String> numBotsComboBox;

    public GameGUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game GUI");

        player1NameField = new JTextField(10);
        player2NameField = new JTextField(10);

        String[] numBotsOptions = {"1", "2", "3", "4"};
        numBotsComboBox = new JComboBox<>(numBotsOptions);

        JButton createMapButton = new JButton("Create Map");
        createMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMapCreationDialog();
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Player 1 Name:"));
        add(player1NameField);
        add(new JLabel("Player 2 Name:"));
        add(player2NameField);
        add(new JLabel("Number of Bots:"));
        add(numBotsComboBox);
        add(createMapButton);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void openMapCreationDialog() {
        MapCreationDialog mapDialog = new MapCreationDialog(this);
        mapDialog.setVisible(true);
    }

    public void setMapDetails(int size, String type, float density) {
        // Adicione aqui a lógica para criar o mapa com os detalhes fornecidos
        // (semelhante ao código original)
        System.out.println("Size: " + size);
        System.out.println("Type: " + type);
        System.out.println("Density: " + density);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameGUI().setVisible(true));
    }
}

class MapCreationDialog extends JDialog {

    private JTextField sizeField;
    private JTextField densityField;
    private JComboBox<String> typeComboBox;
    private GameGUI parentGameGUI;

    public MapCreationDialog(GameGUI parent) {
        super(parent, "Map Creation", true);
        parentGameGUI = parent;
        initComponents();
    }

    private void initComponents() {
        sizeField = new JTextField(5);
        densityField = new JTextField(5);
        String[] types = {"direcional", "bidirecional"};
        typeComboBox = new JComboBox<>(types);

        JButton createMapButton = new JButton("Create Map");
        createMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMap();
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Map Size:"));
        add(sizeField);
        add(new JLabel("Type:"));
        add(typeComboBox);
        add(new JLabel("Edge Density:"));
        add(densityField);
        add(createMapButton);

        pack();
        setLocationRelativeTo(parentGameGUI); // Center the dialog relative to the GameGUI
    }

    private void createMap() {
        try {
            int size = Integer.parseInt(sizeField.getText());
            String type = (String) typeComboBox.getSelectedItem();
            float density = Float.parseFloat(densityField.getText());

            // Chamando o método de GameGUI para definir os detalhes do mapa
            parentGameGUI.setMapDetails(size, type, density);

            // Fechar o diálogo após criar o mapa
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para o tamanho e densidade.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI gameGUI = new GameGUI();
            gameGUI.setVisible(true);

            // Simula a criação do mapa após a criação do jogo
            MapCreationDialog mapDialog = new MapCreationDialog(gameGUI);
            mapDialog.setVisible(true);
        });
    }
}
