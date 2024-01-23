package trabalho_ed_mf;

import ClassImplementation.ArrayList;
import ClassImplementation.LinkedList;

import java.util.Random;

/**
 * Classe que implementa os algoritmos de movimentação dos bots.
 */
public class BotMovement {

    /**
     * Algoritmo de movimentação que move o bot para o caminho mais curto entre ele e a bandeira inimiga.
     *
     * @param map     O mapa no qual o bot está a mover-se.
     * @param index1  O índice do bot no grafo.
     * @param index2  O índice da bandeira inimiga no grafo.
     * @return O índice do próximo vértice para o qual o bot deve mover-se.
     */
    public static int shortestPath(Map map, int index1, int index2) {
        Map map1 = map;
        ArrayList<Integer> path = map1.getNetwork().dijkstraAlgorithm(index1, index2);
        if (path.size() == 1) {
            return index1;
        }
        int nextVertex = path.get(1);
        Location currentLocation = map1.getNetwork().getVertex(nextVertex);

        if (!currentLocation.getHasFlag() && currentLocation.getHasBot()) {
            map1.removeLocal(currentLocation);
            return shortestPath(map1, index1, index2);
        } else {
            return nextVertex;
        }
    }

    /**
     * Algoritmo de movimentação que move o bot para um vértice aleatório adjacente ao seu.
     *
     * @param map    O mapa no qual o bot está a mover-se.
     * @param index  O índice do bot no grafo.
     * @return O índice do próximo vértice para o qual o bot deve mover-se.
     */
    public static int randomPath(Map map, int index) {
        LinkedList<Integer> locals = new LinkedList<Integer>();
        for (int i = 0; i < map.getNetwork().getAdjMatrix().length; i++) {
            if (16 > map.getNetwork().getAdjMatrix()[index][i] && map.getNetwork().getAdjMatrix()[index][i] > 0) {
                if (!map.getNetwork().getVertex(i).getHasBot() || map.getNetwork().getVertex(i).getHasFlag()) {
                    if (map.getNetwork().getVertex(i).getIndex() != index) {
                        locals.add(i);
                    }
                }
            }
        }
        if (locals.isEmpty()) {
            return index;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(locals.size());
        return locals.get(randomIndex);

    }

    /**
     * Algoritmo de movimentação que move o bot para o caminho mais longo entre ele e a sua localização anterior.
     *
     * @param map     O mapa no qual o bot está a mover-se.
     * @param index   O índice do bot no grafo.
     * @param index2  O índice da localização anterior do bot no grafo.
     * @return O índice do próximo vértice para o qual o bot deve mover-se.
     */
    public static int atheleticPath(Map map, int index, int index2) {
        double longestPath = 0;
        int longestPathIndex = -1;
        for (int i = 0; i < map.getNetwork().getAdjMatrix().length; i++) {
            if (i != index2) {
                if (map.getNetwork().getVertex(i).getHasFlag() && map.getNetwork().getAdjMatrix()[index][i] < 16) {
                    return i;
                } else if (16 > map.getNetwork().getAdjMatrix()[index][i] && map.getNetwork().getAdjMatrix()[index][i] > longestPath) {
                    if (!map.getNetwork().getVertex(i).getHasBot()) {
                        longestPath = map.getNetwork().getAdjMatrix()[index][i];
                        longestPathIndex = i;
                    }
                }
            }
        }
        if (longestPathIndex == -1) {
            return index;
        }
        return longestPathIndex;
    }


}
