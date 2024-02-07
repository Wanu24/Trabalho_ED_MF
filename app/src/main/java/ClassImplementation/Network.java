package ClassImplementation;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
import Interfaces.NetworkADT;

import java.util.Iterator;

/**
 * Classe Network que estende a classe Graph e implementa a interface NetworkADT.
 * Esta classe representa uma rede ou grafo com pesos nas arestas.
 * @param <T> O tipo de dados que os vértices no grafo irão conter.
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {
    private double[][] adjMatrix;

    public double[][] getAdjMatrix(){
        return this.adjMatrix;
    }

    /**
     * Construtor da classe Network.
     * Inicializa a matriz de adjacência e o array de vértices.
     */
    public Network() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }


    /**
     * Retorna uma representação em string do grafo.
     * @return Uma representação em string do grafo.
     */
    public String toString() {
        if (numVertices == 0) {
            return "Graph is empty";
        }
        String result = "";

        result += "Adjacency Matrix\n";
        result += "----------------\n";
        result += "index\t";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i;
            if (i < 10) {
                result += " ";
            }
        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += i + "\t";

            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    result += "1 ";
                } else {
                    result += "0 ";
                }
            }
            result += "\n";
        }

        result += "\n\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += i + "\t";
            result += vertices[i].toString() + "\n";
        }

        result += "\n\nWeights of Edges";
        result += "\n----------------\n";
        result += "index\tweight\n\n";

        for (int i = 0; i < numVertices; i++) {
            for (int j = numVertices - 1; j >=0; j--) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    result += i + " to " + j + "\t";
                    result += adjMatrix[i][j] + "\n";
                }
            }
        }
        result += "\n";
        return result;
    }

    /**
     * Adiciona uma aresta entre dois índices com um peso específico.
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     * @param weight O peso da aresta.
     */
    public void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            adjMatrix[index2][index1] = weight;
            System.out.println("Edge added between " + index1 + " and " + index2 + " with weight " + weight);
        }else{
            System.out.println("Edge not added");
        }
    }

    /**
     * Adiciona uma aresta direcional entre dois índices com um peso específico.
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     * @param weight O peso da aresta.
     */
    public void addEdgeDiretional(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
            System.out.println("Edge added between " + index1 + " and " + index2 + " with weight " + weight);
        }else{
            System.out.println("Edge not added");
        }
    }

    /**
     * Remove uma aresta entre dois índices.
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = Double.POSITIVE_INFINITY;
            adjMatrix[index2][index1] = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * Adiciona uma aresta entre dois vértices com um peso específico.
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     * @param weight O peso da aresta.
     */
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    /**
     * Adiciona uma aresta entre dois vértices.
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), 0);
    }

    /**
     * Remove uma aresta entre dois vértices.
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     */
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Adiciona um vértice ao grafo.
     * Verifica se o array de vértices está cheio e expande-o se necessário.
     */
    public void addVertex() {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = null;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    /**
     * Adiciona um vértice ao grafo.
     * @param vertex O vértice a ser adicionado.
     */
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    /**
     * Remove um vértice do grafo.
     * @param index O índice do vértice a ser removido.
     */
    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;
            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }
            for (int i = index; i < numVertices; i++) {
                System.arraycopy(adjMatrix[i + 1], 0, adjMatrix[i], 0, numVertices);
            }
            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[j][i] = adjMatrix[j][i + 1];
                }
            }
        }
    }

    /**
     * Remove um vértice do grafo.
     * @param vertex O vértice a ser removido.
     */
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    /**
     * Retorna um iterador que realiza uma busca em profundidade a partir de um índice inicial.
     * @param startIndex O índice inicial para a busca.
     * @return Um iterador que realiza uma busca em profundidade.
     */
    public Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            //Find a vertex adjacent to x that has not been visited and push it
            //on the stack
            for (int i = 0; (i < numVertices) && !found; i++) {
                if ((adjMatrix[x.intValue()][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    /**
     * Retorna um iterador que realiza uma busca em profundidade a partir de um vértice inicial.
     * @param startVertex O vértice inicial para a busca.
     * @return Um iterador que realiza uma busca em profundidade.
     */
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * Retorna um iterador que realiza uma busca em largura a partir de um índice inicial.
     * @param startIndex O índice inicial para a busca.
     * @return Um iterador que realiza uma busca em largura.
     */
    public Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);

            //Find all vertices adjacent to x that have not been visited and
            //queue them up
            for (int i = 0; i < numVertices; i++) {
                if ((adjMatrix[x.intValue()][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Retorna um iterador que realiza uma busca em largura a partir de um vértice inicial.
     * @param startVertex O vértice inicial para a busca.
     * @return Um iterador que realiza uma busca em largura.
     */
    public Iterator<T> iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * Retorna um iterador que contém os índices dos vértices no caminho mais curto entre dois índices.
     * @param startIndex O índice inicial do caminho.
     * @param targetIndex O índice final do caminho.
     * @return Um iterador que contém os índices dos vértices no caminho mais curto.
     */
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) {
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex]
                        + adjMatrix[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }
        do {
            weight = (traversalMinHeap.removeMin()).doubleValue();
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, weight);
                visited[index] = true;
            }
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }
        return resultList.iterator();
    }

    /**
     * Retorna o índice do vértice adjacente com um peso específico.
     * @param visited Um array de booleanos que indica se um vértice foi visitado.
     * @param pathWeight Um array de pesos.
     * @param weight O peso a ser procurado.
     * @return O índice do vértice adjacente com um peso específico.
     */
    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited, double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if ((adjMatrix[i][j] < Double.POSITIVE_INFINITY) && visited[j]) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Retorna um iterador que contém os índices dos vértices no caminho mais curto entre dois vértices.
     * @param startIndex O vértice inicial do caminho.
     * @param finalIndex O vértice final do caminho.
     * @return Um iterador que contém os índices dos vértices no caminho mais curto.
     */
    public ArrayList<Integer> dijkstraAlgorithm(int startIndex, int finalIndex) {
        ArrayList<Double> distances = new ArrayList<>(numVertices);
        ArrayList<Boolean> visited = new ArrayList<>(numVertices);
        ArrayList<Integer> predecessor = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            distances.add(Double.POSITIVE_INFINITY);
            predecessor.add(-1);
            visited.add(false);
        }
        distances.set(startIndex, 0.0);

        for (int i = 0; i < numVertices; i++) {
            int vertex = -1;
            for (int j = 0; j < numVertices; j++) {
                if (!visited.get(j) && (vertex == -1 || distances.get(j) < distances.get(vertex))) {
                    vertex = j;
                }
            }

            visited.set(vertex, true);

            for (int j = 0; j < numVertices; j++) {
                double edgeDistance = adjMatrix[vertex][j];
                if (edgeDistance < Double.POSITIVE_INFINITY) {
                    double newDistance = distances.get(vertex) + edgeDistance;
                    if (newDistance < distances.get(j)) {
                        distances.set(j, newDistance);
                        predecessor.set(j, vertex);
                    }
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        int current = finalIndex;
        while (current != -1) {
            path.add(0, current);
            current = predecessor.get(current);
        }

        return path;
    }



    /**
     * Retorna um iterador que contém os índices dos vértices no caminho mais curto entre dois vértices.
     * @param startIndex vértice inicial do caminho.
     * @param targetIndex O vértice final do caminho.
     * @return Um iterador que contém os índices dos vértices no caminho mais curto.
     */
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);
        while (it.hasNext()) {
            templist.addToRear(vertices[(it.next()).intValue()]);
        }
        return templist.iterator();
    }

    /**
     * Retorna um iterador que contém os índices dos vértices no caminho mais curto entre dois vértices.
     * @param startVertex O vértice inicial do caminho.
     * @param targetVertex O vértice final do caminho.
     * @return Um iterador que contém os índices dos vértices no caminho mais curto.
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }
    public double shortestPathWeight(int startIndex, int targetIndex) {
        double result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);

        if (it.hasNext()) {
            index1 = it.next().intValue();
        } else {
            return Double.POSITIVE_INFINITY;
        }
        while (it.hasNext()) {
            index2 = (it.next()).intValue();
            result += adjMatrix[index1][index2];
            index1 = index2;
        }
        return result;
    }

    /**
     * Retorna o peso do caminho mais curto entre dois vértices.
     * @param startVertex O vértice inicial do caminho.
     * @param targetVertex O vértice final do caminho.
     * @return O peso do caminho mais curto.
     */
    public double shortestPathWeight(T startVertex, T targetVertex) {
        return shortestPathWeight(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Calcula uma árvore geradora mínima da rede original.
     * @return Uma nova rede que é uma árvore geradora mínima.
     */
    public Network mstNetwork() {
        int x, y;
        int index;
        double weight;
        int[] edge = new int[2];
        LinkedHeap<Double> minHeap = new LinkedHeap<>();
        Network<T> resultGraph = new Network<>();

        if (isEmpty() || !isConnected()) {
            return resultGraph;
        }
        resultGraph.adjMatrix = new double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                resultGraph.adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
            resultGraph.vertices = (T[]) (new Object[numVertices]);
        }
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;

        for (int i = 0; i < numVertices; i++) {
            minHeap.addElement(adjMatrix[0][i]);
        }

        while ((resultGraph.size() < this.size()) && !minHeap.isEmpty()) {

            do {
                weight = (minHeap.removeMin()).doubleValue();
                edge = getEdgeWithWeightOf(weight, visited);
            } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));

            x = edge[0];
            y = edge[1];
            if (!visited[x]) {
                index = x;
            } else {
                index = y;
            }


            resultGraph.vertices[index] = this.vertices[index];
            visited[index] = true;
            resultGraph.numVertices++;

            resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
            resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];


            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && (this.adjMatrix[i][index]
                        < Double.POSITIVE_INFINITY)) {
                    edge[0] = index;
                    edge[1] = i;
                    minHeap.addElement(adjMatrix[index][i]);
                }
            }
        }
        return resultGraph;
    }

    /**
     * Retorna o índice do vértice adjacente com um peso específico.
     * @param weight O peso a ser procurado.
     * @param visited Um array de booleanos que indica se um vértice foi visitado.
     * @return O índice do vértice adjacente com um peso específico.
     */
    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if ((adjMatrix[i][j] == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }


    /**
     * Expande a capacidade da matriz de adjacência e do array de vértices.
     * Este método é chamado quando o array de vértices ou a matriz de adjacência estão cheios e é necessário adicionar mais um vértice ou aresta.
     * Ele cria um novo array de vértices e uma nova matriz de adjacência com o dobro do tamanho dos atuais.
     * Em seguida, copia os vértices e a matriz de adjacência existentes para os novos array e matriz.
     * Finalmente, atribui o novo array de vértices e a nova matriz de adjacência aos atuais, efetivamente expandindo sua capacidade.
     */
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        double[][] largerAdjMatrix
                = new double[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(adjMatrix[i], 0, largerAdjMatrix[i], 0, numVertices);
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }
    /**
     * Verifica se os vértices são conectados
     * @return Verdadeiro os vértices são conectados e falso caso contrário.
     */
    public boolean hasEdge(int startIndex, int targetIndex){
        return adjMatrix[startIndex][targetIndex] < Double.POSITIVE_INFINITY;
    }
}
