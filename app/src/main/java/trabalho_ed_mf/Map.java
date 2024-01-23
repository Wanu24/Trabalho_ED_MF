package trabalho_ed_mf;

import ClassImplementation.Network;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.*;

public class Map {
    private Network<Location> network;
    int size;

    public Map() {
        this.network = new Network<Location>();
        this.size = 0;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public void addLocal(Location local) {
        network.addVertex(local);
        System.out.println("Vertex added: " + local.getIndex());
    }

    public void removeLocal(Location local) {
        network.removeVertex(local);
        System.out.println("Vertex removed: " + local.getIndex());
    }

    public void addFlag(int index, Flag flag) {
        network.getVertex(index).setFlag(flag);
        network.getVertex(index).setHasFlag(true);
        System.out.println("Flag added to vertex: " + index);
    }

    public void removeFlag(int index) {
        network.getVertex(index).setFlag(null);
        network.getVertex(index).setHasFlag(false);
        System.out.println("Flag removed from vertex: " + index);
    }

    public Location[] getLocations() {
        Location[] location = new Location[network.size()];
        for (int i = 0; i < network.size(); i++) {
            location[i] = network.getVertex(i);
        }
        return location;
    }

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

    public Network<Location> getNetwork() {
        return network;
    }
}

