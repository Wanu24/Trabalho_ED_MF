package ClassImplementation;

import Interfaces.GraphADT;

import java.util.Iterator;

/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */
/**
 * Classe Graph que implementa GraphADT.
 * Cada Graph tem uma capacidade padrão, um número de vértices, uma matriz de adjacência e um array de vértices.
 *
 * @param <T> O tipo de elementos que este grafo contém.
 */
public class Graph<T> implements GraphADT<T> {
    /**
     * A capacidade padrão deste grafo.
     */
    protected final int DEFAULT_CAPACITY = 10;
    /**
     * O número de vértices neste grafo.
     */
    protected int numVertices;
    /**
     * A matriz de adjacência deste grafo.
     */
    protected boolean[][] adjMatrix;
    /**
     * O array de vértices deste grafo.
     */
    protected T[] vertices;
    /**
     * Construtor para a classe Graph.
     * Inicializa o número de vértices como 0, a matriz de adjacência com a capacidade padrão e o array de vértices com a capacidade padrão.
     */
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
    }
    /**
     * Construtor para a classe Graph.
     * Inicializa o número de vértices como 0, a matriz de adjacência com o tamanho fornecido e o array de vértices com o tamanho fornecido.
     *
     * @param size O tamanho inicial do grafo.
     */
    public Graph(int size) {
        numVertices = 0;
        this.adjMatrix = new boolean[size][size];
        this.vertices = (T[])(new Object[size]);
    }
    /**
     * Adiciona uma aresta ao grafo.
     * Se os índices forem válidos, adiciona uma aresta entre os vértices nos índices fornecidos.
     *
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     */
    public void addEdge (int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;
        }
    }
    /**
     * Remove uma aresta do grafo.
     * Se os índices forem válidos, remove a aresta entre os vértices nos índices fornecidos.
     *
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }
    /**
     * Adiciona uma aresta ao grafo.
     * Adiciona uma aresta entre os vértices fornecidos.
     *
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     */
    public void addEdge (T vertex1, T vertex2) {
        addEdge (getIndex(vertex1), getIndex(vertex2));
    }
    /**
     * Remove uma aresta do grafo.
     * Remove a aresta entre os vértices fornecidos.
     *
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }
    /**
     * Retorna um iterador para a busca em largura a partir de um índice inicial.
     * Se o índice não for válido, retorna um iterador para uma lista vazia.
     *
     * @param startIndex O índice inicial para a busca em largura.
     * @return Um iterador para a busca em largura a partir do índice inicial.
     */
    private Iterator<T> iteratorBFS(int startIndex) {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

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
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalQueue.enqueue(Integer.valueOf(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }
    /**
     * Retorna um iterador para a busca em largura a partir de um índice inicial.
     * Se o índice não for válido, retorna um iterador para uma lista vazia.
     *
     * @param startVertex O índice inicial para a busca em largura.
     * @return Um iterador para a busca em largura a partir do índice inicial.
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        return iteratorBFS(getIndex(startVertex));
    }
    /**
     * Retorna um iterador para a busca em profundidade a partir de um índice inicial.
     * Se o índice não for válido, retorna um iterador para uma lista vazia.
     *
     * @param startIndex O índice inicial para a busca em profundidade.
     * @return Um iterador para a busca em profundidade a partir do índice inicial.
     */
    private Iterator<T> iteratorDFS(int startIndex) {
        Integer x;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(Integer.valueOf(startIndex));
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.pop();
            resultList.addToRear(vertices[x.intValue()]);

            //Find all vertices adjacent to x that have not been visited and
            //push them onto the stack
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalStack.push(Integer.valueOf(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }
    /**
     * Retorna um iterador para a busca em profundidade a partir de um vértice inicial.
     *
     * @param startVertex O vértice inicial para a busca em profundidade.
     * @return Um iterador para a busca em profundidade a partir do vértice inicial.
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        return iteratorDFS(getIndex(startVertex));
    }
    /**
     * Retorna um iterador para o caminho mais curto entre dois índices.
     * Se os índices não forem válidos ou forem iguais, retorna um iterador para uma lista vazia.
     *
     * @param startIndex O índice inicial para o caminho mais curto.
     * @param targetIndex O índice alvo para o caminho mais curto.
     * @return Um iterador para o caminho mais curto entre os dois índices.
     */
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) {
        int index = startIndex;
        int[] pathLength = new int[numVertices];
        int[] predecessor = new int[numVertices];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<Integer> resultList
                = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
                || (startIndex == targetIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(Integer.valueOf(startIndex));
        visited[startIndex] = true;
        pathLength[startIndex] = 0;
        predecessor[startIndex] = -1;

        while (!traversalQueue.isEmpty() && (index != targetIndex)) {
            index = (traversalQueue.dequeue()).intValue();

            //Update the pathLength for each unvisited vertex adjacent to the
            //vertex at the current index
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[index][i] && !visited[i]) {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    traversalQueue.enqueue(Integer.valueOf(i));
                    visited[i] = true;
                }
            }
        }
        if (index != targetIndex) // no path must have been found
        {
            return resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        index = targetIndex;
        stack.push(Integer.valueOf(index));
        do {
            index = predecessor[index];
            stack.push(Integer.valueOf(index));
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear(stack.pop());
        }

        return resultList.iterator();
    }
    /**
     * Retorna um iterador para o caminho mais curto entre dois índices.
     * Se os índices não forem válidos, retorna um iterador para uma lista vazia.
     *
     * @param startIndex O índice inicial para o caminho mais curto.
     * @param targetIndex O índice alvo para o caminho mais curto.
     * @return Um iterador para o caminho mais curto entre os dois índices.
     */
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) {
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return resultList.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);
        while (it.hasNext()) {
            resultList.addToRear(vertices[it.next().intValue()]);
        }
        return resultList.iterator();
    }
    /**
     * Retorna um iterador para o caminho mais curto entre dois vértices.
     *
     * @param startVertex O vértice inicial para o caminho mais curto.
     * @param targetVertex O vértice alvo para o caminho mais curto.
     * @return Um iterador para o caminho mais curto entre os dois vértices.
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }
    /**
     * Verifica se o grafo está vazio.
     *
     * @return Verdadeiro se o grafo está vazio, falso caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return (numVertices == 0);
    }
    /**
     * Verifica se o grafo está conectado.
     *
     * @return Verdadeiro se o grafo está conectado, falso caso contrário.
     */
    @Override
    public boolean isConnected() {
        if (isEmpty()) {
            return false;
        }

        Iterator<T> it = iteratorBFS(0);
        int count = 0;

        while (it.hasNext()) {
            it.next();
            count++;
        }
        return (count == numVertices);
    }
    /**
     * Retorna o tamanho do grafo.
     *
     * @return O tamanho do grafo.
     */
    @Override
    public int size() {
        return numVertices;
    }
    /**
     * Adiciona um vértice ao grafo.
     * Se o grafo estiver cheio, expande a capacidade do grafo.
     * Adiciona o vértice no final do array de vértices e incrementa o número de vértices.
     *
     * @param vertex O vértice a ser adicionado ao grafo.
     */
    public void addVertex (T vertex) {
        if (numVertices == vertices.length)
            expandCapacity();
        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;
    }
    /**
     * Remove um vértice do grafo.
     * Se o índice for válido, remove o vértice no índice fornecido.
     *
     * @param index O índice do vértice a ser removido.
     */
    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;

            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; i++) {
                if (numVertices + 1 >= 0) System.arraycopy(adjMatrix[i + 1], 0, adjMatrix[i], 0, numVertices + 1);
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
     * Remove o vértice fornecido do grafo.
     *
     * @param vertex O vértice a ser removido.
     */
    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }
    /**
     * Expande a capacidade do grafo.
     * Cria um novo array e uma nova matriz de adjacência com o dobro da capacidade do array e da matriz de adjacência atuais e copia os elementos do array e da matriz de adjacência atuais para o novo array e a nova matriz de adjacência.
     * Atualiza o array e a matriz de adjacência deste grafo para o novo array e a nova matriz de adjacência.
     */
    private void expandCapacity() {
        T[] larger = (T[])(new Object[vertices.length*2]);
        System.arraycopy(vertices, 0, larger, 0, vertices.length);
        vertices = larger;
        boolean[][] largerMatrix = new boolean[vertices.length][vertices.length];
        for (int i = 0; i < numVertices; i++)
            System.arraycopy(adjMatrix[i], 0, largerMatrix[i], 0, numVertices);
        adjMatrix = largerMatrix;
    }
    /**
     * Retorna o índice de um vértice.
     *
     * @param vertex O vértice cujo índice deve ser retornado.
     * @return O índice do vértice.
     */
    public int getIndex(T vertex) {
        for(int i = 0; i < numVertices; i++){
            if(vertices[i].equals(vertex)){
                return i;
            }
        }
        return -1;
    }
    /**
     * Checks if the given index is valid.
     * An index is considered valid if it is greater than or equal to 0 and less than the number of vertices.
     *
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    public boolean indexIsValid(int index) {
        return (index >= 0) && (index < numVertices);
    }
    /**
     * Returns a string representation of the graph.
     * The string representation includes the adjacency matrix and the vertex values.
     * If the graph is empty, returns "Graph is empty".
     *
     * @return A string representation of the graph.
     */
    public String toString(){
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
            result += "" + i + "\t";
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j]) {
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
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }
        result += "\n";
        return result;
    }
    /**
     * Retorna um grafo que é uma árvore geradora mínima do grafo original.
     * Se o grafo original estiver vazio ou não estiver conectado, retorna um grafo vazio.
     * Cria um novo grafo, adiciona o primeiro vértice do grafo original ao novo grafo e marca o vértice como visitado.
     * Enquanto o novo grafo não tiver o mesmo tamanho que o grafo original e houver arestas para adicionar, adiciona a aresta de menor peso que conecta um vértice visitado a um vértice não visitado ao novo grafo e marca o vértice não visitado como visitado.
     *
     * @return Um grafo que é uma árvore geradora mínima do grafo original.
     */
    public Graph getMST() {
        int x, y;
        int[] edge = new int[2];
        LinkedStack<int[]> vertexStack = new LinkedStack<>();
        Graph<T> resultGraph = new Graph<T>();

        if (isEmpty() || !isConnected()) {
            return resultGraph;
        }

        resultGraph.adjMatrix = new boolean[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                resultGraph.adjMatrix[i][j] = false;
            }
        }

        resultGraph.vertices = (T[]) (new Object[numVertices]);
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        edge[0] = 0;
        resultGraph.vertices[0] = this.vertices[0];
        resultGraph.numVertices++;
        visited[0] = true;

        //Add all edges that are adjacent to vertex 0 to the stack
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && this.adjMatrix[0][i]) {
                edge[1] = i;
                vertexStack.push(edge.clone());
                visited[i] = true;
            }
        }

        while ((resultGraph.size() < this.size()) && !vertexStack.isEmpty()) {

            //Pop an edge off the stack and add it to the resultGraph
            edge = vertexStack.pop();
            x = edge[0];
            y = edge[1];
            resultGraph.vertices[y] = this.vertices[y];
            resultGraph.numVertices++;
            resultGraph.adjMatrix[x][y] = true;
            resultGraph.adjMatrix[y][x] = true;
            visited[y] = true;

            //Add all unvisited edges that are adjacent to vertex y to the stack
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i] && this.adjMatrix[i][y]) {
                    edge[0] = y;
                    edge[1] = i;
                    vertexStack.push(edge.clone());
                    visited[i] = true;
                }
            }
        }
        return resultGraph;
    }
    /**
     * Retorna o vértice em um índice específico.
     *
     * @param index O índice do vértice a ser retornado.
     * @return O vértice no índice especificado.
     */
    public T getVertex(int index){
        return vertices[index];
    }
}
