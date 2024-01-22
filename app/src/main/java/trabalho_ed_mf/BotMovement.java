package trabalho_ed_mf;

import ClassImplementation.LinkedList;

import java.util.Random;

public class BotMovement {
    public static int shortestPath(Map map, int index1, int index2) {
        Map map1 = map;
        if (map1.getNetwork().iteratorShortestPath(index1, index2).next().getHasFlag() || !map1.getNetwork().iteratorShortestPath(index1, index2).next().getHasBot()) {
            return map1.getNetwork().iteratorShortestPath(index1, index2).next().getIndex();
        } else {
            map1.removeLocal(map1.getNetwork().iteratorShortestPath(index1, index2).next());
            return shortestPath(map1, index1, index2);
        }
    }

    public static int randomPath(Map map, int index) {
        LinkedList<Integer> locals = new LinkedList<Integer>();
        for (int i = 0; i < map.getSize(); i++) {
            if (map.getNetwork().getAdjMatrix()[index][i] != 0 && (!map.getNetwork().getVertex(i).getHasBot() || map.getNetwork().getVertex(i).getHasFlag())) {
                locals.add(i);
            }
        }
        if (locals.isEmpty()) {
            return index;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(locals.size());
        return locals.get(randomIndex);
    }


    public static int atheleticPath(Map map, int index, int index2) {
        double longestPath = 0;
        int longestPathIndex = -1;
        for (int i = 0; i < map.getSize(); i++) {
            if (map.getNetwork().getAdjMatrix()[index][i] > longestPath && (!map.getNetwork().getVertex(i).getHasBot() || map.getNetwork().getVertex(i).getHasFlag()) && i != index2) {
                longestPath = map.getNetwork().getAdjMatrix()[index][i];
                longestPathIndex = i;
            }
            if (longestPathIndex == -1) {
                return index;
            }
        }
        return longestPathIndex;
    }
}
