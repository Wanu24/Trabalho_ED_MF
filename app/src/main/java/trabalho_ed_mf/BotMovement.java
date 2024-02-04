package trabalho_ed_mf;

import ClassImplementation.ArrayList;
import ClassImplementation.LinkedList;
import ClassImplementation.LinkedQueue;
import Exceptions.EmptyCollectionException;

import java.util.Random;

/**
 * Classe que implementa os algoritmos de movimentação dos bots.
 */
public class BotMovement {

    /**
     * Algoritmo de movimentação que move o bot para o caminho mais curto entre ele e a bandeira inimiga.
     *
     * @param map    O mapa no qual o bot está a mover-se.
     * @param index1 O índice do bot no grafo.
     * @param index2 O índice da bandeira inimiga no grafo.
     * @return O índice do próximo vértice para o qual o bot deve mover-se.
     */
    public static int shortestWeightPath(Map map, int index1, int index2) {
        if (index1 == index2) {
            return index1;
        }
        ArrayList<Integer> path = map.getNetwork().dijkstraAlgorithm(index1, index2);
        if (path.size() == 1) {
            return index1;
        }
        return path.get(1);
    }

    /**
     * Algoritmo de movimentação que move o bot para um vértice aleatório conectado ao vértice atual.
     *
     * @param map   O mapa no qual o bot está a mover-se.
     * @param index O índice do bot no grafo.
     * @return O índice do próximo vértice para o qual o bot deve mover-se.
     */
    public static int randomPath(Map map, int index) {
        double[][] adjMatrix = map.getNetwork().getAdjMatrix();
        ArrayList<Integer> connectedVertices = new ArrayList<>();

        for (int i = 0; i < adjMatrix[index].length; i++) {
            if (adjMatrix[index][i] != 0) {
                connectedVertices.add(i);
            }
        }

        if (connectedVertices.isEmpty()) {
            return index;
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(connectedVertices.size());
        return connectedVertices.get(randomIndex);
    }


    /**
     * Algoritmo de Arvóre geradora de custo mínimo
     *
     * @param map         O mapa no qual o bot está a mover-se.
     * @param startIndex  O índice do bot no grafo.
     * @param targetIndex O índice da bandeira inimiga no grafo.
     * @return O índice do próximo vértice para o qual o bot deve mover-se.
     */
    public static int minimumSpanningTree(Map map, int startIndex, int targetIndex) {
        if (startIndex == targetIndex) {
            return startIndex;
        }
        int[] parent = new int[map.getNetwork().getAdjMatrix().length];
        int[] key = new int[map.getNetwork().getAdjMatrix().length];
        boolean[] mstSet = new boolean[map.getNetwork().getAdjMatrix().length];

        for (int i = 0; i < map.getNetwork().getAdjMatrix().length; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[startIndex] = 0;
        parent[startIndex] = -1;

        for (int count = 0; count < map.getNetwork().getAdjMatrix().length - 1; count++) {
            int u = minKey(map, key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < map.getNetwork().getAdjMatrix().length; v++) {
                if (map.getNetwork().getAdjMatrix()[u][v] != 0 && !mstSet[v] && map.getNetwork().getAdjMatrix()[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = (int) map.getNetwork().getAdjMatrix()[u][v];
                }
            }
        }

        // Find the path from the start index to the target index
        LinkedList<Integer> path = new LinkedList<>();
        int vertex = targetIndex;
        while (vertex != startIndex) {
            path.add(0, vertex);
            vertex = parent[vertex];
        }
        path.add(0, startIndex);

        return path.get(1);
    }

    /**
     * Função auxiliar para encontrar o vértice com a menor chave
     *
     * @param map     O mapa no qual o bot está a mover-se.
     * @param key     Array de chaves
     * @param mstSet  Array de booleans que indica se o vértice está na árvore geradora de custo mínimo
     * @return O índice do vértice com a menor chave
     */
    public static int minKey(Map map, int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < map.getNetwork().getAdjMatrix().length; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    /**
     * Algoritmo de movimentação que move o bot para o caminho mais curto entre ele e a bandeira inimiga.
     *
     * @param map         O mapa no qual o bot está a mover-se.
     * @param startIndex  O índice do bot no grafo.
     * @param targetIndex O índice da bandeira inimiga no grafo.
     * @return O índice do próximo vértice para o qual o bot deve mover-se.
     */
    public static int shortestConnectionPath(Map map, int startIndex, int targetIndex) {
        int vertices = map.getNetwork().getAdjMatrix().length;
        if (startIndex == targetIndex) {
            return startIndex;
        }

        int[] pred = new int[vertices];
        boolean[] visited = new boolean[vertices];

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!queue.isEmpty()) {
            int current = 0;
            try {
                current = queue.dequeue();
            } catch (EmptyCollectionException e) {
                e.printStackTrace();
            }
            if (current == targetIndex) {
                int next = pred[targetIndex];
                while (pred[next] != startIndex) {
                    next = pred[next];
                }
                return next;
            } else {
                double[] adj = map.getNetwork().getAdjMatrix()[current];
                for (int i = 0; i < vertices; i++) {
                    if (adj[i] != 0 && !visited[i]) {
                        queue.enqueue(i);
                        visited[i] = true;
                        pred[i] = current;
                    }
                }
            }
        }
        return startIndex;
    }
}
