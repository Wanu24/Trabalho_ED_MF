package trabalho_ed_mf;

public class BotMovement {
    public static void shortestPath(Map map, int index1, int index2){
        map.getNetwork().iteratorShortestPath(index1,index2).next();
    }

    public static void randomPath(Map map, int index){
        //Implementar o algoritmo de busca aleatoria para encontrar a bandeira
    }

    public static void greedyPath(Map map, int index){
        //Implementar o algoritmo de busca gulosa para encontrar a bandeira
    }
}
