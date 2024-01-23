package GUI;

import trabalho_ed_mf.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Uma classe main GUI para configurações de jogo 
 */
public class GameGUI extends JFrame {

    private JTextField player1NameField;
    private JTextField player2NameField;
    private JComboBox<String> numBotsComboBox;

    /**
     * Construtor para criar uma janela de configurações de jogo.
     */
    public GameGUI() {
        initComponents();
    }

    /**
     * Inicializa os componentes da janela.
     */
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

    /**
     * Abre um diálogo para criação de mapa.
     */
    private void openMapCreationDialog() {
        MapCreationDialog mapDialog = new MapCreationDialog(this);
        mapDialog.setVisible(true);
    }

    /**
     * Define os detalhes do mapa.
     * @param size O tamanho do mapa.
     * @param type O tipo de mapa.
     * @param density A densidade do mapa.
     */
    public void setMapDetails(int size, String type, float density) {
        System.out.println("Size: " + size);
        System.out.println("Type: " + type);
        System.out.println("Density: " + density);
    }

    /**
     * Abre uma janela de configurações de jogo.
     * @param args argumentos de linha de comando.
     */
    public class MapCreationDialog extends JDialog {

        private JTextField sizeField;
        private JTextField densityField;
        private JComboBox<String> typeComboBox;
        private GameGUI parentGameGUI;

        /**
         * Construtor para criar uma janela de criação de mapa.
         * @param parent O GameGUI pai.
         */
        public MapCreationDialog(GameGUI parent) {
            super(parent, "Map Creation", true);
            parentGameGUI = parent;
            initComponents();
        }

        /**
         * Inicializa os componentes da janela.
         */
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
            setLocationRelativeTo(parentGameGUI);
        }

        /**
         * Cria o mapa com os detalhes fornecidos.
         */
        private void createMap() {
            try {
                int size = Integer.parseInt(sizeField.getText());
                String type = (String) typeComboBox.getSelectedItem();
                float density = Float.parseFloat(densityField.getText());

                parentGameGUI.setMapDetails(size, type, density);

                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para o tamanho e densidade.");
            }
        }
    }
}