package ClassImplementation;

import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;
/*
 * @author 8210666 Manuel Pereira
 * @author 8190183 Fábio Cunha
 */

/**
 * Classe LinkedQueue que representa uma fila usando uma lista encadeada.
 * @param <T> O tipo de dados que a fila irá conter.
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private int count; // O número de elementos na fila.
    private Node<T> front; // O início da fila.
    private Node<T> rear; // O fim da fila.

    /**
     * Construtor padrão da classe LinkedQueue.
     * Inicializa o contador como 0 e o início e o fim da fila como nulos.
     */
    public LinkedQueue(){
        this.count = 0;
        this.front = null;
        this.rear = null;
    }

    /**
     * Adiciona um elemento ao fim da fila.
     * @param element O elemento a ser adicionado ao fim da fila.
     */
    public void enqueue(T element) {
        Node<T> node = new Node<>(element);

        if (this.isEmpty()) {
            this.front = node;
            this.rear = node;
        } else {
            this.rear.setNext(node);
            this.rear = node;
        }

        ++this.count;
    }

    /**
     * Remove e retorna o elemento no início da fila.
     * @return O elemento no início da fila.
     * @throws EmptyCollectionException Se a fila estiver vazia.
     */
    public T dequeue() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("LinkedQueue");
        } else {
            T result = this.front.getElement();
            this.front = this.front.getNext();
            --this.count;
            return result;
        }
    }

    /**
     * Retorna o elemento no início da fila sem removê-lo.
     * @return O elemento no início da fila.
     * @throws EmptyCollectionException Se a fila estiver vazia.
     */
    public T first() {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("LinkedQueue");
        } else {
            return this.front.getElement();
        }
    }

    /**
     * Verifica se a fila está vazia.
     * @return Verdadeiro se a fila estiver vazia, falso caso contrário.
     */
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /**
     * Retorna o número de elementos na fila.
     * @return O número de elementos na fila.
     */
    public int size() {
        return this.count;
    }

    /**
     * Retorna uma representação em string da fila.
     * @return Uma representação em string da fila.
     */
    public String toString() {
        String result = "LinkedQueue {";
        Node<T> current = this.front;
        if (!this.isEmpty()) {
            do {
                result = result + " " + current.getElement();
            } while((current = current.getNext()) != null);
        }

        result = result + " }";
        return result;
    }

    /**
     * Retorna o nó no início da fila.
     * @return O nó no início da fila.
     */
    public Node<T> getFront() {
        return front;
    }

    /**
     * Retorna o nó no fim da fila.
     * @return O nó no fim da fila.
     */
    public Node<T> getRear() {
        return rear;
    }
}