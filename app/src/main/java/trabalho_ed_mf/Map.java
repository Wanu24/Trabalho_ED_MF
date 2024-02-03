package trabalho_ed_mf;

import ClassImplementation.Network;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.*;

/**
 * Representa o mapa do jogo, que contém uma rede de localizações ligadas.
 */
public class Map {
    /** Rede de localizações ligadas. */
    private Network<Location> network;
    /** Tamanho do mapa. */
    int size;

    /**
     * Construtor que inicializa o mapa com uma nova rede e tamanho zero.
     */
    public Map() {
        this.network = new Network<Location>();
        this.size = 0;
    }

    /**
     * Define o tamanho do mapa.
     *
     * @param size O tamanho do mapa a ser definido.
     */
    public void setSize(int size) {
        this.size = size;
    }

     /**
     * Obtém o tamanho do mapa.
     *
     * @return O tamanho do mapa.
     */
    public int getSize() {
        return size;
    }

    /**
     * Adiciona uma localização à rede do mapa.
     *
     * @param local A localização a ser adicionada.
     */
    public void addLocal(Location local) {
        network.addVertex(local);
        System.out.println("Vertex added: " + local.getIndex());
    }

    /**
     * Remove uma localização da rede do mapa.
     *
     * @param local A localização a ser removida.
     */
    public void removeLocal(Location local) {
        network.removeVertex(local);
        System.out.println("Vertex removed: " + local.getIndex());
    }
    /**
     * Obtém um array de todas as localizações no mapa.
     *
     * @return Um array de localizações no mapa.
     */
    public Location[] getLocations() {
        Location[] location = new Location[network.size()];
        for (int i = 0; i < network.size(); i++) {
            location[i] = network.getVertex(i);
        }
        return location;
    }

    /**
     * Obtém um array de índices das localizações no mapa.
     *
     * @return Um array de índices das localizações no mapa.
     */
    public Integer[] getVertices() {
        Integer[] location = new Integer[network.size()];
        for (int i = 0; i < network.size(); i++) {
            location[i] = network.getVertex(i).getIndex();
        }
        return location;
    }

    /**
     * Importa um mapa a partir de um arquivo JSON.
     *
     * @param file O caminho do arquivo JSON contendo as informações do mapa.
     */
    public void importMap(String file) {
        try (FileReader fileReader = new FileReader(file)) {
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONObject json = new JSONObject(tokener);

            // Read nodes from JSON
            JSONArray nodesArray = json.getJSONArray("Locations");
            for (int i = 0; i < nodesArray.length(); i++) {
                JSONObject nodeJson = nodesArray.getJSONObject(i);
                int nodeId = nodeJson.getInt("id");
                // Create your Locations object and add it to the graph
                Location location = new Location(nodeId); // Replace this with your actual logic
                this.network.addVertex(location);
            }

            // Read edges from JSON
            JSONArray edgesArray = json.getJSONArray("edges");
            for (int i = 0; i < edgesArray.length(); i++) {
                JSONObject edgeJson = edgesArray.getJSONObject(i);
                int source = edgeJson.getInt("source");
                int target = edgeJson.getInt("target");
                double weight = edgeJson.getDouble("weight");
                // Add the edge to the graph
                this.network.addEdge(source, target, weight);
            }

            System.out.println("Map successfully imported from " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exporta o mapa para um arquivo JSON.
     */
    public void exportMap() {
        JSONObject json = new JSONObject();

        // Add nodes to JSON
        JSONArray nodesArray = new JSONArray();
        for (Location location : getLocations()) {
            JSONObject nodeJson = new JSONObject();
            nodeJson.put("id", location.getIndex());
            nodesArray.put(nodeJson);
        }
        json.put("Locations", nodesArray);

        // Add edges to JSON (adjacency matrix)
        JSONArray edgesArray = new JSONArray();
        double[][] adjacencyMatrix = this.network.getAdjMatrix();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] > 0 && adjacencyMatrix[i][j] < 16) {
                    JSONObject edgeJson = new JSONObject();
                    edgeJson.put("source", i);
                    edgeJson.put("target", j);
                    edgeJson.put("weight", adjacencyMatrix[i][j]);
                    edgesArray.put(edgeJson);
                }
            }
        }
        json.put("edges", edgesArray);

        // Write the JSON to the file
        try (FileWriter file = new FileWriter("map.json")) {
            file.write(json.toString());
            System.out.println("Map successfully exported to map.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém a rede de localizações do mapa.
     *
     * @return A rede de localizações do mapa.
     */
    public Network<Location> getNetwork() {
        return network;
    }
}

