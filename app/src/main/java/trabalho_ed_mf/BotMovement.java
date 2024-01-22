package trabalho_ed_mf;

import ClassImplementation.LinkedList;

import java.util.Iterator;
import java.util.Random;

public class BotMovement {
    public static int shortestPath(Map map, int index1, int index2) {
        Map map1 = map;
        if (map1 == null) {
            return index1;
        }
        Iterator<Location> iterator = map.getNetwork().iteratorShortestPath(index1, index2);
        if (iterator.hasNext()) {
            iterator.next();
            Location currentLocation = iterator.next();

            if (currentLocation.getHasFlag() || !currentLocation.getHasBot()) {
                return currentLocation.getIndex();
            } else {

                map1.removeLocal(currentLocation);


                return shortestPath(map1, index1, index2);
            }
        } else {
            return index1;
        }

    }

    public static int randomPath(Map map, int index) {
        LinkedList<Integer> locals = new LinkedList<Integer>();
        for (int i = 0; i < map.getNetwork().getAdjMatrix().length; i++) {
            if (16 < map.getNetwork().getAdjMatrix()[index][i] && map.getNetwork().getAdjMatrix()[index][i] > 0) {
                if (!map.getNetwork().getVertex(i).getHasBot() || map.getNetwork().getVertex(i).getHasFlag()) {
                    locals.add(i);
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


    public static int atheleticPath(Map map, int index, int index2) {
        double longestPath = 0;
        int longestPathIndex = -1;
        for (int i = 0; i < map.getNetwork().getAdjMatrix().length; i++) {
            if (i != index2) {
                if (16 > map.getNetwork().getAdjMatrix()[index][i] && map.getNetwork().getAdjMatrix()[index][i] > longestPath) {
                    if ((!map.getNetwork().getVertex(i).getHasBot() || map.getNetwork().getVertex(i).getHasFlag())) {
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
